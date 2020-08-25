package sahil.mulla.myretrofitbasicapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sahil.mulla.myretrofitbasicapplication.R;
import sahil.mulla.myretrofitbasicapplication.activities.MainActivity;
import sahil.mulla.myretrofitbasicapplication.model.User;
import sahil.mulla.myretrofitbasicapplication.services.MyInterface;


public class LoginFragment extends Fragment {
    MyInterface myInterface_login;
        Button loginBtn_op;
        EditText emailInput,passwordInput,emailOutput,passwordOutput;
        TextView registerTV_op;
    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        emailInput=view.findViewById(R.id.emailInput);
        passwordInput=view.findViewById(R.id.passwordInput);
        loginBtn_op=view.findViewById(R.id.loginBtn);
        emailOutput=view.findViewById(R.id.emailInput_L);
        passwordOutput=view.findViewById(R.id.passwordInput_L);
        loginBtn_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailOutput.getText().toString().trim();
                String password = passwordOutput.getText().toString().trim();
               loginUser(email,password);
            }
        });

        // register

        registerTV_op=view.findViewById(R.id.registerTV);
        registerTV_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Register Activity", Toast.LENGTH_SHORT).show();
                myInterface_login.register();

            }
        });

        return view;
    }

    private void loginUser(String email, String password) {

        if (email.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Email Address", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
        } else if(password.length()<6)
        {
            Toast.makeText(getContext(), "Password Must Contain 6 letter", Toast.LENGTH_SHORT).show();
        }



        else {
            Call<User> userCall = MainActivity.serviceApi.doLogin(email, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                   if(response.body().getResponse().equals("data"))
                   {    MainActivity.appPreference.setLoginStatus(true);
                       myInterface_login.login(response.body().getName(),response.body().getEmail(),response.body().getCreatedAt());
                       Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();

                   }else if(response.body().getResponse().equals("login_failed"))
                   {
                       Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                       emailOutput.setText("");
                       passwordOutput.setText("");
                   }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity)context;
        myInterface_login= (MyInterface) activity;
    }
}