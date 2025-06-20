package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Delivery.DeliveryFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;


public class AddAdress extends Fragment {

    private LoginSession session;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Fragment fragment=null;
    Bundle bundle=new Bundle();
    String b="",c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_adress, container, false);

       final EditText name=v.findViewById(R.id.Name);
       final EditText number=v.findViewById(R.id.Number);
        final  EditText pincode=v.findViewById(R.id.Pincode);
        final EditText town=v.findViewById(R.id.Town);
        final  EditText flat=v.findViewById(R.id.Flat);
        final EditText area=v.findViewById(R.id.Area);
        final EditText landmark=v.findViewById(R.id.Landmark);
        session = new LoginSession(getContext());

        if(getArguments().getString("edit").equals("yes")){
            b=getArguments().getString("pushid");


            FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("Address").child(b)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            name.setText(dataSnapshot.child("Name").getValue().toString());
                            number.setText(dataSnapshot.child("Number").getValue().toString());
                            pincode.setText(dataSnapshot.child("Pincode").getValue().toString());
                            town.setText(dataSnapshot.child("Town").getValue().toString());
                            flat.setText(dataSnapshot.child("Flat").getValue().toString());
                            area.setText(dataSnapshot.child("Area").getValue().toString());
                            landmark.setText(dataSnapshot.child("Landmark").getValue().toString());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

        }





        Button add=v.findViewById(R.id.Add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(b)) {
                    if (TextUtils.isEmpty(name.getText().toString())) {
                        Toast.makeText(getContext(), "Enter  Name!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(number.getText().toString())) {
                        Toast.makeText(getContext(), "Enter Number!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (number.getText().length() != 10) {
                        Toast.makeText(getContext(), "Enter 10 digits Number!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(pincode.getText().toString())) {
                        Toast.makeText(getContext(), "Enter Pincode!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(town.getText().toString())) {
                        Toast.makeText(getContext(), "Enter Town!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(flat.getText().toString())) {
                        Toast.makeText(getContext(), "Enter Flat!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(area.getText().toString())) {
                        Toast.makeText(getContext(), "Enter Area!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child("+91" + session.getusename()).child("Address").push();
                    ref.child("Pushid").setValue(ref.getKey().toString());
                    ref.child("Userid").setValue("+91" + session.getusename());
                    ref.child("Name").setValue(name.getText().toString());
                    ref.child("Number").setValue(number.getText().toString());
                    ref.child("Pincode").setValue(pincode.getText().toString());
                    ref.child("Town").setValue(town.getText().toString());
                    ref.child("Flat").setValue(flat.getText().toString());
                    ref.child("Area").setValue(area.getText().toString());
                    ref.child("Landmark").setValue(landmark.getText().toString());


//                fragment=new DeliveryFragment();
//                bundle.putString("pushid",b);
//                bundle.putString("price",c);
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();

                    getActivity().onBackPressed();
                } else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child("+91" + session.getusename()).child("Address").child(b);
                    ref.child("Pushid").setValue(ref.getKey().toString());
                    ref.child("Userid").setValue("+91" + session.getusename());
                    ref.child("Name").setValue(name.getText().toString());
                    ref.child("Number").setValue(number.getText().toString());
                    ref.child("Pincode").setValue(pincode.getText().toString());
                    ref.child("Town").setValue(town.getText().toString());
                    ref.child("Flat").setValue(flat.getText().toString());
                    ref.child("Area").setValue(area.getText().toString());
                    ref.child("Landmark").setValue(landmark.getText().toString());


                    getActivity().onBackPressed();

                }
            }
        });




        return v;
    }


}
