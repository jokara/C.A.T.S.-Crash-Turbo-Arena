package com.example.catscrasharenaturbostars.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.catscrasharenaturbostars.database.entity.Box;

import java.util.List;

@Dao
public abstract class BoxDao {

    @Insert
    public abstract long insert(Box box);


    @Query("select * from box_table")
    public abstract List<Box> getAllBoxes();

    @Query("delete from box_table where username=:username")
    public abstract void remove_username_box(String username);

}
