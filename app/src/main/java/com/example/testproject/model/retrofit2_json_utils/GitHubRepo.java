package com.example.testproject.model.retrofit2_json_utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Андрей on 21.02.2018.
 */

public class GitHubRepo {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("html_url")
    @Expose
    private String link;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    public GitHubRepo() {
    }

    public GitHubRepo(String id, String name, String description, String link, Owner owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
