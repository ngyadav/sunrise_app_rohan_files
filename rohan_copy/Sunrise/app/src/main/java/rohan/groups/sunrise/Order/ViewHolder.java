package rohan.groups.sunrise.Order;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kofigyan.stateprogressbar.StateProgressBar;

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

    public void setDetails(final Context ctx,String OrderNo,String OrderDateTime,String Total,String UserId,String PushId,String Status,String Type){

        TextView orderno=mView.findViewById(R.id.ordernumber);
        TextView total=mView.findViewById(R.id.total);
        TextView date=mView.findViewById(R.id.date);
        TextView pushid=mView.findViewById(R.id.pushid);
        TextView type=mView.findViewById(R.id.type);
        StateProgressBar stateProgressBar=mView.findViewById(R.id.state);



        orderno.setText(OrderNo);
        date.setText(OrderDateTime);
        pushid.setText(PushId);
        total.setText("\u20b9"+Total);
        type.setText(Type);


        String[] descriptionData = {"Placed", "Packed", "Picked", "Shipped","Delivered"};

        stateProgressBar.setStateDescriptionData(descriptionData);

        if(Status.equals("1"))
        {
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);
        }
        else if(Status.equals("2"))
        {
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
        }
        else if(Status.equals("3"))
        {
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
        }
        else if(Status.equals("4"))
        {
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
        }
        else if(Status.equals("5"))
        {
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
        }
        else if(Status.equals("10"))
        {

            String[] descriptionData1 = {"Cancelled"};

            stateProgressBar.setMaxStateNumber(StateProgressBar.StateNumber.ONE);
            stateProgressBar.setStateDescriptionData(descriptionData1);
            stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.ONE);

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





