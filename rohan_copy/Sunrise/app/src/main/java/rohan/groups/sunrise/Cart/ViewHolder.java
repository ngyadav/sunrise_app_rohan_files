package rohan.groups.sunrise.Cart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.googledirection.model.Line;
import com.bumptech.glide.Glide;

import rohan.groups.sunrise.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    private  ClickListener mClickListener;
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



    public void setDetails(Context ctx, String ItemName, String Qty, String Price,String Total,String Image1,String PushId,String Color,String Size,String Rewards){

        TextView itemname,itemprice,qtybadge,qtydesc,foodtype,pushid,rewards;
        itemname = (TextView) mView.findViewById(R.id.itemname);
        itemprice = (TextView) mView.findViewById(R.id.itemprice);
        qtydesc = (TextView) mView.findViewById(R.id.itemdesc);
        pushid = (TextView) mView.findViewById(R.id.pushid);
        rewards = (TextView) mView.findViewById(R.id.rewards);
        ImageView indicator=mView.findViewById(R.id.indicator);
        LinearLayout cardviewitem=mView.findViewById(R.id.itemsrow);
        TextView qty1=mView.findViewById(R.id.qty1);



        qty1.setText(Qty);


        String temp="";
        if(!TextUtils.isEmpty(Color)){
            temp+="Color: "+Color;
        }
        if(!TextUtils.isEmpty(Size)){
            temp+="\nSize: "+Size;
        }
        itemname.setText(ItemName);
        qtydesc.setText(temp);

        pushid.setText(PushId);
        itemprice.setText("\u20b9"+Price);
        Glide.with(ctx).load(Image1).into(indicator);


        rewards.setText(Rewards);

    }


    public void setDetails1(Context ctx, String ItemName, String Qty, String Price,String Total,String Image1,String PushId,String Color,String Size,String Rewards){

        TextView itemname,itemprice,qtybadge,qtydesc,foodtype,pushid,rewards;
        itemname = (TextView) mView.findViewById(R.id.itemname);
        itemprice = (TextView) mView.findViewById(R.id.itemprice);
        qtydesc = (TextView) mView.findViewById(R.id.itemdesc);
        pushid = (TextView) mView.findViewById(R.id.pushid);
        rewards = (TextView) mView.findViewById(R.id.rewards);
        ImageView indicator=mView.findViewById(R.id.indicator);
        LinearLayout cardviewitem=mView.findViewById(R.id.itemsrow);
        TextView qty2=mView.findViewById(R.id.qty2);

        LinearLayout row=mView.findViewById(R.id.row);

        final LinearLayout qty=mView.findViewById(R.id.qty);
        final TextView add=mView.findViewById(R.id.add);
        final ImageView minus=mView.findViewById(R.id.minus);
        final ImageView plus=mView.findViewById(R.id.plus);
        final ImageView delete=mView.findViewById(R.id.delete);

        qty.setVisibility(View.GONE);
        add.setVisibility(View.GONE);
        minus.setVisibility(View.GONE);
        plus.setVisibility(View.GONE);
        delete.setVisibility(View.GONE);


        qty2.setText("Quantity:"+Qty);
        qty2.setVisibility(View.VISIBLE);


        rewards.setText(Rewards);

        String temp="";
        if(!TextUtils.isEmpty(Color)){
            temp+="Color: "+Color;
        }
        if(!TextUtils.isEmpty(Size)){
            temp+="\nSize: "+Size;
        }
        itemname.setText(ItemName);
        qtydesc.setText(temp);

        pushid.setText(PushId);
        itemprice.setText("\u20b9"+Price);
        Glide.with(ctx).load(Image1).into(indicator);

    }



    public interface ClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }
    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}

