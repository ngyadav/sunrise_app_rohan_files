package rohan.groups.sunrise.Functionality;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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



    public void setpp(String usename)
    {
        prefs.edit().putString("pp", usename).commit();
    }

    public String getpp() {
        String usename = prefs.getString("pp","");
        return usename;
    }


    public void setdaname(String username) {
        prefs.edit().putString("daname", username).commit();
    }

    public String getdaname() {
        String username = prefs.getString("daname", "");
        return username;
    }


    public void setdaaddress(String username) {
        prefs.edit().putString("daaddress", username).commit();
    }

    public String getdaaddress() {
        String username = prefs.getString("daaddress", "");
        return username;
    }

    public void setdaf(String username) {
        prefs.edit().putString("daf", username).commit();
    }

    public String getdaf() {
        String username = prefs.getString("daf", "");
        return username;
    }


    public void setdal(String username) {
        prefs.edit().putString("dal", username).commit();
    }

    public String getdal() {
        String username = prefs.getString("dal", "");
        return username;
    }

    public void setdaloc(String username) {
        prefs.edit().putString("daloc", username).commit();
    }

    public String getdaloc() {
        String username = prefs.getString("daloc", "");
        return username;
    }

    public void setdadist(String username) {
        prefs.edit().putString("dadist", username).commit();
    }

    public String getdadist() {
        String username = prefs.getString("dadist", "");
        return username;
    }


    public void setisfirsttime(String username) {
        prefs.edit().putString("first", username).commit();
    }

    public String getisfirsttime() {
        String username = prefs.getString("first", "");
        return username;
    }


    public void setcart(String username) {
        prefs.edit().putString("cart", username).commit();
    }

    public String getcart() {
        String username = prefs.getString("cart", "");
        return username;
    }



    public void settoken(String username) {
        prefs.edit().putString("token", username).commit();
    }

    public String gettoken() {
        String username = prefs.getString("token", "");
        return username;
    }

    public void setsub(String username) {
        prefs.edit().putString("sub", username).commit();
    }

    public String getsub() {
        String username = prefs.getString("sub", "");
        return username;
    }



    public void setrange(String username) {
        prefs.edit().putString("range", username).commit();
    }

    public String getrange() {
        String username = prefs.getString("range", "");
        return username;
    }



    public void setpincode(String username) {
        prefs.edit().putString("pincode", username).commit();
    }

    public String getpincode() {
        String username = prefs.getString("pincode", "");
        return username;
    }


    public void setnumber(String username) {
        prefs.edit().putString("setnumber", username).commit();
    }

    public String getnumber() {
        String username = prefs.getString("setnumber", "");
        return username;
    }



    public void setextras(String username) {
        prefs.edit().putString("esetextras", username).commit();
    }

    public String getextras() {
        String username = prefs.getString("esetextras", "");
        return username;
    }




    public void setcartitem(String username) {
        prefs.edit().putString("cartitem", username).commit();
    }

    public String getcartitem() {
        String username = prefs.getString("cartitem", "");
        return username;
    }

    public void setcarttotal(String username) {
        prefs.edit().putString("carttotal", username).commit();
    }

    public String getcarttotal() {
        String username = prefs.getString("carttotal", "");
        return username;
    }

    public void setreferral(String username) {
        prefs.edit().putString("referral", username).commit();
    }

    public String getreferral() {
        String username = prefs.getString("referral", "");
        return username;
    }

    public void setitemname(ArrayList<String> list, String key){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getitemname(String key){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void setitemprice(ArrayList<String> list, String key){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getitemprice(String key){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }


    public void setitemqty(ArrayList<String> list, String key){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public ArrayList<String> getitemqty(String key){
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }





}
