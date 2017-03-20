public abstract class Hoagie {

    final void makeSammich() {
        System.out.println("Make it yourself");
    }
    /**
     * This method defines how to make a sammich,
     * and shouldn't change based on the subclass.
     * Override the `customerWants` methods, and/or
     *
     */
    final void sudoMakeSammich() {
        cutBun();

        if (customerWantsMeat()) {
            addMeat();
        }

        if (customerWantsCheese()) {
            addCheese();
        }

        if (customerWantsVeggies()) {
            addVeggies();
        }

        if (customerWantsCondiments()) {
            addCondiments();
        }

        wrapIt();
    }

    abstract void addMeat();
    abstract void addCheese();
    abstract void addVeggies();
    abstract void addCondiments();

    public void cutBun() {
        System.out.println("The bun is cut!");
    }

    public void wrapIt() {
        System.out.println("That's a wrap!");
    }

    boolean customerWantsMeat() { return true; }
    boolean customerWantsCheese() { return true; }
    boolean customerWantsVeggies() { return true; }
    boolean customerWantsCondiments() { return true; }

}