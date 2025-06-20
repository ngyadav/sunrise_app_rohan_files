package rohan.groups.sunrise.Address;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import rohan.groups.sunrise.R;

public  class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    private ClickListener mClickListener;

    public ViewHolder(View itemView) {

        super(itemView);
        mView = itemView;
        //Item Click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        //Item Long Click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    public void setDetails(Context ctx,String Name,String Flat,String Landmark,String Pushid,String Location,String LocationCoord,String Pincode,String Number) {

        TextView name = (TextView) mView.findViewById(R.id.Name);
        TextView rest = (TextView) mView.findViewById(R.id.Rest);
        TextView flat = (TextView) mView.findViewById(R.id.Flat);
        TextView landmark = (TextView) mView.findViewById(R.id.Landmark);
        TextView location = (TextView) mView.findViewById(R.id.Location);
        TextView locationcoord = (TextView) mView.findViewById(R.id.LocationCoord);
        TextView pincode = (TextView) mView.findViewById(R.id.pincode);
        TextView number = (TextView) mView.findViewById(R.id.number);



        if(!TextUtils.isEmpty(Name)) {
            if (Name.equals("Dummy")) {
                name.setText("Add New Address");
                rest.setVisibility(View.GONE);
            } else {
                name.setText(Name);
                rest.setText(Location + "," + Flat + "," + Landmark);
                flat.setText("Flat/HouseNo: "+Flat);
                landmark.setText("Landmark: "+Landmark);
                location.setText(Location);
                locationcoord.setText(LocationCoord);
                pincode.setText("Pincode :"+Pincode);
                if(!TextUtils.isEmpty(Number))
                    number.setText("Number :"+Number);

            }
        }

    }

    public void setOnClickListener(ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }
}
