public class Railroad extends Property {
    private int baseRent; // Loyer de base pour une gare

    // Constructeur
    public Railroad(String name, int price, int baseRent) {
        super(name, price, baseRent);
        this.baseRent = baseRent;
    }

    // Calcul du loyer
    @Override
    public int calculateRent() {
        if (this.isMortgaged()) return 0;  // Si hypothéqué, pas de loyer

        Player owner = this.getOwner();
        if (owner != null) {
            // Compter le nombre de gares que possède le propriétaire
            long railroadsOwned = owner.getProperties().stream()
                    .filter(p -> p instanceof Railroad)
                    .count();

            return baseRent * (int) railroadsOwned;  // Multiplier le loyer par le nombre de gares possédées
        }
        return baseRent;
    }
}
