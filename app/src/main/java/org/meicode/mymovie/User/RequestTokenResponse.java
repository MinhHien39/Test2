package org.meicode.mymovie.User;

import com.google.gson.annotations.SerializedName;

import io.reactivex.rxjava3.annotations.NonNull;

public class RequestTokenResponse {

    @SerializedName("request_token")
    public String requestToken;

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

}
