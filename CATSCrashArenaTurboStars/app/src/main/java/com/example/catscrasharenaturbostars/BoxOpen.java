package com.example.catscrasharenaturbostars;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoxOpen extends Fragment {


    public BoxOpen() {
        // Required empty public constructor
    }
    Vehicle mojeVozilo=null;

    MyViewModel myViewModel;
    List<Vehicle> svaVozila;
    ArrayList<Vehicle> novaListaVozila=new ArrayList<>();
    List<Box> sveKutije;
    ArrayList<Box> novaListaKutije= new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_box_open, container, false);
        /*********************POSTAVLJANJE POZADINE************************************************/
        ConstraintLayout constraintLayout = view.findViewById(R.id.boxOpen_constraint);
        constraintLayout.setBackgroundResource(R.drawable.box_open);
        /******************************VIEW MODEL**************************************************/
        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        final String ulogovan = myViewModel.getUlogovan().getValue();
        /********************************IMAGE VIEW************************************************/
        ImageView raketa = view.findViewById(R.id.boxOpen_raketa);
        ImageView tocak_p = view.findViewById(R.id.boxOpen_tocak_prednji);
        ImageView tocak_z = view.findViewById(R.id.boxOpen_tocak_zadnji);
        ImageView buzdovan = view.findViewById(R.id.boxOpen_buzdovan);
        ImageView busilica = view.findViewById(R.id.boxOpen_busilica);
        ImageView testera = view.findViewById(R.id.boxOpen_testera);
        ImageView forklift = view.findViewById(R.id.boxOpen_forklift);
        /********************************GASENJE SVIH**********************************************/
        raketa.setVisibility(View.INVISIBLE);
        tocak_p.setVisibility(View.INVISIBLE);
        tocak_z.setVisibility(View.INVISIBLE);
        buzdovan.setVisibility(View.INVISIBLE);
        busilica.setVisibility(View.INVISIBLE);
        testera.setVisibility(View.INVISIBLE);
        forklift.setVisibility(View.INVISIBLE);
        /*******************************DOHVATANJE VOZILA******************************************/
        sveKutije=myViewModel.getBoxes().getValue();
        svaVozila=myViewModel.getVehicles().getValue();
        for (Vehicle v:svaVozila) {
            if (v.getUsername().equals(ulogovan)){
                mojeVozilo=v;
            }
        }


        int prvi_dobitak=(int)(Math.random()*3);
        int drugi_dobitak=(int)(Math.random()*4);
        switch (prvi_dobitak){
            case 1:{
                raketa.setVisibility(View.VISIBLE);
                mojeVozilo.setRaketa(1);
                break;
            }
            case 2:{
                tocak_p.setVisibility(View.VISIBLE);
                mojeVozilo.setTocak_p(1);
                break;
            }
            case 3:{
                tocak_z.setVisibility(View.VISIBLE);
                mojeVozilo.setTocak_z(1);
                break;
            }
        }

        switch (drugi_dobitak){
            case 1:{
                busilica.setVisibility(View.VISIBLE);
                mojeVozilo.setBusilica(1);
                break;
            }
            case 2:{
                buzdovan.setVisibility(View.VISIBLE);
                mojeVozilo.setBuzdovan(1);
                break;
            }
            case 3:{
                testera.setVisibility(View.VISIBLE);
                mojeVozilo.setTestera(1);
                break;
            }
            case 4:{
                forklift.setVisibility(View.VISIBLE);
                mojeVozilo.setForklift(1);
                break;
            }
        }

        Button button_boxOpen = view.findViewById(R.id.button_boxOpen);
        button_boxOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (Vehicle v:svaVozila) {
                            if (!v.getUsername().equals(ulogovan)){
                                novaListaVozila.add(v);
                            }
                        }
                        novaListaVozila.add(mojeVozilo);
                        myViewModel.postaviListuVozila(novaListaVozila);

                        for (Box b:sveKutije) {
                            if (!b.getUsername().equals(ulogovan)){
                                novaListaKutije.add(b);
                            }
                        }
                        myViewModel.postaviListuKutija(novaListaKutije);
                        MyRoomDatabase.getDatabase(getContext()).boxDao().remove_username_box(ulogovan);
                        MyRoomDatabase.getDatabase(getContext()).vehicleDao().updateVehicle(ulogovan,mojeVozilo.getRaketa(),mojeVozilo.getBusilica(),mojeVozilo.getBuzdovan(),mojeVozilo.getTocak_p(),mojeVozilo.getTocak_z(),mojeVozilo.getForklift(),mojeVozilo.getTestera(),mojeVozilo.getLevi_deo(),mojeVozilo.getDesni_deo(),mojeVozilo.getLevi_tockovi(),mojeVozilo.getDesni_tockovi());
                    }
                }).start();
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_boxOpen_to_garageFragment);
            }

        });


        return view;
    }

}
