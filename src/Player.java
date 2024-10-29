import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    private String name;
    private int balance;
    private List<Property> properties;


    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.properties = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public List<Property> getProperties() {
        return properties;
    }


    public void buyProperty(Property property) {
        Player currentOwner = property.getOwner();

        if (property.isForSale()) {
            if (currentOwner != null && currentOwner != this) {
                if (balance >= property.getPrice()) {
                    balance -= property.getPrice();

                } else {
                    System.out.println(name + " n'a pas assez d'argent pour acheter " + property.getName() + ".");
                    property.setMortgaged(true);
                    System.out.println(name + " a effectué un mortgage pour acheter" + property.getName());
                }
                currentOwner.removeProperty(property);
                currentOwner.receivePayment(property.getPrice());
                System.out.println(currentOwner.getName() + " a vendu " + property.getName() + " à " + name + ".");

                property.setOwner(this);
                properties.add(property);
                property.setForSale(false);
                System.out.println(name + " a acheté " + property.getName() + " pour " + property.getPrice() + " unités.");

            }else if(currentOwner ==null){
                if (balance >= property.getPrice()) {
                    balance -= property.getPrice();

                } else {
                    System.out.println(name + " n'a pas assez d'argent pour acheter " + property.getName() + ".");
                    property.setMortgaged(true);
                    System.out.println(name + " a effectué un mortgage pour acheter" + property.getName());

                }
                property.setOwner(this);
                properties.add(property);
                property.setForSale(false);
                System.out.println(name + " a acheté " + property.getName() + " pour " + property.getPrice() + " unités.");


            } else if (currentOwner == this) {
                System.out.println("Vous êtes déjà le propriétaire de " + property.getName() + ".");
            }
        } else {
            System.out.println("Cette propriété n'est pas à vendre.");
        }
    }


    public void removeProperty(Property property) {
        if (properties.contains(property)) {
            properties.remove(property);
            System.out.println(name + " n'est plus propriétaire de " + property.getName() + ".");
        }
    }


    public void receivePayment(int amount) {
        balance += amount;
        System.out.println(name + " a reçu un paiement de " + amount + " unités.");
    }
    public void setForSale(Property property,boolean forSale){
        if(properties.contains(property)){
            property.setForSale(forSale);
        }
        else System.out.println("Player doesn't own this property, can't modify it");
    }
    public int getRent(){
        int sum = 0;
        Iterator<Property> iterator = properties.stream().iterator();
        while(iterator.hasNext()){
            Property item = iterator.next();
            sum+=item.getBaseRent();
        }
        balance+=sum;
        return sum;
    }

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
