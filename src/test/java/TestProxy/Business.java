package TestProxy;

public class Business implements Sell{
    private Vendor vendor;
    
    public Business(Vendor mVender){
        this.vendor=mVender;
    }
    @Override
    public void sell(String str) {
        System.out.println("before");
        vendor.sell(str);
        System.out.println("after");
    }

}
