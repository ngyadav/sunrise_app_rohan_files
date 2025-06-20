package sunrise.release.rohan.sunrise.DatabaseFetch;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sunrise.release.rohan.sunrise.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mView;
    private  ViewHolder.ClickListener mClickListener;
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

    public void setDetails(Context ctx,String Email,String Name,String Profile,String Referral,String Role,String Status,String Phone)
    {
        TextView mName=(TextView)mView.findViewById(R.id.rNameTV);
        TextView mEmail=(TextView)mView.findViewById(R.id.rEmailTV);
        TextView mStatus=(TextView)mView.findViewById(R.id.rStatusTV);
        TextView mReferral=(TextView)mView.findViewById(R.id.rReferralTv);
        TextView mRole=(TextView)mView.findViewById(R.id.rRoleTv);
        TextView mPhone=(TextView)mView.findViewById(R.id.rNumberTv);
        ImageView mProfile=(ImageView)mView.findViewById(R.id.rProfileIV);

        LinearLayout mLinearLayout=(LinearLayout)mView.findViewById(R.id.rLinearLayout);
        mName.setText(Name);
        mEmail.setText(Email);
        mReferral.setText(Referral);
        mStatus.setText(Status);
        mRole.setText(Role);
        mPhone.setText(Phone);
        Glide.with(ctx)
             .load(Profile)
              .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(mProfile);

    }

    public interface ClickListener{
        void onItemClick(View v,int position);
        void onItemLongClick(View v,int position);
    }

    public  void setOnClickListener(ViewHolder.ClickListener clickListener){
            mClickListener=clickListener;

    }
}
