package sunrise.release.rohan.sunriseseller.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;
import sunrise.release.rohan.sunriseseller.fragments.BillFragment;
import sunrise.release.rohan.sunriseseller.fragments.PasscodeFragment;
import sunrise.release.rohan.sunriseseller.fragments.RewardsFragment;
import sunrise.release.rohan.sunriseseller.fragments.TopupFragment;
import sunrise.release.rohan.sunriseseller.fragments.homefragment;
import sunrise.release.rohan.sunriseseller.login.login;


public class home extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;
    private LoginSession session;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setNavigationItemSelectedListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        View headerview = navigation.getHeaderView(0);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView Hname=(TextView)headerview.findViewById(R.id.Hname);
        final TextView Hemail=(TextView)headerview.findViewById(R.id.Hemail);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        session=new LoginSession(home.this);
        DatabaseReference ref = database.child("SellerUsers").child("+91"+session.getusename());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);


                Hname.setText(user.Name);
                Hemail.setText(user.Email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        fragment=new BillFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_container, fragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
            return super.onOptionsItemSelected(item);

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.Nlogout)
        {
            session = new LoginSession(home.this);
            session.setusename("");
            session.setstatus("");
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.Nbill)
        {
            fragment=new BillFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }
//          else if(id==R.id.Nhome)
//        {
//            fragment=new homefragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.frame_container, fragment).commit();
//
//        }
            else if(id==R.id.Npassword)
        {
            fragment=new PasscodeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }else if(id==R.id.Nrewards)
        {
            fragment=new RewardsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }else if(id==R.id.Ntopup)
        {
            fragment=new TopupFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.frame_container, fragment).commit();

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerLayout.closeDrawers();
        return false;

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
}