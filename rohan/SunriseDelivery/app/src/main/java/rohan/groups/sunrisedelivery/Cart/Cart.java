package rohan.groups.sunrisedelivery.Cart;


public class Cart {
    public String ItemName;
    public String Qty;
    public String QtyDescription;
    public String Price;
    public String Total;
    public String Image;


    public Cart() {
    }

    public Cart(String ItemName,String Qty,String Price,String Total,String Image,String QtyDescription) {

        this.Image=Image;
        this.Qty=Qty;
        this.Price=Price;
        this.Total=Total;
        this.ItemName=ItemName;
        this.QtyDescription=QtyDescription;
    }
}