package org.meicode.mymovie.User;

import com.google.gson.annotations.SerializedName;

public class SessionRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("request_token")
    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

