package sunrise.release.rohan.sunrise.DatabaseFetch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import sunrise.release.rohan.sunrise.R;

public class ViewHolderOffers extends RecyclerView.ViewHolder {

    View mView;
    private  ViewHolder.ClickListener mClickListener;
    public  ViewHolderOffers(View itemView)
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

    public void setDetails(Context ctx, String Image,String Text)
    {
        TextView text=(TextView)mView.findViewById(R.id.offerdetails);
        ImageView mProfile=(ImageView)mView.findViewById(R.id.offersimage);
        text.setText(Text);
        text.setVisibility(View.GONE);
            Glide.with(ctx)
                .load(Image)
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

