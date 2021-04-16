package com.adidyk.controller;

import com.adidyk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;

/**
 * Class GiHubController used for create cv from github rest api..
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 18.03.2021.
 * @version 1.0.
 */
@Controller
@RequestMapping(value = "/github")
public class GitHubController {

    /**
     * @param clientId - client id from OAuth App (keys come from your application's configuration page).
     */
    @Value("${clientId}")
    private String clientId;

    /**
     * @param clientSecret - client secret from OAuth App (keys come from your application's configuration page).
     */
    @Value("${clientSecret}")
    private String clientSecret;

    /**
     * @param urlAuthorize - url authorize - github rest api.
     */
    @Value("${urlAuthorize}")
    private String urlAuthorize;

    /**
     * @param urlAccessToken - url access token - github rest api.
     */
    @Value("${urlAccessToken}")
    private String urlAccessToken;

    /**
     * @param urlLogin - url for gets account info - github rest api.
     */
    @Value("${urlLogin}")
    private String urlLogin;

    @Value("${increment}")
    private Integer increment;

    /**
     * @param token - token.
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
     * setTokenToHeaders - sets token to headers and returns http entity.
     * @return - return http entity..
     */
    private HttpEntity<?> setTokenToHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", this.token.getToken_type() + " " + this.token.getAccess_token());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(null, headers);
    }

    /**
     * getLanguage - returns list language, percentage of programming languages ​​per account - aggregated by main
     * repository language in relation to size repository.
     * @param repos - list repos.
     * @return - returns list language.
     */
    private List<Language> getLanguage(List<Repo> repos) {
        int repoCounter = 0;
        Map<String, Double> mapLanguage = new HashMap<>();
        for (Repo repo : repos) {
            if (repo.getLanguage() != null) {
                if (!mapLanguage.containsKey(repo.getLanguage())) {
                    mapLanguage.put(repo.getLanguage(), Double.valueOf(this.increment));
                } else if (mapLanguage.containsKey(repo.getLanguage())) {
                    mapLanguage.put(repo.getLanguage(), mapLanguage.get(repo.getLanguage()) + this.increment);
                }
                repoCounter ++;
            }
        }
        for (Map.Entry<String, Double> item : mapLanguage.entrySet()) {
            item.setValue((Math.round((item.getValue() * 100 / repoCounter)*100))/100.00);
        }
        List<Language> listLanguage = new ArrayList<>();
        for (Map.Entry<String, Double> item : mapLanguage.entrySet()) {
            listLanguage.add(new Language(item.getKey(), item.getValue()));
        }
        return listLanguage;
    }

    /**
     * authorize - build authorize url used client id and adds url to model.
     * @param model - model.
     * @return - return model and returns page authorize.html.
     */
    @RequestMapping(value = "/oauth/authorize", method = RequestMethod.GET)
    public String authorize(Model model) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(this.urlAuthorize)
                .queryParam("response_type", "code")
                .queryParam("state")
                .queryParam("client_id", this.clientId)
                .queryParam("scope");
        model.addAttribute("urlAuthorize", urlBuilder.toUriString());
        return "authorize";
    }

    /**
     * getCode - gets temporary code from application github, after POST this code back to GitHub in exchange for an
     * access token.
     * @param code - code.
     * @return - returns start page index.html.
     */
    @RequestMapping(value = "/oauth/authorize/callback", method = RequestMethod.GET)
    public String getCodeAndGetToken(@RequestParam("code") String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<OAuthEntity> entity = new HttpEntity<>(new OAuthEntity(this.clientId, this.clientSecret, code), headers);
        ResponseEntity<Token> token = this.restTemplate.exchange(this.urlAccessToken, HttpMethod.POST, entity, Token.class);
        this.token = new Token(Objects.requireNonNull(token.getBody()).getAccess_token(), token.getBody().getToken_type(), token.getBody().getScope());
        return "index";
    }

    /**
     * findAccountByLogin - adds param login to model and returns page findAccountByLogin.html.
     * @param model - model.
     * @return - adds param login to model and returns page findAccountByLogin.html.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String findAccountByLogin(Model model) {
        if (this.token == null) {
            model.addAttribute("tokenError", "no token you need to authorize");
            return this.authorize(model);
        }
        model.addAttribute("login");
        return "findAccountByLogin";
    }

    /**
     * findAccountAndReposByLogin - find account and repos by login in github and returns account and repos.
     * @param login - user login.
     * @param model - model.
     * @return - returns accounts and repos.
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String findAccountAndReposByLogin(@RequestParam("login") String login, Model model) {
        try {
            ResponseEntity<Account> account = this.restTemplate.exchange(this.urlLogin + login, HttpMethod.GET, setTokenToHeaders(), Account.class);
            ResponseEntity<List<Repo>> repos = this.restTemplate.exchange(Objects.requireNonNull(account.getBody()).getRepos_url(), HttpMethod.GET, setTokenToHeaders(), new ParameterizedTypeReference<List<Repo>>() {});
            account.getBody().setRepos(repos.getBody());
            account.getBody().setLanguage(this.getLanguage(Objects.requireNonNull(repos.getBody())));
            return this.getCV(account.getBody(), model);
        } catch (Exception ex) {
            model.addAttribute("accountLoginError", "account by login don`t found or your token is old");
            return "findAccountByLogin";
        }
    }

    /**
     * getCV - returns model account, model repos and page getCV.html.
     * @param account - account.
     * @param model - model.
     * @return - returns model account and model repos and page getCV.html.
     */
    @RequestMapping(value = "/getCV", method = RequestMethod.GET)
    public String getCV(Account account, Model model) {
        model.addAttribute("account", account);
        return "getCV";
    }

}