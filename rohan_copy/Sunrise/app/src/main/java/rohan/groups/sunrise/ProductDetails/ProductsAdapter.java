package rohan.groups.sunrise.ProductDetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>implements Filterable {

    private ArrayList<Product> products;
    private ArrayList<Product> productsfiltered;

    ArrayList<String> iname = new ArrayList<String>();
    ArrayList<String> iprice = new ArrayList<String>();


    public ProductsAdapter(ArrayList<Product> products) {

        this.products = products;
        this.productsfiltered = products;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=(View) LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Product product=productsfiltered.get(position);


        holder.name.setText(product.Name);
        holder.desc.setText(product.Desc);
        holder.pushid.setText(product.PushId);
//        holder.units.setText(product.Units);
        Glide.with(holder.view.getContext())
                .load(product.Image)
                .into(holder.image);




        int count=0;
        int selection=0;


        final Session session = new Session(holder.view.getContext());
        final ArrayList<String> spinner1 = new ArrayList<String>();
        spinner1.clear();


        int mainno=-1;

        iname=session.getitemname("INAME");
        iprice=session.getitemname("IPRICE");
        String a[]=new String[iname.size()];
        a=iname.toArray(a);
        String b[]=new String[iprice.size()];
        b=iname.toArray(b);
        for (int i=0;i<a.length;i++) {
            if (a[i].contains(product.Name)) {
                holder.add.setVisibility(View.GONE);
                holder.count1.setVisibility(View.VISIBLE);
                String a100[] = a[i].split(":");
                holder.qty.setText(a100[1]);
                mainno = i;
                break;
            }
        }




        if(product.Units.equals("kilogram")){


                if(Integer.parseInt(product.W1)>0){
                    spinner1.add("\u20b9"+product.W1+" / 1 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W2)>0){
                    spinner1.add("\u20b9"+product.W2+" / 2 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W3)>0){
                    spinner1.add("\u20b9"+product.W3+" / 3 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W4)>0){
                    spinner1.add("\u20b9"+product.W4+" / 5 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W5)>0){
                    spinner1.add("\u20b9"+product.W5+" / 6 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W6)>0){
                    spinner1.add("\u20b9"+product.W6+" / 10 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W7)>0){
                    spinner1.add("\u20b9"+product.W7+" / 15 Kg");
                    count++;
                }
                if(Integer.parseInt(product.W8)>0){
                    spinner1.add("\u20b9"+product.W8+" / 25 Kg");
                    count++;
                }

                if(count==0){
                    holder.stock.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);
                   
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(holder.view.getContext(), R.layout.spinner, spinner1);
                holder.spinner.setAdapter(adapter);



                if(mainno>-1) {

                    String x ="\u20b9" +iprice.get(mainno);
                    if (spinner1.indexOf(x) > -1) {


                        holder.spinner.setSelection(spinner1.indexOf(x));

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                    } else {

                        holder.add.setVisibility(View.VISIBLE);
                        holder.count1.setVisibility(View.GONE);
                        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                        int q=Integer.parseInt(holder.qty.getText().toString());

                        double ctot = Double.parseDouble(session.getcarttotal());
                        ctot -= q*Double.parseDouble(holder.price.getText().toString());
                        session.setcarttotal(""+ctot);

                        int cqty = Integer.parseInt(session.getcartitem());
                        cqty -= q;
                        session.setcartitem(Integer.toString(cqty));





                        iname = session.getitemname("INAME");
                        iprice = session.getitemname("IPRICE");

                        iname.remove(mainno);
                        iprice.remove(mainno);


                        holder.qty.setText("1");

                        session.setitemprice(iprice,"IPRICE");
                        session.setitemname(iname,"INAME");

                    }
                }
                else{
                    String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                    holder.price.setText(z[0].substring(1));
                    holder.units.setText(z[1]);
                }



            }

        else if(product.Units.equals("gram")){


                if(Integer.parseInt(product.W1)>0){
                    spinner1.add("\u20b9"+product.W1+" / 10 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W2)>0){
                    spinner1.add("\u20b9"+product.W2+" / 25 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W3)>0){
                    spinner1.add("\u20b9"+product.W3+" / 50 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W4)>0){
                    spinner1.add("\u20b9"+product.W4+" / 100 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W5)>0){
                    spinner1.add("\u20b9"+product.W5+" / 200 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W6)>0){
                    spinner1.add("\u20b9"+product.W6+" / 250 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W7)>0){
                    spinner1.add("\u20b9"+product.W7+" / 500 Gm");
                    count++;
                }
                if(Integer.parseInt(product.W8)>0){
                    spinner1.add("\u20b9"+product.W8+" / 750 Gm");
                    count++;
                }

                if(count==0){
                    holder.stock.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(holder.view.getContext(), R.layout.spinner, spinner1);
                holder.spinner.setAdapter(adapter);

                if(mainno>-1) {

                    String x ="\u20b9" +iprice.get(mainno);
                    if (spinner1.indexOf(x) > -1) {


                        holder.spinner.setSelection(spinner1.indexOf(x));

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                    } else {

                        holder.add.setVisibility(View.VISIBLE);
                        holder.count1.setVisibility(View.GONE);
                        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                        int q=Integer.parseInt(holder.qty.getText().toString());

                        double ctot = Double.parseDouble(session.getcarttotal());
                        ctot -= q*Double.parseDouble(holder.price.getText().toString());
                        session.setcarttotal(""+ctot);

                        int cqty = Integer.parseInt(session.getcartitem());
                        cqty -= q;
                        session.setcartitem(Integer.toString(cqty));





                        iname = session.getitemname("INAME");
                        iprice = session.getitemname("IPRICE");

                        iname.remove(mainno);
                        iprice.remove(mainno);


                        holder.qty.setText("1");

                        session.setitemprice(iprice,"IPRICE");
                        session.setitemname(iname,"INAME");

                    }
                }
                else{
                    String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                    holder.price.setText(z[0].substring(1));
                    holder.units.setText(z[1]);
                }


            }


        else if(product.Units.equals("litre")){


                if(Integer.parseInt(product.W1)>0){
                    spinner1.add("\u20b9"+product.W1+" / 1 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W2)>0){
                    spinner1.add("\u20b9"+product.W2+" / 2 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W3)>0){
                    spinner1.add("\u20b9"+product.W3+" / 3 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W4)>0){
                    spinner1.add("\u20b9"+product.W4+" / 5 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W5)>0){
                    spinner1.add("\u20b9"+product.W5+" / 6 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W6)>0){
                    spinner1.add("\u20b9"+product.W6+" / 10 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W7)>0){
                    spinner1.add("\u20b9"+product.W7+" / 15 Ltr");
                    count++;
                }
                if(Integer.parseInt(product.W8)>0){
                    spinner1.add("\u20b9"+product.W8+" / 25 Ltr");
                    count++;
                }

                if(count==0){
                    holder.stock.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(holder.view.getContext(), R.layout.spinner, spinner1);
                holder.spinner.setAdapter(adapter);

                if(mainno>-1) {

                    String x ="\u20b9" +iprice.get(mainno);
                    if (spinner1.indexOf(x) > -1) {


                        holder.spinner.setSelection(spinner1.indexOf(x));

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                    } else {

                        holder.add.setVisibility(View.VISIBLE);
                        holder.count1.setVisibility(View.GONE);
                        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                        int q=Integer.parseInt(holder.qty.getText().toString());

                        double ctot = Double.parseDouble(session.getcarttotal());
                        ctot -= q*Double.parseDouble(holder.price.getText().toString());
                        session.setcarttotal(""+ctot);

                        int cqty = Integer.parseInt(session.getcartitem());
                        cqty -= q;
                        session.setcartitem(Integer.toString(cqty));





                        iname = session.getitemname("INAME");
                        iprice = session.getitemname("IPRICE");

                        iname.remove(mainno);
                        iprice.remove(mainno);


                        holder.qty.setText("1");

                        session.setitemprice(iprice,"IPRICE");
                        session.setitemname(iname,"INAME");

                    }
                }
                else{
                    String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                    holder.price.setText(z[0].substring(1));
                    holder.units.setText(z[1]);
                }
        }
        else if(product.Units.equals("ml")){

                if(Integer.parseInt(product.W1)>0){
                    spinner1.add("\u20b9"+product.W1+" / 10 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W2)>0){
                    spinner1.add("\u20b9"+product.W2+" / 25 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W3)>0){
                    spinner1.add("\u20b9"+product.W3+" / 50 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W4)>0){
                    spinner1.add("\u20b9"+product.W4+" / 100 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W5)>0){
                    spinner1.add("\u20b9"+product.W5+" / 200 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W6)>0){
                    spinner1.add("\u20b9"+product.W6+" / 250 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W7)>0){
                    spinner1.add("\u20b9"+product.W7+" / 500 Ml");
                    count++;
                }
                if(Integer.parseInt(product.W8)>0){
                    spinner1.add("\u20b9"+product.W8+" / 750 Ml");
                    count++;
                }

                if(count==0){
                    holder.stock.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(holder.view.getContext(), R.layout.spinner, spinner1);
                holder.spinner.setAdapter(adapter);

                if(mainno>-1) {

                    String x ="\u20b9" +iprice.get(mainno);
                    if (spinner1.indexOf(x) > -1) {


                        holder.spinner.setSelection(spinner1.indexOf(x));

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                    } else {

                        holder.add.setVisibility(View.VISIBLE);
                        holder.count1.setVisibility(View.GONE);
                        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                        int q=Integer.parseInt(holder.qty.getText().toString());

                        double ctot = Double.parseDouble(session.getcarttotal());
                        ctot -= q*Double.parseDouble(holder.price.getText().toString());
                        session.setcarttotal(""+ctot);

                        int cqty = Integer.parseInt(session.getcartitem());
                        cqty -= q;
                        session.setcartitem(Integer.toString(cqty));





                        iname = session.getitemname("INAME");
                        iprice = session.getitemname("IPRICE");

                        iname.remove(mainno);
                        iprice.remove(mainno);


                        holder.qty.setText("1");

                        session.setitemprice(iprice,"IPRICE");
                        session.setitemname(iname,"INAME");

                    }
                }
                else{
                    String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                    holder.price.setText(z[0].substring(1));
                    holder.units.setText(z[1]);
                }


        }
        else if(product.Units.equals("peices")){

                if(Integer.parseInt(product.W1)>0){
                    spinner1.add("\u20b9"+product.W1+" / 1 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W2)>0){
                    spinner1.add("\u20b9"+product.W2+" / 2 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W3)>0){
                    spinner1.add("\u20b9"+product.W3+" / 3 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W4)>0){
                    spinner1.add("\u20b9"+product.W4+" / 5 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W5)>0){
                    spinner1.add("\u20b9"+product.W5+" / 6 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W6)>0){
                    spinner1.add("\u20b9"+product.W6+" / 10 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W7)>0){
                    spinner1.add("\u20b9"+product.W7+" / 12 Pcs");
                    count++;
                }
                if(Integer.parseInt(product.W8)>0){
                    spinner1.add("\u20b9"+product.W8+" / 30 Pcs");
                    count++;
                }

                if(count==0){
                    holder.stock.setVisibility(View.VISIBLE);
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);

                }


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(holder.view.getContext(), R.layout.spinner, spinner1);
                holder.spinner.setAdapter(adapter);


                if(mainno>-1) {

                    String x ="\u20b9" +iprice.get(mainno);
                    if (spinner1.indexOf(x) > -1) {


                        holder.spinner.setSelection(spinner1.indexOf(x));

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                    } else {

                        holder.add.setVisibility(View.VISIBLE);
                        holder.count1.setVisibility(View.GONE);
                        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").child(holder.pushid.getText().toString()).removeValue();

                        String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                        holder.price.setText(z[0].substring(1));
                        holder.units.setText(z[1]);

                        int q=Integer.parseInt(holder.qty.getText().toString());

                        double ctot = Double.parseDouble(session.getcarttotal());
                        ctot -= q*Double.parseDouble(holder.price.getText().toString());
                        session.setcarttotal(""+ctot);

                        int cqty = Integer.parseInt(session.getcartitem());
                        cqty -= q;
                        session.setcartitem(Integer.toString(cqty));





                        iname = session.getitemname("INAME");
                        iprice = session.getitemname("IPRICE");

                        iname.remove(mainno);
                        iprice.remove(mainno);


                        holder.qty.setText("1");

                        session.setitemprice(iprice,"IPRICE");
                        session.setitemname(iname,"INAME");

                    }
                }
                else{
                    String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                    holder.price.setText(z[0].substring(1));
                    holder.units.setText(z[1]);
                }

        }

        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery");



        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {

                iname=session.getitemname("INAME");
                iprice=session.getitemname("IPRICE");
                int tempno=0,count=0;
                String a1[]=new String[iname.size()];
                a1=iname.toArray(a1);
                for (int i=0;i<a1.length;i++) {
                    if (a1[i].contains(holder.name.getText().toString())) {
                        count++;
                        tempno=i;
                    }
                }

               if(count>0){

                   double base = 0, diff = 0;

                   base = Double.parseDouble(holder.price.getText().toString());

                   String temp[]=holder.spinner.getSelectedItem().toString().split(" / ");
                   holder.units.setText(temp[1]);
                   holder.price.setText(temp[0].substring(1));


                   iprice.set(tempno,holder.price.getText().toString()+" / "+holder.units.getText().toString());
                   session.setitemname(iprice,"IPRICE");


                   diff = base - Double.parseDouble(temp[0].substring(1));

                   double ctot = Double.parseDouble(session.getcarttotal());
                   ctot -=(diff*Integer.parseInt(holder.qty.getText().toString()));
                   session.setcarttotal(""+ctot);

                   double tot = Double.parseDouble(holder.price.getText().toString()) * Integer.parseInt(holder.qty.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Units").setValue(holder.units.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Price").setValue(holder.price.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                   mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                   mref.child(holder.pushid.getText().toString()).child("Image").setValue(product.Image);

               }
               else{

                   String z[]=holder.spinner.getSelectedItem().toString().split(" / ");
                   holder.units.setText(z[1]);
                   holder.price.setText(z[0].substring(1));




               }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.count1.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.GONE);

                double ctot = Double.parseDouble(session.getcarttotal());
                ctot += Double.parseDouble(holder.price.getText().toString());
                session.setcarttotal(""+ctot);

                int cqty = Integer.parseInt(session.getcartitem());
                cqty++;
                session.setcartitem(Integer.toString(cqty));

                iname = session.getitemname("INAME");
                iprice = session.getitemname("IPRICE");
                String temp1 = holder.name.getText().toString() +":1";
                iname.add(temp1);
                iprice.add(holder.price.getText().toString()+" / "+holder.units.getText().toString());
                session.setitemname(iname, "INAME");
                session.setitemname(iprice, "IPRICE");

                double tot = Double.parseDouble(holder.price.getText().toString()) * Integer.parseInt(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Units").setValue(holder.units.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Price").setValue(holder.price.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Image").setValue(product.Image);

            }
        });




        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int a = Integer.parseInt(holder.qty.getText().toString());
                a--;
                if (a == 0) {
                    holder.add.setVisibility(View.VISIBLE);
                    holder.count1.setVisibility(View.GONE);

                    double ctot = Double.parseDouble(session.getcarttotal());
                    ctot -= Double.parseDouble(holder.price.getText().toString());
                    session.setcarttotal(""+ctot);

                    int cqty = Integer.parseInt(session.getcartitem());
                    cqty--;
                    session.setcartitem(Integer.toString(cqty));

                    iname=session.getitemname("INAME");
                    iprice=session.getitemname("IPRICE");
                    int tempno=0,count=0;
                    String a1[]=new String[iname.size()];
                    a1=iname.toArray(a1);
                    for (int i=0;i<a1.length;i++) {
                        if (a1[i].contains(holder.name.getText().toString())) {
                            tempno=i;
                            count++;
                        }
                    }

                    iname.remove(tempno);
                    iprice.remove(tempno);
                    session.setitemname(iname,"INAME");
                    session.setitemname(iprice,"IPRICE");

                    mref.child(holder.pushid.getText().toString()).removeValue();


                }
                else{
                   holder.qty.setText("" + a);

                    double ctot = Double.parseDouble(session.getcarttotal());
                    ctot -= Double.parseDouble(holder.price.getText().toString());
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
                    String temp1=holder.price.getText().toString()+" / "+holder.units.getText().toString();
                    iname.set(tempno,temp);
                    iprice.set(tempno,temp1);
                    session.setitemname(iname,"INAME");
                    session.setitemprice(iprice,"IPRICE");



                    double tot = Double.parseDouble(holder.price.getText().toString()) * Integer.parseInt(holder.qty.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Units").setValue(holder.units.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Price").setValue(holder.price.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                    mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                    mref.child(holder.pushid.getText().toString()).child("Image").setValue(product.Image);
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
                ctot += Double.parseDouble(holder.price.getText().toString());
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
                String temp1=holder.price.getText().toString()+" / "+holder.units.getText().toString();
                iname.set(tempno,temp);
                iprice.set(tempno,temp1);
                session.setitemname(iname,"INAME");
                session.setitemprice(iprice,"IPRICE");




                double tot = Double.parseDouble(holder.price.getText().toString()) * Integer.parseInt(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Name").setValue(holder.name.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Qty").setValue(holder.qty.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Units").setValue(holder.units.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Price").setValue(holder.price.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Total").setValue("" + tot);
                mref.child(holder.pushid.getText().toString()).child("PushId").setValue(holder.pushid.getText().toString());
                mref.child(holder.pushid.getText().toString()).child("Image").setValue(product.Image);

            }
        });



    }
//
//    public void removeItem(int position) {
//        slit.remove(position);
//        notifyItemRemoved(position);
//    }

    @Override
    public int getItemCount() {
        if(products!=null){
            return products.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    productsfiltered = products;
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product row : products) {
                        if (row.Name.toLowerCase().contains(charString.toLowerCase()) || row.Desc.toLowerCase().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    productsfiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = productsfiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productsfiltered = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        public final View view;
        TextView name;
        TextView desc;
        TextView pushid;
        TextView stock;
        TextView units;
        ImageView image;
        Spinner spinner;
        LinearLayout count1,minus,plus;
        TextView qty;
        TextView price;
        TextView add;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
             name = view.findViewById(R.id.name);
             desc = view.findViewById(R.id.desc);
             pushid = view.findViewById(R.id.pushid);
             stock = view.findViewById(R.id.stock);
             units = view.findViewById(R.id.units);
             image = view.findViewById(R.id.image);
             spinner = view.findViewById(R.id.spinner);
             count1 = view.findViewById(R.id.count);
             qty = view.findViewById(R.id.qty);
             price = view.findViewById(R.id.price);
             add = view.findViewById(R.id.add);
             count1 = view.findViewById(R.id.count);
             minus = view.findViewById(R.id.minus);
             plus = view.findViewById(R.id.plus);

        }


        @Override
        public void onClick(View v) {
        }
    }

}



