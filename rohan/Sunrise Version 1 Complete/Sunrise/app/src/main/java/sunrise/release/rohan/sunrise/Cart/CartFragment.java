package sunrise.release.rohan.sunrise.Cart;


import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import sunrise.release.rohan.sunrise.Delivery.DeliveryFragment;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class CartFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Fragment fragment=null;
    Bundle bundle=new Bundle();
    private LoginSession session;
    public CartFragment() {
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
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        mRecyclerView = v.findViewById(R.id.recyclerviewcart);
        mRecyclerView.setHasFixedSize(true);
         LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        session = new LoginSession(getContext());
        mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Cart, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Cart, ViewHolder>(
                        Cart.class,
                        R.layout.cardview_order,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Cart orders, int position) {
                        viewHolder.setDetails(getContext(), orders.ProductName, orders.Mrp, orders.Rewards, orders.Image1, orders.OrderStatus, orders.OrderNo, orders.Date,orders.Quantity,orders.Size,orders.Pushid,orders.Colour);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(final View v, int position) {

                                final CharSequence[] items = {"Order",
                                        "Delete From Cart"};
                                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                                builder.setTitle("CART MENU");

                                //SET ITEMS AND THERE LISTENERS
                                builder.setItems(items, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int item) {

                                                 if (items[item].equals("Order")) {
                                                            TextView dummy=(TextView)v.findViewById(R.id.dummy1);
                                                            TextView mrp=(TextView)v.findViewById(R.id.omrp);
                                                            TextView quantity=(TextView)v.findViewById(R.id.oquantity);
                                                            fragment=new DeliveryFragment();
                                                            bundle.putString("pushid",dummy.getText().toString());
                                                            Integer qty=Integer.parseInt(quantity.getText().toString());
                                                            Integer price=Integer.parseInt(mrp.getText().toString().substring(1));
                                                            price=qty*price;
                                                            bundle.putString("price",Integer.toString(price));
                                                            fragment.setArguments(bundle);
                                                     FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                           fragmentManager.beginTransaction()
                                                                   .addToBackStack(null)
                                                                   .replace(R.id.frame_container, fragment).commit();
//                                   DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders");
//                                   ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                                       @Override
//                                       public void onDataChange(DataSnapshot dataSnapshot) {
//                                           // get total available quest
//                                           TextView order_productname=(TextView)v.findViewById(R.id.oproduct);
//                                           TextView order_mrp=(TextView)v.findViewById(R.id.omrp);
//                                           TextView order_rewards=(TextView)v.findViewById(R.id.orewards);
//                                           TextView order_orderno=(TextView)v.findViewById(R.id.oorderno);
//                                           TextView dummy=(TextView)v.findViewById(R.id.dummy);
//                                           ImageView order_image=(ImageView)v.findViewById(R.id.oimage);
//                                           TextView order_quantity=(TextView)v.findViewById(R.id.oquantity);
//                                           int size = (int) dataSnapshot.getChildrenCount();
//                                           size++;
//                                           String abc="ORD"+Integer.toString(size);
//                                           DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").push();
//                                           ref.child("Pushid").setValue(ref.getKey().toString());
//                                           ref.child("Image1").setValue(dummy.getText().toString());
//                                           ref.child("Userid").setValue("+91"+session.getusename());
//                                           ref.child("ProductName").setValue(order_productname.getText().toString());
//                                           ref.child("Mrp").setValue(order_mrp.getText().toString());
//                                           ref.child("Rewards").setValue(order_rewards.getText().toString());
//                                           ref.child("OrderNo").setValue(abc);
//                                           ref.child("Referral").setValue(session.getreferral());
//                                           ref.child("Quantity").setValue(order_quantity.getText().toString());
//                                           ref.child("OrderStatus").setValue("Processing");
//                                           Date currentDate = new Date(System.currentTimeMillis());
//                                           SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                                           String date1=df.format(currentDate);
//                                           ref.child("Date").setValue(date1);
//
//                                           AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                                           builder.setTitle("Sucessful");
//                                           builder.setMessage("Your Order Number is "+abc)
//                                                   .setCancelable(false)
//                                                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                       public void onClick(DialogInterface dialog, int id) {
//
//                                                           fragment=new OrdersFragment();
//                                                           FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                                                           fragmentManager.beginTransaction()
//                                                                   .addToBackStack(null)
//                                                                   .replace(R.id.frame_container, fragment).commit();
//
//                                                       }
//                                                   })
//                                                   .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                                       public void onClick(DialogInterface dialog, int id) {
//                                                           dialog.cancel();
//                                                       }
//                                                   });
//
//                                           AlertDialog alertDialog = builder.create();
//                                           alertDialog.show();
//                                           mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");
//                                           Query mref1=mref.orderByChild("OrderNo").equalTo(order_orderno.getText().toString());
//                                           mref1.addListenerForSingleValueEvent(new ValueEventListener() {
//                                               @Override
//                                               public void onDataChange(DataSnapshot dataSnapshot) {
//                                                   for (DataSnapshot a : dataSnapshot.getChildren())
//                                                       a.getRef().removeValue();
//                                               }
//
//                                               @Override
//                                               public void onCancelled(DatabaseError databaseError) {
//
//                                               }
//                                           });
//                                       }
//                                       @Override
//                                       public void onCancelled(DatabaseError databaseError) {
//
//                                       }
//                                   });
//
//                                   dialog.dismiss();

                               } else if (items[item].equals("Delete From Cart")) {
                                   TextView status = v.findViewById(R.id.oorderno);
                                   mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");
                                   Query mref1=mref.orderByChild("OrderNo").equalTo(status.getText().toString());
                                   mref1.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(DataSnapshot dataSnapshot) {
                                           for (DataSnapshot a : dataSnapshot.getChildren())
                                               a.getRef().removeValue();
                                       }

                                       @Override
                                       public void onCancelled(DatabaseError databaseError) {

                                       }
                                   });

                                   dialog.dismiss();
                                        }
                                    }
                                });
                                builder.show();

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

}
