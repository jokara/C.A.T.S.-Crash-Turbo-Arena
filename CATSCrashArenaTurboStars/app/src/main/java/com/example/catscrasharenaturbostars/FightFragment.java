package com.example.catscrasharenaturbostars;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.FightsView;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class FightFragment extends Fragment {


    public FightFragment() {
        // Required empty public constructor
    }

    private int firstPointerId;
    MyViewModel myViewModel;
    List<Vehicle> svaVozila;
    Vehicle mojeVozilo=null;
    int brojPobedjenihBorbi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_fight, container, false);
        /***************************VIEW MODEL*****************************************************/
        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        final String ulogovan= myViewModel.getUlogovan().getValue();
        /***************************POSTAVLJANJE POZADINE******************************************/
        LinearLayout linearLayout= view.findViewById(R.id.fight_linear_layout);
        linearLayout.setBackgroundResource(R.drawable.fight_field);
        final CustomViewFight customViewFight = view.findViewById(R.id.custom_view_fight);
        /*******************DOHVATANJE BROJA POBEDJENIH BORBI*******************************/
        brojPobedjenihBorbi = Integer.parseInt(myViewModel.getBrojDobijenihBorbi().getValue());
        /*******************DOHVATANJE MOG VOZILA I NJEGOVOG IZGLEDA*******************************/
        svaVozila=myViewModel.getVehicles().getValue();
        for (Vehicle v:svaVozila) {
            if (v.getUsername().equals(ulogovan)){
                mojeVozilo=v;
            }
        }
        /****************************POSTAVLJANJE VREDNOSTI SNAGE I ODBRANE************************/
        int jacina_napada = mojeVozilo.getJacina_napada();
        int jacina_odbrane = mojeVozilo.getJacina_odbrane();
        if (mojeVozilo.getLevi_tockovi()!=-1){
            jacina_odbrane+=13;
        }
        if (mojeVozilo.getDesni_tockovi()!=-1){
            jacina_odbrane+=13;
        }
        if (mojeVozilo.getLevi_deo()==1){
            jacina_napada+=9;
        }
        switch (mojeVozilo.getDesni_deo()){
            case 2:{
                jacina_napada+=12;
                break;
            }
            case 3:{
                jacina_napada+=7;
                break;
            }
            case 6:{
                jacina_odbrane+=10;
                break;
            }
            case 7:{
                jacina_napada+=19;
                break;
            }
        }
        /****************************POSTAVLJANJE SNAGE MOG VOZILA I USERNAME-A MOG****************/
        final TextView username_moj=view.findViewById(R.id.player1_name);
        username_moj.setText(ulogovan);
        TextView napad_moj = view.findViewById(R.id.player1_attack);
        napad_moj.setText(String.valueOf(jacina_napada));
        /****************************DOHVATANJE PROGRES BAROVA*************************************/
        final ProgressBar p1_health = view.findViewById(R.id.player1_health);
        final ProgressBar p2_health = view.findViewById(R.id.player2_health);
        p1_health.setMax(135);
        p2_health.setMax(100);
        p1_health.setProgress(135);
        p2_health.setProgress(100);
        /**obrisati ovo na kraju**/
        //mojeVozilo.setDesni_deo(6);
        /*************************/
        final Vehicle v1=mojeVozilo;
        /*****************************UGRADJENI PROTIVNICI ODABIR**********************************/
        final TextView p2_ime=view.findViewById(R.id.player2_name);
        int raketa=(int)(Math.random()*5);
        if (raketa>2) raketa=1;
        else raketa=-1;
        int tp=(int)(Math.random()*5);
        if (tp>1) tp=4;
        else tp=-1;
        int oruzje=(int)(Math.random()*12);
        if (oruzje>0 && oruzje <3) oruzje=2;
        if (oruzje>3 && oruzje <6) oruzje=3;
        if (oruzje>6 && oruzje <9) oruzje=7;
        if (oruzje>9 && oruzje <12) oruzje=6;

        final Vehicle v2=new Vehicle("a",1,1,1,1,1,1,1,1,raketa,oruzje,tp+1,tp,135,12);;
        final Vehicle v3=new Vehicle("a",1,1,1,1,1,1,1,1,1,2,-1,-1,135,12);
        //final Vehicle v4=new Vehicle("a",1,1,1,1,1,1,1,1,1,7,5,4,135,28);
        //final Vehicle v5=new Vehicle("a",1,1,1,1,1,1,1,1,-1,7,5,4,135,19);
        //final Vehicle v6=new Vehicle("a",1,1,1,1,1,1,1,1,-1,3,5,4,135,7);
        int random= 1+(int)(Math.random()*4);
        switch (random){
            case 1: {
                p2_ime.setText("jovan");
                break;
            }
            case 2: {
                p2_ime.setText("jelena");
                break;
            }
            case 3: {
                p2_ime.setText("guster");
                break;
            }
            case 4: {
                p2_ime.setText("macak");
                break;
            }
        }
        String kontrolaIgraca= myViewModel.getKontroliseIgrac().getValue();
        if (kontrolaIgraca.equals("DA")){
            customViewFight.fire_button.setBounds(50,70,180,200);
            myViewModel.postaviPucanje("NE");
        }
        else{
            customViewFight.fire_button.setBounds(0,0,0,0);
        }

        GestureDetector.SimpleOnGestureListener onGestureListener =
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDown(MotionEvent e) {


                        return true;
                    }

                    @Override
                    public boolean onFling(
                            MotionEvent e1, MotionEvent e2,
                            float velocityX, float velocityY) {
                        //Log.d(DEBUG_TAG, "onFling()");
                        return true;
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        //Log.d(DEBUG_TAG, "onDoubleTap()");
                        return true;
                    }
                };

        final GestureDetector gestureDetector = new GestureDetector(getContext(), onGestureListener);

        customViewFight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int indexOfActionPointer = event.getActionIndex();
                int idOfActionPointer = event.getPointerId(indexOfActionPointer);
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        //1500,300,1750,600
                        if(event.getX(event.getActionIndex())>=1500 && event.getX(event.getActionIndex())<=1750 && event.getY(event.getActionIndex())>300 && event.getY(event.getActionIndex())<600) {
                            //ubacivanje u bazu, navigacija nazad, update-pobeda u view modelu, ako je dobio kutiju ubacivanje nove kutije brisanje stare
                            if (p1_health.getProgress()>p2_health.getProgress()) {
                                brojPobedjenihBorbi++;
                            }
                            else{
                                brojPobedjenihBorbi=0;
                            }
                            myViewModel.postaviBrojPobeda(brojPobedjenihBorbi+"");
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    int brojPobeda= brojPobedjenihBorbi;

                                    String pobednik="";
                                    if (p1_health.getProgress()>p2_health.getProgress()) {
                                        pobednik = username_moj.getText().toString();
                                    }
                                    else {
                                        pobednik= p2_ime.getText().toString();
                                    }
                                    if (brojPobeda==3){
                                        ArrayList<Box> novaListaKutija=new ArrayList<>();
                                        List<Box> sveKutije=myViewModel.getBoxes().getValue();
                                        //sveKutije.add(new Box(ulogovan,new Date(),1,0));
                                        for (Box b:sveKutije) {
                                            if (b.getUsername().equals(ulogovan)){
                                            }
                                            else {
                                                novaListaKutija.add(b);
                                            }
                                        }
                                        novaListaKutija.add(new Box(ulogovan,new Date(),1,0));
                                        myViewModel.postaviListuKutija(novaListaKutija);
                                        brojPobeda=0;
                                        myViewModel.postaviBrojPobeda(brojPobeda+"");
                                        MyRoomDatabase.getDatabase(getContext()).boxDao().insert(new Box(ulogovan,new Date(),1,0));
                                    }
                                    //myViewModel.addFightView(new FightsView(username_moj.getText().toString(),p2_ime.getText().toString(),pobednik));
                                    ArrayList<FightsView> listaBorbiZaPregled=myViewModel.getFightViews().getValue();
                                    listaBorbiZaPregled.add(new FightsView(username_moj.getText().toString(),p2_ime.getText().toString(),pobednik));
                                    myViewModel.postaviListuBorbi(listaBorbiZaPregled);
                                    MyRoomDatabase.getDatabase(getContext()).fightsViewDao().insert(new FightsView(username_moj.getText().toString(),p2_ime.getText().toString(),pobednik));
                                    MyRoomDatabase.getDatabase(getContext()).userDao().updateUserWinStrike(username_moj.getText().toString(),brojPobeda);

                                }
                            }).start();
                            NavController navController= Navigation.findNavController(view);
                            navController.navigate(R.id.action_fightFragment_to_garageFragment);
                        }

                        if(event.getX(event.getActionIndex())>=50 && event.getX(event.getActionIndex())<=180 && event.getY(event.getActionIndex())>70 && event.getY(event.getActionIndex())<250) {
                            //pucanje iz rakete
                            if (myViewModel.getPucajIzRakete().getValue().equals("NE")) {
                                myViewModel.postaviPucanje("DA");
                            }
                        }
                            firstPointerId = idOfActionPointer;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_UP:


                        break;
                }

                customViewFight.invalidate();

                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }

                return FightFragment.super.getActivity().onTouchEvent(event);
            }
        });
        customViewFight.p1_tocak_z.setBounds(0,0,0,0);
        customViewFight.p1_tocak_p.setBounds(0,0,0,0);
        customViewFight.p1_raketa.setBounds(0,0,0,0);
        customViewFight.p1_busilica.setBounds(0,0,0,0);
        customViewFight.p1_testera.setBounds(0,0,0,0);
        customViewFight.p1_forklift.setBounds(0,0,0,0);
        customViewFight.p1_buzdovan.setBounds(0,0,0,0);
        /**********************POSTAVLJANJE GRANICA P2*****************************************/
        customViewFight.p2_tocak_p.setBounds(0,0,0,0);
        customViewFight.p2_tocak_z.setBounds(0,0,0,0);
        customViewFight.p2_raketa.setBounds(0,0,0,0);
        customViewFight.p2_busilica.setBounds(0,0,0,0);
        customViewFight.p2_testera.setBounds(0,0,0,0);
        customViewFight.p2_forklift.setBounds(0,0,0,0);
        customViewFight.p2_buzdovan.setBounds(0,0,0,0);

        /*********************************VOZILO 1*************************************************/
        if (v1.getLevi_deo()!=-1){
            customViewFight.p1_raketa.setBounds(250,520,380,640);
        }

        if (v1.getDesni_tockovi()!=-1){
            customViewFight.p1_tocak_p.setBounds(475,630,585,750);
        }

        if (v1.getLevi_tockovi()!=-1){
            customViewFight.p1_tocak_z.setBounds(260,640,360,750);
        }

        switch (v1.getDesni_deo()){
            case 2:{
                customViewFight.p1_busilica.setBounds(510,530,740,680);
                break;
            }
            case 3:{
                customViewFight.p1_buzdovan.setBounds(340,520,600,700);
                break;
            }
            case 6:{
                customViewFight.p1_forklift.setBounds(340,530,600,700);
                break;
            }
            case 7:{
                customViewFight.p1_testera.setBounds(510,530,740,680);
                break;
            }
        }

        /*********************************VOZILO 2*************************************************/
        if (v2.getLevi_deo()!=-1){
            customViewFight.p2_raketa.setBounds(1430,515,1560,640);
        }

        if (v2.getDesni_tockovi()!=-1){
            customViewFight.p2_tocak_p.setBounds(1220,635,1360,750);
        }

        if (v2.getLevi_tockovi()!=-1){
            customViewFight.p2_tocak_z.setBounds(1450,630,1570,730);
        }

        switch (v2.getDesni_deo()){
            case 2:{
                customViewFight.p2_busilica.setBounds(1090,530,1320,680);
                break;
            }
            case 3:{
                customViewFight.p2_buzdovan.setBounds(1210,520,1470,700);
                break;
            }
            case 6:{
                customViewFight.p2_forklift.setBounds(1210,530,1470,700);
                break;
            }
            case 7:{
                customViewFight.p2_testera.setBounds(1090,530,1320,680);
                break;
            }
        }
        /*********************************BORBA****************************************************/
        /*********************************BORBA****************************************************/


        new Thread(new Runnable() {
            @Override
            public void run() {
                int player1_forklifted=0;
                int player2_forklifted=0;
                int pozicija_sasije1=160;
                int pozicija_rakete1=250;
                int pozicija_zadnjeg_tocka=260;
                int pozicija_prednjeg_tocka=475;
                int pozicija_busilice=510;
                int pozicija_buzdovana=340;
                int pozicija_forklifta=340;
                int pozicija_testere=510;
                int pozicija_ispaljene_rakete_p1=251;

                int pozicija_rakete2=1430;
                int pozicija_sasije2=1160;
                int pozicija_zadnjeg_tocka2=1450;
                int pozicija_prednjeg_tocka2=1220;
                int pozicija_busilice2=1090;
                int pozicija_buzdovana2=1210;
                int pozicija_forklifta2=1210;
                int pozicija_testere2=1090;
                int pozicija_ispaljene_rakete_p2=1430;
                boolean kraj_borbe=false;

                int health_p1=120;
                int zidovi1=health_p1;
                int health_p2=120;
                int zidovi2=health_p2;
                int skini_snagu_p1=0;
                int skini_snagu_p2=0;
                /*******ZIDOVI *******/
                int pozicija_levog_zida=-40;//pomeraj 200   |200/800
                int pozicija_desnog_zida=1670; //ostale dimenzije iste;
                int pozicija_rotirane_rakete_p1=535;
                int pozicija_rotirane_rakete_p2=1560;
                /**INTEGER ZA BUZDOVAN**/
                int brojacBuzdovan1=0;
                int brojacBuzdovan2=0;
                /*********************/
                /**INTEGER ZA BUZDOVAN**/
                int brojacForklift1=0;
                int brojacForklift2=0;
                long brojacZaZidove=0;
                boolean zidoviFlag=false;
                /*********************/
                while (!kraj_borbe){
                    try {
                        /**************************KRETANJE ZIDOVA***************************************************************/
                        brojacZaZidove++;
                        String pucaj=myViewModel.getPucajIzRakete().getValue();
                        String kontorliseIgrac=myViewModel.getKontroliseIgrac().getValue();
                        if(brojacZaZidove>1200){
                            zidoviFlag=true;
                        }
                        if ((zidovi1!=health_p1 || zidovi2!=health_p2)&& !zidoviFlag){
                            zidovi1=health_p1;
                            zidovi2=health_p2;
                            brojacZaZidove=0;
                        }
                        if (zidoviFlag){
                            int random=(int)Math.random()*6;
                            int pomoc=0;
                            if (random<3){
                                pomoc=-20;
                            }
                            pozicija_levog_zida++;
                            customViewFight.left_wall.setBounds(pozicija_levog_zida,200,pozicija_levog_zida+200,800);
                            pozicija_desnog_zida--;
                            customViewFight.right_wall.setBounds(pozicija_desnog_zida,200,pozicija_desnog_zida+200,800);
                            if (pozicija_desnog_zida<pozicija_sasije2+380){
                                kraj_borbe=true;
                                health_p2=0;
                                zidoviFlag=false;
                                brojacZaZidove=0;
                            }
                            if (pozicija_levog_zida+200>pozicija_sasije1+150+pomoc){
                                kraj_borbe=true;
                                health_p1=0;
                                zidoviFlag=false;
                                brojacZaZidove=0;
                            }

                        }

                        /***************************RAKETA1***********************************/
                        if (v1.getLevi_deo()==1 && (pozicija_sasije1+440)<pozicija_sasije2){
                            if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4){
                                pozicija_rakete1=pozicija_rakete1+1;
                            }
                            customViewFight.p1_raketa.setBounds(pozicija_rakete1,515,pozicija_rakete1+130,640);
                        }
                        else {
                            if((pozicija_sasije1+440)>=pozicija_sasije2 && player1_forklifted==1 && v1.getLevi_deo()==1){
                                customViewFight.p1_raketa.setBounds(0,0,0,0);
                                customViewFight.p1_raketa_rotirana.setBounds(pozicija_rakete1,530,pozicija_rakete1+130,685);
                            }
                        }

                        Log.d("PLAYER1",String.valueOf(player1_forklifted));
                        if (player1_forklifted==0 && kontorliseIgrac.equals("NE")) {
                            Log.d("PLAYER1",String.valueOf(player1_forklifted));
                            Log.d("PLAYER1",String.valueOf(v1.getLevi_deo()));
                            if ((v1.getLevi_deo() == 1)) {
                                customViewFight.ispaljena_raketa_p1.setBounds(pozicija_ispaljene_rakete_p1, 535, pozicija_ispaljene_rakete_p1 + 50, 590);
                                pozicija_ispaljene_rakete_p1 = pozicija_ispaljene_rakete_p1 + 9;
                                if (pozicija_ispaljene_rakete_p1 + 50 > pozicija_sasije2 + 50) {
                                    pozicija_ispaljene_rakete_p1 = pozicija_rakete1;
                                    health_p2 = health_p2 - 9;
                                    p2_health.setProgress(health_p2);
                                    if (health_p2 < 0) {
                                        kraj_borbe = true;
                                    }
                                }

                            }
                        }
                        else{
                            if (player1_forklifted==1 && v1.getLevi_deo()==1 && kontorliseIgrac.equals("NE")) {
                                customViewFight.ispaljena_raketa_p1.setBounds(0, 0, 0, 0);
                                customViewFight.p1_ispaljena_raketa_rotirana.setBounds(pozicija_ispaljene_rakete_p1, pozicija_rotirane_rakete_p1, pozicija_ispaljene_rakete_p1 + 50, pozicija_rotirane_rakete_p1 + 55);
                                pozicija_ispaljene_rakete_p1 = pozicija_ispaljene_rakete_p1 + 9;
                                pozicija_rotirane_rakete_p1 = pozicija_rotirane_rakete_p1 - 9;
                                if (pozicija_rotirane_rakete_p1 < 0) {
                                    pozicija_rotirane_rakete_p1 = 535;
                                    pozicija_ispaljene_rakete_p1 = pozicija_rakete1;
                                }
                            }
                        }

                        if (player1_forklifted==0 && kontorliseIgrac.equals("DA") && pucaj.equals("DA")) {
                            if ((v1.getLevi_deo() == 1)) {
                                customViewFight.ispaljena_raketa_p1.setBounds(pozicija_ispaljene_rakete_p1, 535, pozicija_ispaljene_rakete_p1 + 50, 590);
                                pozicija_ispaljene_rakete_p1 = pozicija_ispaljene_rakete_p1 + 9;
                                if (pozicija_ispaljene_rakete_p1 + 50 > pozicija_sasije2 + 50) {
                                    myViewModel.postaviPucanje("NE");
                                    pozicija_ispaljene_rakete_p1 = pozicija_rakete1;
                                    health_p2 = health_p2 - 9;
                                    p2_health.setProgress(health_p2);
                                    if (health_p2 < 0) {
                                        kraj_borbe = true;
                                    }
                                    customViewFight.ispaljena_raketa_p1.setBounds(pozicija_ispaljene_rakete_p1, 535, pozicija_ispaljene_rakete_p1 + 50, 590);
                                }

                            }
                        }
                        else{
                            if (player1_forklifted==1 && v1.getLevi_deo()==1 && kontorliseIgrac.equals("DA") && pucaj.equals("DA")) {
                                customViewFight.ispaljena_raketa_p1.setBounds(0, 0, 0, 0);
                                customViewFight.p1_ispaljena_raketa_rotirana.setBounds(pozicija_ispaljene_rakete_p1, pozicija_rotirane_rakete_p1, pozicija_ispaljene_rakete_p1 + 50, pozicija_rotirane_rakete_p1 + 55);
                                pozicija_ispaljene_rakete_p1 = pozicija_ispaljene_rakete_p1 + 9;
                                pozicija_rotirane_rakete_p1 = pozicija_rotirane_rakete_p1 - 9;
                                if (pozicija_rotirane_rakete_p1 < 0) {
                                    myViewModel.postaviPucanje("NE");
                                    pozicija_rotirane_rakete_p1 = 535;
                                    pozicija_ispaljene_rakete_p1 = pozicija_rakete1;
                                    customViewFight.p1_ispaljena_raketa_rotirana.setBounds(pozicija_ispaljene_rakete_p1, pozicija_rotirane_rakete_p1, pozicija_ispaljene_rakete_p1 + 50, pozicija_rotirane_rakete_p1 + 55);
                                }

                            }
                        }

                        /***************************SASIJA1***********************************/
                        if((pozicija_sasije1+440)<pozicija_sasije2){
                            if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                pozicija_sasije1 = pozicija_sasije1 + 1;
                            }
                            customViewFight.p1_sasija.setBounds(pozicija_sasije1,400,pozicija_sasije1+500,750);
                        }
                        else{
                            if (player1_forklifted==1){
                                customViewFight.p1_sasija.setBounds(pozicija_sasije1+500,pozicija_sasije1+500,pozicija_sasije1+500,pozicija_sasije1+500);
                                customViewFight.p1_sasija_rotirana.setBounds(pozicija_sasije1,400,pozicija_sasije1+500,750);
                            }
                        }
                        /***************************PREDNJI TOCAK 1***********************************/
                        if (v1.getDesni_tockovi()==4 && (pozicija_sasije1+440)<pozicija_sasije2){
                            if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                pozicija_prednjeg_tocka = pozicija_prednjeg_tocka + 1;
                            }
                            customViewFight.p1_tocak_p.setBounds(pozicija_prednjeg_tocka,630,pozicija_prednjeg_tocka+100,750);
                        }
                        else{
                            if(v1.getDesni_tockovi()==4 && (pozicija_sasije1+440)>=pozicija_sasije2 && player1_forklifted==1) {
                                customViewFight.p1_tocak_p.setBounds(pozicija_prednjeg_tocka + 20, 480, pozicija_prednjeg_tocka + 120, 600);
                            }
                        }
                        /***************************ZADNJI TOCAK 1***********************************/
                        if (v1.getLevi_tockovi()==5 && (pozicija_sasije1+440)<pozicija_sasije2){
                            if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                pozicija_zadnjeg_tocka = pozicija_zadnjeg_tocka + 1;
                            }
                            customViewFight.p1_tocak_z.setBounds(pozicija_zadnjeg_tocka,640,pozicija_zadnjeg_tocka+100,750);

                        }
                        else{
                            if (v1.getLevi_tockovi()==5 && (pozicija_sasije1+440)>=pozicija_sasije2 && player1_forklifted==1){
                                customViewFight.p1_tocak_z.setBounds(pozicija_zadnjeg_tocka+60,640,pozicija_zadnjeg_tocka+160,750);
                            }
                        }
                        switch(v1.getDesni_deo()){
                            /***************************BUSILICA1***********************************/
                            case 2:{
                                if((pozicija_sasije1+500)<pozicija_sasije2){
                                    if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                        pozicija_busilice = pozicija_busilice + 1;
                                    }
                                    customViewFight.p1_busilica.setBounds(pozicija_busilice,530,pozicija_busilice+230,680);

                                }
                                else{
                                    if((pozicija_sasije1+500)>=pozicija_sasije2 && player1_forklifted==1){
                                        customViewFight.p1_busilica.setBounds(0,0,0,0);
                                        customViewFight.p1_busilica_rotirana.setBounds(pozicija_busilice,340,pozicija_busilice+230,490);
                                    }
                                }
                                if(player1_forklifted==0) {
                                    skini_snagu_p2++;
                                    if (pozicija_busilice + 180 > pozicija_sasije2 && skini_snagu_p2 > 50) {
                                        skini_snagu_p2 = 0;
                                        health_p2 = health_p2 - 12;
                                        p2_health.setProgress(health_p2);
                                        if (health_p2 < 0) {
                                            kraj_borbe = true;
                                        }
                                    }
                                }
                                break;
                            }
                            /***************************BUZDOVAN1***********************************/
                            case 3:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                        pozicija_buzdovana = pozicija_buzdovana + 1;
                                    }
                                    if (brojacBuzdovan1>=0 && brojacBuzdovan1 <5){
                                        customViewFight.p1_buzdovan.setBounds(pozicija_buzdovana, 520, pozicija_buzdovana + 260, 700);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                        brojacBuzdovan1++;
                                    }
                                    if (brojacBuzdovan1>=5 && brojacBuzdovan1< 10){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(pozicija_buzdovana+145,550,pozicija_buzdovana+285,830);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                        brojacBuzdovan1++;
                                    }
                                    if (brojacBuzdovan1>=10 && brojacBuzdovan1 <15){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(pozicija_buzdovana+170,510,pozicija_buzdovana+430,690);
                                        skini_snagu_p2++;
                                        if (pozicija_buzdovana+430>pozicija_sasije2 && skini_snagu_p2>40){
                                            skini_snagu_p2=0;
                                            health_p2=health_p2-7;
                                            p2_health.setProgress(health_p2);
                                            if (health_p2<0){
                                                kraj_borbe=true;
                                            }
                                        }
                                        brojacBuzdovan1++;
                                    }
                                    if (brojacBuzdovan1>=15 && brojacBuzdovan1<20){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(pozicija_buzdovana+135,400,pozicija_buzdovana+285,650);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                       brojacBuzdovan1=0;
                                    }
                                }
                                else{
                                    /*************************ROTACIJA*********************************/
                                    if (brojacBuzdovan1>=0 && brojacBuzdovan1 <5 && player1_forklifted==0){
                                        customViewFight.p1_buzdovan.setBounds(pozicija_buzdovana, 520, pozicija_buzdovana + 260, 700);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                        brojacBuzdovan1++;
                                    }
                                    else{
                                        if (brojacBuzdovan1>=0 && brojacBuzdovan1 <5 && player1_forklifted==1){
                                            customViewFight.p1_buzdovan.setBounds(pozicija_buzdovana-30, 390, pozicija_buzdovana + 230, 580);
                                            customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                            brojacBuzdovan1++;
                                        }
                                    }
                                    if (brojacBuzdovan1>=5 && brojacBuzdovan1< 10 && player1_forklifted==0){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(pozicija_buzdovana+135,450,pozicija_buzdovana+285,730);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                        brojacBuzdovan1++;
                                    }
                                    else{
                                        if (brojacBuzdovan1>=5 && brojacBuzdovan1< 10 && player1_forklifted==1){
                                            customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p1_buzdovan_dole.setBounds(pozicija_buzdovana+110,420,pozicija_buzdovana+265,710);
                                            customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                            brojacBuzdovan1++;
                                        }
                                    }
                                    if (brojacBuzdovan1>=10 && brojacBuzdovan1 <15 && player1_forklifted==0){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_desno.setBounds(pozicija_buzdovana+170,510,pozicija_buzdovana+430,690);
                                        skini_snagu_p2++;
                                        if (pozicija_buzdovana+430>pozicija_sasije2 && skini_snagu_p2>40){
                                            skini_snagu_p2=0;
                                            health_p2=health_p2-7;
                                            p2_health.setProgress(health_p2);
                                            if (health_p2<0){
                                                kraj_borbe=true;
                                            }
                                        }
                                        brojacBuzdovan1++;
                                    }
                                    else{
                                        if (brojacBuzdovan1>=10 && brojacBuzdovan1 <15 && player1_forklifted==1){
                                            customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_desno.setBounds(pozicija_buzdovana+135,380,pozicija_buzdovana+410,570);
                                            skini_snagu_p2++;
                                            if (pozicija_buzdovana+430>pozicija_sasije2 && skini_snagu_p2>40){
                                                skini_snagu_p2=0;
                                                health_p2=health_p2-7;
                                                p2_health.setProgress(health_p2);
                                                if (health_p2<0){
                                                    kraj_borbe=true;
                                                }
                                            }
                                            brojacBuzdovan1++;
                                        }
                                    }
                                    if (brojacBuzdovan1>=15 && brojacBuzdovan1<20 && player1_forklifted==0){
                                        customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_buzdovan_gore.setBounds(pozicija_buzdovana+125,370,pozicija_buzdovana+285,650);
                                        customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                        brojacBuzdovan1=0;
                                    }
                                    else{
                                        if (brojacBuzdovan1>=15 && brojacBuzdovan1<20 && player1_forklifted==1){
                                            customViewFight.p1_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                                            customViewFight.p1_buzdovan_gore.setBounds(pozicija_buzdovana+90,240,pozicija_buzdovana+265,530);
                                            customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                                            brojacBuzdovan1=0;
                                        }
                                    }
                                }
                                /******************************************************************/
                                break;
                            }
                            /***************************FORKLIFT1***********************************/
                            case 6:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                        pozicija_forklifta = pozicija_forklifta + 1;
                                    }
                                    if (brojacForklift1>=0 && brojacForklift1 <5){
                                        customViewFight.p1_forklift.setBounds(pozicija_forklifta, 520, pozicija_forklifta + 260, 700);
                                        customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_desno.setBounds(0,0,0,0);
                                        brojacForklift1++;
                                    }
                                    if (brojacForklift1>=5 && brojacForklift1< 10){
                                        customViewFight.p1_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_forklift_dole.setBounds(pozicija_forklifta+145,550,pozicija_forklifta+285,830);
                                        customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_desno.setBounds(0,0,0,0);
                                        brojacForklift1++;
                                    }
                                    if (brojacForklift1>=10 && brojacForklift1 <15){
                                        customViewFight.p1_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_desno.setBounds(pozicija_forklifta+170,510,pozicija_forklifta+430,690);
                                        if (pozicija_forklifta+300>pozicija_sasije2) {
                                            player2_forklifted=1;
                                        }
                                        brojacForklift1++;
                                    }
                                    if (brojacForklift1>=15 && brojacForklift1<20){
                                        customViewFight.p1_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_gore.setBounds(pozicija_forklifta+135,400,pozicija_forklifta+285,650);
                                        customViewFight.p1_forklift_desno.setBounds(0,0,0,0);
                                        brojacForklift1=0;
                                    }
                                }
                                else{
                                    /*************************ROTACIJA*********************************/
                                    if (/*brojacForklift1>=10 && brojacForklift1 <15 &&*/ player1_forklifted==0){
                                        customViewFight.p1_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p1_forklift_desno.setBounds(pozicija_forklifta+170,510,pozicija_forklifta+430,690);

                                        if (pozicija_forklifta+300>pozicija_sasije2 && skini_snagu_p2>40){
                                           player2_forklifted=1;
                                        }
                                        brojacForklift1++;
                                    }
                                    else{
                                        if (/*brojacForklift1>=10 && brojacForklift1 <15 &&*/ player1_forklifted==1){
                                            customViewFight.p1_forklift.setBounds(0, 0, 0, 0);
                                            customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                                            customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                                            customViewFight.p1_forklift_desno.setBounds(pozicija_forklifta+135,380,pozicija_forklifta+410,570);

                                            if (pozicija_forklifta+430>pozicija_sasije2 && skini_snagu_p2>40){
                                                player2_forklifted=1;
                                            }
                                            brojacForklift1++;
                                        }
                                    }
                                }
                                /******************************************************************/
                                break;
                            }
                            /***************************TESTERA1***********************************/
                            case 7:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v1.getLevi_tockovi()==5 && v1.getDesni_tockovi()==4) {
                                        pozicija_testere = pozicija_testere + 1;
                                    }
                                    customViewFight.p1_testera.setBounds(pozicija_testere, 530, pozicija_testere + 230, 680);

                                }
                                else{
                                    if (player1_forklifted==1){
                                        customViewFight.p1_testera.setBounds(0, 0, 0, 0);
                                        customViewFight.p1_testera_rotirana.setBounds(pozicija_testere-20,340,pozicija_testere+210,490);
                                    }
                                }
                                if (player1_forklifted==0) {
                                    skini_snagu_p2++;
                                    if (pozicija_testere + 180 > pozicija_sasije2 && skini_snagu_p2 > 50) {
                                        skini_snagu_p2 = 0;
                                        health_p2 = health_p2 - 19;
                                        p2_health.setProgress(health_p2);
                                        if (health_p2 < 0) {
                                            kraj_borbe = true;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                        /**********************************KRETANJE DRUGOG VOZILA******************************************************/

                        /***************************RAKETA2***********************************/
                        if (v2.getLevi_deo()==1 && (pozicija_sasije1+440)<pozicija_sasije2) {
                            if (v2.getLevi_tockovi() == 5 && v2.getDesni_tockovi() == 4) {
                                pozicija_rakete2 = pozicija_rakete2 - 1;
                            }
                            customViewFight.p2_raketa.setBounds(pozicija_rakete2,515,pozicija_rakete2+130,640);
                        }

                        else{
                            if (v2.getLevi_deo()==1 && (pozicija_sasije1+440)>=pozicija_sasije2 && player2_forklifted==1){
                                customViewFight.p2_raketa.setBounds(0,0,0,0);
                                customViewFight.p2_raketa_rotirana.setBounds(pozicija_rakete2,530,pozicija_rakete2+130,685);
                            }
                        }


                        if (v2.getLevi_deo()==1 && player2_forklifted==0){
                            customViewFight.ispaljena_raketa_p2.setBounds(pozicija_ispaljene_rakete_p2,535,pozicija_ispaljene_rakete_p2+50,590);
                            pozicija_ispaljene_rakete_p2=pozicija_ispaljene_rakete_p2-9;
                            if(pozicija_ispaljene_rakete_p2<pozicija_sasije1+450){
                                pozicija_ispaljene_rakete_p2 = pozicija_rakete2+130;
                                health_p1=health_p1-9;
                                p1_health.setProgress(health_p1);
                                if (health_p1<0){
                                    kraj_borbe=true;
                                }
                            }
                        }
                        else{
                            if (v2.getLevi_deo()==1 && player2_forklifted==1) {
                                customViewFight.ispaljena_raketa_p2.setBounds(0, 0, 0, 0);
                                customViewFight.p2_ispaljena_raketa_rotirana.setBounds(pozicija_ispaljene_rakete_p2, pozicija_rotirane_rakete_p2, pozicija_ispaljene_rakete_p2 + 50, pozicija_rotirane_rakete_p2 + 55);
                                pozicija_ispaljene_rakete_p2 = pozicija_ispaljene_rakete_p2 - 9;
                                pozicija_rotirane_rakete_p2 = pozicija_rotirane_rakete_p2 - 9;
                                if (pozicija_rotirane_rakete_p2 < 0) {
                                    pozicija_rotirane_rakete_p2 = 545;
                                    pozicija_ispaljene_rakete_p2 = pozicija_rakete2 + 130;
                                }
                            }
                        }

                        /***************************SASIJA2***********************************/

                        if((pozicija_sasije1+440)<pozicija_sasije2) {
                            if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                pozicija_sasije2 = pozicija_sasije2 - 1;
                            }
                            customViewFight.p2_sasija.setBounds(pozicija_sasije2,400,pozicija_sasije2+500,750);
                        }
                        else{
                            if (player2_forklifted==1){
                                customViewFight.p2_sasija.setBounds(0,0,0,0);
                                customViewFight.p2_sasija_rotirana.setBounds(pozicija_sasije2,400,pozicija_sasije2+500,750);
                            }
                        }


                        /***************************TOCKOVI PREDNJI 2***********************************/
                        if (v2.getDesni_tockovi()==4 && (pozicija_sasije1+440)<pozicija_sasije2){
                            if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                pozicija_prednjeg_tocka2 = pozicija_prednjeg_tocka2 - 1;
                            }
                            customViewFight.p2_tocak_p.setBounds(pozicija_prednjeg_tocka2,630,pozicija_prednjeg_tocka2+100,750);
                        }
                        else{
                            if (v2.getDesni_tockovi()==4 && (pozicija_sasije1+440)>=pozicija_sasije2 && player2_forklifted==1){
                                customViewFight.p2_tocak_p.setBounds(pozicija_prednjeg_tocka2-5,480,pozicija_prednjeg_tocka2+95,600);
                            }
                        }

                        /***************************TOCKOVI ZADNJI 2***********************************/
                        if (v2.getLevi_tockovi()==5 && (pozicija_sasije1+440)<pozicija_sasije2){
                            if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                pozicija_zadnjeg_tocka2 = pozicija_zadnjeg_tocka2 - 1;
                            }
                            customViewFight.p2_tocak_z.setBounds(pozicija_zadnjeg_tocka2,640,pozicija_zadnjeg_tocka2+100,750);

                        }
                        else{
                            if (player2_forklifted==1){
                                customViewFight.p2_tocak_z.setBounds(pozicija_zadnjeg_tocka2-55,640,pozicija_zadnjeg_tocka2+45,750);
                            }
                        }


                        switch(v2.getDesni_deo()){
                            /***************************BUSILICA2***********************************/
                            case 2:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                        pozicija_busilice2 = pozicija_busilice2 - 1;
                                    }
                                    customViewFight.p2_busilica.setBounds(pozicija_busilice2, 530, pozicija_busilice2 + 230, 680);
                                }
                                else{
                                    if((pozicija_sasije1+440)>=pozicija_sasije2 && player2_forklifted==1) {
                                        customViewFight.p2_busilica.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_busilica_rotirana.setBounds(pozicija_busilice2, 340, pozicija_busilice2 + 230, 490);
                                    }
                                }
                                if (player2_forklifted==0) {
                                    skini_snagu_p1++;
                                    if (pozicija_busilice2 + 20 < pozicija_sasije1 + 500 && skini_snagu_p1 > 50) {
                                        skini_snagu_p1 = 0;
                                        health_p1 = health_p1 - 12;
                                        p1_health.setProgress(health_p1);
                                        if (health_p1 < 0) {
                                            kraj_borbe = true;
                                        }
                                    }
                                }
                                break;
                            }
                            /***************************BUZDOVAN2***********************************/
                            case 3:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                        pozicija_buzdovana2 = pozicija_buzdovana2 - 1;
                                    }
                                    if (brojacBuzdovan2>=0 && brojacBuzdovan2 <5){
                                        customViewFight.p2_buzdovan.setBounds(pozicija_buzdovana2,520,pozicija_buzdovana2+260,700);
                                        customViewFight.p2_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_levo.setBounds(0,0,0,0);
                                        brojacBuzdovan2++;
                                    }
                                    if (brojacBuzdovan2>=5 && brojacBuzdovan2< 10){
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(pozicija_buzdovana2-5,550,pozicija_buzdovana2+140,830);
                                        customViewFight.p2_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_levo.setBounds(0,0,0,0);
                                        brojacBuzdovan2++;
                                    }
                                    if (brojacBuzdovan2>=10 && brojacBuzdovan2 <15){
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_levo.setBounds(pozicija_buzdovana2-160,510,pozicija_buzdovana2+100,690);
                                        skini_snagu_p1++;
                                        if (pozicija_buzdovana2-125<pozicija_sasije1+500 && skini_snagu_p1>40){
                                            skini_snagu_p1=0;
                                            health_p1=health_p1-19;
                                            p1_health.setProgress(health_p1);
                                            if (health_p1<0){
                                                kraj_borbe=true;
                                            }
                                        }
                                        brojacBuzdovan2++;
                                    }
                                    if (brojacBuzdovan2>=15 && brojacBuzdovan2<20){
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_buzdovan_gore.setBounds(pozicija_buzdovana2-5,400,pozicija_buzdovana2+140,650);
                                        customViewFight.p2_buzdovan_levo.setBounds(0,0,0,0);
                                        brojacBuzdovan2=0;
                                    }

                                }
                                else {
                                    /******************************ROTACIJA***************************/
                                    if (brojacBuzdovan2 >= 0 && brojacBuzdovan2 < 5 && player2_forklifted==0) {
                                        customViewFight.p2_buzdovan.setBounds(pozicija_buzdovana2, 520, pozicija_buzdovana2 + 260, 700);
                                        customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                        brojacBuzdovan2++;
                                    }
                                    else{
                                        if (brojacBuzdovan2 >= 0 && brojacBuzdovan2 < 5 && player2_forklifted==1) {
                                            customViewFight.p2_buzdovan.setBounds(pozicija_buzdovana2+30, 390, pozicija_buzdovana2 + 290, 580);
                                            customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                            brojacBuzdovan2++;
                                        }
                                    }
                                    if (brojacBuzdovan2 >= 5 && brojacBuzdovan2 < 10 && player2_forklifted==0) {
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(pozicija_buzdovana2 - 5, 550, pozicija_buzdovana2 + 140, 830);
                                        customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                        brojacBuzdovan2++;
                                    }
                                    else{
                                        if (brojacBuzdovan2 >= 5 && brojacBuzdovan2 < 10 && player2_forklifted==1) {
                                            customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_dole.setBounds(pozicija_buzdovana2 +20, 420, pozicija_buzdovana2 + 175, 710);
                                            customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                            brojacBuzdovan2++;
                                        }
                                    }
                                    if (brojacBuzdovan2 >= 10 && brojacBuzdovan2 < 15 && player2_forklifted==0) {
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_levo.setBounds(pozicija_buzdovana2 - 160, 510, pozicija_buzdovana2 + 100, 690);
                                        skini_snagu_p1++;
                                        if (pozicija_buzdovana2 - 125 < pozicija_sasije1 + 500 && skini_snagu_p1 > 40) {
                                            skini_snagu_p1 = 0;
                                            health_p1 = health_p1 - 19;
                                            p1_health.setProgress(health_p1);
                                            if (health_p1 < 0) {
                                                kraj_borbe = true;
                                            }
                                        }
                                        brojacBuzdovan2++;
                                    }
                                    else{
                                        if (brojacBuzdovan2 >= 10 && brojacBuzdovan2 < 15 && player2_forklifted==1) {
                                            customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_gore.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_levo.setBounds(pozicija_buzdovana2 - 125, 370, pozicija_buzdovana2 + 135, 560);
                                            skini_snagu_p1++;
                                            if (pozicija_buzdovana2 - 125 < pozicija_sasije1 + 500 && skini_snagu_p1 > 40) {
                                                skini_snagu_p1 = 0;
                                                health_p1 = health_p1 - 19;
                                                p1_health.setProgress(health_p1);
                                                if (health_p1 < 0) {
                                                    kraj_borbe = true;
                                                }
                                            }
                                            brojacBuzdovan2++;
                                        }
                                    }
                                    if (brojacBuzdovan2 >= 15 && brojacBuzdovan2 < 20 && player2_forklifted==0) {
                                        customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_buzdovan_gore.setBounds(pozicija_buzdovana2 - 5, 400, pozicija_buzdovana2 + 140, 650);
                                        customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                        brojacBuzdovan2 = 0;
                                    }
                                    else{
                                        if (brojacBuzdovan2 >= 15 && brojacBuzdovan2 < 20 && player2_forklifted==1) {
                                            customViewFight.p2_buzdovan.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_dole.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_buzdovan_gore.setBounds(pozicija_buzdovana2 - 5, 240, pozicija_buzdovana2 + 140, 530);
                                            customViewFight.p2_buzdovan_levo.setBounds(0, 0, 0, 0);
                                            brojacBuzdovan2 = 0;
                                        }
                                    }
                                }
                                break;
                            }
                            /***************************FORKLIFT2***********************************/
                            case 6:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                        pozicija_forklifta2 = pozicija_forklifta2 - 1;
                                    }
                                    if (brojacForklift2>=0 && brojacForklift2 <5){
                                        customViewFight.p2_forklift.setBounds(pozicija_forklifta2,520,pozicija_forklifta2+260,700);
                                        customViewFight.p2_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_levo.setBounds(0,0,0,0);
                                        brojacForklift2++;
                                    }
                                    if (brojacForklift2>=5 && brojacForklift2< 10){
                                        customViewFight.p2_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_dole.setBounds(pozicija_forklifta2-5,550,pozicija_forklifta2+140,830);
                                        customViewFight.p2_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_levo.setBounds(0,0,0,0);
                                        brojacForklift2++;
                                    }
                                    if (brojacForklift2>=10 && brojacForklift2 <15){
                                        customViewFight.p2_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_gore.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_levo.setBounds(pozicija_forklifta2-160,510,pozicija_forklifta2+100,690);

                                        if (pozicija_forklifta2-100<pozicija_sasije1+500){
                                            player1_forklifted=1;
                                        }
                                        brojacForklift2++;
                                    }
                                    if (brojacForklift2>=15 && brojacForklift2<20){
                                        customViewFight.p2_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_dole.setBounds(0,0,0,0);
                                        customViewFight.p2_forklift_gore.setBounds(pozicija_forklifta2-5,400,pozicija_forklifta2+140,650);
                                        customViewFight.p2_forklift_levo.setBounds(0,0,0,0);
                                        brojacForklift2=0;
                                    }

                                }
                                else {
                                    /******************************ROTACIJA***************************/
                                    if (player2_forklifted==0) {
                                        customViewFight.p2_forklift.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_dole.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_gore.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_forklift_levo.setBounds(pozicija_forklifta2 - 160, 510, pozicija_forklifta2 + 100, 690);
                                        skini_snagu_p1++;
                                        if (pozicija_forklifta2 - 125 < pozicija_sasije1 + 500 && skini_snagu_p1 > 40) {
                                            player1_forklifted=1;
                                        }
                                    }
                                    else{
                                        if (player2_forklifted==1) {
                                            customViewFight.p2_forklift.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_forklift_dole.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_forklift_gore.setBounds(0, 0, 0, 0);
                                            customViewFight.p2_forklift_levo.setBounds(pozicija_forklifta2 - 125, 370, pozicija_forklifta2 + 135, 560);

                                            if (pozicija_forklifta2 - 100 < pozicija_sasije1 + 500 && skini_snagu_p1 > 40) {
                                                player1_forklifted=1;
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                            /***************************TESTERA2***********************************/
                            case 7:{
                                if((pozicija_sasije1+440)<pozicija_sasije2) {
                                    if (v2.getLevi_tockovi()==5 && v2.getDesni_tockovi()==4) {
                                        pozicija_testere2 = pozicija_testere2 - 1;
                                    }
                                    customViewFight.p2_testera.setBounds(pozicija_testere2, 530, pozicija_testere2 + 230, 680);
                                }
                                else{
                                    if((pozicija_sasije1+440)>=pozicija_sasije2 && player2_forklifted==1) {
                                        customViewFight.p2_testera.setBounds(0, 0, 0, 0);
                                        customViewFight.p2_testera_rotirana.setBounds(pozicija_testere2, 340, pozicija_testere2 + 230, 490);

                                    }
                                }
                                if(player2_forklifted==0) {
                                    skini_snagu_p1++;
                                    if (pozicija_testere2 + 45 < pozicija_sasije1 + 500 && skini_snagu_p1 > 50) {
                                        skini_snagu_p1 = 0;
                                        health_p1 = health_p1 - 19;
                                        p1_health.setProgress(health_p1);
                                        if (health_p1 < 0) {
                                            kraj_borbe = true;
                                        }
                                    }
                                }
                                break;
                            }


                        }

                        myViewModel.postaviPromenuCustomViewa("da");
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                myViewModel.postaviPromenuCustomViewa("da");
                if (health_p1>health_p2){
                    customViewFight.p2_sasija.setBounds(0,0,0,0);
                    customViewFight.p2_tocak_p.setBounds(0,0,0,0);
                    customViewFight.p2_tocak_z.setBounds(0,0,0,0);
                    customViewFight.p2_raketa.setBounds(0,0,0,0);
                    customViewFight.p2_busilica.setBounds(0,0,0,0);
                    customViewFight.p2_testera.setBounds(0,0,0,0);
                    customViewFight.p2_forklift.setBounds(0,0,0,0);
                    customViewFight.p2_buzdovan.setBounds(0,0,0,0);
                    customViewFight.p2_buzdovan_levo.setBounds(0,0,0,0);
                    customViewFight.p2_buzdovan_dole.setBounds(0,0,0,0);
                    customViewFight.p2_buzdovan_gore.setBounds(0,0,0,0);
                    customViewFight.p2_forklift.setBounds(0,0,0,0);
                    customViewFight.p2_forklift_dole.setBounds(0,0,0,0);
                    customViewFight.p2_forklift_gore.setBounds(0,0,0,0);
                    customViewFight.p2_forklift_levo.setBounds(0,0,0,0);
                    customViewFight.p2_ispaljena_raketa_rotirana.setBounds(0,0,0,0);
                    customViewFight.ispaljena_raketa_p2.setBounds(0,0,0,0);
                    customViewFight.p2_busilica_rotirana.setBounds(0,0,0,0);
                    customViewFight.p2_sasija_rotirana.setBounds(0,0,0,0);
                    customViewFight.p2_raketa_rotirana.setBounds(0,0,0,0);
                    customViewFight.p2_testera_rotirana.setBounds(0,0,0,0);
                    customViewFight.eksplozija.setBounds(pozicija_sasije2,400,pozicija_sasije2+400,750);
                    myViewModel.postaviPromenuCustomViewa("da");
                }
                else{
                    customViewFight.p1_sasija.setBounds(0,0,0,0);
                    customViewFight.p1_tocak_p.setBounds(0,0,0,0);
                    customViewFight.p1_tocak_z.setBounds(0,0,0,0);
                    customViewFight.p1_raketa.setBounds(0,0,0,0);
                    customViewFight.p1_busilica.setBounds(0,0,0,0);
                    customViewFight.p1_testera.setBounds(0,0,0,0);
                    customViewFight.p1_forklift.setBounds(0,0,0,0);
                    customViewFight.p1_buzdovan.setBounds(0,0,0,0);
                    customViewFight.p1_buzdovan_desno.setBounds(0,0,0,0);
                    customViewFight.p1_buzdovan_dole.setBounds(0,0,0,0);
                    customViewFight.p1_buzdovan_gore.setBounds(0,0,0,0);
                    customViewFight.p1_forklift.setBounds(0,0,0,0);
                    customViewFight.p1_forklift_gore.setBounds(0,0,0,0);
                    customViewFight.p1_forklift_dole.setBounds(0,0,0,0);
                    customViewFight.p1_forklift_desno.setBounds(0,0,0,0);
                    customViewFight.p1_ispaljena_raketa_rotirana.setBounds(0,0,0,0);
                    customViewFight.ispaljena_raketa_p1.setBounds(0,0,0,0);
                    customViewFight.p1_busilica_rotirana.setBounds(0,0,0,0);
                    customViewFight.p1_sasija_rotirana.setBounds(0,0,0,0);
                    customViewFight.p1_raketa_rotirana.setBounds(0,0,0,0);
                    customViewFight.p1_testera_rotirana.setBounds(0,0,0,0);
                    customViewFight.eksplozija.setBounds(pozicija_sasije1,400,pozicija_sasije1+400,750);
                    myViewModel.postaviPromenuCustomViewa("da");
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                customViewFight.eksplozija.setBounds(0,0,0,0);
                myViewModel.postaviPromenuCustomViewa("da");
                if (health_p1>health_p2){
                    customViewFight.porazena_macka.setBounds(pozicija_sasije2+100,200,pozicija_sasije2+300,400);
                    myViewModel.postaviPromenuCustomViewa("da");
                }
                else{
                    customViewFight.porazena_macka.setBounds(pozicija_sasije1+100,200,pozicija_sasije1+300,400);
                    myViewModel.postaviPromenuCustomViewa("da");
                }
                customViewFight.return_to_garage.setBounds(1500,300,1750,600);

            }
        }).start();



        myViewModel.getPromeniCustomView().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                customViewFight.invalidate();
            }
        });


        return view;
    }

}
