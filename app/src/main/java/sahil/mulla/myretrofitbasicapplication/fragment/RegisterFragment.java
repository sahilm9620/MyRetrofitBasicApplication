package sahil.mulla.myretrofitbasicapplication.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sahil.mulla.myretrofitbasicapplication.R;
import sahil.mulla.myretrofitbasicapplication.activities.MainActivity;
import sahil.mulla.myretrofitbasicapplication.extras.AppPreference;
import sahil.mulla.myretrofitbasicapplication.model.User;


public class RegisterFragment extends Fragment {

    EditText nameInput_reg, emailInput_reg, phoneInput_reg, passInput_reg;
    Button btn_reg;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        nameInput_reg = view.findViewById(R.id.nameInput);
        emailInput_reg = view.findViewById(R.id.emailInput);
        phoneInput_reg = view.findViewById(R.id.phoneInput);
        passInput_reg = view.findViewById(R.id.passwordInput);
        btn_reg = view.findViewById(R.id.regBtn);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });
        return view;

    }

    private void registerUser() {
        String name = nameInput_reg.getText().toString().trim();
        String email = emailInput_reg.getText().toString().trim();
        String phone = phoneInput_reg.getText().toString().trim();
        String password = passInput_reg.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            MainActivity.appPreference.showToast("please enter your name");
        } else if (TextUtils.isEmpty(password)) {
            MainActivity.appPreference.showToast("please enter your password");
        } else if (TextUtils.isEmpty(email)) {
            MainActivity.appPreference.showToast("please enter your email");
        } else if (TextUtils.isEmpty(phone)) {
            MainActivity.appPreference.showToast("please enter your phone");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            MainActivity.appPreference.showToast("Your email is invalid");
        } else if (password.length() < 6) {
            MainActivity.appPreference.showToast("Password Must Contain 6 letter");
        } else {
            // data on server
            Call<User> userCall = MainActivity.serviceApi.doRegisteration(name, email, phone, password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    //Log.i("my response ",response.body().getResponse());
                    //  System.out.println("My Response" + response.body().getResponse());
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    if (response.body().getResponse().matches("inserted")) {
                        showMsg("sucessfully Registered", "Welcome " + name);
                    } else {
                        showMsg("Already Registered", "This email ID is already exits " + email);
                    }

                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    //  Toast.makeText(getContext(), "Error" + t, Toast.LENGTH_SHORT).show();
                    showMsg("Server-Error", "Please Try Again ");
                }
            });
        }
    }

    private void showMsg(String success, String o) {


//            final AlertDialog.Builder builder_1 = new AlertDialog.Builder(getContext());
//            builder_1.setTitle(success);
//            builder_1.setMessage(o);
//
//            builder_1.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    clearText();
////                    Intent intent_my = new Intent(RegisterPage.this, LoginPage.class);
////                    startActivity(intent_my);
//                    builder_1.setCancelable(true);
//                }
//            });
//
//            builder_1.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(success);
        builder.setMessage(o);
        builder.setCancelable(false);
        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                clearText();
                builder.setCancelable(true);
            }
        });
        builder.show();


    }

    private void clearText() {
        nameInput_reg.setText("");
        emailInput_reg.setText("");
        phoneInput_reg.setText("");
        passInput_reg.setText("");
    }

}