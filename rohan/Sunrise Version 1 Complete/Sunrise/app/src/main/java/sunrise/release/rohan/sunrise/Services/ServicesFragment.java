package sunrise.release.rohan.sunrise.Services;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServicesFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    List<String> areas = new ArrayList<String>();
    Spinner spinner;
    private LoginSession session;
    Button submit;
    TextView label1,label2,label3,label4,label5,label6,label7;
    EditText text1,text2,text3,text4,text5,text6,text7;
    CheckBox issue1,issue2,issue3,issue4,issue5,issue6;
    String issue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_services, container, false);
        spinner = (Spinner) v.findViewById(R.id.sspinner);
        submit =(Button)  v.findViewById(R.id.ssubmit);
        label1=(TextView)v.findViewById(R.id.label1);
        label2=(TextView)v.findViewById(R.id.label2);
        label3=(TextView)v.findViewById(R.id.label3);
        label4=(TextView)v.findViewById(R.id.label4);
        label5=(TextView)v.findViewById(R.id.label5);
        label6=(TextView)v.findViewById(R.id.label6);
        label7=(TextView)v.findViewById(R.id.label7);
        text1=(EditText)v.findViewById(R.id.text1);
        text2=(EditText)v.findViewById(R.id.text2);
        text3=(EditText)v.findViewById(R.id.text3);
        text4=(EditText)v.findViewById(R.id.text4);
        text5=(EditText)v.findViewById(R.id.text5);
        text6=(EditText)v.findViewById(R.id.text6);
        text7=(EditText)v.findViewById(R.id.text7);
        issue1=(CheckBox)v.findViewById(R.id.issue1);
        issue2=(CheckBox)v.findViewById(R.id.issue2);
        issue3=(CheckBox)v.findViewById(R.id.issue3);
        issue4=(CheckBox)v.findViewById(R.id.issue4);
        issue5=(CheckBox)v.findViewById(R.id.issue5);
        issue6=(CheckBox)v.findViewById(R.id.issue6);

        areas.add("DTH");
        areas.add("MOBILE");
        areas.add("APPLIANCES");
        areas.add("AUTO CARE");





        ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, areas);
        areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(areasAdapter);

        label6.setVisibility(View.GONE);
        label7.setVisibility(View.GONE);
        text6.setVisibility(View.GONE);
        text7.setVisibility(View.GONE);
        issue6.setVisibility(View.GONE);


        label1.setText("Company:");
        label2.setText("ID/CARD NO:");
        label3.setText("Regitered Mobile No:");
        label4.setText("Alternate Mobile No:");
        label5.setText("Address:");
        text3.setInputType(InputType.TYPE_CLASS_PHONE);
        text4.setInputType(InputType.TYPE_CLASS_PHONE);
        issue1.setText("No Signal");
        issue2.setText("Power Issue");
        issue3.setText("Image Distrubance");
        issue4.setText("New Connection");
        issue5.setText("Others");
        spinner.setOnItemSelectedListener(this);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItem().equals("DTH")) {
                    if (label1.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Company", Toast.LENGTH_SHORT).show();
                        label1.requestFocus();
                        return;
                    }
                    if (label2.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter ID", Toast.LENGTH_SHORT).show();
                        label2.requestFocus();
                        return;
                    }
                    if (label3.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Register Mobile No", Toast.LENGTH_SHORT).show();
                        label3.requestFocus();
                        return;
                    }
                    if (label5.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                        label5.requestFocus();
                        return;
                    }

                    if(issue1.isChecked()||issue2.isChecked()||issue3.isChecked()||issue4.isChecked()||issue5.isChecked())
                    {
                        issue="";
                        if(issue1.isChecked())
                            issue+=issue1.getText()+",";
                        if(issue2.isChecked())
                            issue+=issue2.getText()+",";
                        if(issue3.isChecked())
                            issue+=issue3.getText()+",";
                        if(issue4.isChecked())
                            issue+=issue4.getText()+",";
                        if(issue5.isChecked())
                            issue+=issue5.getText()+",";
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Please select atleast one issue",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    session = new LoginSession(getContext());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // get total available quest
                            int size = (int) dataSnapshot.getChildrenCount();
                            size++;
                            String abc = "SACK" + Integer.toString(size);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services").push();
                            ref.child("Pushid").setValue(ref.getKey().toString());
                            ref.child("Company").setValue(text1.getText().toString());
                            ref.child("IDCardnumber").setValue(text2.getText().toString());
                            ref.child("MobileNumber").setValue(text3.getText().toString());
                            ref.child("AlternateNumber").setValue(text4.getText().toString());
                            ref.child("Address").setValue(text5.getText().toString());
                            ref.child("ServiceAknowledgment").setValue(abc);
                            ref.child("Userid").setValue("+91" + session.getusename());
                            ref.child("Type").setValue(spinner.getSelectedItem().toString());
                            ref.child("Issue").setValue(issue);
                            Date currentDate = new Date(System.currentTimeMillis());
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String date1 = df.format(currentDate);
                            ref.child("Date").setValue(date1);
                            text1.setText("");
                            text2.setText("");
                            text3.setText("");
                            text4.setText("");
                            text5.setText("");
                            issue1.setChecked(false);
                            issue2.setChecked(false);
                            issue3.setChecked(false);
                            issue4.setChecked(false);
                            issue5.setChecked(false);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Sucessful");

                            builder.setMessage("Your Acknowledgment Number is " + abc)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // TODO: handle the OK
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }


            if (spinner.getSelectedItem().equals("MOBILE")) {
                if (label1.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter Company", Toast.LENGTH_SHORT).show();
                    label1.requestFocus();
                    return;
                }
                if (label2.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter Model", Toast.LENGTH_SHORT).show();
                    label2.requestFocus();
                    return;
                }if (label3.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter Colour", Toast.LENGTH_SHORT).show();
                    label3.requestFocus();
                    return;
                }
                if (label4.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter Register Mobile No", Toast.LENGTH_SHORT).show();
                    label4.requestFocus();
                    return;
                }
                if (label6.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter IMEI Number", Toast.LENGTH_SHORT).show();
                    label6.requestFocus();
                    return;
                }if (label7.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(getContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                    label7.requestFocus();
                    return;
                }

                if(issue1.isChecked()||issue2.isChecked()||issue3.isChecked()||issue4.isChecked()||issue5.isChecked())
                {
                    issue="";
                    if(issue1.isChecked())
                        issue+=issue1.getText()+",";
                    if(issue2.isChecked())
                        issue+=issue2.getText()+",";
                    if(issue3.isChecked())
                        issue+=issue3.getText()+",";
                    if(issue4.isChecked())
                        issue+=issue4.getText()+",";
                    if(issue5.isChecked())
                        issue+=issue5.getText()+",";
                }
                else
                {
                    Toast.makeText(getContext(),"Please select atleast one issue",Toast.LENGTH_SHORT).show();
                    return;
                }
                session = new LoginSession(getContext());
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available quest
                        int size = (int) dataSnapshot.getChildrenCount();
                        size++;
                        String abc = "SACK" + Integer.toString(size);
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services").push();
                        ref.child("Pushid").setValue(ref.getKey().toString());
                        ref.child("Company").setValue(text1.getText().toString());
                        ref.child("ModelNumber").setValue(text2.getText().toString());
                        ref.child("Colour").setValue(text3.getText().toString());
                        ref.child("MobileNumber").setValue(text4.getText().toString());
                        ref.child("AlternateNumber").setValue(text5.getText().toString());
                        ref.child("IMEI").setValue(text6.getText().toString());
                        ref.child("Address").setValue(text7.getText().toString());
                        ref.child("ServiceAknowledgment").setValue(abc);
                        ref.child("Userid").setValue("+91" + session.getusename());
                        ref.child("Type").setValue(spinner.getSelectedItem().toString());
                        ref.child("Issue").setValue(issue);
                        Date currentDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String date1 = df.format(currentDate);
                        ref.child("Date").setValue(date1);
                        text1.setText("");
                        text2.setText("");
                        text3.setText("");
                        text4.setText("");
                        text5.setText("");
                        text6.setText("");
                        text7.setText("");
                        issue1.setChecked(false);
                        issue2.setChecked(false);
                        issue3.setChecked(false);
                        issue4.setChecked(false);
                        issue5.setChecked(false);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Sucessful");

                        builder.setMessage("Your Acknowledgment Number is " + abc)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // TODO: handle the OK
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

                if (spinner.getSelectedItem().equals("APPLIANCES")) {
                    if (label1.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Company", Toast.LENGTH_SHORT).show();
                        label1.requestFocus();
                        return;
                    }
                    if (label2.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Product", Toast.LENGTH_SHORT).show();
                        label2.requestFocus();
                        return;
                    }if (label3.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Model Number", Toast.LENGTH_SHORT).show();
                        label3.requestFocus();
                        return;
                    }
                    if (label4.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Register Mobile No", Toast.LENGTH_SHORT).show();
                        label4.requestFocus();
                        return;
                    }
                    if (label6.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                        label6.requestFocus();
                        return;
                    }

                    if(issue1.isChecked()||issue2.isChecked())
                    {
                        issue="";
                        if(issue1.isChecked())
                            issue+=issue1.getText()+",";
                        if(issue2.isChecked())
                            issue+=issue2.getText()+",";
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Please select atleast one issue",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    session = new LoginSession(getContext());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // get total available quest
                            int size = (int) dataSnapshot.getChildrenCount();
                            size++;
                            String abc = "SACK" + Integer.toString(size);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services").push();
                            ref.child("Pushid").setValue(ref.getKey().toString());
                            ref.child("Company").setValue(text1.getText().toString());
                            ref.child("Product").setValue(text2.getText().toString());
                            ref.child("ModelNumber").setValue(text3.getText().toString());
                            ref.child("MobileNumber").setValue(text4.getText().toString());
                            ref.child("AlternateNumber").setValue(text5.getText().toString());
                            ref.child("Address").setValue(text6.getText().toString());
                            ref.child("ServiceAknowledgment").setValue(abc);
                            ref.child("Userid").setValue("+91" + session.getusename());
                            ref.child("Type").setValue(spinner.getSelectedItem().toString());
                            ref.child("Issue").setValue(issue);
                            Date currentDate = new Date(System.currentTimeMillis());
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String date1 = df.format(currentDate);
                            ref.child("Date").setValue(date1);
                            text1.setText("");
                            text2.setText("");
                            text3.setText("");
                            text4.setText("");
                            text5.setText("");
                            text6.setText("");
                            issue1.setChecked(false);
                            issue2.setChecked(false);

                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Sucessful");

                            builder.setMessage("Your Acknowledgment Number is " + abc)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // TODO: handle the OK
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }

                if (spinner.getSelectedItem().equals("AUTO CARE")) {
                    if (label1.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Company", Toast.LENGTH_SHORT).show();
                        label1.requestFocus();
                        return;
                    }
                    if (label2.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Model Number", Toast.LENGTH_SHORT).show();
                        label2.requestFocus();
                        return;
                    }
                    if (label3.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Register Mobile No", Toast.LENGTH_SHORT).show();
                        label3.requestFocus();
                        return;
                    }
                    if (label5.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(getContext(), "Enter Address", Toast.LENGTH_SHORT).show();
                        label5.requestFocus();
                        return;
                    }

                    if(issue1.isChecked()||issue2.isChecked()||issue3.isChecked())
                    {
                        issue="";
                        if(issue1.isChecked())
                            issue+=issue1.getText()+",";
                        if(issue2.isChecked())
                            issue+=issue2.getText()+",";
                        if(issue3.isChecked())
                            issue+=issue3.getText()+",";

                    }
                    else
                    {
                        Toast.makeText(getContext(),"Please select atleast one issue",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    session = new LoginSession(getContext());
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services");
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // get total available quest
                            int size = (int) dataSnapshot.getChildrenCount();
                            size++;
                            String abc = "SACK" + Integer.toString(size);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Services").push();
                            ref.child("Pushid").setValue(ref.getKey().toString());
                            ref.child("Company").setValue(text1.getText().toString());
                            ref.child("ModelNumber").setValue(text2.getText().toString());
                            ref.child("MobileNumber").setValue(text3.getText().toString());
                            ref.child("AlternateNumber").setValue(text4.getText().toString());
                            ref.child("Location").setValue(text5.getText().toString());
                            ref.child("ServiceAknowledgment").setValue(abc);
                            ref.child("Userid").setValue("+91" + session.getusename());
                            ref.child("Type").setValue(spinner.getSelectedItem().toString());
                            ref.child("Issue").setValue(issue);
                            Date currentDate = new Date(System.currentTimeMillis());
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String date1 = df.format(currentDate);
                            ref.child("Date").setValue(date1);
                            text1.setText("");
                            text2.setText("");
                            text3.setText("");
                            text4.setText("");
                            text5.setText("");
                            issue1.setChecked(false);
                            issue2.setChecked(false);
                            issue3.setChecked(false);
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                            builder.setTitle("Sucessful");

                            builder.setMessage("Your Acknowledgment Number is " + abc)
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // TODO: handle the OK
                                        }
                                    })
                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
            }



        });

        return v;
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        if(spinner.getSelectedItem().equals("MOBILE")){
            label6.setVisibility(View.VISIBLE);
            label7.setVisibility(View.VISIBLE);
            text6.setVisibility(View.VISIBLE);
            text7.setVisibility(View.VISIBLE);
            issue6.setVisibility(View.GONE);
            issue1.setVisibility(View.VISIBLE);
            issue2.setVisibility(View.VISIBLE);
            issue3.setVisibility(View.VISIBLE);
            issue4.setVisibility(View.VISIBLE);
            issue5.setVisibility(View.VISIBLE);

            text1.requestFocus();
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            text7.setText("");


            label1.setText("Company:");
            label2.setText("Model:");
            label3.setText("Colour:");
            label4.setText("Registered Mobile No:");
            label5.setText("Alternate Mobile No:");
            label6.setText("IMEI No:");
            label7.setText("Address:");
            text3.setInputType(InputType.TYPE_CLASS_TEXT);
            text4.setInputType(InputType.TYPE_CLASS_PHONE);
            text5.setInputType(InputType.TYPE_CLASS_PHONE);
            text6.setInputType(InputType.TYPE_CLASS_PHONE);
            issue1.setText("Network Issue");
            issue2.setText("Software Update");
            issue3.setText("Display Brekage");
            issue4.setText("Charging Issues");
            issue5.setText("Others");

        }else if(spinner.getSelectedItem().equals("DTH")){
            label6.setVisibility(View.GONE);
            label7.setVisibility(View.GONE);
            text6.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
            issue6.setVisibility(View.GONE);
            issue1.setVisibility(View.VISIBLE);
            issue2.setVisibility(View.VISIBLE);
            issue3.setVisibility(View.VISIBLE);
            issue4.setVisibility(View.VISIBLE);
            issue5.setVisibility(View.VISIBLE);

            text1.requestFocus();
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            text7.setText("");

            label1.setText("Company:");
            label2.setText("ID/CARD NO:");
            label3.setText("Regitered Mobile No:");
            label4.setText("Alternate Mobile No:");
            label5.setText("Address:");
            text3.setInputType(InputType.TYPE_CLASS_PHONE);
            text4.setInputType(InputType.TYPE_CLASS_PHONE);
            issue1.setText("No Signal");
            issue2.setText("Power Issue");
            issue3.setText("Image Distrubance");
            issue4.setText("New Connection");
            issue5.setText("Others");

        }else if(spinner.getSelectedItem().equals("APPLIANCES")){
            label6.setVisibility(View.VISIBLE);
            text6.setVisibility(View.VISIBLE);
            label7.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
            issue3.setVisibility(View.GONE);
            issue4.setVisibility(View.GONE);
            issue5.setVisibility(View.GONE);
            issue6.setVisibility(View.GONE);

            text1.requestFocus();
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            text7.setText("");


            label1.setText("Company:");
            label2.setText("Product:");
            label3.setText("Model No:");
            label4.setText("Registered Mobile No:");
            label5.setText("Alternate Mobile No:");
            label6.setText("Address:");
            text3.setInputType(InputType.TYPE_CLASS_TEXT);
            text4.setInputType(InputType.TYPE_CLASS_PHONE);
            text5.setInputType(InputType.TYPE_CLASS_PHONE);
            issue1.setText("Power Supply");
            issue2.setText("Others");

        }else if(spinner.getSelectedItem().equals("AUTO CARE")){
            label6.setVisibility(View.GONE);
            label7.setVisibility(View.GONE);
            text6.setVisibility(View.GONE);
            text7.setVisibility(View.GONE);
            issue4.setVisibility(View.GONE);
            issue5.setVisibility(View.GONE);
            issue6.setVisibility(View.GONE);
            issue3.setVisibility(View.VISIBLE);


            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
            text6.setText("");
            text7.setText("");
            text1.requestFocus();
            label1.setText("Company:");
            label2.setText("Model No:");
            label3.setText("Registered Mobile No:");
            label4.setText("Alternate Mobile No:");
            label5.setText("ShareLocation");
            text3.setInputType(InputType.TYPE_CLASS_PHONE);
            text4.setInputType(InputType.TYPE_CLASS_PHONE);
            text5.setInputType(InputType.TYPE_CLASS_TEXT);
            issue1.setText("Fuel");
            issue2.setText("Puncher");
            issue3.setText("Others");

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

}
