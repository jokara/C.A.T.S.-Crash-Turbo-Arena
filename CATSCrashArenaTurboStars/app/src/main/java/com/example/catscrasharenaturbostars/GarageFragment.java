package com.example.catscrasharenaturbostars;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.FightsView;
import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.service.PlayerService;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;


/**
 * A simple {@link Fragment} subclass.
 */
public class GarageFragment extends Fragment {


    public GarageFragment() {
        // Required empty public constructor
    }
    MyViewModel myViewModel;
    List<Vehicle> svaVozila;
    List<Box> sveKutije;
    Vehicle mojeVozilo=null;
    Box mojaKutija=null;
    List<User> users;
    List<FightsView> sveBorbe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_garage, container, false);
        LinearLayout l=view.findViewById(R.id.garageLayout);
        l.setBackgroundResource(R.drawable.garage_background);
        /*****************DOHVATANJE LABELE ZA POSTAVLJANJE ULOGOVANOG KORISNIKA*******************/
        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        String ulogovan= myViewModel.getUlogovan().getValue();
        TextView ulogovani=view.findViewById(R.id.ulogovani_korisnik);
        ulogovani.setText(ulogovan);
        /*****************DOHVATANJE BROJA POBEDA I POSTAVLJANJE SLIKE ODGOVARAJUCE****************/
        int brojPobeda=Integer.parseInt(myViewModel.getBrojDobijenihBorbi().getValue());
        /*users = myViewModel.getUsers().getValue();
        for (User u: users) {
            if (u.getUsername().equals(ulogovan)){
                brojPobeda= u.getWinStrike();
            }
        }*/
        myViewModel.postaviBrojPobeda(brojPobeda+"");
        Log.d("BROJ POBEDA GARAZA",String.valueOf(brojPobeda));
        /*************************NEUTRALISANJE SVIH DELOVA SA SLIKE*******************************/
        ImageView raketa=view.findViewById(R.id.raketa_garaza);
        ImageView busilica= view.findViewById(R.id.busilica_garaza);
        ImageView buzdovan= view.findViewById(R.id.buzdovan_garaza);
        ImageView tocak_p= view.findViewById(R.id.tocak_p_garaza);
        ImageView tocak_z= view.findViewById(R.id.tocak_z_garaza);
        ImageView forklift = view.findViewById(R.id.forklift_garaza);
        ImageView testera = view.findViewById(R.id.testera_garaza);
        raketa.setVisibility(View.INVISIBLE);
        busilica.setVisibility(View.INVISIBLE);
        buzdovan.setVisibility(View.INVISIBLE);
        tocak_p.setVisibility(View.INVISIBLE);
        tocak_z.setVisibility(View.INVISIBLE);
        forklift.setVisibility(View.INVISIBLE);
        testera.setVisibility(View.INVISIBLE);
        /*******************DOHVATANJE MOJE KUTIJE*************************************************/
        sveKutije=myViewModel.getBoxes().getValue();
        for (Box b:sveKutije) {
            if (b.getUsername().equals(ulogovan)){
                mojaKutija=b;
            }
        }
        /*******************DOHVATANJE MOG VOZILA I NJEGOVOG IZGLEDA*******************************/
        svaVozila=myViewModel.getVehicles().getValue();
        for (Vehicle v:svaVozila) {
            if (v.getUsername().equals(ulogovan)){
                mojeVozilo=v;
            }
        }
        //1 - raketa 2-busilica 3-budzovan 4- tocak+prednji 5- tocak_zadnji 6- forklift 7-testera
        if (mojeVozilo!=null) {
            switch (mojeVozilo.getLevi_deo()){
                case 1: raketa.setVisibility(View.VISIBLE);
                break;
            }
            switch (mojeVozilo.getDesni_deo()){
                case 2: {
                    busilica.setVisibility(View.VISIBLE);
                    break;
                }
                case 3: {
                    buzdovan.setVisibility(View.VISIBLE);
                    break;
                }
                case 6: {
                    forklift.setVisibility(View.VISIBLE);
                    break;
                }
                case 7: {
                    testera.setVisibility(View.VISIBLE);
                    break;
                }
            }
            switch (mojeVozilo.getLevi_tockovi()){
                case 5: {
                    tocak_z.setVisibility(View.VISIBLE);
                    break;
                }
            }
            switch (mojeVozilo.getDesni_tockovi()){
                case 4: {
                    tocak_p.setVisibility(View.VISIBLE);
                    break;
                }
            }

        }
        /****************************POSTAVLJANJE VREDNOSTI SNAGE I ODBRANE************************/
        int jacina_napada = 0;
        int jacina_odbrane = 0;
        jacina_napada = mojeVozilo.getJacina_napada();
        jacina_odbrane = mojeVozilo.getJacina_odbrane();
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

        TextView jacina_n=view.findViewById(R.id.jacina_napada);
        TextView jacina_o=view.findViewById(R.id.jacina_odbrane);
        jacina_n.setText(String.valueOf(jacina_napada));
        jacina_o.setText(String.valueOf(jacina_odbrane));

        /****************************PUSTANJE MUZIKE***********************************************/
        Intent intent=new Intent(getContext(), PlayerService.class);
        intent.putExtra("state","play");
        getActivity().startService(intent);
        /****************************DIALOG ZA KONTROLU**********************************************/
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        builder2.setMessage("Da li zelite vi da kontrolisete borbu?").setTitle("Borba");
        // Add the buttons
        builder2.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //Gasenje muzike
                myViewModel.postaviDaIgracKontrolise("DA");
            }
        });
        builder2.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                myViewModel.postaviDaIgracKontrolise("NE");
            }
        });

        final AlertDialog dialog2 = builder2.create();


        /****************************DIALOG ZA MUZIKU**********************************************/
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Da li zelite da ukljucite ili iskljucite muziku?").setTitle("Muzika");
        // Add the buttons
        builder.setPositiveButton("Upali", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                //Gasenje muzike
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
                //Paljenje muzike
                Intent intent=new Intent(getContext(), PlayerService.class);
                intent.putExtra("state","play");
                getActivity().startService(intent);
            }
        });
        builder.setNegativeButton("Ugasi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                //Gasenje muzike
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
            }
        });

        final AlertDialog dialog = builder.create();

        /****************************DIALOG ZA BORBE**********************************************/
        String nizStringova="";
        sveBorbe=myViewModel.getFightViews().getValue();
        for (FightsView f:sveBorbe) {
            if (f.getNamep1().equals(ulogovan)){
                nizStringova+=f.getNamep1()+" vs " +f.getNamep2()+"->"+f.getResult()+" won"+"\r\n";
            }
        }
        Log.d("NIZ", nizStringova);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle("Moje borbe").setMessage(nizStringova);

        // Add the buttons
        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        final AlertDialog dialog1 = builder1.create();

        /******************PRELAZAK NA FRAGMENT ZA MODIFIKACIJU VOZILA*****************************/
        ImageView sasija=view.findViewById(R.id.sasija_garaza);
        sasija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prelazak na fragment za modifikaciju vozila
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_garageFragment_to_modVehicleFragment);
            }
        });

        /*********************************SETTINGS*************************************************/
        ImageView settings=view.findViewById(R.id.Ugasi);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        /*********************************LOGOUT***************************************************/
        ImageView logout= view.findViewById(R.id.logout_image);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_garageFragment_to_signFragment);
            }
        });
        /*********************************PREGLED BORBI***************************************************/
        ImageView pregledBorbi= view.findViewById(R.id.pregled_borbi_image_view);
        pregledBorbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.show();
            }
        });
        /****************************PRELAZAK NA BORBU********************************************/
        ImageView borba= view.findViewById(R.id.borba_image_view);
        ImageView borba1=view.findViewById(R.id.borba1_image_view);
        ImageView borba2=view.findViewById(R.id.borba2_image_view);
        if (brojPobeda == 0){
            borba.setVisibility(View.VISIBLE);
            borba1.setVisibility(View.INVISIBLE);
            borba2.setVisibility(View.INVISIBLE);
        }
        if (brojPobeda == 1){
            borba.setVisibility(View.INVISIBLE);
            borba1.setVisibility(View.VISIBLE);
            borba2.setVisibility(View.INVISIBLE);
        }
        if (brojPobeda == 2){
            borba.setVisibility(View.INVISIBLE);
            borba1.setVisibility(View.INVISIBLE);
            borba2.setVisibility(View.VISIBLE);
        }
        borba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_garageFragment_to_fightFragment);
            }
        });
        borba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_garageFragment_to_fightFragment);
            }
        });
        borba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(getContext(),PlayerService.class);
                getActivity().stopService(intent1);
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_garageFragment_to_fightFragment);
            }
        });
        /****************************PRVA KUTIJA***************************************************/
        ImageView prvaKutija = view.findViewById(R.id.prva_kutija);
        prvaKutija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mojaKutija!=null) {
                    if (mojaKutija.getLocked() == 0) {
                        myViewModel.postaviVreme("Zakljucano");
                        mojaKutija.setLocked(1);
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_garageFragment_to_boxOpen);
                    }
                }
            }
        });
        /***************************STANJE KUTIJE**************************************************/
        final TextView kutija2=view.findViewById(R.id.kutija2_stanje);

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag = true;
                if (mojaKutija != null) {
                    while (flag) {
                        try {
                            Date currentDate = new Date();
                            Date date = mojaKutija.getTime();
                            int sati = 0;
                            int minuti = 0;
                            int sati1 = 0;
                            int preostalo;
                            sati = date.getHours();
                            minuti = date.getMinutes();
                            sati = sati * 60 + 5 + minuti;
                            sati1 = currentDate.getHours() * 60 + currentDate.getMinutes();
                            preostalo = sati - sati1;
                            String s = "" + preostalo + "min";
                            if (preostalo < 0) {
                                flag = false;
                                mojaKutija.setLocked(0);
                                myViewModel.postaviVreme("Otkljucano");
                            } else {
                                myViewModel.postaviVreme(s);
                            }
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    myViewModel.postaviVreme("Zakljucano");
                }
            }
        }).start();
        /************************OBSERVE ZA VREME KUTIJE*******************************************/
        myViewModel.getVremeKutija().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                kutija2.setText(s);
            }
        });
        /***********************IMAGE VIEW KONTROLA************************************************/
        ImageView kontrola = view.findViewById(R.id.kontrolaIgraca);
        kontrola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.show();
            }
        });

        return view;
    }

}
