package com.example.catscrasharenaturbostars.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.catscrasharenaturbostars.database.entity.Vehicle;

import java.util.List;

@Dao
public abstract class VehicleDao {

    @Insert
    public abstract long insert(Vehicle vehicle);

    @Query("select * from vehicle_table where username=:searchName")
    public abstract Vehicle getVehicleByUsername(String searchName);

    @Query("select * from vehicle_table")
    public abstract List<Vehicle> getAllVehichles();

    @Query("update vehicle_table set raketa=:raketa, busilica=:busilica, buzdovan=:buzdovan, tocak_p=:tocak_p, tocak_z=:tocak_z, forklift=:forklift, testera=:testera, levi_deo=:levi_deo, desni_deo=:desni_deo , levi_tockovi=:zadnji_tocak , desni_tockovi=:prednji_tocak where username=:username")
    public abstract void updateVehicle(String username, int raketa,int busilica, int buzdovan, int tocak_p,int tocak_z,int forklift,int testera,int levi_deo,int desni_deo,int zadnji_tocak,int prednji_tocak);


}
