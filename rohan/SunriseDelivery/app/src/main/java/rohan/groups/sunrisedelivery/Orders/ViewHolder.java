package rohan.groups.sunrisedelivery.Orders;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kofigyan.stateprogressbar.StateProgressBar;

import rohan.groups.sunrisedelivery.R;


public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    private  ClickListener mClickListener;
    public ViewHolder(View itemView)
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



    public void setDetails(Context ctx, String Items, String OrderDate, String OrderNo,String Total,String Status,String PushId,String Number,String CName,String LocationCoordinates,String Flat,String Address,String Landmark,String Payment,String Cashback,String UserId,String Verified){

        TextView itemsname,orderdate,orderno,total,status,pushid,payment,cashback,userid,verified;
        itemsname=mView.findViewById(R.id.itemsname);
        orderdate=mView.findViewById(R.id.orderdate);
        orderno=mView.findViewById(R.id.orderno);
        total=mView.findViewById(R.id.total);
        pushid=mView.findViewById(R.id.pushid);
        payment=mView.findViewById(R.id.payment);
        cashback=mView.findViewById(R.id.cashback);
        userid=mView.findViewById(R.id.userid);
        verified=mView.findViewById(R.id.verified);
        CardView book=mView.findViewById(R.id.book);


        TextView cname=mView.findViewById(R.id.cname);
        TextView number=mView.findViewById(R.id.number);
        TextView latlng=mView.findViewById(R.id.latlng);
        TextView address=mView.findViewById(R.id.address);


        number.setText(Number);
        cname.setText(CName);
        latlng.setText(LocationCoordinates);
        address.setText(Flat+","+Landmark+","+Address);

        payment.setText(Payment);

        userid.setText(UserId);

        cashback.setText(Cashback);



        String[] descriptionData = {"Confirmed", "Packed", "Out for Delivery", "Delivered"};

        StateProgressBar stateProgressBar = (StateProgressBar) mView.findViewById(R.id.statusbar);
        stateProgressBar.setStateDescriptionData(descriptionData);


        if(!Status.equals("4"))
        {
            book.setVisibility(View.GONE);
            book.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }


        stateProgressBar.setVisibility(View.GONE);

        verified.setText(Verified);


        if(TextUtils.isEmpty(Items)) {
            itemsname.setText("Grocery");
        }
        else{
            itemsname.setText(Items);
        }
        orderdate.setText(OrderDate);
        orderno.setText(OrderNo);
        total.setText("\u20b9"+Total);
        pushid.setText(PushId);



    }


    public void setDetails1(Context ctx, String Items, String OrderDate, String OrderNo,String Total,String Status,String PushId,String Cashback){

        TextView itemsname,orderdate,orderno,total,status,pushid,cashback;
        itemsname=mView.findViewById(R.id.itemsname);
        orderdate=mView.findViewById(R.id.orderdate);
        orderno=mView.findViewById(R.id.orderno);
        total=mView.findViewById(R.id.total);
        pushid=mView.findViewById(R.id.pushid);
        cashback=mView.findViewById(R.id.cashback);

        cashback.setText(Cashback);



        StateProgressBar stateProgressBar = (StateProgressBar) mView.findViewById(R.id.statusbar);
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

        itemsname.setText(Items);
        orderdate.setText(OrderDate);
        orderno.setText(OrderNo);
        total.setText("\u20b9"+Total);
        pushid.setText(PushId);



    }


    public interface ClickListener{
        void onItemClick(View v, int position);
        void onItemLongClick(View v, int position);
    }
    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener=clickListener;

    }
}

