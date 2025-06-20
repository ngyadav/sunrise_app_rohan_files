package sunrise.release.rohan.sunrise.Cart;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sunrise.release.rohan.sunrise.R;

import org.w3c.dom.Text;

public  class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView order_productname,order_mrp,order_rewards,order_date,order_orderno,order_status,dummy,order_quantity,order_size;
    TextView otdate,otstatus,otorderno,otordersize;
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
    public void setDetails(Context ctx, String Name, String Price, String rewards, String Image1, String Status, String orderno, String date,String quantity,String size,String Pushid,String PColor){

        order_productname=(TextView)mView.findViewById(R.id.oproduct);
        order_mrp=(TextView)mView.findViewById(R.id.omrp);
        order_rewards=(TextView)mView.findViewById(R.id.orewards);
        order_status=(TextView)mView.findViewById(R.id.ostatus);
        order_date=(TextView)mView.findViewById(R.id.odate);
        order_orderno=(TextView)mView.findViewById(R.id.oorderno);
        order_size=(TextView)mView.findViewById(R.id.oordersize);
        order_quantity=(TextView)mView.findViewById(R.id.oquantity);
        order_image=(ImageView)mView.findViewById(R.id.oimage);
        dummy=(TextView)mView.findViewById(R.id.dummy);
        otdate=(TextView)mView.findViewById(R.id.otdate);
        otstatus=(TextView)mView.findViewById(R.id.otstatus);
        otorderno=(TextView)mView.findViewById(R.id.otorderno);
        otordersize=(TextView)mView.findViewById(R.id.otordersize);
        TextView dummy1=(TextView)mView.findViewById(R.id.dummy1);

        order_productname.setText(Name);
        order_mrp.setText(Price);
        order_rewards.setText(rewards);
        order_orderno.setText(orderno);
        order_quantity.setText(quantity);
        dummy.setText(Image1);
        order_date.setVisibility(View.GONE);
        order_size.setText(size);
        otdate.setVisibility(View.GONE);
        otstatus.setVisibility(View.GONE);
        dummy.setVisibility(View.GONE);
        order_date.setText(date);
        dummy1.setText(Pushid);
        otorderno.setText("Cart No:");
        if(Status.equalsIgnoreCase("Processing")) {
            order_status.setText(Status);
            order_status.setTextColor(Color.parseColor("#E90303"));
        }
        if(size.equals(""))
        {
            order_size.setVisibility(View.GONE);
            otordersize.setVisibility(View.GONE);
        }
        else{
            order_size.setVisibility(View.VISIBLE);
            otordersize.setVisibility(View.VISIBLE);
        }

        if(!TextUtils.isEmpty(PColor)){
            if(PColor.equals(""))
            {
                order_size.setVisibility(View.GONE);
                otordersize.setVisibility(View.GONE);
            }
            else{
                order_size.setText(PColor);
                order_size.setVisibility(View.VISIBLE);
                otordersize.setVisibility(View.VISIBLE);
            }
        }


        if(Status.equalsIgnoreCase("Delivered")) {
            order_status.setText(Status);
            order_status.setTextColor(Color.parseColor("#23C348"));
        }
        order_status.setVisibility(View.GONE);
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