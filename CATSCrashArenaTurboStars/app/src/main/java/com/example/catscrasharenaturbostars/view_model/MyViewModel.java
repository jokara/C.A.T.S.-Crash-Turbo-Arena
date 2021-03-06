package com.example.catscrasharenaturbostars.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.FightsView;
import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {

    MutableLiveData<ArrayList<User>> users;
    MutableLiveData<ArrayList<String>> userUsername;
    MutableLiveData<String> izabranoIme;
    MutableLiveData<String> ulogovan;
    MutableLiveData<ArrayList<Vehicle>> vehicles;
    MutableLiveData<ArrayList<Box>> boxes;
    MutableLiveData<String> vremeKutija;
    MutableLiveData<String> promeniCustomView;
    MutableLiveData<String> brojDobijenihBorbi;
    MutableLiveData<String> kontroliseIgrac;
    MutableLiveData<String> pucajIzRakete;
    MutableLiveData<ArrayList<FightsView>> fightViews;

    public MyViewModel() {
        users=new MutableLiveData<ArrayList<User>>();
        ArrayList<User> list=new ArrayList<User>();
        users.setValue(list);
        /*****************************************************/
        userUsername=new MutableLiveData<ArrayList<String>>();
        ArrayList<String> list1=new ArrayList<>();
        userUsername.setValue(list1);
        /*****************************************************/
        izabranoIme=new MutableLiveData<String>();
        izabranoIme.setValue("");
        /*****************************************************/
        ulogovan=new MutableLiveData<String>();
        ulogovan.setValue("");
        /*****************************************************/
        vehicles=new MutableLiveData<ArrayList<Vehicle>>();
        ArrayList<Vehicle> list2=new ArrayList<Vehicle>();
        vehicles.setValue(list2);
        /*****************************************************/
        boxes=new MutableLiveData<ArrayList<Box>>();
        ArrayList<Box> list3=new ArrayList<Box>();
        boxes.setValue(list3);
        /*****************************************************/
        vremeKutija=new MutableLiveData<String>();
        vremeKutija.setValue("");
        /*****************************************************/
        promeniCustomView=new MutableLiveData<String>();
        promeniCustomView.setValue("");
        /*****************************************************/
        pucajIzRakete=new MutableLiveData<String>();
        pucajIzRakete.setValue("");
        /*****************************************************/
        brojDobijenihBorbi=new MutableLiveData<String>();
        brojDobijenihBorbi.setValue("");
        /*****************************************************/
        kontroliseIgrac=new MutableLiveData<String>();
        kontroliseIgrac.setValue("");
        /*****************************************************/
        fightViews=new MutableLiveData<ArrayList<FightsView>>();
        ArrayList<FightsView> list4=new ArrayList<FightsView>();
        fightViews.setValue(list4);
    }

    public MutableLiveData<String> getPucajIzRakete() {
        return pucajIzRakete;
    }

    public void setPucajIzRakete(MutableLiveData<String> pucajIzRakete) {
        this.pucajIzRakete = pucajIzRakete;
    }

    public MutableLiveData<String> getKontroliseIgrac() {
        return kontroliseIgrac;
    }

    public void setKontroliseIgrac(MutableLiveData<String> kontroliseIgrac) {
        this.kontroliseIgrac = kontroliseIgrac;
    }

    public MutableLiveData<ArrayList<FightsView>> getFightViews() {
        return fightViews;
    }

    public void setFightViews(MutableLiveData<ArrayList<FightsView>> fightViews) {
        this.fightViews = fightViews;
    }

    public MutableLiveData<String> getBrojDobijenihBorbi() {
        return brojDobijenihBorbi;
    }

    public void setBrojDobijenihBorbi(MutableLiveData<String> brojDobijenihBorbi) {
        this.brojDobijenihBorbi = brojDobijenihBorbi;
    }

    public MutableLiveData<String> getPromeniCustomView() {
        return promeniCustomView;
    }

    public void setPromeniCustomView(MutableLiveData<String> promeniCustomView) {
        this.promeniCustomView = promeniCustomView;
    }

    public MutableLiveData<String> getVremeKutija() {
        return vremeKutija;
    }

    public void setVremeKutija(MutableLiveData<String> vremeKutija) {
        this.vremeKutija = vremeKutija;
    }

    public MutableLiveData<ArrayList<Box>> getBoxes() {
        return boxes;
    }

    public void setBoxes(MutableLiveData<ArrayList<Box>> boxes) {
        this.boxes = boxes;
    }

    public MutableLiveData<ArrayList<Vehicle>> getVehicles() {
        return vehicles;
    }

    public void setVehicles(MutableLiveData<ArrayList<Vehicle>> vehicles) {
        this.vehicles = vehicles;
    }

    public MutableLiveData<String> getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(MutableLiveData<String> ulogovan) {
        this.ulogovan = ulogovan;
    }

    public MutableLiveData<String> getIzabranoIme() {
        return izabranoIme;
    }

    public void setIzabranoIme(MutableLiveData<String> izabranoIme) {
        this.izabranoIme = izabranoIme;
    }

    public MutableLiveData<ArrayList<String>> getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(MutableLiveData<ArrayList<String>> userUsername) {
        this.userUsername = userUsername;
    }

    public MutableLiveData<ArrayList<User>> getUsers() {
        return users;
    }

    public void setUsers(MutableLiveData<ArrayList<User>> users) {
        this.users = users;
    }

    /********************************************************************/
    public void postaviListuBorbi(ArrayList<FightsView> lista){
        fightViews.postValue(lista);
    }

    public void postaviListuVozila(ArrayList<Vehicle> lista){
        vehicles.postValue(lista);
    }

    public void postaviListuKutija(ArrayList<Box> lista){
        boxes.postValue(lista);
    }

    public void postaviDaIgracKontrolise(String kontrola){
        kontroliseIgrac.postValue(kontrola);
    }

    public void postaviPucanje(String pucaj){
        pucajIzRakete.postValue(pucaj);
    }

    public void postaviVreme(String vreme){
        vremeKutija.postValue(vreme);
    }

    public void postaviBrojPobeda(String pobede){
        brojDobijenihBorbi.postValue(pobede);
    }

    public void postaviPromenuCustomViewa(String promena){
        promeniCustomView.postValue(promena);
    }

    public void addUser(User u){
        ArrayList<User> list=users.getValue();

        /*ArrayList<User> list;
        if (users.getValue()==null){
            list=users.getValue();
        }
        else{
            list=new ArrayList<>();
        }*/
        if (list.isEmpty()) {
            list.add(u);
        }
        boolean flag=false;
        for (int i=0; i<list.size();i++){
            if (list.get(i).getUsername().equals(u.getUsername())){
               flag=true;
            }
        }
        if (!flag){
            list.add(u);
            //Log.d("VM KOR USAO", u.getUsername());
        }
        users.postValue(list);
    }

    public void addVehicle(Vehicle v){
        ArrayList<Vehicle> list=vehicles.getValue();
        if (list.isEmpty()) {
            list.add(v);
        }
        boolean flag=false;
        for (int i=0; i<list.size();i++){
            if (list.get(i).getUsername().equals(v.getUsername())){
                flag=true;
            }
        }
        if (!flag){
            list.add(v);
            //Log.d("VM KOR USAO", u.getUsername());
        }
        vehicles.postValue(list);
    }

    public void addFightView(FightsView f){
        ArrayList<FightsView> list=fightViews.getValue();
        if (list.isEmpty()) {
            list.add(f);
        }
        boolean flag=false;
        for (int i=0; i<list.size();i++){
            if (list.get(i).getId()==(f.getId())){
                flag=true;
            }
        }
        if (!flag){
            list.add(f);
            //Log.d("VM KOR USAO", u.getUsername());
        }
        fightViews.postValue(list);
    }

    public void addBox(Box b){
        ArrayList<Box> list=boxes.getValue();
        if (list.isEmpty()) {
            list.add(b);
        }
        boolean flag=false;
        for (int i=0; i<list.size();i++){
            if (list.get(i).getUsername().equals(b.getUsername())){
                flag=true;
            }
        }
        if (!flag){
            list.add(b);
            //Log.d("VM KOR USAO", u.getUsername());
        }
        boxes.postValue(list);
    }

    public void addUserUsername(String username){
        ArrayList<String> list=userUsername.getValue();
        /*Log.d("VM KOR NAME USAO", username);
        ArrayList<String> list;
        if (userUsername.getValue()!=null) {
            list=userUsername.getValue();
            Log.d("POSTOJECA", username);
        }
        else {
             list=new ArrayList<>();
             Log.d("NOVA LISTA", username);
        }*/
        if (list.isEmpty()) {
            list.add(username);
            Log.d("FLAG", username);
            userUsername.postValue(list);
            return;
        }
        boolean flag=false;
        for (int i=0; i<list.size();i++){
            if (list.get(i).equals(username)){
                flag=true;
            }
        }
        if (!flag){
            list.add(username);
            userUsername.postValue(list);
            Log.d("VM KOR NAME", username);
            return;
        }

    }

    public void postaviIme(String s){
        izabranoIme.postValue(s);
    }

    public void postaviUlogovanog(String s){
        ulogovan.postValue(s);
    }
}
