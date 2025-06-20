package sunrise.release.rohan.sunrise.Delivery;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import sunrise.release.rohan.sunrise.R;

public  class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

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
        public void setDetails(Context ctx,String Name,String Number,String Pincode,String Town,String Flat,String Area,String Landmark,String Userid,String Pushid){

            TextView name=(TextView)mView.findViewById(R.id.Name);
            TextView number=(TextView)mView.findViewById(R.id.Phone);
            TextView rest=(TextView)mView.findViewById(R.id.Rest);
            TextView dummy=(TextView)mView.findViewById(R.id.dummy);

            name.setText(Name);
            number.setText(Number);
            String extra=Flat+","+Area+","+Landmark+","+Town+","+Pincode;
            rest.setText(extra);
            dummy.setText(Pushid);



        }

        public void setOnClickListener(ClickListener clickListener) {
            mClickListener=clickListener;
        }
        public interface ClickListener{
            void onItemClick(View v,int position);
            void onItemLongClick(View v,int position);
        }
    }