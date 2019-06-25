package TestProxy;

public class Vendor implements Sell {

    @Override
    public void sell(String str) {
        System.out.println("in vender sell "+str);
    }
}
