package com.githubrestapi.controller;

import com.githubrestapi.model.Account;
import com.githubrestapi.model.OAuthEntity;
import com.githubrestapi.model.Repo;
import com.githubrestapi.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class OAuthController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 18.03.2021.
 * @version 1.0.
 */
@Controller
@RequestMapping(value = "/github")
public class GitHubController {

    /**
     *
     */
    private final String clientId = "6fbd5ea9a05c0d689e8f";

    /**
     *
     */
    private final String clientSecret = "9588182a4b419b4ab37a736d8f0405c1eff5756e";

    /**
     *
     */
    private final String urlAuthorize = "https://github.com/login/oauth/authorize";

    /**
     *
     */
    private final String urlAccessToken = "https://github.com/login/oauth/access_token";

    private final String urlLogin = "https://api.github.com/users/";

    /**
     *
     */
    private Token token;

    /**
     * @param restTemplate - rest template.
     */
    private final RestTemplate restTemplate;

    /**
     * GitHubRestTemplateController - constructor.
     * @param restTemplate - rest template.
     */
    @Autowired
    public GitHubController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * authorize - authorize.
     * @param model - model.
     * @return - return index.
     */
    @RequestMapping(value = "/oauth/authorize", method = RequestMethod.GET)
    public String authorize(Model model) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlAuthorize)
                .queryParam("response_type", "code")
                .queryParam("state")
                .queryParam("client_id", clientId)
                .queryParam("scope");
        model.addAttribute("urlAuthorize", urlBuilder.toUriString());
        return "authorize";
    }

    /**
     * getCode - getCode.
     * @param code - code.
     * @return - returns code.
     */
    @RequestMapping(value = "/oauth/authorize/callback", method = RequestMethod.GET)
    public String getCode(@RequestParam("code") String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<OAuthEntity> entity = new HttpEntity<>(new OAuthEntity(clientId, clientSecret, code), headers);
        ResponseEntity<Token> token = this.restTemplate.exchange(urlAccessToken, HttpMethod.POST, entity, Token.class);
        this.token = new Token(Objects.requireNonNull(token.getBody()).getAccess_token(), token.getBody().getToken_type(), token.getBody().getScope());
        System.out.println("token : " + this.token);
        return "index";
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String findUserByLogin(Model model) {
        System.out.println("findByLogin");
        model.addAttribute("login");
        return "findUserByLogin";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String findUserByLogin(@RequestParam("login") String login, Model model) {
        System.out.println("input login: " + login);
        String url = urlLogin + login;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token.getToken_type() + " " + this.token.getAccess_token());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Account> account = this.restTemplate.exchange(url, HttpMethod.GET, entity, Account.class);
        System.out.println("account" + account);

        String url1 = url + "/repos";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("Authorization", this.token.getToken_type() + " " + this.token.getAccess_token());
        headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity1 = new HttpEntity<>(null, headers1);
        ResponseEntity<List<Repo>> repos = this.restTemplate.exchange(url1, HttpMethod.GET, entity1, new ParameterizedTypeReference<List<Repo>>() {});
        System.out.println();
        int index = 0;
        for (Repo repo : Objects.requireNonNull(repos.getBody())) {
            System.out.println("[" + index + "]" + repo.getId() + repo.getName() + repo.getHtml_url() + repo.getDescription() + repo.getLanguage());
            index++;
        }
        System.out.println();
        //model.addAttribute("account", account);
        //model.addAttribute("repos", repos);
        return this.getCV(account.getBody(), repos.getBody(), model);
    }

    @RequestMapping(value = "/getCV", method = RequestMethod.GET)
    public String getCV(Account account, List<Repo> repos, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("repos", repos);
        return "getCV";
    }



    /*
    @RequestMapping(value = "/{login123}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Account> getUser(@PathVariable("login123") String login) {
        String url = "https://api.github.com/users/" + login;
        System.out.println("url: " + url);
        System.out.println("test test test test");
        System.out.println("test test test test");
        System.out.println("test test test test");
        System.out.println("test test test test");
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
    }*/

}