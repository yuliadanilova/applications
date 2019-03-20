package com.project.controller;

import com.project.entity.Account;
import com.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    Account getAccount(@PathVariable("id") Long id) throws Exception {
        return accountService.getAccount(id);
    }


    @RequestMapping(value = "/account/fromclientid/{id}", method = RequestMethod.GET)
    Account getAccountFromClientId(@PathVariable("id") int id) throws Exception {
        return accountService.getAccountFromClientId(id);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    Iterable<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    void addAccount(@RequestBody int client_Id) throws Exception {
        accountService.addAccount(client_Id);
    }

    @RequestMapping(value = "/account/increase/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    void increaseAccount(@PathVariable("id") Long id,@RequestParam int sum) throws Exception {

        accountService.increaseAccount(id,sum);
    }

    @RequestMapping(value = "/account/withdraw/{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    void withdrawAccount(@PathVariable("id") Long id,@RequestParam int sum) throws Exception {

        accountService.withdrawAccount(id,sum);
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteAccount(@PathVariable("id") Long id) throws Exception {
        accountService.deleteAccount(id);
    }
}