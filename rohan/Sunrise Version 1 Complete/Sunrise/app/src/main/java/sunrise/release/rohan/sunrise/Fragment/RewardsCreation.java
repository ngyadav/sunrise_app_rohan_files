package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;

public class RewardsCreation extends Fragment {
        List<String> areas = new ArrayList<String>();
    Spinner mRewards;
    EditText Rewardsmpin,Rewardsamount;
    Button Rewardsproceed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_rewards_creation, container, false);
        mRewards=v.findViewById(R.id.mRewards);
        Rewardsamount=v.findViewById(R.id.Rewardsamount);
        Rewardsmpin=v.findViewById(R.id.Rewardsmpin);
        Rewardsproceed=v.findViewById(R.id.Rewardsproceed);
        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference();


        ref.child("SellerUsers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot v:dataSnapshot.getChildren())
                    areas.add(v.child("Phone").getValue().toString());

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mRewards.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Rewardsproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rewardsproceed.setVisibility(View.GONE);
                if(Rewardsamount.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter Amount",Toast.LENGTH_SHORT).show();
                    Rewardsproceed.setVisibility(View.VISIBLE);
                    return;
                }
                if(Rewardsamount.getText().toString().length()>6){
                    Toast.makeText(getContext(),"Enter Amount Less than One Lakh",Toast.LENGTH_SHORT).show();
                    Rewardsproceed.setVisibility(View.VISIBLE);
                    return;
                }
                if(Rewardsmpin.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter MPIN",Toast.LENGTH_SHORT).show();
                    Rewardsproceed.setVisibility(View.VISIBLE);
                    return;
                }

                if(Rewardsmpin.getText().toString().equalsIgnoreCase("1201")){
//                    ref.child("+91"+mRewards.getSelectedItem().toString()).child("Rewards").setValue(Rewardsamount.getText().toString());


                    ref.child("SellerUsers").child("+91"+mRewards.getSelectedItem().toString())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            int a = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
                            a = a + Integer.parseInt(Rewardsamount.getText().toString());
                            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+mRewards.getSelectedItem().toString());
                            ref1.child("Rewards").setValue(Integer.toString(a));
                            final DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("Rewards");
                                final int finalA = a;
                                rref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        int size = (int) dataSnapshot.getChildrenCount();
                                        size++;
                                        String abc = "TRANS" + Integer.toString(size);
                                        DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Rewards").push();
                                        rref.child("PushId").setValue(rref.getKey().toString());
                                        rref.child("Authorised").setValue("+91"+mRewards.getSelectedItem().toString());
                                        rref.child("Amount").setValue(Rewardsamount.getText().toString());
                                        rref.child("AuthorisedBalance").setValue(Integer.toString(finalA));
                                        rref.child("TransactionId").setValue(abc);
                                        Date currentDate = new Date(System.currentTimeMillis());
                                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                        String date1 = df.format(currentDate);
                                        rref.child("Date").setValue(date1);
                                        LoginSession session=new LoginSession(getContext());
                                        rref.child("Generated").setValue("+91" + session.getusename());
                                        rref.child("AuthorisedType").setValue("Cr");
                                        rref.child("AuthorisedName").setValue("TopUp").addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getContext(),"Transaction Successful",Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                        Rewardsamount.setText("");
                                        Rewardsmpin.setText("");

                                        Rewardsproceed.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                             }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }else{
                    Toast.makeText(getContext(),"Enter Correct MPIN",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        return v;
    }


}
