package TestProxy;


public class Test {

    @org.junit.Test
    public void test() {
        Sell sell = new Business(new Vendor());
        sell.sell("123");
    }
    @org.junit.Test
    public void test1() {
        Sell vendor = new Vendor();
        JdkDynamicProxy proxy = new JdkDynamicProxy();
        Sell sell = (Sell) proxy.bind(vendor);
        sell.sell("123");
    }
    @org.junit.Test
    public void test2() {
        BookFacadeImpl book = new BookFacadeImpl();
        BookFacadeCglib proxy = new BookFacadeCglib();
        BookFacadeImpl instance = (BookFacadeImpl)proxy.getInstance(book);
        instance.addBook();
    }

}
