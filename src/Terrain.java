import java.util.stream.IntStream;

public class Terrain extends Property {
    private int houses;      // Nombre de maisons (0 à 4)
    private boolean hotel;   // Indique s'il y a un hôtel
    private String group;    // Groupe de couleur auquel appartient le terrain
    private int[] houseRent; // Loyer avec 1 à 4 maisons
    private int hotelRent;   // Loyer avec un hôtel

    // Constructeur
    public Terrain(String name, int price, int baseRent, int[] houseRent, int hotelRent, String group) {
        super(name, price, baseRent);
        this.houses = 0;
        this.hotel = false;
        this.houseRent = houseRent;  // Tableau des loyers pour 1 à 4 maisons
        this.hotelRent = hotelRent;  // Loyer avec un hôtel
        this.group = group;          // Groupe de couleur
    }

    // Getters et setters
    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public boolean hasHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public String getGroup() {
        return group;
    }

    // Vérifie si le joueur possède tous les terrains d'un même groupe de couleur (monopole)
    public boolean hasMonopoly() {
        Player owner = this.getOwner();
        if (owner == null) {
            return false;  // Pas de propriétaire, donc pas de monopole
        }

        // Compter le nombre de propriétés dans ce groupe de couleur que le joueur possède
        long numberOfPropertiesInGroup = owner.getProperties().stream()
                .filter(p -> p instanceof Terrain && ((Terrain) p).getGroup().equals(this.group))
                .count();

        // Supposons que chaque groupe ait 3 terrains
        return numberOfPropertiesInGroup == 3;
    }


    @Override
    public int calculateRent() {
        if (this.isMortgaged()) {
            return 0;  // Si le terrain est hypothéqué, pas de loyer
        }

        if (hotel) {
            return hotelRent;  // Si un hôtel est construit, utiliser le loyer de l'hôtel
        } else if (houses > 0) {
            return IntStream.of(houseRent).sum(); ///houseRent[houses - 1];  // Loyer en fonction du nombre de maisons
        } else {
            // Si le joueur a un monopole et pas de maison ni hôtel, loyer de base doublé
            return hasMonopoly() ? super.calculateRent() * 2 : super.calculateRent();
        }
    }
}
