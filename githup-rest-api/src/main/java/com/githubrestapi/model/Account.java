package com.githubrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Class Account used for creates new object user with params: id, login, password, passwordConfirm,
 * name, surname, money.
 * @author Didyk Andrey (androsdav@gmail.com).
 * @since 10.03.2021.
 * @version 1.0.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    String login;
    int id;
    String node_id;
    String avatar_url;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;
    boolean site_admin;
    String name;
    String company;
    String blog;
    String location;
    String email;
    String hireable;
    String bio;
    String twitter_username;
    int public_repos;
    int public_gists;
    int followers;
    int following;
    String created_at;
    String updated_at;
    /*
    @JsonProperty("login")
    String login;
    @JsonProperty("id")
    int id;
    @JsonProperty("node_id")
    String nodeId;
    @JsonProperty("avatar_url")
    String avatarUrl;
    @JsonProperty("gravatar_id")
    String gravatarId;
    @JsonProperty("url")
    String url;
    @JsonProperty("html_url")
    String htmlUrl;
    @JsonProperty("followers_url")
    String followersUrl;
    @JsonProperty("following_url")
    String followingUrl;
    @JsonProperty("gists_url")
    String gistsUrl;
    @JsonProperty("starred_url")
    String starredUrl;
    @JsonProperty("subscriptions_url")
    String subscriptionsUrl;
    @JsonProperty("organizations_url")
    String organizationsUrl;
    @JsonProperty("repos_url")
    String reposUrl;
    @JsonProperty("events_url")
    String eventsUrl;
    @JsonProperty("received_events_url")
    String receivedEventsUrl;
    @JsonProperty("type")
    String type;
    @JsonProperty("site_admin")
    String siteAdmin;
    @JsonProperty("name")
    String name;
    @JsonProperty("company")
    String company;
    @JsonProperty("blog")
    String blog;
    @JsonProperty("location")
    String location;
    @JsonProperty("email")
    String email;
    @JsonProperty("hireable")
    String hireable;
    @JsonProperty("bio")
    String bio;
    @JsonProperty("twitter_username")
    String twitterUsername;
    @JsonProperty("public_repos")
    int publicRepos;
    @JsonProperty("public_gists")
    int publicGists;
    @JsonProperty("followers")
    int followers;
    @JsonProperty("following")
    int following;
    @JsonProperty("created_at")
    String createdAt;
    @JsonProperty("updated_at")
    String updatedAt;*/
}