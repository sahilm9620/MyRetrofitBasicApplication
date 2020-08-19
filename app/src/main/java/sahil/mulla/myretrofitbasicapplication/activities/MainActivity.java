package sahil.mulla.myretrofitbasicapplication.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import sahil.mulla.myretrofitbasicapplication.R;
import sahil.mulla.myretrofitbasicapplication.fragment.LoginFragment;

public class MainActivity extends AppCompatActivity {
FrameLayout container_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container_fragment=findViewById(R.id.fragment_container);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new LoginFragment())
                .commit();



    }
}