package rohan.groups.sunrise.Products;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import rohan.groups.sunrise.R;


public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView mobile_name,mobile_price,mobile_cashback,pushid;
    ImageView mobile_pic;
    CardView cardview;
    private  ViewHolder.ClickListener mClickListener;
    public  ViewHolder(View itemView)
    {

        super(itemView);
        mView=itemView;
        //Item Click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());
            }
        });

        //Item Long Click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v,getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx,String Name,String Price,String Cashback,String Image1,String Category,String CategoryName,String Feature1,String PushId){
        TextView mobile_feature=(TextView)mView.findViewById(R.id.mobile_featureTV);
        mobile_feature.setText(Feature1);

        cardview=(CardView)mView.findViewById(R.id.cardveiewmobile);
          mobile_name=(TextView)mView.findViewById(R.id.mobile_nameTV);
        mobile_cashback=(TextView)mView.findViewById(R.id.mobile_cashbackTV);
        mobile_price=(TextView)mView.findViewById(R.id.mobile_priceTV);
        mobile_pic=(ImageView)mView.findViewById(R.id.mobile_picIV);
        pushid=(TextView) mView.findViewById(R.id.pushid);



        String str="\u20B9";
        mobile_name.setText(Name);
        mobile_price.setText(str+Price);
        mobile_cashback.setText(" "+Cashback+" Reward Points");
        pushid.setText(PushId);
        Glide.with(ctx)
                .load(Image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mobile_pic);




    }


    public void setDetails1(Context ctx,String Name,String Price,String Cashback,String Image1,String Category,String CategoryName,String Feature1,String PushId,String a,String b){
        TextView mobile_feature=(TextView)mView.findViewById(R.id.mobile_featureTV);
        mobile_feature.setText(Feature1);

        cardview=(CardView)mView.findViewById(R.id.cardveiewmobile);
        mobile_name=(TextView)mView.findViewById(R.id.mobile_nameTV);
        mobile_cashback=(TextView)mView.findViewById(R.id.mobile_cashbackTV);
        mobile_price=(TextView)mView.findViewById(R.id.mobile_priceTV);
        mobile_pic=(ImageView)mView.findViewById(R.id.mobile_picIV);
        pushid=(TextView) mView.findViewById(R.id.pushid);



        String str="\u20B9";
        mobile_name.setText(Name);
        mobile_price.setText(str+Price);
        mobile_cashback.setText(" "+Cashback+" Reward Points");
        pushid.setText(PushId);
        Glide.with(ctx)
                .load(Image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mobile_pic);
        long p = Long.parseLong(Price);

        if(p>=Long.parseLong(a)&&p<=Long.parseLong(b)){
                cardview.setVisibility(View.VISIBLE);
            }
        else if(Long.parseLong(b)==0){
                if(p>=Long.parseLong(a)) {
                    cardview.setVisibility(View.VISIBLE);
                }
                else {
                    cardview.setVisibility(View.GONE);
                    cardview.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                }
            }
        else {
            cardview.setVisibility(View.GONE);
            cardview.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }


    }




    public interface ClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }
    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}
