package org.meicode.mymovie.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {InfoDetailMovie.class},version = 3)
public abstract class DatabaseClass extends RoomDatabase {
    public abstract InfoDetailMovieDao getDao();

    private static DatabaseClass instance;
    public static DatabaseClass getDatabase(final Context context) {
        if (instance == null) {
            synchronized (DatabaseClass.class) {
                instance = Room.databaseBuilder(context, DatabaseClass.class, "WeatherInfoDatabase").allowMainThreadQueries().build();
            }
        }
        //instance = Room.databaseBuilder(context , DatabaseClass.class , "ItemDatabase").allowMainThreadQueries().build();
        return instance;
    }

}
