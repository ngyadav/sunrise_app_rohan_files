package sunrise.release.rohan.sunrise.DatabaseFetch;

public class Profile {
   public  String Email,Name,Password,ProfileUrl,Referral,Role,Status,Phone;

    public Profile(){}

    public Profile(String Email,String Name,String Password,String ProfileUrl,String Referral,String Role,String Status,String Phone)
    {
        this.Email=Email;
        this.Name=Name;
        this.Password=Password;
        this.ProfileUrl=ProfileUrl;
        this.Referral=Referral;
        this.Role=Role;
        this.Status=Status;
        this.Phone=Phone;
    }
}
