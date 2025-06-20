package sunrise.release.rohan.sunriseseller.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sunrise.release.rohan.sunriseseller.Functionality.Encryption;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;

public class TopupFragment extends Fragment {

    EditText Rewardsnumber,Rewardsamount,Rewardsmpin;
    Button Rewardsproceed;
    CheckBox checkBox;
    int amount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_topup, container, false);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Rewardsnumber=(EditText)v.findViewById(R.id.Rewardsuserid);
        Rewardsamount=(EditText)v.findViewById(R.id.Rewardsamount);
        Rewardsmpin=(EditText)v.findViewById(R.id.Rewardsmpin);
        Rewardsproceed=(Button)v.findViewById(R.id.Rewardsproceed);
        checkBox=(CheckBox)v.findViewById(R.id.checkBox);

        Rewardsproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(Rewardsamount.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter Amount",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Rewardsnumber.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter Customer Number",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Rewardsmpin.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter MPIN",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String[] mpin = new String[1];
                final LoginSession session=new LoginSession(getContext());

                DatabaseReference pref= FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+session.getusename());
                pref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("Passcode")) {
                            mpin[0] = dataSnapshot.child("Passcode").getValue().toString();
                                    try {
                                        mpin[0] = Encryption.decrypt(mpin[0]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (Rewardsmpin.getText().toString().equalsIgnoreCase(mpin[0])) {

                                        Rewardsproceed.setVisibility(View.GONE);

                                        FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+session.getusename())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        amount = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
                                                        if (amount >= Integer.parseInt(Rewardsamount.getText().toString())) {
                                                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+Rewardsnumber.getText().toString());
                                                            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                                    int a = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
                                                                    a = a + Integer.parseInt(Rewardsamount.getText().toString());
                                                                    DatabaseReference ref10 = FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+Rewardsnumber.getText().toString());
                                                                    ref10.child("Rewards").setValue(Integer.toString(a));




                                                                    final int finalA = a;

                                                                            Date currentDate = new Date(System.currentTimeMillis());
                                                                            SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
                                                                            String date1 = df.format(currentDate);
                                                                            int random = new Random().nextInt(10000);
                                                                            String abc = "TRANS" + date1+random;

                                                                            final DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Rewards").push();
                                                                            rref.child("PushId").setValue(rref.getKey().toString());
                                                                            rref.child("UserId").setValue("+91"+Rewardsnumber.getText().toString());
                                                                            rref.child("Amount").setValue(Rewardsamount.getText().toString());
                                                                            rref.child("Balance").setValue(Integer.toString(finalA));
                                                                            rref.child("TransactionId").setValue(abc);
                                                                            Date currentDate1 = new Date(System.currentTimeMillis());
                                                                            SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                                                                            String date2 = df1.format(currentDate1);
                                                                            rref.child("Date").setValue(date2);
                                                                            rref.child("Authorised").setValue("+91" + session.getusename());

                                                                            rref.child("AuthorisedType").setValue("Dr");
                                                                            rref.child("AuthorisedName").setValue("Topup");

                                                                            rref.child("Type").setValue("Cr");
                                                                            if(checkBox.isChecked())
                                                                            rref.child("Name").setValue("Reward Points");
                                                                            else
                                                                                rref.child("Name").setValue("Topup");

                                                                            amount = amount - Integer.parseInt(Rewardsamount.getText().toString());
                                                                            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91" + session.getusename());
                                                                            ref1.child("Rewards").setValue(Integer.toString(amount));
                                                                            rref.child("AuthorisedBalance").setValue(Integer.toString(amount));

                                                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                                            builder.setTitle("Successful");
                                                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                        public void onClick(DialogInterface dialog, int id) {


                                                                                        }
                                                                                    });
                                                                            AlertDialog alertDialog = builder.create();
                                                                            alertDialog.show();

                                                                            Rewardsamount.setText("");
                                                                            Rewardsnumber.setText("");
                                                                            Rewardsmpin.setText("");

                                                                            Rewardsproceed.setVisibility(View.VISIBLE);
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(DatabaseError databaseError) {

                                                                        }
                                                                    });



                                                        }
                                                        else{
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                            builder.setTitle("TOPUP");
                                                            builder.setMessage("Please Add Rewards points to ur Wallet")
                                                                    .setCancelable(false)
                                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int id) {
                                                                            TopupFragment fragment = new TopupFragment();
                                                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                            fragmentManager.beginTransaction()
                                                                                    .replace(R.id.frame_container, fragment).commit();

                                                                        }
                                                                    });


                                                            AlertDialog alertDialog = builder.create();
                                                            alertDialog.show();
                                                            Rewardsproceed.setVisibility(View.VISIBLE);
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                    }
                                    else {
                                        Toast.makeText(getContext(), "Mpin Doesnt Match", Toast.LENGTH_SHORT).show();

                                        return;
                                    }


                        }
                        else{
                            Toast.makeText(getContext(),"Please Set Mpin First",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



            }
        });




        return  v;
    }


}
