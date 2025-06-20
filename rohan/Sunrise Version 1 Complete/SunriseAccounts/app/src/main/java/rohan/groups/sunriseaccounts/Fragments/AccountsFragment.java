package rohan.groups.sunriseaccounts.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rohan.groups.sunriseaccounts.Entity.Accounts;
import rohan.groups.sunriseaccounts.Entity.EasyRecharge;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.R;

public class AccountsFragment extends Fragment {


    DatePickerDialog datePickerDialog;
    Calendar calendar;
    int year,month,dayofmonth;
    TextView date;
    Spinner particular,type,cr;
    EditText amount;
    Button submit;
    double total;
    int temp=0;
    Sessions sessions;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    List<String> zone = new ArrayList<String>();
    List<String> zone1 = new ArrayList<String>();
    List<String> zone2 = new ArrayList<String>();
    public AccountsFragment() {
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
        View v=inflater.inflate(R.layout.fragment_accounts, container, false);
        date=v.findViewById(R.id.date);
        particular=v.findViewById(R.id.particulars);
        type=v.findViewById(R.id.type);
        cr=v.findViewById(R.id.cr);
        amount=v.findViewById(R.id.amount);
        submit=v.findViewById(R.id.submit);
        sessions=new Sessions(getContext());

        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String date1 = df.format(currentDate);


        date.setText(date1);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar= Calendar.getInstance();
                year=calendar.get(Calendar.YEAR);
                month=calendar.get(Calendar.MONTH);
                dayofmonth=calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog=new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                String day1,month1;
                                if(day<=9)
                                    day1="0"+day;
                                else{
                                    day1=Integer.toString(day);
                                }
                                month++;
                                if(month<9)
                                    month1="0"+month;
                                else
                                    month1=Integer.toString(month);

                                date.setText(day1+"-"+(month1)+"-"+year);

                            }
                        },year,month,dayofmonth);
                datePickerDialog.show();

            }
        });

        database.child("Particulars").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot v : dataSnapshot.getChildren()) {
                    zone.add(v.getValue().toString());
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, zone);
                areasAdapter.setDropDownViewResource(R.layout.spinner_layout);
                particular.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        database.child("Type").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot v : dataSnapshot.getChildren()) {
                    zone1.add(v.getKey().toString());
                    zone2.add(v.getValue().toString());
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, zone1);
                areasAdapter.setDropDownViewResource(R.layout.spinner_layout);
                type.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(amount.getText().length()==0){
                    amount.setError("Enter Amount");
                    return;
                }


                if(Integer.parseInt(amount.getText().toString())<=0){
                    amount.setError("Enter Amount Greater Than 0");
                    return;
                }

                new AlertDialog.Builder(getContext())
                        .setTitle("Accounts Transcation")
                        .setMessage("Are You sure to Proceed. Transaction Done Cannot Be Reverted Back.")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                                DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("Accounts").push();
                                        Accounts dsponsorEarnings1=new Accounts(rref1.getKey(),sessions.getusername(),"",date1,amount.getText().toString(),"Admin",
                                                type.getSelectedItem().toString(),particular.getSelectedItem().toString(),zone2.get(type.getSelectedItemPosition()),"Processing");
                                        rref1.setValue(dsponsorEarnings1);

                                new AlertDialog.Builder(getContext())
                                      .setTitle("Accounts")
                                      .setMessage("Transaction Done.. Please Get it Approved to Verify The Balance!!")
                                      .setPositiveButton(android.R.string.ok, null)
                                      .show();
                                amount.setText("");
                                        temp=0;



//                                DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Users").child(sessions.getusername()).child("Accounts");
//                                dref.runTransaction(new Transaction.Handler() {
//                                    @NonNull
//                                    @Override
//                                    public Transaction.Result doTransaction(@NonNull MutableData currentData) {
//                                        long value = 0;
//                                        if(currentData.getValue() != null) {
//                                            value = Long.parseLong(currentData.getValue().toString());
//                                            if(zone2.get(type.getSelectedItemPosition()).equals("CR")) {
//                                                value = Long.parseLong(currentData.getValue().toString()) + Integer.parseInt(amount.getText().toString());
//                                                total = Long.parseLong(currentData.getValue().toString()) + Integer.parseInt(amount.getText().toString());
//                                            }
//
//                                            if(zone2.get(type.getSelectedItemPosition()).equals("DR")) {
//                                                    value = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(amount.getText().toString());
//                                                    total = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(amount.getText().toString());
//
//                                            }
//
//                                        }
//                                        currentData.setValue(value);
//
//                                        DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("Accounts").push();
//                                        Accounts dsponsorEarnings1=new Accounts(rref1.getKey(),sessions.getusername(),Double.toString(total),date1,amount.getText().toString(),"Admin",
//                                                type.getSelectedItem().toString(),particular.getSelectedItem().toString(),zone2.get(type.getSelectedItemPosition()),"Approved");
//                                        rref1.setValue(dsponsorEarnings1);
//                                        temp=1;
//
//                                        return Transaction.success(currentData);
//                                    }
//
//                                    @Override
//                                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
//                                        if(temp==1) {
//                                            new AlertDialog.Builder(getContext())
//                                                    .setTitle("Accounts")
//                                                    .setMessage("Transaction Done Successfully!!")
//                                                    .setPositiveButton(android.R.string.ok, null)
//                                                    .show();
//                                        }
//                                        else{
//                                            new AlertDialog.Builder(getContext())
//                                                    .setTitle("Accounts")
//                                                    .setMessage("Insufficient Balance")
//                                                    .setPositiveButton(android.R.string.ok, null)
//                                                    .show();
//                                        }
//                                        amount.setText("");
//                                        temp=0;
//                                    }
//                                });
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                return;
                            }
                        })
                        .show();





            }
        });



        return v;
    }

}
