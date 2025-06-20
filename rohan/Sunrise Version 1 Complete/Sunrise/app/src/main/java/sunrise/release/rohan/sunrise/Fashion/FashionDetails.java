package sunrise.release.rohan.sunrise.Fashion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.LinearLayout;
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

public class FashionDetails extends Fragment implements View.OnClickListener {

    ImageView mobile_share_details;
    TextView mobile_name_details, mobile_price_details, mobile_cashback_details, mobile_feature1_details, mobile_feature2_details, mobile_feature3_details;
    TextView mobile_feature4_details, mobile_feature5_details, mobile_feature6_details, mobile_stock_details;
    TextView mobile_feature7_details, mobile_feature8_details, mobile_feature9_details, mobile_feature10_details, mobile_feature11_details, mobile_feature12_details;
    TextView array[] = new TextView[26];
    LinearLayout array1[]=new LinearLayout[26];
    String[] sampleimages = new String[3];
    CarouselView mobile_pic_details;
    Button cart;
    String rewards;
    Fragment fragment = null;
    int sizeselected = 0;
    private LoginSession session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fashion_details, container, false);
        final Bundle bundle = getArguments();
        final String name = bundle.getString("name");
        array[1] = (TextView) v.findViewById(R.id.one);
        array[2] = (TextView) v.findViewById(R.id.two);
        array[3] = (TextView) v.findViewById(R.id.three);
        array[4] = (TextView) v.findViewById(R.id.four);
        array[5] = (TextView) v.findViewById(R.id.five);
        array[6] = (TextView) v.findViewById(R.id.six);
        array[7] = (TextView) v.findViewById(R.id.seven);
        array[8] = (TextView) v.findViewById(R.id.eight);
        array[9] = (TextView) v.findViewById(R.id.nine);
        array[10] = (TextView) v.findViewById(R.id.ten);
        array[11] = (TextView) v.findViewById(R.id.eleven);
        array[12] = (TextView) v.findViewById(R.id.twelve);
        array[13] = (TextView) v.findViewById(R.id.thirteen);
        array[14] = (TextView) v.findViewById(R.id.fourteen);
        array[15] = (TextView) v.findViewById(R.id.fifteen);
        array[16] = (TextView) v.findViewById(R.id.sixteen);
        array[17] = (TextView) v.findViewById(R.id.seventeen);
        array[18] = (TextView) v.findViewById(R.id.eighteen);
        array[19] = (TextView) v.findViewById(R.id.nineteen);
        array[20] = (TextView) v.findViewById(R.id.twenty);
        array[21] = (TextView) v.findViewById(R.id.twentyone);
        array[22] = (TextView) v.findViewById(R.id.twentytwo);
        array[23] = (TextView) v.findViewById(R.id.twentythree);
        array[24] = (TextView) v.findViewById(R.id.twentyfour);
        array[25] = (TextView) v.findViewById(R.id.twentyfive);
        array1[1] = (LinearLayout) v.findViewById(R.id.l1);
        array1[2] = (LinearLayout) v.findViewById(R.id.l2);
        array1[3] = (LinearLayout) v.findViewById(R.id.l3);
        array1[4] = (LinearLayout) v.findViewById(R.id.l4);
        array1[5] = (LinearLayout) v.findViewById(R.id.l5);
        array1[6] = (LinearLayout) v.findViewById(R.id.l6);
        array1[7] = (LinearLayout) v.findViewById(R.id.l7);
        array1[8] = (LinearLayout) v.findViewById(R.id.l8);
        array1[9] = (LinearLayout) v.findViewById(R.id.l9);
        array1[10] = (LinearLayout) v.findViewById(R.id.l10);
        array1[11] = (LinearLayout) v.findViewById(R.id.l11);
        array1[12] = (LinearLayout) v.findViewById(R.id.l12);
        array1[13] = (LinearLayout) v.findViewById(R.id.l13);
        array1[14] = (LinearLayout) v.findViewById(R.id.l14);
        array1[15] = (LinearLayout) v.findViewById(R.id.l15);
        array1[16] = (LinearLayout) v.findViewById(R.id.l16);
        array1[17] = (LinearLayout) v.findViewById(R.id.l17);
        array1[18] = (LinearLayout) v.findViewById(R.id.l18);
        array1[19] = (LinearLayout) v.findViewById(R.id.l19);
        array1[20] = (LinearLayout) v.findViewById(R.id.l20);
        array1[21] = (LinearLayout) v.findViewById(R.id.l21);
        array1[22] = (LinearLayout) v.findViewById(R.id.l22);
        array1[23] = (LinearLayout) v.findViewById(R.id.l23);
        array1[24] = (LinearLayout) v.findViewById(R.id.l24);
        array1[25] = (LinearLayout) v.findViewById(R.id.l25);




        for (int i = 1; i <= 25; i++)
        { array1[i].setVisibility(View.GONE);
        array1[i].setOnClickListener(this);}

        mobile_share_details = (ImageView) v.findViewById(R.id.mobile_share_details);
        mobile_pic_details = (CarouselView) v.findViewById(R.id.mobile_pic_details);
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
        mobile_name_details = (TextView) v.findViewById(R.id.mobile_name_details);
        mobile_price_details = (TextView) v.findViewById(R.id.mobile_price_details);
        mobile_cashback_details = (TextView) v.findViewById(R.id.mobile_cashback_details);
        mobile_feature1_details = (TextView) v.findViewById(R.id.mobile_feature1_details);
        mobile_feature2_details = (TextView) v.findViewById(R.id.mobile_feature2_details);
        mobile_feature3_details = (TextView) v.findViewById(R.id.mobile_feature3_details);
        mobile_feature4_details = (TextView) v.findViewById(R.id.mobile_feature4_details);
        mobile_feature5_details = (TextView) v.findViewById(R.id.mobile_feature5_details);
        mobile_feature6_details = (TextView) v.findViewById(R.id.mobile_feature6_details);
        mobile_feature7_details = (TextView) v.findViewById(R.id.mobile_feature7_details);
        mobile_feature8_details = (TextView) v.findViewById(R.id.mobile_feature8_details);
        mobile_feature9_details = (TextView) v.findViewById(R.id.mobile_feature9_details);
        mobile_feature10_details = (TextView) v.findViewById(R.id.mobile_feature10_details);
        mobile_feature11_details = (TextView) v.findViewById(R.id.mobile_feature11_details);
        mobile_feature12_details = (TextView) v.findViewById(R.id.mobile_feature12_details);
        mobile_stock_details = (TextView) v.findViewById(R.id.mobile_stock_details);
        cart = (Button) v.findViewById(R.id.Bcart);
        session = new LoginSession(getContext());
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("Category").child(name);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Fashion mobile = dataSnapshot.getValue(Fashion.class);

                String currentString = mobile.Size;
                String[] size = currentString.split(",");

                for (int i = 1; i < size.length; i++) {
                    array[i].setText(size[i]);
                    array1[i].setVisibility(View.VISIBLE);

                }


                mobile_name_details.setText(mobile.Name);
                String str = "\u20B9";
                rewards = mobile.Cashback;
                mobile_price_details.setText(str + mobile.Price);
                String str1 = "Get Upto " + mobile.Cashback + " Reward Points";
                mobile_cashback_details.setText(str1);
                String str2 = "\u2022";

                if (!mobile.Feature1.equals("")) {
                    mobile_feature1_details.setText(str2 + mobile.Feature1);
                }
                if (!mobile.Feature2.equals("")) {
                    mobile_feature2_details.setText(str2 + mobile.Feature2);
                }
                if (!mobile.Feature3.equals("")) {
                    mobile_feature3_details.setText(str2 + mobile.Feature3);
                }
                if (!mobile.Feature4.equals("")) {
                    mobile_feature4_details.setText(str2 + mobile.Feature4);
                }
                if (!mobile.Feature5.equals("")) {
                    mobile_feature5_details.setText(str2 + mobile.Feature5);
                }
                if (!mobile.Feature6.equals("")) {
                    mobile_feature6_details.setText(str2 + mobile.Feature6);
                }
                if (!mobile.Feature7.equals("")) {
                    mobile_feature7_details.setText(str2 + mobile.Feature7);
                }
                if (!mobile.Feature8.equals("")) {
                    mobile_feature8_details.setText(str2 + mobile.Feature8);
                }
                if (!mobile.Feature9.equals("")) {
                    mobile_feature9_details.setText(str2 + mobile.Feature9);
                }
                if (!mobile.Feature10.equals("")) {
                    mobile_feature10_details.setText(str2 + mobile.Feature10);
                }
                if (!mobile.Feature11.equals("")) {
                    mobile_feature11_details.setText(str2 + mobile.Feature11);
                }
                if (!mobile.Feature12.equals("")) {
                    mobile_feature12_details.setText(str2 + mobile.Feature12);
                }

                if (mobile.Stock.equalsIgnoreCase("In Stock")) {
                    mobile_stock_details.setText(mobile.Stock);
                    mobile_stock_details.setTextColor(Color.parseColor("#23C348"));
                }
                if (mobile.Stock.equalsIgnoreCase("Out of Stock")) {
                    mobile_stock_details.setText(mobile.Stock);
                    cart.setClickable(false);
                    mobile_stock_details.setTextColor(Color.parseColor("#E90303"));
                }

                sampleimages[0] = mobile.Image1;
                sampleimages[1] = mobile.Image2;
                sampleimages[2] = mobile.Image3;

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

                            if(sizeselected==0)
                            {
                                Toast.makeText(getContext(),"Please Select Size",Toast.LENGTH_SHORT).show();
                                return;
                            }
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child("+91" + session.getusename()).child("Cart");
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        // get total available quest
                                        int size = (int) dataSnapshot.getChildrenCount();
                                        size++;
                                        String abc = "CART" + Integer.toString(size);
                                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child("+91" + session.getusename()).child("Cart").push();
                                        ref.child("Pushid").setValue(ref.getKey().toString());
                                        ref.child("Image1").setValue(sampleimages[0]);
                                        ref.child("Userid").setValue("+91" + session.getusename());
                                        ref.child("ProductName").setValue(mobile_name_details.getText().toString());
                                        ref.child("Mrp").setValue(mobile_price_details.getText().toString());
                                        ref.child("Rewards").setValue(rewards);
                                        ref.child("OrderNo").setValue(abc);
                                        ref.child("OrderStatus").setValue("Processing");
                                        Date currentDate = new Date(System.currentTimeMillis());
                                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                        String date1 = df.format(currentDate);
                                        ref.child("Date").setValue(date1);
                                        ref.child("Size").setValue(array[sizeselected].getText());
                                        ref.child("Quantity").setValue("1");
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setTitle("Sucessful");
                                        builder.setMessage("Your Cart Number is " + abc)
                                                .setCancelable(false)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        fragment = new CartFragment();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l1:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 1) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 1;
                    }
                }
                break;
            case R.id.l2:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 2) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 2;
                    }
                }
                break;
            case R.id.l3:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 3) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 3;
                    }
                }
                break;
            case R.id.l4:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 4) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 4;
                    }
                }
                break;
            case R.id.l5:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 5) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 5;
                    }
                }
                break;
            case R.id.l6:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 6) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 6;
                    }
                }
                break;
            case R.id.l7:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 7) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 7;
                    }
                }
                break;
            case R.id.l8:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 8) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 8;
                    }
                }
                break;
            case R.id.l9:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 9) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 9;
                    }
                }
                break;
            case R.id.l10:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 10) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 10;
                    }
                }
                break;
            case R.id.l11:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 11) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 11;
                    }
                }
                break;
            case R.id.l12:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 12) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 12;
                    }
                }
                break;
            case R.id.l13:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 13) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 13;
                    }
                }
                break;
            case R.id.l14:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 14) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 14;
                    }
                }
                break;
            case R.id.l15:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 15) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 15;
                    }
                }
                break;
            case R.id.l16:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 16) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 16;
                    }
                }
                break;
            case R.id.l17:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 17) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 17;
                    }
                }
                break;
            case R.id.l18:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 18) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 18;
                    }
                }
                break;
            case R.id.l19:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 19) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 19;
                    }
                }
                break;
            case R.id.l20:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 20) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 20;
                    }
                }
                break;
            case R.id.l21:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 21) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 21;
                    }
                }
                break;
            case R.id.l22:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 22) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 22;
                    }
                }
                break;
            case R.id.l23:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 23) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 23;
                    }
                }
                break;
            case R.id.l24:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 24) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 24;
                    }
                }
                break;
            case R.id.l25:
                for (int i = 1; i < 26; i++) {
                    array1[i].setBackgroundResource(R.drawable.cardview_border1);
                    if (i == 25) {
                        array1[i].setBackgroundResource(R.drawable.cardview_border3);
                        sizeselected = 25;
                    }
                }
                break;


        }
    }
}