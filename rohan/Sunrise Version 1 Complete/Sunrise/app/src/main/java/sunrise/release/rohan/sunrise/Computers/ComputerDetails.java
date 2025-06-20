package sunrise.release.rohan.sunrise.Computers;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import sunrise.release.rohan.sunrise.Cart.CartFragment;
import sunrise.release.rohan.sunrise.Functionality.EnlargeImage;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComputerDetails extends Fragment {
        ImageView mobile_share_details;
        TextView mobile_name_details,mobile_price_details,mobile_cashback_details,mobile_feature1_details,mobile_feature2_details,mobile_feature3_details;
        TextView mobile_feature4_details,mobile_feature5_details,mobile_feature6_details,mobile_stock_details;
        TextView mobile_feature7_details,mobile_feature8_details,mobile_feature9_details,mobile_feature10_details,mobile_feature11_details,mobile_feature12_details;
        String[] sampleimages=new String[3];
        CarouselView mobile_pic_details;
        Button cart;
        String rewards;
        Fragment fragment=null;
        private LoginSession session;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v= inflater.inflate(R.layout.fragment_computer_details, container, false);
            final Bundle bundle=getArguments();
            final String name=bundle.getString("name");
            mobile_share_details=(ImageView)v.findViewById(R.id.mobile_share_details);
            mobile_pic_details=(CarouselView)v.findViewById(R.id.mobile_pic_details);
            mobile_pic_details.setImageClickListener(new ImageClickListener() {
                @Override
                public void onClick(int position) {
                    fragment=new EnlargeImage();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    bundle.putString("name",name);
                    bundle.putString("0",sampleimages[0]);
                    bundle.putString("1",sampleimages[1]);
                    bundle.putString("2",sampleimages[2]);
                    fragment.setArguments(bundle);
                    fragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame_container, fragment).commit();
                }
            });
            mobile_name_details=(TextView)v.findViewById(R.id.mobile_name_details);
            mobile_price_details=(TextView)v.findViewById(R.id.mobile_price_details);
            mobile_cashback_details=(TextView)v.findViewById(R.id.mobile_cashback_details);
            mobile_feature1_details=(TextView)v.findViewById(R.id.mobile_feature1_details);
            mobile_feature2_details=(TextView)v.findViewById(R.id.mobile_feature2_details);
            mobile_feature3_details=(TextView)v.findViewById(R.id.mobile_feature3_details);
            mobile_feature4_details=(TextView)v.findViewById(R.id.mobile_feature4_details);
            mobile_feature5_details=(TextView)v.findViewById(R.id.mobile_feature5_details);
            mobile_feature6_details=(TextView)v.findViewById(R.id.mobile_feature6_details);
            mobile_feature7_details=(TextView)v.findViewById(R.id.mobile_feature7_details);
            mobile_feature8_details=(TextView)v.findViewById(R.id.mobile_feature8_details);
            mobile_feature9_details=(TextView)v.findViewById(R.id.mobile_feature9_details);
            mobile_feature10_details=(TextView)v.findViewById(R.id.mobile_feature10_details);
            mobile_feature11_details=(TextView)v.findViewById(R.id.mobile_feature11_details);
            mobile_feature12_details=(TextView)v.findViewById(R.id.mobile_feature12_details);
            mobile_stock_details=(TextView)v.findViewById(R.id.mobile_stock_details);
            cart=(Button)v.findViewById(R.id.Bcart);
            session = new LoginSession(getContext());
            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = database.child("Category").child(name);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Computer mobile = dataSnapshot.getValue(Computer.class);

                    mobile_name_details.setText(mobile.Name);
                    String str="\u20B9";
                    rewards=mobile.Cashback;
                    mobile_price_details.setText(str+mobile.Price);
                    String str1="Get Upto "+mobile.Cashback+" Reward Points";
                    mobile_cashback_details.setText(str1);
                    String str2="\u2022";

                    if(!mobile.Feature1.equals("")) {
                        mobile_feature1_details.setText(str2 + mobile.Feature1);
                    }if(!mobile.Feature2.equals("")) {
                        mobile_feature2_details.setText(str2 + mobile.Feature2);
                    }if(!mobile.Feature3.equals("")) {
                        mobile_feature3_details.setText(str2 + mobile.Feature3);
                    }if(!mobile.Feature4.equals("")) {
                        mobile_feature4_details.setText(str2 + mobile.Feature4);
                    }if(!mobile.Feature5.equals("")) {
                        mobile_feature5_details.setText(str2 + mobile.Feature5);
                    }if(!mobile.Feature6.equals("")) {
                        mobile_feature6_details.setText(str2 + mobile.Feature6);
                    }if(!mobile.Feature7.equals("")) {
                        mobile_feature7_details.setText(str2 + mobile.Feature7);
                    }if(!mobile.Feature8.equals("")) {
                        mobile_feature8_details.setText(str2 + mobile.Feature8);
                    }if(!mobile.Feature9.equals("")) {
                        mobile_feature9_details.setText(str2 + mobile.Feature9);
                    }if(!mobile.Feature10.equals("")) {
                        mobile_feature10_details.setText(str2 + mobile.Feature10);
                    }if(!mobile.Feature11.equals("")) {
                        mobile_feature11_details.setText(str2 + mobile.Feature11);
                    }if(!mobile.Feature12.equals("")) {
                        mobile_feature12_details.setText(str2 + mobile.Feature12);
                    }

                    if(mobile.Stock.equalsIgnoreCase("In Stock")) {
                        mobile_stock_details.setText(mobile.Stock);
                        mobile_stock_details.setTextColor(Color.parseColor("#23C348"));
                    }
                    if(mobile.Stock.equalsIgnoreCase("Out of Stock")) {
                        mobile_stock_details.setText(mobile.Stock);
                        cart.setClickable(false);
                        mobile_stock_details.setTextColor(Color.parseColor("#E90303"));
                    }

                    sampleimages[0]=mobile.Image1;
                    sampleimages[1]=mobile.Image2;
                    sampleimages[2]=mobile.Image3;

                    mobile_pic_details.setImageListener(imageListener);
                    mobile_pic_details.setPageCount(sampleimages.length);

                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });






            cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    View mview=getLayoutInflater().inflate(R.layout.order_spinner,null);
                    builder.setTitle("Select Quantity");
                    final Spinner mspinner=(Spinner)mview.findViewById(R.id.spinner);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.ordercount));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mspinner.setAdapter(adapter);

                    builder.setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child("+91"+session.getusename()).child("Cart");
                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // get total available quest
                                            int size = (int) dataSnapshot.getChildrenCount();
                                            size++;
                                            String abc="CART"+Integer.toString(size);
                                            DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Users").child("+91"+session.getusename()).child("Cart").push();
                                            ref.child("Pushid").setValue(ref.getKey().toString());
                                            ref.child("Image1").setValue(sampleimages[0]);
                                            ref.child("Userid").setValue("+91"+session.getusename());
                                            ref.child("ProductName").setValue(mobile_name_details.getText().toString());
                                            ref.child("Mrp").setValue(mobile_price_details.getText().toString());
                                            ref.child("Rewards").setValue(rewards);
                                            ref.child("OrderNo").setValue(abc);
                                            ref.child("Size").setValue("");
                                            ref.child("OrderStatus").setValue("Processing");
                                            Date currentDate = new Date(System.currentTimeMillis());
                                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                            String date1=df.format(currentDate);
                                            ref.child("Date").setValue(date1);
                                            ref.child("Quantity").setValue(mspinner.getSelectedItem().toString());
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                            builder.setTitle("Sucessful");
                                            builder.setMessage("Your Cart Number is "+abc)
                                                    .setCancelable(false)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            fragment=new CartFragment();
                                                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                            fragmentManager.beginTransaction()
                                                                    .addToBackStack(null)
                                                                    .replace(R.id.frame_container, fragment).commit();
                                                        }
                                                    })
                                                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });

                                            AlertDialog alertDialog = builder.create();
                                            alertDialog.show();
                                        }
                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
//
                                }
                            })
                            .setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int id){
                                    dialog.cancel();

                                }
                            });


                    builder.setView(mview);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }
            });


//            mobile_share_details.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    shareimage();
//                }
//            });
            return v;
        }

//        public void shareimage(){
//            try{
//                BitmapDrawable drawable = (BitmapDrawable) mobile_pic_details.getDrawable();
//                Bitmap bitmap = drawable.getBitmap();
//                String s=mobile_name_details.getText().toString()+"\n"+mobile_price_details;
//                File file=new File(getActivity().getExternalCacheDir(),"sample.png");
//                FileOutputStream fout=new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,fout);
//                fout.flush();
//                fout.close();
//                file.setReadable(true,false);
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(intent.EXTRA_TEXT,s);
//                intent.putExtra(intent.EXTRA_STREAM,Uri.fromFile(file));
//                intent.setType("image/png");
//                startActivity(Intent.createChooser(intent,"Share via"));
//
//
//            }
//            catch(Exception e){
//                Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//
//        }

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(R.drawable.accesories);
                Glide.with(getContext())
                        .load(sampleimages[position])
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
            }
        };

    }
