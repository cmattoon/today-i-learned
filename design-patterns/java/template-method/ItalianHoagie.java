public class ItalianHoagie extends Hoagie {

    String[] meatUsed = {"Salami", "Pepperoni", "Capicola Ham"};
    String[] cheeseUsed = {"Murican"};
    String[] veggiesUsed = {"Lettuce", "More Lettuce", "Just a bit more Lettuce", "Onions", "Tomatoes", "Peppers"};
    String[] condimentsUsed = {"Oil", "Vinegar"};

    void addMeat() {
        for (String meat : meatUsed) {
            System.out.println(meat + " ");
        }
    }

    void addCheese() {
        for (String cheese : cheeseUsed) {
            System.out.println(cheese + " ");
        }
    }

    void addVeggies() {
        for (String veggies : veggiesUsed) {
            System.out.println(veggies + " ");
        }
    }

    void addCondiments() {
        for (String condom : condimentsUsed) {
            System.out.println(condom + " ");
        }
    }
}