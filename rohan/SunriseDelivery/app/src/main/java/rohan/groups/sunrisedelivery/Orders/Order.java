package rohan.groups.sunrisedelivery.Orders;

public class Order {

    public String Address;
    public String Amount;
    public String CName;
    public String DeliveryCharges;
    public String Flat;
    public String Items;
    public String Landmark;
    public String LocationCoordinates;
    public String Number;
    public String OrderDate;
    public String OrderDateTime;
    public String OrderNo;
    public String Payment;
    public String Pushid;
    public String Qty;
    public String Status;
    public String Total;
    public String UserId;
    public String Cashback;
    public String Verified;

    public Order(){}

    public  Order(String Address,String Amount,String CName,String DeliveryCharges,String Flat,String Items,String Landmark,String LocationCoordinates,String Number,String OrderDate,String OrderDateTime,String OrderNo,String Payment,String Pushid,String Qty,String Status,String Total,String UserId,String Cashback,String Verified){
        this.Address=Address;
        this.Amount=Amount;
        this.CName=CName;
        this.DeliveryCharges=DeliveryCharges;
        this.Flat=Flat;
        this.Items=Items;
        this.Landmark=Landmark;
        this.LocationCoordinates=LocationCoordinates;
        this.Number=Number;
        this.OrderDate=OrderDate;
        this.OrderNo=OrderNo;
        this.Payment=Payment;
        this.Pushid=Pushid;
        this.Qty=Qty;
        this.Status=Status;
        this.Total=Total;
        this.UserId=UserId;
        this.OrderDateTime=OrderDateTime;
        this.Cashback=Cashback;
        this.Verified=Verified;
    }
}
