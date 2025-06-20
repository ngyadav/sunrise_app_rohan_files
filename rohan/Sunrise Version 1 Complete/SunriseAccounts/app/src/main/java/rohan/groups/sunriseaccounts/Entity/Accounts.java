package rohan.groups.sunriseaccounts.Entity;

public class Accounts {
    public String  PushId;
    public String  UserId;
    public String  UserBalance;
    public String  Date;
    public String  Amount;
    public String  Generated;
    public String  TransactionParticulars;
    public String  TransactionName;
    public String  TransactionType;
    public String  Status;


    public  Accounts(){}

    public Accounts(String PushId,String UserId,String UserBalance,String Date,String Amount,String Generated,String TransactionParticulars,String TransactionName,String TransactionType,String Status){
        this.PushId=PushId;
        this.UserId=UserId;
        this.UserBalance=UserBalance;
        this.Date=Date;
        this.Amount=Amount;
        this.Generated=Generated;
        this.TransactionParticulars=TransactionParticulars;
        this.TransactionName=TransactionName;
        this.TransactionType=TransactionType;
        this.Status=Status;
    }
}
