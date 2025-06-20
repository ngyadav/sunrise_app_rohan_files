package sunrise.release.rohan.sunrise.HomeAppliances;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sunrise.release.rohan.sunrise.R;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView mobile_name,mobile_price,mobile_cashback;
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

    public void setDetails(Context ctx,String Name,String Price,String Cashback,String Image1,String Category,String CategoryName,String Feature1){
        TextView mobile_feature=(TextView)mView.findViewById(R.id.mobile_featureTV);
        mobile_feature.setText(Feature1);

        cardview=(CardView)mView.findViewById(R.id.cardveiewmobile);
        mobile_name=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_nameTV);
        mobile_cashback=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_cashbackTV);
        mobile_price=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_priceTV);
        mobile_pic=(ImageView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_picIV);



        String str="\u20B9";
        mobile_name.setText(Name);
        mobile_price.setText(str+Price);
        mobile_cashback.setText(" "+Cashback+" Reward Points");
        Glide.with(ctx)
                .load(Image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mobile_pic);
    }
    public void setDetails1(Context ctx,String Name,String Price,String Cashback,String Image1,String Category,String CategoryName,String Searchtext,String Feature1){
        TextView mobile_feature=(TextView)mView.findViewById(R.id.mobile_featureTV);
        mobile_feature.setText(Feature1);
        cardview=(CardView)mView.findViewById(R.id.cardveiewmobile);
        mobile_name=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_nameTV);
        mobile_cashback=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_cashbackTV);
        mobile_price=(TextView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_priceTV);
        mobile_pic=(ImageView)mView.findViewById(sunrise.release.rohan.sunrise.R.id.mobile_picIV);

        Searchtext=Searchtext.toUpperCase();
        if(Searchtext==null){
            Searchtext="empty";
        }


        if(!TextUtils.isEmpty(Searchtext)) {
            if (CategoryName.contains(Searchtext)) {
                cardview.setVisibility(View.VISIBLE);
            } else {
                cardview.setVisibility(View.GONE);
                cardview.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        }


        String str="\u20B9";
        mobile_name.setText(Name);
        mobile_price.setText(str+Price);
        mobile_cashback.setText(" "+Cashback+" Reward Points");
        Glide.with(ctx)
                .load(Image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mobile_pic);
    }


    public interface ClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }
    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}