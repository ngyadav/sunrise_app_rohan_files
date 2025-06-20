package rohan.groups.sunriseaccounts.Fragments;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rohan.groups.sunriseaccounts.Entity.EasyRecharge;
import rohan.groups.sunriseaccounts.R;

public class AddEasyFragment extends Fragment {

    private Spinner spinner;
    private EditText amount;
    private Button submit;
    private long total;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    List<String> zone = new ArrayList<String>();

    public AddEasyFragment() {
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
        View v=inflater.inflate(R.layout.fragment_add_easy, container, false);
        spinner=v.findViewById(R.id.sname);
        submit=v.findViewById(R.id.submit);
        amount=v.findViewById(R.id.amount);






        database.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot v : dataSnapshot.getChildren()) {
                    zone.add(v.child("UserName").getValue().toString());
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, zone);
                areasAdapter.setDropDownViewResource(R.layout.spinner_layout);
                spinner.setAdapter(areasAdapter);

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

                Date currentDate = new Date(System.currentTimeMillis());
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                final String date1 = df.format(currentDate);

                DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Users").child(spinner.getSelectedItem().toString()).child("EasyRecharge");
                dref.runTransaction(new Transaction.Handler() {
                    @NonNull
                    @Override
                    public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                        long value = 0;
                        if(currentData.getValue() != null) {
                            value=Long.parseLong(currentData.getValue().toString())+Integer.parseInt(amount.getText().toString());
                            total=Long.parseLong(currentData.getValue().toString())+Integer.parseInt(amount.getText().toString());

                        }
                        currentData.setValue(value);

                        DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("EasyRecharge").push();
                        EasyRecharge dsponsorEarnings1=new EasyRecharge(rref1.getKey(),spinner.getSelectedItem().toString(),Double.toString(total),date1,amount.getText().toString(),"Admin",
                                "Cr","EasyRecharge","Approved");
                        rref1.setValue(dsponsorEarnings1);

                        return Transaction.success(currentData);
                    }

                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("EasyRecharge")
                                .setMessage("Amount Has Been Successfully Added")
                                .setPositiveButton(android.R.string.ok, null)
                                .show();

                        amount.setText("");
                    }
                });

            }
        });







        return v;
    }


}
