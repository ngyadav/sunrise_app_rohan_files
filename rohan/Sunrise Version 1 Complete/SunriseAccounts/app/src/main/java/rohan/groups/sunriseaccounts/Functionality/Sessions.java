package rohan.groups.sunriseaccounts.Functionality;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class Sessions {

    private SharedPreferences prefs;

    public Sessions(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusername(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getusername() {
        String username = prefs.getString("username", "");
        return username;
    }



    public void setrole(String username) {
        prefs.edit().putString("role", username).commit();
    }

    public String getrole() {
        String username = prefs.getString("role", "");
        return username;
    }

    public String getname() {
        String username = prefs.getString("name", "");
        return username;
    }

    public void setname(String username) {
        prefs.edit().putString("name", username).commit();
    }


}
