public class GrabStocks {

    public static void main(String[] args) {
        StockGrabber stockGrabber = new StockGrabber();

        StockObserver o1 = new StockObserver(stockGrabber);

        stockGrabber.setIBMPrice(123.45);
        stockGrabber.setAAPLPrice(456.78);
        stockGrabber.setGOOGPrice(678.90);

        StockObserver o2 = new StockObserver(stockGrabber);
    }
}