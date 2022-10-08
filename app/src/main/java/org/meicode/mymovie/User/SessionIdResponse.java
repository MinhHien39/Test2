package org.meicode.mymovie.User;

import com.google.gson.annotations.SerializedName;

public class SessionIdResponse {
    @SerializedName("session_id")
    public String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
