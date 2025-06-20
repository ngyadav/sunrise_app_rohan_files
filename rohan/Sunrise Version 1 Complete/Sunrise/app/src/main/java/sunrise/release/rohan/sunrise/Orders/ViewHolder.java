package sunrise.release.rohan.sunrise.Orders;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.concurrent.TimeoutException;

import sunrise.release.rohan.sunrise.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView order_productname,order_mrp,order_rewards,order_date,order_orderno,order_status,order_quantity;
    ImageView order_image;
    private ClickListener mClickListener;
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

    public void setDetails(Context ctx, String Name, String Price, String rewards, String Image1,String Status,String orderno,String date,String quantity,String DeliveryName,String DeliveryAddress,String DeliveryNumber,String Size,String PColor){

        order_productname=(TextView)mView.findViewById(R.id.oproduct);
        order_mrp=(TextView)mView.findViewById(R.id.omrp);
        order_rewards=(TextView)mView.findViewById(R.id.orewards);
        order_status=(TextView)mView.findViewById(R.id.ostatus);
        order_date=(TextView)mView.findViewById(R.id.odate);
        order_orderno=(TextView)mView.findViewById(R.id.oorderno);
        order_image=(ImageView)mView.findViewById(R.id.oimage);
        order_quantity=(TextView)mView.findViewById(R.id.oquantity);

        TextView otdelivery=(TextView)mView.findViewById(R.id.otdelivery);
        otdelivery.setVisibility(View.VISIBLE);
        TextView odelivery=(TextView)mView.findViewById(R.id.odelivery);
        TextView otordersize=(TextView)mView.findViewById(R.id.otordersize);
        TextView oordersize=(TextView)mView.findViewById(R.id.oordersize);
        if(Size.equals(""))
        {
            oordersize.setVisibility(View.GONE);
            otordersize.setVisibility(View.GONE);
        }
        else{
            oordersize.setVisibility(View.VISIBLE);
            otordersize.setVisibility(View.VISIBLE);
        }

        if(!TextUtils.isEmpty(PColor)){
            if(PColor.equals(""))
            {
                oordersize.setVisibility(View.GONE);
                otordersize.setVisibility(View.GONE);
            }
            else{
                oordersize.setText(PColor);
                oordersize.setVisibility(View.VISIBLE);
                otordersize.setVisibility(View.VISIBLE);
            }
        }

        odelivery.setVisibility(View.VISIBLE);
        oordersize.setText(Size);
        odelivery.setText(DeliveryName+"\n"+DeliveryNumber+"\n"+DeliveryAddress);
        order_productname.setText(Name);
        order_mrp.setText(Price);
        order_rewards.setText(rewards);
        order_orderno.setText(orderno);
        order_date.setText(date);
        order_quantity.setText(quantity);
        if(Status.equalsIgnoreCase("Processing")) {
            order_status.setText(Status);
            order_status.setTextColor(Color.parseColor("#E90303"));
        }
        if(Status.equalsIgnoreCase("Delivered")) {
            order_status.setText(Status);
            order_status.setTextColor(Color.parseColor("#23C348"));
        }
        Glide.with(ctx)
                .load(Image1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(order_image);

    }

    public void setOnClickListener(ClickListener clickListener) {
        mClickListener=clickListener;
    }

    public interface ClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }

}

