package rohan.groups.sunrise.Cart;


public class Cart {
    public String ItemName;
    public String Qty;
    public String Price;
    public String Total;
    public String Image;
    public String PushId;
    public String Color;
    public String Size;
    public String Rewards;


    public Cart() {
    }

    public Cart(String ItemName,String Qty,String Price,String Total,String Image,String PushId,String Color,String Size,String Rewards) {

        this.Image=Image;
        this.Qty=Qty;
        this.Price=Price;
        this.Total=Total;
        this.ItemName=ItemName;
        this.PushId=PushId;
        this.Color=Color;
        this.Size=Size;
        this.Rewards=Rewards;
    }
}