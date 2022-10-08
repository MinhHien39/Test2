package org.meicode.mymovie.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import org.meicode.mymovie.Model.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface InfoDetailMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllData(InfoDetailMovie info);

    @Query("SELECT * FROM movieTable")
    Single<List<InfoDetailMovie>> getAllData();

    @Query("SELECT id FROM movieTable WHERE id =:id")
    int checkId(int id);

    @Delete
    Completable deleteAllData(InfoDetailMovie info);

}
