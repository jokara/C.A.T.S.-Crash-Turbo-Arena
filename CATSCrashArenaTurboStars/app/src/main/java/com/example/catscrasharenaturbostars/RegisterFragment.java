package com.example.catscrasharenaturbostars;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.catscrasharenaturbostars.database.MyRoomDatabase;
import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.database.entity.Vehicle;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }

    List<User> listaKorisnika;
    MyViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register, container, false);
        LinearLayout l=view.findViewById(R.id.registerLayout);
        l.setBackgroundResource(R.drawable.register);

        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                listaKorisnika = MyRoomDatabase.getDatabase(getContext()).userDao().getAllUsers();
            }
        }).start();

        final EditText ime=view.findViewById(R.id.imeKorisnika);
        final EditText prezime=view.findViewById(R.id.prezimeKorisnika);
        final EditText username=view.findViewById(R.id.korImeKorisnika);
        final EditText password=view.findViewById(R.id.loznikaKorisnika);
        Button registracija=view.findViewById(R.id.registrujSe);
        Button odustani=view.findViewById(R.id.odustani);

        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_registerFragment_to_loginFragment2);
            }
        });


        registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String i=ime.getText().toString();
                final String p=prezime.getText().toString();
                final String u=username.getText().toString();
                final String pass=password.getText().toString();
                boolean flag=false;

                if (i.equals("") || p.equals("") || u.equals("") || pass.equals("")){
                    flag=true;
                }
                /*
                for (User user: listaKorisnika) {
                    Toast.makeText(getContext(),user.getUsername(),Toast.LENGTH_SHORT).show();
                }*/

                for (User user: listaKorisnika) {
                    if (user.getUsername().equals(username.getText().toString())) {
                        flag = true;
                    }
                }

                if (flag==false) {
                    final NavController navController= Navigation.findNavController(view);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MyRoomDatabase.getDatabase(getContext()).userDao().insert(new User(i, p, u, pass, "korisnik"));
                            MyRoomDatabase.getDatabase(getContext()).vehicleDao().insert(new Vehicle(u,1,0,0,1,1,1,0,0,-1,-1,-1,-1,85,0));
                            myViewModel.postaviBrojPobeda(0+"");

                            myViewModel.postaviUlogovanog(u);

                            navController.navigate(R.id.action_registerFragment_to_loginFragment2);
                        }
                    }).start();
                    Toast.makeText(getContext(),"Uspesna registracija!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"Vec postoji username!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
