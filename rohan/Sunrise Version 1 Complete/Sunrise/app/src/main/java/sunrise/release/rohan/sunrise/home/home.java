package sunrise.release.rohan.sunrise.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import sunrise.release.rohan.sunrise.Fragment.Imei;
import sunrise.release.rohan.sunrise.Fragment.RewardsCreation;
import sunrise.release.rohan.sunrise.Fragment.RewardsFragment;
import sunrise.release.rohan.sunrise.Fragment.SellerFragment;
import sunrise.release.rohan.sunrise.Functionality.ForceUpdateChecker;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.ads.AdView;
import com.razorpay.Checkout;

import java.util.logging.Level;

import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Fragment.CategoryFragment;
import sunrise.release.rohan.sunrise.Fragment.HomeFragment;
import sunrise.release.rohan.sunrise.Fragment.OffersFragment;
import sunrise.release.rohan.sunrise.Fragment.ProfileFragment;
import sunrise.release.rohan.sunrise.Fragment.ReferFragement;
import sunrise.release.rohan.sunrise.Fragment.RegisterFragment;
import sunrise.release.rohan.sunrise.Fragment.ReportFragment;
import sunrise.release.rohan.sunrise.Fragment.SupportFragment;
import sunrise.release.rohan.sunrise.Fragment.TermsFragment;
import sunrise.release.rohan.sunrise.Fragment.UploadFragment;
import sunrise.release.rohan.sunrise.login.login;


public class home extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private LoginSession session;
    private static final String TAG = "Token";
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView rewards;
    private Button signout;
    private NavigationView navigation;
    private StorageReference mstorageReference;
    private FirebaseAnalytics mFirebaseAnalytics;
    public Uri uri;
    private AdView mAdView;
    Bundle bundle=new Bundle();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String a[]=new String[100];
    int i=0;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

