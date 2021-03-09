package com.githubrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GitHubRestTemplateController {

    private final RestTemplate restTemplate;

    @Autowired
    public GitHubRestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        String result = this.restTemplate.getForObject("https://api.github.com/users/androsdav", String.class);
        return  new ResponseEntity<>(result, HttpStatus.OK);
    }

}