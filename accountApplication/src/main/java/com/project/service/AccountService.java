package com.project.service;

import com.project.entity.Account;
import com.project.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

@Service
public class AccountService {

    @Autowired
    AccountRepository repository;


    public void addAccount(int client_Id)  {
        Account account= new Account();
        account.setDate(new Date().toString());
        account.setType("текущий");
        account.setBalance(0);
        account.setClientId(client_Id);

    }

    public Iterable<Account> getAccounts() {

        return repository.findAll();
    }

    public Account getAccount(Long id) throws Exception {

        if (repository.existsById(id)) {
            return repository.findById(id).get();
        } else {
            throw new Exception("Account not found");
        }
    }

    public Account getAccountFromClientId(int clientId) throws Exception {
        Account account = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://accounts-db:5432/account";
            String login = "postgres";
            String password = "postgres";
            Connection con = DriverManager.getConnection(url, login, password);
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from accounts where client_id =" + clientId);
                while (rs.next()) {
                    //account.setId(rs.getInt("id"));
                    account.setBalance(rs.getInt("balance"));
                    account.setType(rs.getString("type"));
                    account.setDate(rs.getString("date"));
                    account.setClientId(clientId);

                }
                rs.close();
                stmt.close();
            } finally {
                con.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }


    public void increaseAccount(Long id ,int sum) throws Exception {
        if(repository.existsById(id)) {
            Account account=repository.findById(id).get();
                account.setBalance(account.getBalance()+sum);
                repository.save(account);
        }
        else {
            throw new Exception("account not found");
        }
    }

    public void withdrawAccount(Long id ,int sum) throws Exception {
        if(repository.existsById(id)) {
            Account account=repository.findById(id).get();
            account.setBalance(account.getBalance()-sum);
            repository.save(account);
        }
        else {
            throw new Exception("account not found");
        }
    }

    public void deleteAccount(Long id) throws Exception {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new Exception("Account not found");
        }
    }

}