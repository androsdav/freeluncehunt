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
public class GitHubRestTemplateController {

    /**
     * @param token - personal token in github.
     */
    private String token = "Bearer 4277a4015f87d65d57b2d7e81ed45fc9a621659e";

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
    @RequestMapping(value = "/github/{login}", method = RequestMethod.GET)
    public ResponseEntity<Account> getOauthAccount(@PathVariable("login") String login) {
        ResponseEntity<Account> account = this.restTemplate.exchange("https://api.github.com/users/androsdav", HttpMethod.GET, requestEntity(), Account.class);
        System.out.println(account);
        return new ResponseEntity<>(account.getBody(), HttpStatus.OK);
    }

}