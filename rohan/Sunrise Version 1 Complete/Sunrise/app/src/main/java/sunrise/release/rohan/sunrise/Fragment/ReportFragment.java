package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.DatabaseFetch.Profile;
import sunrise.release.rohan.sunrise.DatabaseFetch.ViewHolder;
import sunrise.release.rohan.sunrise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ReportFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View v = inflater.inflate(R.layout.fragment_register, container, false);

            mRecyclerView=v.findViewById(R.id.recyclerview);
            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mref = mFirebaseDatabase.getReference("Users");


            return v;
        }

        @Override
        public  void onStart()
        {
            super.onStart();

            FirebaseRecyclerAdapter<Profile,ViewHolder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Profile,ViewHolder>(
                            Profile.class,
                            R.layout.row,
                            ViewHolder.class,
                            mref
                    ){
                        @Override
                        protected void populateViewHolder(ViewHolder viewHolder,Profile profile,int position)
                        {
                            viewHolder.setDetails(getContext(),profile.Email,profile.Name,profile.ProfileUrl,profile.Referral,profile.Role,profile.Status,profile.Phone);

                        }

                        @Override
                        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                            ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                            viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    TextView mName=v.findViewById(R.id.rNameTV);
                                    TextView mPhone=v.findViewById(R.id.rNumberTv);
                                    final String ph=mPhone.getText().toString();
                                    String name=mName.getText().toString();

                                    new AlertDialog.Builder(getContext())
                                            .setTitle("Title")
                                            .setMessage("Do you want the User: "+name+" to be Registered")
                                            .setIcon(android.R.drawable.ic_dialog_alert)
                                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                                public void onClick(DialogInterface dialog, int whichButton) {

                                                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+ph);
                                                    ref.child("Status").setValue("Registered");
                                                    Toast.makeText(getContext(), "Confirm", Toast.LENGTH_SHORT).show();
                                                }})
                                            .setNegativeButton(android.R.string.no, null).show();

                                }

                                @Override
                                public void onItemLongClick(View v, int position) {

                                }
                            });
                            return viewHolder;
                        }
                    };

            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        }


//        private  void firebasesearch(String searchText)
//        {
//            Query firebasesearchquery = mref.orderByChild("Phone").startAt(searchText).endAt(searchText+"\uf0ff");
//
//            FirebaseRecyclerAdapter<Profile,ViewHolder> firebaseRecyclerAdapter=
//                    new FirebaseRecyclerAdapter<Profile, ViewHolder>(
//                            Profile.class,
//                            R.layout.row,
//                            ViewHolder.class,
//                            firebasesearchquery
//                    ) {
//                        @Override
//                        protected void populateViewHolder(ViewHolder viewHolder, Profile profile, int position) {
//                            viewHolder.setDetails(getContext(),profile.Email,profile.Name,profile.ProfileUrl,profile.Referral,profile.Role,profile.Status,profile.Phone);
//                        }
//
//                        @Override
//                        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                            ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
//                            viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
//                                @Override
//                                public void onItemClick(View v, int position) {
//                                    TextView mName=v.findViewById(R.id.rNameTV);
//                                    TextView mPhone=v.findViewById(R.id.rNumberTv);
//                                    final String ph=mPhone.getText().toString();
//                                    String name=mName.getText().toString();
//                                }
//
//                                @Override
//                                public void onItemLongClick(View v, int position) {
//
//                                }
//                            });
//                            return viewHolder;
//                        }
//                    };
//            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//
//        }
//
//        @Override
//        public  void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
//        {
//            inflater.inflate(R.menu.main_menu,menu);
//            MenuItem item=menu.findItem(R.id.search);
//            SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    firebasesearch(query);
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    firebasesearch(newText);
//                    return false;
//                }
//            });
//
//            super.onCreateOptionsMenu(menu,inflater);
//        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.search:
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

//        private  void registersearch(String searchText)
//        {
//            Query firebasesearchquery = mref.orderByChild("Phone").startAt(searchText).endAt(searchText+"\uf0ff");
//
//            FirebaseRecyclerAdapter<Profile,ViewHolder> firebaseRecyclerAdapter=
//                    new FirebaseRecyclerAdapter<Profile, ViewHolder>(
//                            Profile.class,
//                            R.layout.row,
//                            ViewHolder.class,
//                            firebasesearchquery
//                    ) {
//                        @Override
//                        protected void populateViewHolder(ViewHolder viewHolder, Profile profile, int position) {
//                            viewHolder.setDetails(getContext(),profile.Email,profile.Name,profile.ProfileUrl,profile.Referral,profile.Role,profile.Status,profile.Phone);
//                        }
//
//                        @Override
//                        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                            ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
//                            viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
//                                @Override
//                                public void onItemClick(View v, int position) {
//                                    TextView mName=v.findViewById(R.id.rNameTV);
//                                    TextView mPhone=v.findViewById(R.id.rNumberTv);
//                                    final String ph=mPhone.getText().toString();
//                                    String name=mName.getText().toString();
//                                }
//
//                                @Override
//                                public void onItemLongClick(View v, int position) {
//
//                                }
//                            });
//                            return viewHolder;
//                        }
//                    };
//            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
//
//        }

    }
