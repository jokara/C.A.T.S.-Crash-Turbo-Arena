package com.example.catscrasharenaturbostars.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.catscrasharenaturbostars.database.entity.User;
import java.util.List;


@Dao
public abstract class UserDao {

    @Insert
    public abstract long insert(User user);

    @Query("select count(*) from user_table")
    public abstract long getPersonCount();

    @Query("select name from user_table where name=:searchName")
    public abstract String getUserByUsername(String searchName);

    @Query("select username from user_table where username=:username and password=:password")
    public abstract String getUserByUsernameAndPassword(String username, String password);

    @Query("select * from user_table")
    public abstract List<User> getAllUsers();

    @Query("update user_table set winStrike=:strike where username=:username")
    public abstract void updateUserWinStrike(String username,int strike);

    @Query("select winStrike from user_table where username=:username")
    public abstract int getUserWinStrike(String username);

}