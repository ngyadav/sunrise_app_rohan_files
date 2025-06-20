package rohan.groups.sunrise.Order;

public class OrderFull {
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
    public String PushId;
    public String Qty;
    public String RazorpayId;
    public String Rewards;
    public String Status;
    public String Total;
    public String UserId;

    public OrderFull(){}

    public OrderFull(String Address,String Amount,String CName,String DeliveryCharges,String Flat,String Items,String Landmark,String LocationCoordinates,
                     String Number,String OrderDate,String OrderDateTime,String OrderNo,String Payment,String PushId,String Qty,String RazorpayId,String Rewards,
                     String Status,String Total,String UserId){
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
        this.OrderDateTime=OrderDateTime;
        this.OrderNo=OrderNo;
        this.Payment=Payment;
        this.PushId=PushId;
        this.Qty=Qty;
        this.RazorpayId=RazorpayId;
        this.Rewards=Rewards;
        this.Status=Status;
        this.Total=Total;
        this.UserId=UserId;
    }
}
