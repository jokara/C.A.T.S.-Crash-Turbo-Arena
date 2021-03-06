package com.example.catscrasharenaturbostars.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.catscrasharenaturbostars.database.entity.FightsView;

import java.util.List;

@Dao
public abstract class FightsViewDao {

    @Insert
    public abstract long insert(FightsView fightsView);

    @Query("select * from fights_view")
    public abstract List<FightsView> getAllFights();

}
