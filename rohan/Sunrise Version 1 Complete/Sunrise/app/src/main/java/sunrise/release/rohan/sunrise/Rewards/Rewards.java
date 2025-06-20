package sunrise.release.rohan.sunrise.Rewards;

public class Rewards {
    public  String Amount;
    public  String Authorised;
    public  String Balance;
    public  String Date;
    public  String Name;
    public  String PushId;
    public  String TransactionId;
    public  String Type;
    public  String OrderNumber;
    public  String UserId;

    public Rewards(){

    }

    public Rewards(String Amount,String Authorised,String OrderNumber,String Balance,String Date,String Name,String PushId,String TransactionId,String Type,String UserId){
        this.Amount=Amount;
        this.Authorised=Authorised;
        this.Balance=Balance;
        this.Date=Date;
        this.Name=Name;
        this.PushId=PushId;
        this.TransactionId=TransactionId;
        this.Type=Type;
        this.OrderNumber=OrderNumber;
        this.UserId=UserId;
    }
}
