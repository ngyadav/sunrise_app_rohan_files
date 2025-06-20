package rohan.groups.sunrise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;

import rohan.groups.sunrise.Fragments.CartFragment;
import rohan.groups.sunrise.Fragments.CategoryFragment;
import rohan.groups.sunrise.Fragments.HelpFragment;
import rohan.groups.sunrise.Fragments.OrderHistoryFragment;
import rohan.groups.sunrise.Fragments.ProfileFragment;
import rohan.groups.sunrise.Fragments.PromotionsFragment;
import rohan.groups.sunrise.Fragments.RechargeFragment;
import rohan.groups.sunrise.Fragments.RewardsFragment;
import rohan.groups.sunrise.Fragments.SearchFragment;
import rohan.groups.sunrise.Fragments.SettingsFragment;
import rohan.groups.sunrise.Fragments.ShopFragment;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.SplashScreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    LinearLayout b1,b2,b3,b4,b5;
    Session session;
    private static final String TAG = "MyFirebaseMsgService";
    private SlidingRootNav slidingRootNav;
    private SlidingRootNav drawer;
    int temp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);

        session = new Session(MainActivity.this);

        session.setisfirsttime("no");

        FirebaseDatabase.getInstance().getReference().keepSynced(true);


        final ImageView gotit, no;
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.update, viewGroup, false);
        gotit = dialogView.findViewById(R.id.yes);
        no = dialogView.findViewById(R.id.no);

        //Now we need an AlertDialog.Builder object
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.setCancelable(false);


        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        final double version = Double.parseDouble(info.versionName);


        gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {

                }

            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Rewards1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Rewards1").setValue(0);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        FirebaseDatabase.getInstance().getReference().child("AppContent").child("Application").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                double vno = Double.parseDouble(dataSnapshot.child("Version").getValue().toString());
                String imp = dataSnapshot.child("IMP").getValue().toString();
                if (version != vno) {
                    if (imp.equals("No")) {
                        alertDialog.show();
                        return;
                    } else {
                        alertDialog.show();
                        alertDialog.setCancelable(false);
                        return;
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        if (!TextUtils.isEmpty(session.getusername())) {
            FirebaseInstanceId.getInstance().getInstanceId()
                    .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "getInstanceId failed", task.getException());
                                return;
                            }

                            // Get new Instance ID token
                            String token = task.getResult().getToken();
                            session.settoken(token);

                            // Log and toast
                            String msg = getString(R.string.msg_token_fmt, token);
                            Log.d(TAG, msg);
                            FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("UMessagingToken").setValue(token);
                        }
                    });
        }


//        String extra = getIntent().getStringExtra("EXTRA");
//
//
//
//
//        if (!TextUtils.isEmpty(extra)) {
//            if (extra.equals("Cart")) {
//                temp++;
//                Fragment fragment = new CartFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
//            }
//            if (extra.equals("Orders")) {
//                temp++;
//                Fragment fragment = new OrderHistoryFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
//            }
//            if (extra.equals("Home")) {
//                temp++;
//                Fragment fragment = new ShopFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(R.id.frame_container, fragment).commit();
//            }
//        }

//        if (temp == 0){
            Fragment fragment = new ShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_container, fragment).commit();
//        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new ShopFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(MainActivity.this,"Coming Soon",Toast.LENGTH_LONG).show();

//                if(!TextUtils.isEmpty(session.getusername())) {
//                    Fragment fragment = new RechargeFragment();
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction()
//                            .addToBackStack(null)
//                            .replace(R.id.frame_container, fragment).commit();
//                }
//                else{
//                    Toast.makeText(MainActivity.this,"Please register to continue",Toast.LENGTH_LONG).show();
//                    return;
//                }

            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(session.getusername())) {
                    Fragment fragment = new CartFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
                else{
                    Toast.makeText(MainActivity.this,"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(session.getusername())) {
                    Fragment fragment = new RewardsFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
                else{
                    Toast.makeText(MainActivity.this,"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });


        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(session.getusername())) {
                    Fragment fragment = new ProfileFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
                else{
                    Toast.makeText(MainActivity.this,"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });



        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);


        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername())
                .child("Cart")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(!dataSnapshot.exists()) {
                            session.setcartitem("0");
                            session.setcarttotal("0");
                            ArrayList<String> iname = new ArrayList<String>();
                            iname.clear();
                            ArrayList<String> iprice = new ArrayList<String>();
                            iname.clear();
                            ArrayList<String> iqty = new ArrayList<String>();
                            iname.clear();
                            session.setitemname(iname, "INAME");
                            session.setitemprice(iprice, "IPRICE");
                            session.setitemqty(iqty, "IQTY");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





        slidingRootNav= new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withMenuLayout(R.layout.menu_left)
                .inject();



        ImageView close=findViewById(R.id.close);
        TextView home=findViewById(R.id.home);
        TextView rewards=findViewById(R.id.rewards);
        TextView orderhistory=findViewById(R.id.orderhistory);
        TextView category=findViewById(R.id.category);
        TextView promotions=findViewById(R.id.promotions);
        TextView help=findViewById(R.id.help);
        TextView settings=findViewById(R.id.settings);
        TextView logout=findViewById(R.id.logout);

        promotions.setVisibility(View.GONE);

        final ImageView toggle=findViewById(R.id.toggle);

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(session.getusername())) {

                    if(slidingRootNav.isMenuOpened()) {
//                    updateStatusBarColor("#000000");
                        slidingRootNav.closeMenu();
                    }
                    else {
//                    updateStatusBarColor("#FC848F");
                        slidingRootNav.openMenu();
                    }


                }
                else{
                    Toast.makeText(MainActivity.this,"Please register to continue",Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });






        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");
            }
        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new ShopFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new RewardsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });

        orderhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new OrderHistoryFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new CategoryFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        promotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new PromotionsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });



        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new SettingsFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();

            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                slidingRootNav.closeMenu();
//                updateStatusBarColor("#000000");

                Fragment fragment = new HelpFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setusername("");
                session.settoken("");
                session.setname("");
                session.setpp("");
                session.setpassword("");
                session.setextras("");
                session.setnumber("");
                session.setpincode("");
                session.setsub("");
                session.setrange("");
                session.settoken("");
                session.setcart("");
                session.setdaaddress("");
                session.setdadist("");
                session.setdaf("");
                session.setdal("");
                session.setdaloc("");
                session.setdaname("");


                startActivity(new Intent(MainActivity.this,
                        SplashScreen.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();

            }
        });


    }



}
