package sunrise.release.rohan.sunrise.login;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.signup.passwordsignup;

public class Updateimei extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateimei);
        textView=(TextView)findViewById(R.id.textView);
        String device_unique_id = Settings.Secure.getString(Updateimei.this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        textView.setText(device_unique_id);
    }
}
