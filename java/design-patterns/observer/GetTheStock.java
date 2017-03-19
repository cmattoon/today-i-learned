import java.text.DcimalFormat;

public class GetTheStock implements Runnable {
    private int startTime;
    private String stock;
    private double price;

    private Subject stockGrabber;

    public GetTheStock(Subject sg, int newStartTime, String newStock, double newPrice) {
        this.stockGrabber = sg;
        startTime = newStartTime;
        stock = newStock;
        price = newPrice;
    }

    public void run() {
        for(int i=1; i <= 20; i++) {
            try {
                Thread.sleep(2000);
            }
            catch(InterruptedException e) {
                System.out.println(e);
            }

            double randNum = (Math.random() * (0.06)) - 0.03;

            DecimalFormat df = new DecimalFormat("#.##");

            price = Double.valueOf(df.format((price + randNum)));

            if (stock == "IBM") stockGrabber.setIBMPrice(price);
        }
    }
}
