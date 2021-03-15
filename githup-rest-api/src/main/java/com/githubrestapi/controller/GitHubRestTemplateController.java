package com.githubrestapi.controller;

import com.githubrestapi.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Class CardController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 12.03.2021.
 * @version 1.0.
 */
@RestController
@RequestMapping(path = "/github")
public class GitHubRestTemplateController {

    /**
     * @param token - personal token in github.
     */
    private String token = "Bearer bb15609056af794cbfea594e5c19cefc5fc24eee";

    /**
     * @param restTemplate - rest template.
     */
    private final RestTemplate restTemplate;

    /**
     * GitHubRestTemplateController - constructor.
     * @param restTemplate - rest template.
     */
    @Autowired
    public GitHubRestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * requestEntity - requestEntity.
     * @return - return headers.
     */
    private HttpEntity<?> requestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token);
        return new HttpEntity<>(null, headers);
    }

    /**
     * getOauthAccount - getOauthAccount.
     * @param login - user login in github.
     * @return - returns response entity from account by input login.
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Account> getUser(@PathVariable("login") String login) {
        String url = "https://api.github.com/users/" + login;
        System.out.println("url: " + url);
        ResponseEntity<Account> account = this.restTemplate.exchange(url, HttpMethod.GET, requestEntity(), Account.class);
        System.out.println();
        System.out.println("Header :  " + account.getHeaders().get("X-RateLimit-Limit"));
        System.out.println("Header :  " + account.getHeaders().get("X-RateLimit-Reset"));
        System.out.println();
        System.out.println("Body :  " + account.getBody());
        System.out.println();
        System.out.println("Status code :  " + account.getStatusCode());
        System.out.println();
        System.out.println("Status code value :  " + account.getStatusCodeValue());
        return new ResponseEntity<>(account.getBody(), HttpStatus.OK);
        //return account;
    }


}