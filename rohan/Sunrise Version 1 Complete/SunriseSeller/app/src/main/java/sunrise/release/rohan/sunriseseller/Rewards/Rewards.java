package sunrise.release.rohan.sunriseseller.Rewards;

public class Rewards {
    public  String Amount;
    public  String Authorised;
    public  String Date;
    public  String AuthorisedName;
    public  String PushId;
    public  String TransactionId;
    public  String AuthorisedType;
    public  String OrderNumber;
    public  String UserId;
    public String AuthorisedBalance;

    public Rewards(){

    }

    public Rewards(String Amount,String Authorised,String OrderNumber,String AuthorisedBalance,String Date,String AuthorisedName,String PushId,String TransactionId,String AuthorisedType,String UserId){
        this.Amount=Amount;
        this.Authorised=Authorised;
        this.AuthorisedBalance=AuthorisedBalance;
        this.Date=Date;
        this.AuthorisedName=AuthorisedName;
        this.PushId=PushId;
        this.TransactionId=TransactionId;
        this.AuthorisedType=AuthorisedType;
        this.OrderNumber=OrderNumber;
        this.UserId=UserId;
    }
}
