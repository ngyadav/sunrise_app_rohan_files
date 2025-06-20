package rohan.groups.sunrise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import rohan.groups.sunrise.SplashScreen.SplashScreen;

public class PermissionActivity extends AppCompatActivity {

    private Button btnGrant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

            if(ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                startActivity(new Intent(PermissionActivity.this,
                        AppIntro.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                return;
            }

            btnGrant = findViewById(R.id.btn_grant);

            btnGrant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dexter.withActivity(PermissionActivity.this)
                            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    startActivity(new Intent(PermissionActivity.this,
                                            AppIntro.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                    finish();
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {
                                    if(response.isPermanentlyDenied()){

                                        final Button ok,cancel;
                                        final TextView heading,content;
                                        ViewGroup viewGroup = findViewById(android.R.id.content);

                                        //then we will inflate the custom alert dialog xml that we created
                                        View dialogView = LayoutInflater.from(PermissionActivity.this).inflate(R.layout.my_dialog, viewGroup, false);
                                        ok=dialogView.findViewById(R.id.buttonOk);
                                        cancel=dialogView.findViewById(R.id.buttonCancel);
                                        heading=dialogView.findViewById(R.id.heading);
                                        content=dialogView.findViewById(R.id.content);

                                        //Now we need an AlertDialog.Builder object
                                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PermissionActivity.this);

                                        //setting the view of the builder to our custom view that we already inflated
                                        builder.setView(dialogView);

                                        //finally creating the alert dialog and displaying it
                                        final AlertDialog alertDialog = builder.create();
                                        heading.setText("Permission Denied");
                                        content.setText("Permission to access device location is permanently denied. you need to go to setting to allow the permission.");
                                        alertDialog.show();

                                        ok.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent();
                                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                intent.setData(Uri.fromParts("package", getPackageName(), null));

                                            }
                                        });


                                        cancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                alertDialog.dismiss();
                                            }
                                        });






                                    } else {
                                        Toast.makeText(PermissionActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            })
                            .check();
                }
            });
        }

    }

