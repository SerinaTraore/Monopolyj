public class Utility extends Property {
    private int multiplierOneUtility;  // Multiplicateur pour un seul service public
    private int multiplierTwoUtilities; // Multiplicateur pour deux services publics


    public Utility(String name, int price, int baseRent, int multiplierOneUtility, int multiplierTwoUtilities) {
        super(name, price, baseRent);
        this.multiplierOneUtility = multiplierOneUtility;
        this.multiplierTwoUtilities = multiplierTwoUtilities;
    }

    // Calcul du loyer
    @Override
    public int calculateRent() {
        if (this.isMortgaged()) return 0;  // Si hypothéqué, pas de loyer

        Player owner = this.getOwner();
        if (owner != null) {
            // Compter le nombre de services publics que possède le joueur
            long utilitiesOwned = owner.getProperties().stream()
                    .filter(p -> p instanceof Utility)
                    .count();

            // Calculer le loyer en fonction du nombre de services publics
            if (utilitiesOwned == 1) {
                return multiplierOneUtility * super.calculateRent();
            } else if (utilitiesOwned == 2) {
                return multiplierTwoUtilities * super.calculateRent();
            }
        }
        return super.calculateRent();
    }
}
