package rohan.groups.sunriseaccounts.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import rohan.groups.sunriseaccounts.Login.Login;
import rohan.groups.sunriseaccounts.R;


public class CreateUserFragment extends Fragment {


    public CreateUserFragment() {
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
        View v=inflater.inflate(R.layout.fragment_create_user, container, false);

        final EditText sname=v.findViewById(R.id.sname);
        final EditText number=v.findViewById(R.id.number);
        final EditText password=v.findViewById(R.id.password);
        Button submit=v.findViewById(R.id.submit);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(sname.getText().length()==0){
                    sname.setError("Enter Name");
                    sname.requestFocus();
                    return;
                }
                if(number.getText().length()==0){
                    number.setError("Enter UserName");
                    number.requestFocus();
                    return ;
                }
                if(password.getText().length()==0){
                    password.setError("Enter Password");
                    password.requestFocus();
                    return ;
                }

                if(password.getText().length()<6){
                    password.setError("Enter Minimum 6 character Password");
                    password.requestFocus();
                    return ;
                }




                Login.User user=new Login.User(sname.getText().toString(),password.getText().toString(),"Employee","Active",number.getText().toString(),0.0,0.0);

                FirebaseDatabase.getInstance().getReference().child("Users").child(number.getText().toString()).setValue(user);

                Toast.makeText(getContext(),"Successful!!!",Toast.LENGTH_SHORT).show();
                sname.setText("");
                number.setText("");
                password.setText("");
            }
        });




        return v;
    }


}
