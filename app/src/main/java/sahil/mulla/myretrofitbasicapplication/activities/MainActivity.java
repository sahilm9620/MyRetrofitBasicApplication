package sahil.mulla.myretrofitbasicapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import sahil.mulla.myretrofitbasicapplication.R;
import sahil.mulla.myretrofitbasicapplication.constants.Constant;
import sahil.mulla.myretrofitbasicapplication.extras.AppPreference;
import sahil.mulla.myretrofitbasicapplication.fragment.LoginFragment;
import sahil.mulla.myretrofitbasicapplication.fragment.ProfileFragment;
import sahil.mulla.myretrofitbasicapplication.fragment.RegisterFragment;
import sahil.mulla.myretrofitbasicapplication.services.MyInterface;
import sahil.mulla.myretrofitbasicapplication.services.RetrofitClient;
import sahil.mulla.myretrofitbasicapplication.services.ServiceApi;

public class MainActivity extends AppCompatActivity implements MyInterface {

    FrameLayout container_fragment;
    public static AppPreference appPreference;
    public static ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment = findViewById(R.id.fragment_container);
        appPreference = new AppPreference(this);

        serviceApi = RetrofitClient.getApiCient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        if(container_fragment!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            if(appPreference.getLoginStatus())
            {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new ProfileFragment())
                        .commit();
            }else
            {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, new LoginFragment())
                        .commit();
            }
        }


    }

    @Override
    public void register() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new RegisterFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void login(String name, String email, String created_at) {
        appPreference.setDisplayName(name);
        appPreference.setDisplayEmail(email);
        appPreference.setDisplayDate(created_at);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ProfileFragment())

                .commit();


    }

    @Override
    public void logout() {
        appPreference.setLoginStatus(false);
        appPreference.setDisplayEmail("Email");
        appPreference.setDisplayName("Name");
        appPreference.setDisplayDate("Date");

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())

                .commit();

    }


}