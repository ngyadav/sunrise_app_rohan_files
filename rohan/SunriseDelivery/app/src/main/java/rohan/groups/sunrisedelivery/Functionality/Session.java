package rohan.groups.sunrisedelivery.Functionality;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusername(String usename)
    {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusername() {
        String usename = prefs.getString("usename","");
        return usename;
    }


    public void setrole(String usename)
    {
        prefs.edit().putString("role", usename).commit();
    }

    public String getrole() {
        String usename = prefs.getString("role","");
        return usename;
    }


    public void setname(String usename)
    {
        prefs.edit().putString("name", usename).commit();
    }

    public String getname() {
        String usename = prefs.getString("name","");
        return usename;
    }


    public void setpassword(String usename)
    {
        prefs.edit().putString("pass", usename).commit();
    }

    public String getpassword() {
        String usename = prefs.getString("pass","");
        return usename;
    }



    public void setverified(String usename)
    {
        prefs.edit().putString("verified", usename).commit();
    }

    public String getverified() {
        String usename = prefs.getString("verified","");
        return usename;
    }


    public void setpp(String usename)
    {
        prefs.edit().putString("pp", usename).commit();
    }

    public String getpp() {
        String usename = prefs.getString("pp","");
        return usename;
    }







}
