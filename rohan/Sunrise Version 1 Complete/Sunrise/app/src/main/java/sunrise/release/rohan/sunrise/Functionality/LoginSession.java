package sunrise.release.rohan.sunrise.Functionality;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LoginSession {

    private SharedPreferences prefs;

    public LoginSession(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename)
    {
        prefs.edit().putString("usename", usename).commit();
    }

    public String getusename() {
        String usename = prefs.getString("usename","");
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
    public void setreferral(String usename)
    {
        prefs.edit().putString("referral", usename).commit();
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
    }public String getreferral() {
        String usename = prefs.getString("referral","");
        return usename;
    }

    public void setdeliveryaddress(String usename)
    {
        prefs.edit().putString("address", usename).commit();
    }

    public String getdeliveryaddress() {
        String usename = prefs.getString("address","");
        return usename;
    }

    public void setdeliveryname(String usename)
    {
        prefs.edit().putString("name", usename).commit();
    }

    public String getdeliveryname() {
        String usename = prefs.getString("name","");
        return usename;
    }
    public void setdeliverynumber(String usename)
    {
        prefs.edit().putString("number", usename).commit();
    }

    public String getdeliverynumber() {
        String usename = prefs.getString("number","");
        return usename;
    }
    public void setcartid(String usename)
    {
        prefs.edit().putString("cart", usename).commit();
    }

    public String getcartid() {
        String usename = prefs.getString("cart","");
        return usename;
    }


    public void setsub(String usename)
    {
        prefs.edit().putString("sub", usename).commit();
    }

    public String getsub() {
        String usename = prefs.getString("sub","");
        return usename;
    }
}