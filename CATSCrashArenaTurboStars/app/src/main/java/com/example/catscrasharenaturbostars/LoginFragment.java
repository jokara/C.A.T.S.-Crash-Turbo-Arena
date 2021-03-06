package com.example.catscrasharenaturbostars;


import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.Box;
import com.example.catscrasharenaturbostars.database.entity.FightsView;
import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    Context context=null;

    public LoginFragment() {
        // Required empty public constructor
    }

    MyViewModel viewModel;
    List<User> listaKorisnika;
    List<Vehicle> listaVozila;
    List<Box> listaKutija;
    List<FightsView> listaBorbi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        context=getActivity();
        View v=inflater.inflate(R.layout.fragment_login, container, false);
        //getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        LinearLayout l=v.findViewById(R.id.loginFragment2);
        l.setBackgroundResource(R.drawable.background_picture);

        viewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);



        //dodati sve korisnike u view model
        new Thread(new Runnable() {
            @Override
            public void run() {
                listaKorisnika = MyRoomDatabase.getDatabase(getContext()).userDao().getAllUsers();
                for (User u: listaKorisnika) {
                    viewModel.addUser(u);
                    viewModel.addUserUsername(u.getUsername());
                }
                listaVozila= MyRoomDatabase.getDatabase(getContext()).vehicleDao().getAllVehichles();
                for (Vehicle v:listaVozila){
                    viewModel.addVehicle(v);
                }
                listaKutija= MyRoomDatabase.getDatabase(getContext()).boxDao().getAllBoxes();
                for (Box b:listaKutija){
                    viewModel.addBox(b);
                }
                listaBorbi= MyRoomDatabase.getDatabase(getContext()).fightsViewDao().getAllFights();
                for (FightsView f:listaBorbi){
                    viewModel.addFightView(f);
                }
            }
        }).start();



        Button noviKorisnik=v.findViewById(R.id.noviKorisnik);
        Button stariKorisnik=v.findViewById(R.id.postojeciKorisnik);


        noviKorisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment2_to_registerFragment);
            }
        });

        stariKorisnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment2_to_signFragment);
            }
        });

        return v;
    }

}
