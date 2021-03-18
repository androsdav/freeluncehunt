package com.githubrestapi.controller;

import com.githubrestapi.model.OAuthEntity;
import com.githubrestapi.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Collections;
import java.util.Objects;

/**
 * Class UserController used for controller with front end.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 18.03.2021.
 * @version 1.0.
 */
@Controller
public class OAuthAuthenticationController {

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
    private final String urlOAuthAuthorize = "https://github.com/login/oauth/authorize";

    /**
     *
     */
    private final String urlOAuthAccessToken = "https://github.com/login/oauth/access_token";

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
    public OAuthAuthenticationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/authorizeForm", method = RequestMethod.GET)
    public String authorizeForm(Model model) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(urlOAuthAuthorize)
                .queryParam("response_type", "code")
                .queryParam("state")
                .queryParam("client_id", clientId)
                .queryParam("scope");
        model.addAttribute("urlBuilder", urlBuilder.toUriString());
        return "authorize";
    }

    /**
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String test1(@RequestParam("code") String code) {
        System.out.println();
        System.out.println("code:" + code);
        System.out.println();
        System.out.println("callback");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        OAuthEntity oAuthEntity = new OAuthEntity(clientId, clientSecret, code);
        HttpEntity<OAuthEntity> entity = new HttpEntity<>(oAuthEntity, headers);
        ResponseEntity<Token> token = this.restTemplate.exchange(urlOAuthAccessToken, HttpMethod.POST, entity, Token.class);
        System.out.println("response: " + token.getBody());
        this.token = new Token(Objects.requireNonNull(token.getBody()).getAccess_token(), token.getBody().getToken_type(), token.getBody().getScope());
        System.out.println("TOKEN: " + this.token);
        return "index";

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