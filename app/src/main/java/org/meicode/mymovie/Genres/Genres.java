package org.meicode.mymovie.Genres;

import com.google.gson.annotations.SerializedName;

public class Genres {
        @SerializedName("id")
        public int id;

        @SerializedName("name")
        public String name;

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

}
