package com.project.service;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class InfoService {


    public JSONObject getInfo(String str, Long id) throws Exception {
        URL url = new URL(str+id.toString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        InputStream inputStream = con.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser jsonParser = new JSONParser(response.toString(),null,false);
        JSONObject json = (JSONObject) jsonParser.parse();


        return json;
    }


    public void transfer(String str1,String str2,JSONObject object) throws IOException {
        String strsrc =object.get("srcid").toString();

        String strdst =object.get("dstid").toString();

        String srcSum=object.get("srcsum").toString();

        String dstSum=object.get("dstsum").toString();
        URL url1 = new URL(str1+strsrc);
        HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setRequestProperty("srcsum",srcSum);
        con.setRequestMethod("POST");

        URL url2 = new URL(str2+strdst);
        HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
        connection.setRequestProperty("dstsum",dstSum);
        connection.setRequestMethod("POST");
    }
}