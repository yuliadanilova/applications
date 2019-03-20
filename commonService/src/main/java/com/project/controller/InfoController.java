package com.project.controller;

import com.project.service.InfoService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

class InfoController {

    @Autowired
    InfoService infoService;

    @RequestMapping(value = "/clientinfo/{id}", method = RequestMethod.GET)
    JSONObject getClientInfo(@PathVariable("id") Long id) throws Exception {
        return infoService.getInfo("http://customer-host:8080/api/client/",id);
    }

    @RequestMapping(value = "/clientinfo/", method = RequestMethod.GET)
    JSONObject getAccInfo(@RequestParam("client_id") Long client_id) throws Exception {
        return infoService.getInfo("http://accounts-host:8080/api/account/fromclientid/",client_id);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void transfer(@RequestBody JSONObject object ) throws Exception {
        infoService.transfer("http://accounts-host:8080/account/withdraw/","http://accounts-host:8080/account/increase/",object);
    }
}