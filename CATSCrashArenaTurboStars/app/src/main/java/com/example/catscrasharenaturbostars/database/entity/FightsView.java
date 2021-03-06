package com.example.catscrasharenaturbostars.database.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fights_view")
public class FightsView {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "namep1")
    private String namep1;

    @ColumnInfo(name = "namep2")
    private String namep2;

    @ColumnInfo(name = "result")
    private String result;

    public FightsView(String namep1, String namep2, String result) {
        this.namep1 = namep1;
        this.namep2 = namep2;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamep1() {
        return namep1;
    }

    public void setNamep1(String namep1) {
        this.namep1 = namep1;
    }

    public String getNamep2() {
        return namep2;
    }

    public void setNamep2(String namep2) {
        this.namep2 = namep2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
