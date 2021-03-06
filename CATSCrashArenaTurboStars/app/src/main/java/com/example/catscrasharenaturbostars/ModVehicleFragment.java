package com.example.catscrasharenaturbostars;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModVehicleFragment extends Fragment {


    public ModVehicleFragment() {
        // Required empty public constructor
    }
    private int firstPointerId;
    //1 - raketa 2-busilica 3-budzovan 4- tocak+prednji 5- tocak_zadnji 6- forklift 7-testera
    private int selected=-1;

    MyViewModel myViewModel;
    List<Vehicle> svaVozila;
    Vehicle mojeVozilo=null;
    ArrayList<Vehicle> novaListaVozila=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_mod_vehicle, container, false);
        final CustomView customView = view.findViewById(R.id.custom_view);
        customView.setBackgroundResource(R.drawable.garage);
        /*****************DOHVATANJE MOG VOZILA*******************/
        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        final String ulogovan= myViewModel.getUlogovan().getValue();
        svaVozila=myViewModel.getVehicles().getValue();
        for (Vehicle v:svaVozila) {
            if (v.getUsername().equals(ulogovan)){
                mojeVozilo=v;
            }
        }
        if (mojeVozilo.getRaketa()==1){
            customView.x1.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getBusilica()==1){
            customView.x2.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getBuzdovan()==1){
            customView.x3.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getTocak_p()==1){
            customView.x4.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getTocak_z()==1){
            customView.x5.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getForklift()==1){
            customView.x6.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getTestera()==1){
            customView.x7.setBounds(0,0,0,0);
        }

        if (mojeVozilo.getLevi_deo()!=-1){
            customView.raketa.setBounds(640,482,800,692);
        }
        if (mojeVozilo.getDesni_tockovi()!=-1){
            customView.tocak_prednji.setBounds(930,625,1110,825);
        }
        if (mojeVozilo.getLevi_tockovi()!=-1){
            customView.tocak_zadnji.setBounds(625,645,815,820);
        }
        switch (mojeVozilo.getDesni_deo()){
            case 2:{
                customView.busilica.setBounds(980,495,1260,745);
                break;
            }
            case 3:{
                customView.buzdovan.setBounds(830,490,1110,740);
                break;
            }
            case 6:{
                customView.forklift.setBounds(830,490,1110,740);
                break;
            }
            case 7:{
                customView.testera.setBounds(980,495,1260,745);
                break;
            }
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

        customView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int indexOfActionPointer = event.getActionIndex();
                int idOfActionPointer = event.getPointerId(indexOfActionPointer);

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        customView.selektovano1.setBounds(0,0,0,0);
                        customView.selektovano2.setBounds(0,0,0,0);
                        customView.selektovano3.setBounds(0,0,0,0);
                        customView.selektovano4.setBounds(0,0,0,0);
                        customView.selektovano5.setBounds(0,0,0,0);
                        customView.selektovano6.setBounds(0,0,0,0);
                        customView.selektovano7.setBounds(0,0,0,0);
                        customView.raketa_atribut.setBounds(0,0,0,0);
                        customView.busilica_atribut.setBounds(0,0,0,0);
                        customView.buzdovan_atribut.setBounds(0,0,0,0);
                        customView.tocak_p_atribut.setBounds(0,0,0,0);
                        customView.tocak_z_atribut.setBounds(0,0,0,0);
                        customView.forklift_atribut.setBounds(0,0,0,0);
                        customView.testera_atribut.setBounds(0,0,0,0);
                        if(event.getX(event.getActionIndex())>=0 && event.getX(event.getActionIndex())<=150 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<200 && mojeVozilo.getRaketa()==1){
                            if (mojeVozilo.getLevi_deo()==-1){
                                Toast.makeText(getContext(),"Raketa",Toast.LENGTH_SHORT).show();
                                customView.selektovano1.setBounds(0,0,150,200);
                                customView.raketa_atribut.setBounds(0,210,150,410);
                                selected=1;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=220 && event.getX(event.getActionIndex())<=500 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<250 && mojeVozilo.getBusilica()==1){
                            if (mojeVozilo.getDesni_deo()==-1){
                                Toast.makeText(getContext(),"Busilica",Toast.LENGTH_SHORT).show();
                                customView.selektovano2.setBounds(220,0,500,200);
                                customView.busilica_atribut.setBounds(220,210,500,410);
                                selected=2;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=520 && event.getX(event.getActionIndex())<=800 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<200 && mojeVozilo.getBuzdovan()==1){
                            if (mojeVozilo.getDesni_deo()==-1){
                                Toast.makeText(getContext(),"Buzdovan",Toast.LENGTH_SHORT).show();
                                customView.selektovano3.setBounds(520,0,800,200);
                                customView.buzdovan_atribut.setBounds(520,210,750,410);
                                selected=3;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=820 && event.getX(event.getActionIndex())<=1000 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<200 && mojeVozilo.getTocak_p()==1){
                            if (mojeVozilo.getDesni_tockovi()==-1){
                                Toast.makeText(getContext(),"Tocak prednji",Toast.LENGTH_SHORT).show();
                                customView.selektovano4.setBounds(820,0,1000,200);
                                customView.tocak_p_atribut.setBounds(820,210,1000,410);
                                selected=4;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=1020 && event.getX(event.getActionIndex())<=1200 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<200 && mojeVozilo.getTocak_z()==1){
                            if (mojeVozilo.getLevi_tockovi()==-1){
                                Toast.makeText(getContext(),"Tocak zadnji",Toast.LENGTH_SHORT).show();
                                customView.selektovano5.setBounds(1020,0,1200,200);
                                customView.tocak_z_atribut.setBounds(1020,210,1200,410);
                                selected=5;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=1220 && event.getX(event.getActionIndex())<=1500 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<250 && mojeVozilo.getForklift()==1){
                            if (mojeVozilo.getDesni_deo()==-1){
                                Toast.makeText(getContext(),"Forklift",Toast.LENGTH_SHORT).show();
                                customView.selektovano6.setBounds(1220,0,1500,200);
                                customView.forklift_atribut.setBounds(1220,210,1500,410);
                                selected=6;
                            }
                        }
                        if(event.getX(event.getActionIndex())>=1520 && event.getX(event.getActionIndex())<=1760 && event.getY(event.getActionIndex())>0 && event.getY(event.getActionIndex())<250 && mojeVozilo.getTestera()==1){
                            if (mojeVozilo.getDesni_deo()==-1){
                                Toast.makeText(getContext(),"Testera",Toast.LENGTH_SHORT).show();
                                customView.selektovano7.setBounds(1520,0,1800,200);
                                customView.testera_atribut.setBounds(1520,210,1800,410);
                                selected=7;
                            }
                        }
                        if (event.getX(event.getActionIndex())>690 && (event.getX(event.getActionIndex())< 745) && (event.getY(event.getActionIndex())<630) && (event.getX(event.getActionIndex())>570)){
                            customView.raketa.setBounds(0,0,150,200);
                            mojeVozilo.setLevi_deo(-1);
                            selected=-1;
                        }
                        if (event.getX(event.getActionIndex())>1010 && (event.getX(event.getActionIndex())< 1050) && (event.getY(event.getActionIndex())<750) && (event.getX(event.getActionIndex())>700)){
                            customView.tocak_prednji.setBounds(820,0,1000,200);
                            mojeVozilo.setDesni_tockovi(-1);
                            selected=-1;
                        }
                        if (event.getX(event.getActionIndex())>700 && (event.getX(event.getActionIndex())< 740) && (event.getY(event.getActionIndex())<750) && (event.getX(event.getActionIndex())>700)){
                            customView.tocak_zadnji.setBounds(1020,20,1200,200);
                            mojeVozilo.setLevi_tockovi(-1);
                            selected=-1;
                        }
                        if (event.getX(event.getActionIndex())>1000 && (event.getX(event.getActionIndex())< 1100) && (event.getY(event.getActionIndex())<660) && (event.getX(event.getActionIndex())>590)){
                            customView.busilica.setBounds(220,0,500,250);
                            customView.buzdovan.setBounds(520,0,800,200);
                            customView.forklift.setBounds(1220,0,1500,250);
                            customView.testera.setBounds(1520,0,1760,200);
                            mojeVozilo.setDesni_deo(-1);
                            selected=-1;
                        }
                        firstPointerId = idOfActionPointer;

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (selected==1){
                            customView.raketa.setBounds((int)(event.getX(event.getActionIndex())-75),(int)(event.getY(event.getActionIndex())-100),(int)(event.getX(event.getActionIndex())+75),(int)(event.getY(event.getActionIndex())+100));
                        }
                        if (selected==2){
                            customView.busilica.setBounds((int)(event.getX(event.getActionIndex())-140),(int)(event.getY(event.getActionIndex())-125),(int)(event.getX(event.getActionIndex())+140),(int)(event.getY(event.getActionIndex())+125));
                        }
                        if (selected==3){
                            customView.buzdovan.setBounds((int)(event.getX(event.getActionIndex())-140),(int)(event.getY(event.getActionIndex())-100),(int)(event.getX(event.getActionIndex())+100),(int)(event.getY(event.getActionIndex())+125));
                        }
                        if (selected==4){
                            customView.tocak_prednji.setBounds((int)(event.getX(event.getActionIndex())-90),(int)(event.getY(event.getActionIndex())-100),(int)(event.getX(event.getActionIndex())+100),(int)(event.getY(event.getActionIndex())+90));
                        }
                        if (selected==5){
                            customView.tocak_zadnji.setBounds((int)(event.getX(event.getActionIndex())-90),(int)(event.getY(event.getActionIndex())-100),(int)(event.getX(event.getActionIndex())+100),(int)(event.getY(event.getActionIndex())+90));
                        }
                        if (selected==6){
                            customView.forklift.setBounds((int)(event.getX(event.getActionIndex())-140),(int)(event.getY(event.getActionIndex())-125),(int)(event.getX(event.getActionIndex())+125),(int)(event.getY(event.getActionIndex())+140));
                        }
                        if (selected==7){
                            customView.testera.setBounds((int)(event.getX(event.getActionIndex())-120),(int)(event.getY(event.getActionIndex())-125),(int)(event.getX(event.getActionIndex())+125),(int)(event.getY(event.getActionIndex())+120));
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_UP:
                        if (selected==1){
                            if (event.getX(event.getActionIndex())>690 && (event.getX(event.getActionIndex())< 745) && (event.getY(event.getActionIndex())<630) && (event.getX(event.getActionIndex())>570)){
                                customView.raketa.setBounds(640,482,800,692);
                                mojeVozilo.setLevi_deo(1);
                                customView.selektovano1.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.raketa.setBounds(0,0,150,200);
                            }
                        }
                        if (selected==4){
                            if (event.getX(event.getActionIndex())>1010 && (event.getX(event.getActionIndex())< 1050) && (event.getY(event.getActionIndex())<750) && (event.getX(event.getActionIndex())>700)){
                                customView.tocak_prednji.setBounds(930,625,1110,825);
                                mojeVozilo.setDesni_tockovi(4);
                                customView.selektovano4.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.tocak_prednji.setBounds(820,0,1000,200);
                            }
                        }
                        if (selected==5){
                            if (event.getX(event.getActionIndex())>700 && (event.getX(event.getActionIndex())< 740) && (event.getY(event.getActionIndex())<750) && (event.getX(event.getActionIndex())>700)){
                                customView.tocak_zadnji.setBounds(625,645,815,820);
                                mojeVozilo.setLevi_tockovi(5);
                                customView.selektovano5.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.tocak_zadnji.setBounds(1020,20,1200,200);
                            }
                        }
                        if (selected==2){
                            if (event.getX(event.getActionIndex())>1000 && (event.getX(event.getActionIndex())< 1100) && (event.getY(event.getActionIndex())<660) && (event.getX(event.getActionIndex())>590)){
                                customView.busilica.setBounds(980,495,1260,745);
                                mojeVozilo.setDesni_deo(2);
                                customView.selektovano2.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.busilica.setBounds(220,0,500,250);
                            }
                        }
                        if (selected==7){
                            if (event.getX(event.getActionIndex())>1000 && (event.getX(event.getActionIndex())< 1100) && (event.getY(event.getActionIndex())<660) && (event.getX(event.getActionIndex())>590)){
                                customView.testera.setBounds(980,495,1260,745);
                                mojeVozilo.setDesni_deo(7);
                                customView.selektovano7.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.testera.setBounds(1520,0,1760,200);
                            }
                        }
                        if (selected==3){
                            if (event.getX(event.getActionIndex())>1000 && (event.getX(event.getActionIndex())< 1100) && (event.getY(event.getActionIndex())<660) && (event.getX(event.getActionIndex())>590)){
                                customView.buzdovan.setBounds(830,490,1110,740);
                                mojeVozilo.setDesni_deo(3);
                                customView.selektovano3.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.buzdovan.setBounds(520,0,800,200);
                            }
                        }
                        if (selected==6){
                            if (event.getX(event.getActionIndex())>1000 && (event.getX(event.getActionIndex())< 1100) && (event.getY(event.getActionIndex())<660) && (event.getX(event.getActionIndex())>590)){
                                customView.forklift.setBounds(830,490,1110,740);
                                mojeVozilo.setDesni_deo(6);
                                customView.selektovano6.setBounds(0,0,0,0);
                                selected=-1;
                            }
                            else{
                                customView.forklift.setBounds(1220,0,1500,250);
                            }
                        }
                        break;
                }

                customView.invalidate();

                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }

                return ModVehicleFragment.super.getActivity().onTouchEvent(event);
            }
        });

        ImageView ok_out=view.findViewById(R.id.ok_back_to_main_page);
        ok_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Vehicle v:svaVozila) {
                    if (!v.getUsername().equals(ulogovan)){
                        novaListaVozila.add(v);
                    }
                }
                novaListaVozila.add(mojeVozilo);
                myViewModel.postaviListuVozila(novaListaVozila);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MyRoomDatabase.getDatabase(getContext()).vehicleDao().updateVehicle(ulogovan,mojeVozilo.getRaketa(),mojeVozilo.getBusilica(),mojeVozilo.getBuzdovan(),mojeVozilo.getTocak_p(),mojeVozilo.getTocak_z(),mojeVozilo.getForklift(),mojeVozilo.getTestera(),mojeVozilo.getLevi_deo(),mojeVozilo.getDesni_deo(),mojeVozilo.getLevi_tockovi(),mojeVozilo.getDesni_tockovi());
                    }
                }).start();

                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_modVehicleFragment_to_garageFragment);


            }
        });




        return view;
    }



}
