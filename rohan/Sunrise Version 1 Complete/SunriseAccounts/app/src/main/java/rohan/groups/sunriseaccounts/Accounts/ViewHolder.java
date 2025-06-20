package rohan.groups.sunriseaccounts.Accounts;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rohan.groups.sunriseaccounts.R;


public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;
    TextView Rewardsname, Rewardsid, Rewardsdate, Rewardsdetails, Rewardsorder, cr,dr, Rewardsbalance;
    ImageView Rewardsarrow;
    CardView Rewardscard;

        private  ViewHolder.ClickListener mClickListener;
    public ViewHolder(View itemView) {

        super(itemView);
        mView = itemView;
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

    public void setDetails(Context ctx, String Date, String Generated, String PushId, String TransactionName, String TransactionType, String UserId, String UserBalance, String Amount, int position,String status,String TransactionParticulars){
        Rewardsname = (TextView) mView.findViewById(R.id.RewardsName);
        Rewardsid = (TextView) mView.findViewById(R.id.Rewardsid);
        Rewardsdate = (TextView) mView.findViewById(R.id.Rewardsdate);
        Rewardsdetails = (TextView) mView.findViewById(R.id.Rewardsdetails);
        Rewardsorder = (TextView) mView.findViewById(R.id.Rewardsorder);
        cr = (TextView) mView.findViewById(R.id.cr);
        dr = (TextView) mView.findViewById(R.id.dr);
        Rewardsbalance = (TextView) mView.findViewById(R.id.Rewardsbalance);
        Rewardscard = (CardView) mView.findViewById(R.id.RewardsCard);
        String str = "\u20B9";


        Rewardsorder.setVisibility(View.GONE);

        Rewardsname.setText(TransactionName);
        Rewardsid.setText(TransactionParticulars);
        Rewardsdate.setText(Date);
        Rewardsdetails.setText(UserId);

        if (status.equals("Approved")) {
            Rewardscard.setVisibility(View.VISIBLE);
        } else {
            Rewardscard.setVisibility(View.GONE);
            Rewardscard.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }

        if (TransactionType.equalsIgnoreCase("Cr")) {
            cr.setText(str + Amount);
            dr.setText("");
        } else {
            dr.setText(str + Amount);
            cr.setText("");
        }
        Rewardsbalance.setText(str + UserBalance);

        if (position % 2 != 0) {
            Rewardscard.setCardBackgroundColor(Color.parseColor("#f0f0f0"));
        } else {
            Rewardscard.setCardBackgroundColor(Color.parseColor("#CCC9C9"));
        }
    }

    public interface ClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }
    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}

