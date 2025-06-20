package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import sunrise.release.rohan.sunrise.Functionality.Encryption;
import sunrise.release.rohan.sunrise.R;

public class SellerFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_seller, container, false);
       final EditText sno=(EditText)v.findViewById(R.id.sno);
       final EditText sname=(EditText)v.findViewById(R.id.sname);
       final EditText semail =(EditText)v.findViewById(R.id.semail);
        Button ssubmit=(Button)v.findViewById(R.id.ssubmit);


        ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sno.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter Mobile No", Toast.LENGTH_LONG).show();
                    return;
                } if (sname.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter Name", Toast.LENGTH_LONG).show();
                    return;
                } if (semail.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (sno.getText().toString().length()!=10) {
                    Toast.makeText(getContext(), "Enter 10 digits Mobile No", Toast.LENGTH_LONG).show();
                    return;
                }
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+sno.getText().toString());
                ref.child("Email").setValue(semail.getText().toString());
                ref.child("Phone").setValue(sno.getText().toString());
                ref.child("Name").setValue(sname.getText().toString());
                ref.child("Rewards").setValue("0");
                ref.child("Status").setValue("Registered");

                String hash= null;
                try {
                    hash = Encryption.encrypt(sno.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ref.child("Password").setValue(hash);
                    sno.setText("");
                    sname.setText("");
                    semail.setText("");

            }
        });





        return v;
    }


}
