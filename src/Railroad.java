public class Railroad extends Property {
    private int baseRent; // Loyer de base pour une gare


    public Railroad(String name, int price, int baseRent) {
        super(name, price, baseRent);
        this.baseRent = baseRent;
    }


    @Override
    public int calculateRent() {
        if (this.isMortgaged()) return 0;

        Player owner = this.getOwner();
        if (owner != null) {
            long railroadsOwned = owner.getProperties().stream()
                    .filter(p -> p instanceof Railroad)
                    .count();

            return baseRent * (int) railroadsOwned;  // The rent is amplified if one has multiple railroads.
        }
        return baseRent;
    }
}
