package rohan.groups.sunriseaccounts.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

import rohan.groups.sunriseaccounts.Entity.EasyRecharge;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.R;
public class EasyFragment extends Fragment {

    DatePickerDialog datePickerDialog;
    Calendar calendar;
    int year,month,dayofmonth;
    EditText amount;
    Button submit;
    double total;
    Spinner particular;
    Sessions sessions;
    int temp=0;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    List<String> zone = new ArrayList<String>();
    public EasyFragment() {
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
        View v=inflater.inflate(R.layout.fragment_easy, container, false);
        final TextView date=v.findViewById(R.id.date);
        amount=v.findViewById(R.id.amount);
        submit=v.findViewById(R.id.submit);
        particular=v.findViewById(R.id.particulars);
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



                DatabaseReference dref= FirebaseDatabase.getInstance().getReference().child("Users").child(sessions.getusername()).child("EasyRecharge");
                dref.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                        long value = 0;
                        if(currentData.getValue() != null) {
                            value = Long.parseLong(currentData.getValue().toString());
                            if(value>=Integer.parseInt(amount.getText().toString())) {
                                value = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(amount.getText().toString());
                                total = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(amount.getText().toString());
                            }
                            else{

                                return Transaction.success(currentData);
                            }

                        }
                        currentData.setValue(value);

                        DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("EasyRecharge").push();
                        EasyRecharge dsponsorEarnings1=new EasyRecharge(rref1.getKey(),sessions.getusername(),Double.toString(total),date.getText().toString(),amount.getText().toString(),"Admin",
                                "Dr",particular.getSelectedItem().toString(),"Approved");
                        rref1.setValue(dsponsorEarnings1);
                        temp=1;
                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        if(temp==1) {
                            new AlertDialog.Builder(getContext())
                                    .setTitle("EasyRecharge")
                                    .setMessage("Amount Has Been Successfully Added")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .show();
                        }
                        else{
                            new AlertDialog.Builder(getContext())
                                    .setTitle("EasyRecharge")
                                    .setMessage("Insufficient Balance")
                                    .setPositiveButton(android.R.string.ok, null)
                                    .show();
                        }

                        temp=0;
                        amount.setText("");
                    }
                });
            }
        });


        return v;
    }

}
