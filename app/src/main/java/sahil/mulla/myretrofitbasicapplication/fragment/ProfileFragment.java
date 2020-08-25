package sahil.mulla.myretrofitbasicapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import sahil.mulla.myretrofitbasicapplication.R;
import sahil.mulla.myretrofitbasicapplication.activities.MainActivity;
import sahil.mulla.myretrofitbasicapplication.services.MyInterface;


public class ProfileFragment extends Fragment {
TextView name,email;
Button logout_btn;
MyInterface myInterface_profile;



    public ProfileFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        logout_btn=view.findViewById(R.id.logoutBtn);
        String name_str="Hi " + MainActivity.appPreference.getDisplayName();
        String email_str=" Registerd on : " + MainActivity.appPreference.getDisplayEmail() + "  Since : "+ MainActivity.appPreference.getDisplayDate();
        System.out.println(name_str);
        System.out.println(email_str);
        name.setText(name_str);
        email.setText( email_str);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myInterface_profile.logout();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity)context;
        myInterface_profile= (MyInterface) activity;
    }
}