package sunrise.release.rohan.sunriseseller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sunrise.release.rohan.sunriseseller.R;

public class BillFragment extends Fragment {
    int n=0;
    Spinner ono;
    List<String> orderno=new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_bill, container, false);
        ono=(Spinner) v.findViewById(R.id.ono);

        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    System.exit(0);
                    return true;
                }
                return false;
            }
        } );


        FirebaseDatabase.getInstance().getReference().child("Orders")
                .orderByChild("OrderStatus").equalTo("Processing")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot v:dataSnapshot.getChildren()) {
                   orderno.add(v.child("OrderNo").getValue().toString());
                }
                Collections.reverse(orderno);
                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_layout,orderno);
                areasAdapter.setDropDownViewResource(R.layout.spinner_layout);
                ono.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        Button oproceed=(Button)v.findViewById(R.id.oproceed);

        oproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle bundle=new Bundle();
                final BillDetailFragment fragment= new BillDetailFragment();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders");
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                       bundle.putString("name",ono.getSelectedItem().toString());
                            fragment.setArguments(bundle);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .replace(R.id.frame_container, fragment).commit();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        return v;
    }


}
