package com.example.testproject.model.retrofit2_json_utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Андрей on 21.02.2018.
 */

public class Owner {
    @SerializedName("login")
    @Expose
    String ownerLogin;

    public Owner() {
    }

    public Owner(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
}
