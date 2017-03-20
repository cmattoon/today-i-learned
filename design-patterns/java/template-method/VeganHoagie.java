public class VeganHoagie extends Hoagie {

    String[] veggiesUsed = {"Lettuce", "More Lettuce", "Just a bit more Lettuce", "Onions", "Tomatoes", "Peppers"};
    String[] condimentsUsed = {"Oil", "Vinegar"};

    boolean customerWantsMeat() { return false; }
    boolean customerWantsCheese() { return false; }

    void addCheese() {}
    void addMeat() {}

    void addVeggies() {
        for (String veggies : veggiesUsed) {
            System.out.print(veggies + " ");
        }
    }

    void addCondiments() {
        for (String condom : condimentsUsed) {
            System.out.print(condom + " ");
        }
    }
}