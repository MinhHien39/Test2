package org.meicode.mymovie.User;

import com.google.gson.annotations.SerializedName;

public class SessionRequestLoginResponse {
    @SerializedName("request_token")
    public String request_token;

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}
