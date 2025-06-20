package sunrise.release.rohan.sunriseseller.home;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;

public class UnRegistered extends AppCompatActivity {
    private LoginSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_registered);
        ImageView call=(ImageView)findViewById(R.id.call);
        ImageView mail=(ImageView)findViewById(R.id.mail);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:09945599388"));
                startActivity(intent);
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new LoginSession(UnRegistered.this);
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"info@sunrise.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Regarding Registrattion of Account:"+session.getusename());
                intent.putExtra(Intent.EXTRA_TEXT,"Please Activate my account as soon as possible.\nThank You");
                intent.setType("text/html");
//                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });
    }


}