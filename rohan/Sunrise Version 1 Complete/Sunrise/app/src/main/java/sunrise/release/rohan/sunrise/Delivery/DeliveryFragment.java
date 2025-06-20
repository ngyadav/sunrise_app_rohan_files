package sunrise.release.rohan.sunrise.Delivery;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import sunrise.release.rohan.sunrise.Cart.Cart;
import sunrise.release.rohan.sunrise.Fragment.AddAdress;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.MainActivity;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.Payment.PaymentActivity;
import sunrise.release.rohan.sunrise.Payment.PaymentFragment;
import sunrise.release.rohan.sunrise.R;

public class DeliveryFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Fragment fragment=null;
    String b,c;
    Bundle bundle=new Bundle();
    private LoginSession session;

    public DeliveryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_delivery, container, false);
        mRecyclerView = v.findViewById(R.id.deliveryview);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        b=getArguments().getString("pushid");
        c=getArguments().getString("price");

        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        session = new LoginSession(getContext());
        mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Address");


        LinearLayout addnew=(LinearLayout)v.findViewById(R.id.New);
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new AddAdress();
                 bundle.putString("edit","no");
                 fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.frame_container, fragment).commit();
            }
        });


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Delivery, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Delivery, ViewHolder>(
                        Delivery.class,
                        R.layout.delivery_row,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Delivery orders, int position) {
                        viewHolder.setDetails(getContext(),orders.Name,orders.Number,orders.Pincode,orders.Town,orders.Flat,orders.Area,orders.Landmark,orders.Userid ,orders.Pushid);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(final View v, int position) {
                                RadioButton radioButton=(RadioButton)v.findViewById(R.id.radiobutton);
                                LinearLayout radio=(LinearLayout)v.findViewById(R.id.radio);
                                Button deliverbutton=(Button)v.findViewById(R.id.Deliverybutton);
                                Button editbutton=(Button)v.findViewById(R.id.Editbutton);
                                final TextView name=(TextView)v.findViewById(R.id.Name);
                                final TextView number=(TextView)v.findViewById(R.id.Phone);
                                final TextView rest=(TextView)v.findViewById(R.id.Rest);

                                final TextView dummy=(TextView)v.findViewById(R.id.dummy);
                                if(radioButton.isChecked())
                                {radioButton.setChecked(false);
                                 deliverbutton.setVisibility(View.GONE);
                                    editbutton.setVisibility(View.GONE);}
                                 else{
                                    radioButton.setChecked(true);
                                    deliverbutton.setVisibility(View.VISIBLE);
                                    editbutton.setVisibility(View.VISIBLE);
                                }

                                deliverbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

//                                        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders");
//                                     ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                                       @Override
//                                       public void onDataChange(DataSnapshot dataSnapshot) {
//                                           // get total available quest
//                                           int size = (int) dataSnapshot.getChildrenCount();
//                                           size++;
//                                           final String abc="ORD"+Integer.toString(size);
//                                           final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").push();
//                                           ref.child("Pushid").setValue(ref.getKey().toString());
//                                           DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("Cart").child(b);
//                                           ref3.addListenerForSingleValueEvent(new ValueEventListener() {
//                                               @Override
//                                               public void onDataChange(DataSnapshot dataSnapshot) {
//                                                   Cart mobile = dataSnapshot.getValue(Cart.class);
//
//
//
//                                                   ref.child("Image1").setValue(mobile.Image1);
//                                                   ref.child("Userid").setValue("+91"+session.getusename());
//                                                   ref.child("ProductName").setValue(mobile.ProductName);
//                                                   ref.child("Size").setValue(mobile.Size);
//                                                   ref.child("OrderNo").setValue(abc);
//                                                   ref.child("Referral").setValue(session.getreferral());
//                                                   ref.child("Quantity").setValue(mobile.Quantity);
//                                                   Integer qty=Integer.parseInt(mobile.Quantity);
//                                                   Integer price=Integer.parseInt(mobile.Mrp.substring(1));
//                                                   Integer rewards=Integer.parseInt(mobile.Rewards);
//                                                   price=qty*price;
//                                                   rewards=qty*rewards;
//                                                   ref.child("Mrp").setValue(Integer.toString(price));
//                                                   ref.child("Rewards").setValue(Integer.toString(rewards));
//                                                   ref.child("OrderStatus").setValue("Processing");
//                                                   ref.child("DeliveryName").setValue(name.getText().toString());
//                                                   ref.child("DeliveryNumber").setValue(number.getText().toString());
//                                                   ref.child("DeliveryAddress").setValue(rest.getText().toString());
//
//                                                   Date currentDate = new Date(System.currentTimeMillis());
//                                                   SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                                                   String date1=df.format(currentDate);
//                                                   ref.child("Date").setValue(date1);
//
//                                                   AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                                                   builder.setTitle("Sucessful");
//                                                   builder.setMessage("Your Order Number is "+abc)
//                                                           .setCancelable(false)
//                                                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                               public void onClick(DialogInterface dialog, int id) {
//
//                                                                   fragment=new OrdersFragment();
//                                                                   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                                                   fragmentManager.beginTransaction()
//                                                                           .replace(R.id.frame_container, fragment).commit();
//
//                                                               }
//                                                           });
//
//
//                                                   AlertDialog alertDialog = builder.create();
//                                                   alertDialog.show();
//                                                   mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");
//                                                   mref.child(b).addListenerForSingleValueEvent(new ValueEventListener() {
//                                                       @Override
//                                                       public void onDataChange(DataSnapshot dataSnapshot) {
//                                                           for (DataSnapshot a : dataSnapshot.getChildren())
//                                                               a.getRef().removeValue();
//                                                       }
//
//                                                       @Override
//                                                       public void onCancelled(DatabaseError databaseError) {
//
//                                                       }
//                                                   });
//                                               }
//
//                                               @Override
//                                               public void onCancelled(DatabaseError databaseError) {
//
//                                               }
//                                           });
//
//                                       }
//                                       @Override
//                                       public void onCancelled(DatabaseError databaseError) {
//
//                                       }
//                                   });


//                                        fragment=new PaymentFragment();
//                                        bundle.putString("pushid",b);
//                                        bundle.putString("name",name.getText().toString());
//                                        bundle.putString("number",number.getText().toString());
//                                        bundle.putString("rest",rest.getText().toString());
//
//                                        fragment.setArguments(bundle);
//                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                        fragmentManager.beginTransaction()
//                                                .addToBackStack(null)
//                                                .replace(R.id.frame_container, fragment).commit();

                                        Intent i = new Intent(getContext(), PaymentActivity.class);
                                        i.putExtra("pushid", b);
                                        i.putExtra("price",c);
                                        i.putExtra("name", name.getText().toString());
                                        i.putExtra("number", number.getText().toString());
                                        i.putExtra("rest", rest.getText().toString());
                                        startActivity(i);




                                    }
                                });

                                 editbutton.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                         fragment=new AddAdress();
                                         bundle.putString("edit","yes");
                                         bundle.putString("pushid",dummy.getText().toString());
                                         fragment.setArguments(bundle);
                                         FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                         fragmentManager.beginTransaction()
                                                 .addToBackStack(null)
                                                 .replace(R.id.frame_container, fragment).commit();
                                     }
                                 });



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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
