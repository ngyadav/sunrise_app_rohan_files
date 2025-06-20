package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import sunrise.release.rohan.sunrise.R;

public class  Imei extends Fragment {
  TextView sno,simei;
    public Imei() {
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
        View v= inflater.inflate(R.layout.fragment_imei, container, false);
         sno=(TextView)v.findViewById(R.id.sno);
         simei=(TextView)v.findViewById(R.id.simei);
        Button ssubmit=(Button)v.findViewById(R.id.ssubmit);

        ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(simei.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getContext(),"Enter IMEI",Toast.LENGTH_SHORT).show();
                    return;}
                if(simei.getText().toString().length()!=16){
                    Toast.makeText(getContext(),"Enter 16 digit IMEI",Toast.LENGTH_SHORT).show();
                    return;}

                DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Users");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("+91"+sno.getText().toString())){
                            DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+sno.getText().toString());
                            ref.child("IMEI").setValue(simei.getText().toString());
                            simei.setText("");
                            sno.setText("");
                            Toast.makeText(getContext(),"Successful",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(),"User Not Found",Toast.LENGTH_SHORT).show();
                            return;
                        }

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