//        mAdView = findViewById(R.id.adView);
//        mAdView.setAdSize(new AdSize(100, 50));
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        session = new LoginSession(home.this);
//        initFCM();







        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

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

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("MessagingToken").setValue(token);

                    }
                });





       String value = "empty";

        if(getIntent().hasExtra("key")) {

           value = getIntent().getStringExtra("key");
            }

        if(value.equals("Orders")) {
            fragment=new OrdersFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container,fragment).commit();
        }
        else {
            fragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
        }




        mstorageReference= FirebaseStorage.getInstance().getReference();

        //Analytics
        final Bundle bundle1 = new Bundle();
        bundle1.putString(FirebaseAnalytics.Param.ITEM_ID, session.getusename());
        bundle1.putString(FirebaseAnalytics.Param.ITEM_NAME, session.getrole());
        bundle1.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle1);


        navigation=(NavigationView)findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.Nregister).setVisible(false);
        navigation.getMenu().findItem(R.id.Nreport).setVisible(false);
        navigation.getMenu().findItem(R.id.Nupload).setVisible(false);
        navigation.getMenu().findItem(R.id.Nseller).setVisible(false);
        navigation.getMenu().findItem(R.id.Nrewardscreation).setVisible(false);
        navigation.getMenu().findItem(R.id.Nimei).setVisible(false);



        if(session.getrole().equalsIgnoreCase("su"))
        {navigation.getMenu().findItem(R.id.Nregister).setVisible(true);
        navigation.getMenu().findItem(R.id.Nreport).setVisible(true);
        navigation.getMenu().findItem(R.id.Nupload).setVisible(false);
        navigation.getMenu().findItem(R.id.Nseller).setVisible(true);
        navigation.getMenu().findItem(R.id.Nrewardscreation).setVisible(true);
        navigation.getMenu().findItem(R.id.Nimei).setVisible(true);
        }

        rewards= (TextView)MenuItemCompat.getActionView(navigation.getMenu().findItem(R.id.Nrewards));
        rewards.setGravity(Gravity.CENTER_VERTICAL);
        rewards.setTypeface(null, Typeface.BOLD);
        rewards.setTextColor(getResources().getColor(R.color.colorAccent));

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);




        View headerview = navigation.getHeaderView(0);

        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView Hname=(TextView)headerview.findViewById(R.id.Hname);
        final TextView Hemail=(TextView)headerview.findViewById(R.id.Hemail);
        final ImageView Hpp=(ImageView)headerview.findViewById(R.id.Hpp);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Users").child("+91"+session.getusename());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);


                Hname.setText(user.Name);
                Hemail.setText(user.Email);
                rewards.setText(user.Rewards);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        String str="\u20B9";

        if(session.geturl()!="")
        {
             Glide.with(home.this)
                    .load(session.geturl())
                     .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(Hpp);
            bundle.putString("image",session.geturl());
        }
        else {

            DatabaseReference ref1=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename());
            ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild("ProfileUrl")) {
                        String url = dataSnapshot.child("ProfileUrl").getValue().toString();
                        Glide.with(home.this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(Hpp);
                        session.seturl(url);
                        bundle.putString("image", session.geturl());
                    }
                    else{
                        bundle.putString("image",session.geturl());
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        ImageView rightarrow = (ImageView) headerview.findViewById(R.id.NRightArrow);
        rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new ProfileFragment();
                bundle.putString("name",Hname.getText().toString());
                bundle.putString("email",Hemail.getText().toString());
                bundle.putString("number",session.getusename());
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container,fragment).commit();
                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
                mDrawerLayout.closeDrawers();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
////        MenuInflater inflater=getMenuInflater();
////        inflater.inflate(R.menu.main_menu,menu);
////        MenuItem itemMessages = menu.findItem(R.id.cart);
////        MenuItem search=menu.findItem(R.id.search);
//////        MenuItem signout=menu.findItem(R.id.signout);
////        View badgeLayout = (RelativeLayout) itemMessages.getActionView();
////        final TextView itemMessagesBadgeTextView = (TextView) badgeLayout.findViewById(R.id.badge_textView);
////        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child("+91"+session.getusename()).child("Cart");
////        ref.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // get total available quest
////                int size = (int) dataSnapshot.getChildrenCount();
////                    itemMessagesBadgeTextView.setText(Integer.toString(size));
////            }
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////
////
////        itemMessagesBadgeTextView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                fragment=new CartFragment();
////                FragmentManager fragmentManager = getSupportFragmentManager();
////                fragmentManager.beginTransaction()
////                        .addToBackStack(null)
////                        .replace(R.id.frame_container, fragment).commit();
////
////            }
////        });
//
////        signout.setOnMenuItemClickListener((new MenuItem.OnMenuItemClickListener() {
////            @Override
////            public boolean onMenuItemClick(MenuItem item) {
////                session = new LoginSession(home.this);
////                session.setusename("");
////                session.setstatus("");
////                session.seturl("");
////                Intent intent = new Intent(home.this, login.class);
////                startActivity(intent);
////                finish();
////                return false;
////            }
////        }));
////        search.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
////            @Override
////            public boolean onMenuItemClick(MenuItem item) {
////                Toast.makeText(home.this,"Coming Soon",Toast.LENGTH_SHORT).show();
////                return false;
////            }
////        });
//
//        return true;
//
//    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle presses on the action bar items
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.search:
//                Toast.makeText(home.this, "Search", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.cart:
//                Toast.makeText(home.this, "Cart", Toast.LENGTH_SHORT).show();
                fragment = new CartFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean onNavigationItemSelected(MenuItem item)
    {

        int id=item.getItemId();
        if(id==R.id.Noffers)
        {
           fragment=new OffersFragment();
        }
        else if(id==R.id.Nhome)
        {
            fragment=new HomeFragment();
        }
        else if(id==R.id.NRightArrow)
        {
            fragment=new HomeFragment();
        }
        else if(id==R.id.Ncategory)
        {
            fragment=new CategoryFragment();
        }
        else if(id==R.id.Nrefer)
        {
            fragment=new ReferFragement();

        }
        else if(id==R.id.Nterms)
        {
            fragment=new TermsFragment();
        }
        else if(id==R.id.Nsupport)
        {
            fragment=new SupportFragment();
        }
        else if(id==R.id.Nregister)
        {
            fragment=new RegisterFragment();
        }
        else if(id==R.id.Nreport)
        {
            fragment=new ReportFragment();
        }else if(id==R.id.Nupload)
        {
            fragment=new UploadFragment();
        }else if(id==R.id.Nseller)
        {
            fragment=new SellerFragment();
        }else if(id==R.id.Norder)
        {
            fragment=new OrdersFragment();
        }else if(id==R.id.Nrewards)
        {
            fragment=new RewardsFragment();
        }else if(id==R.id.Nrewardscreation)
        {
            fragment=new RewardsCreation();
        }else if(id==R.id.Nimei)
        {
            fragment=new Imei();
        }
        else if(id==R.id.Nlogout)
        {
            session = new LoginSession(home.this);
            session.setusename("");
            session.setstatus("");
            session.seturl("");
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
            Checkout.clearUserData(home.this);
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.closeDrawers();
       return true;
    }




    @IgnoreExtraProperties
    public  static class User {

        public String Name;
        public String Email;
        public String Rewards;


        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
        }

        public User(String Name,String Email,String Rewards) {
            this.Name=Name;
            this.Email=Email;
           this.Rewards=Rewards;
        }

    }



    private void sendRegistrationToServer(String token) {
        Log.d(TAG, "sendRegistrationToServer: sending token to server: " + token);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child("+91"+session.getusename())
                .child("MessagingToken").setValue(token);
    }


}
