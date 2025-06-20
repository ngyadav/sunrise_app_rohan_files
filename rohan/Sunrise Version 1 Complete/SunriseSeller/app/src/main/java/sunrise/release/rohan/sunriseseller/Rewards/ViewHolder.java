package sunrise.release.rohan.sunriseseller.Rewards;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import sunrise.release.rohan.sunriseseller.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    TextView Rewardsname,Rewardsid,Rewardsdate,Rewardsdetails,Rewardsorder,Rewardsamount,Rewardsbalance;
    ImageView Rewardsarrow;
    CardView Rewardscard;
    //    private  ViewHolder.ClickListener mClickListener;
    public  ViewHolder(View itemView)
    {

        super(itemView);
        mView=itemView;
        //Item Click
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mClickListener.onItemClick(v,getAdapterPosition());
//            }
//        });
//
//        //Item Long Click
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mClickListener.onItemLongClick(v,getAdapterPosition());
//                return true;
//            }
//        });
    }

    public void setDetails(Context ctx, String Amount, String Balance, String Date, String Name, String TransactionId, String Type, String OrderNumber, int position){

        Rewardsname=(TextView)mView.findViewById(R.id.RewardsName);
        Rewardsid=(TextView)mView.findViewById(R.id.Rewardsid);
        Rewardsdate=(TextView)mView.findViewById(R.id.Rewardsdate);
        Rewardsdetails=(TextView)mView.findViewById(R.id.Rewardsdetails);
        Rewardsorder=(TextView)mView.findViewById(R.id.Rewardsorder);
        Rewardsamount=(TextView)mView.findViewById(R.id.Rewardsamount);
        Rewardsbalance=(TextView)mView.findViewById(R.id.Rewardsbalance);
        Rewardsarrow=(ImageView)mView.findViewById(R.id.Rewardsarrow);
        Rewardscard=(CardView)mView.findViewById(R.id.RewardsCard);
        String str="\u20B9";
        Rewardsname.setText(Name);
        Rewardsid.setText(TransactionId);
        Rewardsdate.setText(Date);
        Rewardsdetails.setText(Name);
        if(!Name.equalsIgnoreCase("Order")){
            Rewardsorder.setVisibility(View.GONE);
            Rewardsarrow.setVisibility(View.GONE);
        }
        else{
            Rewardsorder.setText(OrderNumber);
        }
        if(Type.equalsIgnoreCase("Cr")) {
            Rewardsamount.setText(str + Amount);
            Rewardsamount.setTextColor(Color.parseColor("#23C348"));
        }
        else{
            Rewardsamount.setText(str + Amount);
            Rewardsamount.setTextColor(Color.parseColor("#E90303"));
        }
        Rewardsbalance.setText(str+Balance);

        if(position%2!=0)
        {
            Rewardscard.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
        }else{
            Rewardscard.setCardBackgroundColor(Color.parseColor("#CCC9C9"));
        }



    }



//        public interface ClickListener{
//            void onItemClick(View v,int position);
//            void onItemLongClick(View v,int position);
//        }
//    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
//        mClickListener=clickListener;
//
//    }
}
