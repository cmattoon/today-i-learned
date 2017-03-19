import java.util.ArrayList;

public class StockGrabber implements Subject {

    private ArrayList<Observer> observers;

    private double ibmPrice;

    private double aaplPrice;

    private double googPrice;

    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    public void unregister(Observer oldObserver) {
        observers.remove(observers.indexOf(oldObserver));
    }

    public void notifyObserver() {
        for (Observer o : this.observers) {
            o.update(ibmPrice, aaplPrice, googPrice);
        }
    }

    public void setIBMPrice(double price) {
        this.ibmPrice = price;
    }

    public void setAAPLPrice(double price) {
        this.aaplPrice = price;
    }

    public void setGOOGPrice(double price) {
        this.googPrice = price;
    }
}