package sunrise.release.rohan.sunrise.Orders;

import java.lang.ref.SoftReference;

public class Orders {
    public  String Image1;
    public  String Mrp;
    public  String OrderNo;
    public  String Rewards;
    public  String Date;
    public  String ProductName;
    public  String OrderStatus;
    public  String Userid;
    public  String Quantity;
    public  String DeliveryName;
    public  String DeliveryNumber;
    public  String DeliveryAddress;
    public  String Size;
    public  String Colour;



    public Orders(){}

    public Orders(String Image1,String Mrp,String OrderNo,String Rewards,String Date,String ProductName,String OrderStatus,String Userid,String Quantity,String DeliveryName,String DeliveryAddress,String DeliveryNumber,String Size,String Colour){
        this.Size=Size;
        this.Image1=Image1;
        this.Mrp=Mrp;
        this.OrderNo=OrderNo;
        this.Rewards=Rewards;
        this.Date=Date;
        this.ProductName=ProductName;
        this.OrderStatus=OrderStatus;
        this.Userid=Userid;
        this.Quantity=Quantity;
        this.DeliveryAddress=DeliveryAddress;
        this.DeliveryName=DeliveryName;
        this.DeliveryNumber=DeliveryNumber;
        this.Colour=Colour;
    }
}
