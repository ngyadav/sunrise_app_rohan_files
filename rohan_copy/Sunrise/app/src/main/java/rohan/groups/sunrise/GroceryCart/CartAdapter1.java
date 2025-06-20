package rohan.groups.sunrise.GroceryCart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class CartAdapter1 extends RecyclerView.Adapter<CartAdapter1.ViewHolder>{

    ArrayList<Cart1> carts;
    ArrayList<String> iname = new ArrayList<String>();
    ArrayList<String> iprice = new ArrayList<String>();
    Session session;

    public CartAdapter1(ArrayList<Cart1> carts) {
        this.carts = carts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter1.ViewHolder holder, final int position) {

        final Cart1 cart = carts.get(position);

        holder.name.setText(cart.Name);
        holder.pushid.setText(cart.PushId);
        holder.total.setText("\u20b9"+cart.Total);
        holder.units.setText("\u20b9"+cart.Price+" / "+cart.Units);
        holder.qty.setText(cart.Qty);
        Glide.with(holder.view.getContext())
                .load(cart.Image)
                .into(holder.image);

        session=new Session(holder.view.getContext());

        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery");



        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(holder.qty.getText().toString());
                a--;
                if (a == 0) {

                    FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                    carts.remove(position);
                    notifyDataSetChanged();

                    int q=Integer.parseInt(holder.qty.getText().toString());

                    double ctot = Double.parseDouble(session.getcarttotal());
                    ctot -= q*Double.parseDouble(cart.Price);
                    session.setcarttotal(""+ctot);

                    int cqty = Integer.parseInt(session.getcartitem());
                    cqty -= q;
                    session.setcartitem(Integer.toString(cqty));


                    int mainno=-1;

                    iname=session.getitemname("INAME");
                    iprice=session.getitemname("IPRICE");
                    String a1[]=new String[iname.size()];
                    a1=iname.toArray(a1);
                    String b[]=new String[iprice.size()];
                    b=iname.toArray(b);
                    for (int i=0;i<a1.length;i++) {
                        if (a1[i].contains(cart.Name)) {
                            mainno = i;
                            break;
                        }
                    }



                    iname = session.getitemname("INAME");
                    iprice = session.getitemname("IPRICE");

                    iname.remove(mainno);
                    iprice.remove(mainno);


                    holder.qty.setText("1");

                    session.setitemprice(iprice,"IPRICE");
                    session.setitemname(iname,"INAME");

                }
                else{
                    holder.qty.setText("" + a);

                    double ctot = Double.parseDouble(session.getcarttotal());
                    ctot -= Double.parseDouble(cart.Price);
                    session.setcarttotal(""+ctot);

                    int cqty = Integer.parseInt(session.getcartitem());
                    cqty--;
                    session.setcartitem(Integer.toString(cqty));

                    iname=session.getitemname("INAME");
                    iprice = session.getitemname("IPRICE");
                    int tempno=0,count=0;
                    String a1[]=new String[iname.size()];
                    a1=iname.toArray(a1);
                    String b1[]=new String[iprice.size()];
                    b1=iname.toArray(b1);
                    for (int i=0;i<a1.length;i++) {
                        if (a1[i].contains(holder.name.getText().toString())) {
                            tempno=i;
                            count++;
                        }
                    }

                    String temp=holder.name.getText().toString()+":"+holder.qty.getText().toString();
                    String temp1=cart.Price+" / "+cart.Units;
                    iname.set(tempno,temp);
                    iprice.set(tempno,temp1);
                    session.setitemname(iname,"INAME");
                    session.setitemprice(iprice,"IPRICE");



                    double tot = Double.parseDouble(cart.Price) * Integer.parseInt(holder.qty.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Units").setValue(cart.Units);
                    mref.child(holder.pushid.getText().toString()).child("Price").setValue(cart.Price);
                    mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                    mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Image").setValue(cart.Image);
                }


            }
        });


        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(holder.qty.getText().toString());
                a++;
                holder.qty.setText("" + a);

                double ctot = Double.parseDouble(session.getcarttotal());
                ctot += Double.parseDouble(cart.Price);
                session.setcarttotal(""+ctot);

                int cqty = Integer.parseInt(session.getcartitem());
                cqty++;
                session.setcartitem(Integer.toString(cqty));

                iname=session.getitemname("INAME");
                iprice = session.getitemname("IPRICE");
                int tempno=0,count=0;
                String a1[]=new String[iname.size()];
                a1=iname.toArray(a1);
                String b1[]=new String[iprice.size()];
                b1=iname.toArray(b1);
                for (int i=0;i<a1.length;i++) {
                    if (a1[i].contains(holder.name.getText().toString())) {
                        tempno=i;
                        count++;
                    }
                }

                String temp=holder.name.getText().toString()+":"+holder.qty.getText().toString();
                String temp1=cart.Price+" / "+ cart.Units;
                iname.set(tempno,temp);
                iprice.set(tempno,temp1);
                session.setitemname(iname,"INAME");
                session.setitemprice(iprice,"IPRICE");




                double tot = Double.parseDouble(cart.Price) * Integer.parseInt(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Units").setValue(cart.Units);
                mref.child(holder.pushid.getText().toString()).child("Price").setValue(cart.Price);
                mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Image").setValue(cart.Image);

            }
        });




    }


    @Override
    public int getItemCount() {
        if(carts!=null){
            return carts.size();
        }
        else {
            return 0;
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public final View view;
        TextView name,units,total,qty,pushid;
        LinearLayout minus,plus;
        ImageView image;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
             name = view.findViewById(R.id.name);
             total = view.findViewById(R.id.total);
             pushid = view.findViewById(R.id.pushid);
             units = view.findViewById(R.id.units);
             qty = view.findViewById(R.id.qty);
             minus = view.findViewById(R.id.minus);
             plus = view.findViewById(R.id.plus);
             image = view.findViewById(R.id.image);

        }


        @Override
        public void onClick(View v) {
        }
    }

}



