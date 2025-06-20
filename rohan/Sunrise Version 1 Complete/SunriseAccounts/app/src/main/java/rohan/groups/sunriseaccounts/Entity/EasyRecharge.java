package rohan.groups.sunriseaccounts.Entity;

public class EasyRecharge {
    public String  PushId;
    public String  UserId;
    public String  UserBalance;
    public String  Date;
    public String  Amount;
    public String  Generated;
    public String  TransactionType;
    public String  TransactionName;
    public String  Status;


    public  EasyRecharge(){}

    public EasyRecharge(String PushId,String UserId,String UserBalance,String Date,String Amount,String Generated,String TransactionType,String TransactionName,String Status){
        this.PushId=PushId;
        this.UserId=UserId;
        this.UserBalance=UserBalance;
        this.Date=Date;
        this.Amount=Amount;
        this.Generated=Generated;
        this.TransactionType=TransactionType;
        this.TransactionName=TransactionName;
        this.Status=Status;
    }
}
