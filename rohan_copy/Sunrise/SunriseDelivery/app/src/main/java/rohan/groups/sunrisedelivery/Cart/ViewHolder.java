package rohan.groups.sunrisedelivery.Cart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import rohan.groups.sunrisedelivery.R;

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


    public void setDetails(Context ctx, String ItemName, String Qty, String Price,String Total,String Image1,String QtyDescription){

        TextView itemname,itemprice,qtybadge,qtydesc,foodtype;
        itemname = (TextView) mView.findViewById(R.id.itemname);
        itemprice = (TextView) mView.findViewById(R.id.itemprice);
        qtybadge = (TextView) mView.findViewById(R.id.qtybadge);
        qtydesc = (TextView) mView.findViewById(R.id.itemdesc);
        ElegantNumberButton counter=mView.findViewById(R.id.counter);
        ImageView indicator=mView.findViewById(R.id.indicator);
        LinearLayout cardviewitem=mView.findViewById(R.id.itemsrow);


        counter.setNumber(Qty);
        itemname.setText(ItemName);

        if(!TextUtils.isEmpty(QtyDescription))
        {
            qtydesc.setText(QtyDescription);
//            itemprice.setVisibility(View.GONE);
        }
        else {
            qtydesc.setVisibility(View.GONE);
//            itemprice.setVisibility(View.VISIBLE);
        }

        itemprice.setText("\u20b9"+Total);
        qtybadge.setText(Qty);
        Glide.with(ctx).load(Image1).into(indicator);

    }


    public void setDetails1(Context ctx, String ItemName, String Qty, String Price,String Total,String Image1,String QtyDescription){

        TextView itemname,itemprice,qtybadge,itemprofit,foodtype,qtydesc;
        itemname = (TextView) mView.findViewById(R.id.itemname);
        itemprice = (TextView) mView.findViewById(R.id.itemprice);
        qtybadge = (TextView) mView.findViewById(R.id.qtybadge);
        qtydesc = (TextView) mView.findViewById(R.id.itemdesc);
        ImageView indicator=mView.findViewById(R.id.indicator);


        itemname.setText(ItemName);

        if(!TextUtils.isEmpty(QtyDescription))
        {
            qtydesc.setText(QtyDescription);
//            itemprice.setVisibility(View.GONE);
        }
        else {
            qtydesc.setVisibility(View.GONE);
//            itemprice.setVisibility(View.VISIBLE);
        }

        itemprice.setText("\u20b9"+Price);
        qtybadge.setText("+"+Qty);
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

