package sahil.mulla.myretrofitbasicapplication.extras;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import sahil.mulla.myretrofitbasicapplication.R;

public class AppPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public AppPreference(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(String.valueOf(R.string.s_pref_file),context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setLoginStatus(boolean status)
    {
        editor.putBoolean(String.valueOf(R.string.s_pref_login_status),status);
        editor.commit();
    }

    public boolean getLoginStatus()
    {
        return sharedPreferences.getBoolean(String.valueOf(R.string.s_pref_login_status),false);
    }

    // name

    public void setDisplayName(String name)
    {
        editor.putString(String.valueOf(R.string.s_pref_login_name),name);
        editor.commit();
    }

    public String getDisplayName()
    {
        return  sharedPreferences.getString(String.valueOf(R.string.s_pref_login_name),"Name");

    }

    //email
    public void setDisplayEmail(String email)
    {
        editor.putString(String.valueOf(R.string.s_pref_login_email),email);
        editor.commit();
    }

    public String getDisplayEmail()
    {
        return  sharedPreferences.getString(String.valueOf(R.string.s_pref_login_email),"Email");

    }
    //date
    public void setDisplayDate(String date)
    {
        editor.putString(String.valueOf(R.string.s_pref_login_date),date);
        editor.commit();
    }

    public String getDisplayDate()
    {
        return  sharedPreferences.getString(String.valueOf(R.string.s_pref_login_date),"Date");

    }

    public void showToast(String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }


}
