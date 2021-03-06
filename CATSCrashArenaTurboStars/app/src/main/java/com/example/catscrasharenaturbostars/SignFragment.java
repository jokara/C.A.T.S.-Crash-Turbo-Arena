package com.example.catscrasharenaturbostars;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catscrasharenaturbostars.database.entity.User;
import com.example.catscrasharenaturbostars.view_model.MyViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignFragment extends Fragment {


    public SignFragment() {
        // Required empty public constructor
    }

    MyViewModel myViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign, container, false);
        LinearLayout l=view.findViewById(R.id.signLayout);
        l.setBackgroundResource(R.drawable.sign_in);

        Button odustani=view.findViewById(R.id.odustani);
        Button ulogujse=view.findViewById(R.id.signNastaviIgru);
        final EditText signLozinka=view.findViewById(R.id.signLozinka);

        myViewModel= ViewModelProviders.of(getActivity()).get(MyViewModel.class);


        //ispis neki radi provere
        ArrayList<String> usernameList=myViewModel.getUserUsername().getValue();
        /*for (String s:usernameList) {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        }*/

        Spinner spinner=(Spinner) view.findViewById(R.id.stariKorisniciSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,usernameList);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s=adapterView.getItemAtPosition(i).toString();
                myViewModel.postaviIme(s);
                Log.d("ODABRAN",s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ulogujse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=myViewModel.getIzabranoIme().getValue();
                String lozinka=signLozinka.getText().toString();
                ArrayList<User> korisnici=myViewModel.getUsers().getValue();
                boolean flag=false;
                int brojPobeda=0;
                for (User u: korisnici) {
                    if (u.getUsername().equals(username) && lozinka.equals(u.getPassword())){
                        flag=true;
                        brojPobeda=u.getWinStrike();
                    }
                }
                if (flag){
                    myViewModel.postaviUlogovanog(username);
                    myViewModel.postaviBrojPobeda(brojPobeda+"");
                    NavController navController= Navigation.findNavController(view);
                    navController.navigate(R.id.action_signFragment_to_garageFragment);
                }
                else{
                    Toast.makeText(getContext(),"Pogresna loznika!",Toast.LENGTH_SHORT).show();
                }
            }
        });


        odustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController= Navigation.findNavController(view);
                navController.navigate(R.id.action_signFragment_to_loginFragment2);
            }
        });





        return view;
    }


}
