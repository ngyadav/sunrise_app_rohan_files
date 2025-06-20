package sunrise.release.rohan.sunriseseller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunriseseller.Functionality.Encryption;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;

public class PasscodeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_passcode, container, false);
        Button Bupdate=(Button)v.findViewById(R.id.Bupdate);
        final EditText Tpassword=(EditText)v.findViewById(R.id.Tpassword);
        final EditText Tpasscode=(EditText)v.findViewById(R.id.Tpasscode);

        Bupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Tpassword.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }if(Tpasscode.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getContext(),"Enter Passcode",Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginSession session=new LoginSession(getContext());
                final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+session.getusename());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String password = null,a;
                               a =dataSnapshot.child("Password").getValue().toString();
                        try {
                            password= Encryption.decrypt(a);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String hash = null;
                        if(Tpassword.getText().toString().equalsIgnoreCase(password)){
                            try {
                                hash=Encryption.encrypt(Tpasscode.getText().toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ref.child("Passcode").setValue(hash);
                            Toast.makeText(getContext(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                            Tpasscode.setText("");
                            Tpassword.setText("");
                        }
                        else{
                            Toast.makeText(getContext(),"Enter Correct Password",Toast.LENGTH_SHORT).show();
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
