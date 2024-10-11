import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;                     // Le nom du joueur
    private int balance;                     // Le solde du joueur (argent disponible)
    private List<Property> properties;       // Liste des propriétés possédées par le joueur

    // Constructeur
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.properties = new ArrayList<>();  // Initialement, le joueur ne possède aucune propriété
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<Property> getProperties() {
        return properties;
    }

    // Méthode pour acheter une propriété
    public void buyProperty(Property property) {
        Player currentOwner = property.getOwner();

        // Si la propriété n'a pas de propriétaire ou si elle est à vendre
        if (currentOwner == null || (property.isForSale() && currentOwner != this)) {
            if (balance >= property.getPrice()) {
                balance -= property.getPrice();  // Déduire le prix de la propriété du solde du joueur acheteur

                // Si la propriété appartient à quelqu'un d'autre
                if (currentOwner != null && currentOwner != this) {
                    currentOwner.removeProperty(property);  // Retirer la propriété de l'ancien propriétaire
                    currentOwner.receivePayment(property.getPrice());  // L'ancien propriétaire reçoit l'argent
                    System.out.println(currentOwner.getName() + " a vendu " + property.getName() + " à " + name + ".");
                }

                // Mettre à jour le propriétaire de la propriété
                property.setOwner(this);
                properties.add(property);  // Ajouter la propriété à la liste du joueur acheteur
                property.setForSale(false);  // La propriété n'est plus en vente après l'achat
                System.out.println(name + " a acheté " + property.getName() + " pour " + property.getPrice() + " unités.");
            } else {
                System.out.println(name + " n'a pas assez d'argent pour acheter " + property.getName() + ".");
            }
        } else if (currentOwner == this) {
            System.out.println("Vous êtes déjà le propriétaire de " + property.getName() + ".");
        } else {
            System.out.println("Cette propriété n'est pas à vendre.");
        }
    }

    // Méthode pour retirer une propriété lorsqu'elle est vendue
    public void removeProperty(Property property) {
        if (properties.contains(property)) {
            properties.remove(property);
            System.out.println(name + " n'est plus propriétaire de " + property.getName() + ".");
        }
    }

    // Méthode pour recevoir un paiement (par exemple après avoir vendu une propriété)
    public void receivePayment(int amount) {
        balance += amount;
        System.out.println(name + " a reçu un paiement de " + amount + " unités.");
    }

    // Méthode pour afficher les propriétés possédées par le joueur
    public void showProperties() {
        System.out.println("Propriétés de " + name + ":");
        if (properties.isEmpty()) {
            System.out.println("Aucune propriété.");
        } else {
            for (Property property : properties) {
                System.out.println("- " + property.getName() + (property.isMortgaged() ? " (hypothéqué)" : ""));
            }
        }
    }
}
