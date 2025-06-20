package sunrise.release.rohan.sunriseseller.Functionality;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginSession {
    private SharedPreferences prefs;

    public LoginSession(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename", "");
        return usename;
    }
    public void setstatus(String usename)
    {
        prefs.edit().putString("status", usename).commit();
    }
    public void setrole(String usename)
    {
        prefs.edit().putString("role", usename).commit();
    }
    public void seturl(String usename)
    {
        prefs.edit().putString("url", usename).commit();
    }
    public void setimei(String usename)
    {
        prefs.edit().putString("imei", usename).commit();
    }

    public String getstatus() {
        String usename = prefs.getString("status","");
        return usename;
    }
    public String getrole() {
        String usename = prefs.getString("role","");
        return usename;
    }public String geturl() {
        String usename = prefs.getString("url","");
        return usename;
    }public String imei() {
        String usename = prefs.getString("imei","");
        return usename;
    }
}
