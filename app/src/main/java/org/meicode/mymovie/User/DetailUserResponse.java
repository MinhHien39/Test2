package org.meicode.mymovie.User;

import com.google.gson.annotations.SerializedName;

public class DetailUserResponse {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("avatar")
    public Avatar avatar;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public class TMDB{
        @SerializedName("avatar_path")
        String avatarPath;

        public String getAvatarPath() {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }
    }
    public class Avatar{
        @SerializedName("tmdb")
        public TMDB tmdb;

        public TMDB getTmdb() {
            return tmdb;
        }

        public void setTmdb(TMDB tmdb) {
            this.tmdb = tmdb;
        }
    }


}

