package rohan.groups.sunrise.Order;

public class Order {

    public String OrderNo;
    public String OrderDateTime;
    public String Status;
    public String Total;
    public String UserId;
    public String Pushid;
    public String OrderType;


    public Order(){}

    public Order(String OrderNo,String OrderDateTime,String Status,String Total,String UserId,String Pushid,String OrderType){
        this.OrderDateTime=OrderDateTime;
        this.OrderNo=OrderNo;
        this.Status=Status;
        this.Total=Total;
        this.UserId=UserId;
        this.Pushid=Pushid;
        this.OrderType=OrderType;
    }
}
