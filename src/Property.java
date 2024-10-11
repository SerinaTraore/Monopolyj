public class Property {
    private String name;
    private int price;
    private int baseRent;
    private Player owner;        // Propriétaire actuel
    private boolean mortgaged;   // Indique si la propriété est hypothéquée
    private boolean isForSale;   // Indique si la propriété est à vendre
    private boolean isRented;    // Indique si la propriété est louée
    private Player currentRenter; // Le joueur qui loue actuellement la propriété

    // Constructeur
    public Property(String name, int price, int baseRent) {
        this.name = name;
        this.price = price;
        this.baseRent = baseRent;
        this.owner = null;      // Par défaut, la propriété n'a pas de propriétaire
        this.mortgaged = false; // Par défaut, la propriété n'est pas hypothéquée
        this.isForSale = false; // Par défaut, la propriété n'est pas en vente
        this.isRented = false;  // Par défaut, la propriété n'est pas louée
        this.currentRenter = null; // Par défaut, la propriété n'a pas de locataire
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getBaseRent() {
        return baseRent;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isMortgaged() {
        return mortgaged;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public boolean isRented() {
        return isRented;
    }

    public Player getCurrentRenter() {
        return currentRenter;
    }

    // Setters
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setMortgaged(boolean mortgaged) {
        this.mortgaged = mortgaged;
    }

    public void setBaseRent(int baseRent) {
        this.baseRent = baseRent;
    }

    public void setForSale(boolean isForSale) {
        this.isForSale = isForSale;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    public void setCurrentRenter(Player renter) {
        this.currentRenter = renter;
    }

    // Méthode pour calculer le loyer
    public int calculateRent() {
        if (this.owner == null) {
            System.out.println("La propriété " + this.name + " n'a pas de propriétaire.");
            return 0;  // Si la propriété n'a pas de propriétaire, pas de loyer
        }
        if (this.mortgaged) {
            return 0;  // Si la propriété est hypothéquée, le loyer est 0
        }
        return baseRent;  // Sinon, on retourne le loyer de base
    }

    // Méthode pour hypothéquer la propriété
    public void mortgage() {
        if (!this.mortgaged) {
            this.mortgaged = true;
            System.out.println(this.name + " est hypothéqué.");
        }
    }

    // Méthode pour lever l'hypothèque
    public void liftMortgage() {
        if (this.mortgaged) {
            this.mortgaged = false;
            System.out.println(this.name + " n'est plus hypothéqué.");
        }
    }
}
