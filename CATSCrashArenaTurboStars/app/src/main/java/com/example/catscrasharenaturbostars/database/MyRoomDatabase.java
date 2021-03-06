package com.example.catscrasharenaturbostars.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.catscrasharenaturbostars.database.converters.DateConverter;
import com.example.catscrasharenaturbostars.database.dao.BoxDao;
import com.example.catscrasharenaturbostars.database.dao.FightsViewDao;
import com.example.catscrasharenaturbostars.database.dao.UserDao;
import com.example.catscrasharenaturbostars.database.dao.VehicleDao;
import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.FightsView;
import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;

import java.util.Date;


@Database( version = 1,
        entities = {
                User.class,
                Vehicle.class,
                Box.class,
                FightsView.class
        },
        exportSchema = false
)

@TypeConverters({DateConverter.class})
public abstract class MyRoomDatabase extends RoomDatabase {

    private static MyRoomDatabase singletonInstance;

    public abstract UserDao userDao();
    public abstract VehicleDao vehicleDao();
    public abstract BoxDao boxDao();
    public abstract FightsViewDao fightsViewDao();

    public static MyRoomDatabase getDatabase(final Context context){
        if (singletonInstance==null){
            synchronized (MyRoomDatabase.class){
                if (singletonInstance==null){
                    singletonInstance = Room.databaseBuilder(context.getApplicationContext(),MyRoomDatabase.class,"my_database").fallbackToDestructiveMigration().addCallback(callback).build();
                }
            }
        }
        return singletonInstance;
    }

    public static RoomDatabase.Callback callback= new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabaseAsync(singletonInstance).execute();
        }
    };


    public static class PopulateDatabaseAsync extends AsyncTask<Void,Void,Void> {

        private UserDao userDao;
        private VehicleDao vehicleDao;
        private BoxDao boxDao;
        private FightsViewDao fightsViewDao;

        public PopulateDatabaseAsync(MyRoomDatabase roomDatabase) {
            userDao=roomDatabase.userDao();
            vehicleDao = roomDatabase.vehicleDao();
            boxDao = roomDatabase.boxDao();
            fightsViewDao = roomDatabase.fightsViewDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //UBACIVANJE KORISNIKA U SISTEM
            long userId1=userDao.insert(new User("Nedeljko","Jokic","admin","admin","admin"));
            long userId2=userDao.insert(new User("Ivan","Ivanovic","ivan","ivan","igrac"));
            long userId3=userDao.insert(new User("Jovana","Jovanovic","jovana","jovana","igrac"));
            long vehicleId1=vehicleDao.insert(new Vehicle("admin",1,1,0,0,1,1,0,1,-1,-1,-1,-1,85,0));
            long vehicleId2=vehicleDao.insert(new Vehicle("ivan",1,0,1,0,0,0,1,0,-1,-1,-1,-1,85,0));
            long vehicleId3=vehicleDao.insert(new Vehicle("jovana",1,1,0,1,1,1,0,0,-1,-1,-1,-1,85,0));
            long boxId1 = boxDao.insert(new Box("admin",new Date(),1,3));
            long firstCombat = fightsViewDao.insert(new FightsView("admin","zeljko","admin"));
            return null;
        }
    }


}
