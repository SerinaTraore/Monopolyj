import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    public void testTwoPlayers(){
        Player Farouk1 = new Player("Farouk1", 100);
        Player Farouk2 = new Player("Farouk2", 200);
        Utility Utility1 = new Utility("Ut 1", 200, 5, 2, 3);

        Farouk1.buyProperty(Utility1);
        List<Property> list = Farouk1.getProperties();

        System.out.println("Player " + Farouk1.getName() + "'s properties are:");
        System.out.println(list);

        Farouk1.setForSale(Utility1, false);
        System.out.println("Property " + Farouk1.getProperties().get(0)  + " is for sale?: " + Farouk1.getProperties().get(0).isForSale());

        System.out.println("Player 2 tries buying this property");
        Farouk2.buyProperty(Utility1);

        Farouk1.setForSale(Utility1, true);
        System.out.println("Property " + Farouk1.getProperties().get(0)  + " is for sale?: " + Farouk1.getProperties().get(0).isForSale());

        System.out.println("Player 2 tries buying this property");
        Farouk2.buyProperty(Utility1);
    }

    @Test
    public void testBuyingPropertyWithSufficientFunds() {
        Player john = new Player("John", 500);
        Property greenStreet = new Property("Green Street", 200, 20);

        john.buyProperty(greenStreet);

        assertEquals(300, john.getBalance(), "Le solde devrait être de 300 après l'achat.");
        assertEquals(john, greenStreet.getOwner(), "John devrait être le propriétaire de Green Street.");
        assertFalse(greenStreet.isForSale(), "Green Street ne devrait plus être en vente.");
    }

    @Test
    public void testBuyingPropertyWithInsufficientFunds() {
        Player alice = new Player("Alice", 100);
        Property redStreet = new Property("Red Street", 200, 25);

        alice.buyProperty(redStreet);

        assertEquals(100, alice.getBalance(), "Le solde d'Alice ne devrait pas changer car elle n'a pas assez de fonds.");
        assertNull(redStreet.getOwner(), "Red Street ne devrait pas avoir de propriétaire.");
        assertTrue(redStreet.isMortgaged(), "Red Street devrait être hypothéqué.");
    }

    @Test
    public void testMortgageProperty() {
        Player bob = new Player("Bob", 500);
        Property blueStreet = new Property("Blue Street", 300, 30);

        bob.buyProperty(blueStreet);
        blueStreet.mortgage();

        assertTrue(blueStreet.isMortgaged(), "Blue Street devrait être hypothéqué.");
        assertEquals(200, bob.getBalance(), "Le solde de Bob devrait refléter l'achat sans bénéficier de la valeur hypothéquée.");
    }

    @Test
    public void testLiftMortgage() {
        Player charlie = new Player("Charlie", 500);
        Property yellowStreet = new Property("Yellow Street", 200, 20);

        charlie.buyProperty(yellowStreet);
        yellowStreet.mortgage();
        yellowStreet.liftMortgage();

        assertFalse(yellowStreet.isMortgaged(), "Yellow Street ne devrait plus être hypothéqué.");
    }

    @Test
    public void testCalculateRentForRailroadOwnership() {
        Player dave = new Player("Dave", 1000);
        Railroad station1 = new Railroad("Central Station", 200, 25);
        Railroad station2 = new Railroad("West Station", 200, 25);

        dave.buyProperty(station1);
        dave.buyProperty(station2);

        assertEquals(50, station1.calculateRent(), "Le loyer devrait être de 50 pour deux gares possédées.");
        assertEquals(50, station2.calculateRent(), "Le loyer devrait être de 50 pour deux gares possédées.");
    }

    @Test
    public void testCalculateRentWithMonopoly() {
        Player emma = new Player("Emma", 1000);
        Terrain red1 = new Terrain("Red 1", 300, 30, new int[]{50, 100, 150, 200}, 250, "Red");
        Terrain red2 = new Terrain("Red 2", 300, 30, new int[]{50, 100, 150, 200}, 250, "Red");
        Terrain red3 = new Terrain("Red 3", 300, 30, new int[]{50, 100, 150, 200}, 250, "Red");

        emma.buyProperty(red1);
        emma.buyProperty(red2);
        emma.buyProperty(red3);

        assertTrue(red1.hasMonopoly(), "Emma devrait avoir un monopole pour le groupe Rouge.");
        assertEquals(60, red1.calculateRent(), "Le loyer devrait être doublé pour le monopole (30 * 2).");
    }

    @Test
    public void testCalculateRentWithHousesAndHotel() {
        Player frank = new Player("Frank", 1500);
        Terrain blue1 = new Terrain("Blue 1", 350, 35, new int[]{70, 140, 210, 280}, 500, "Blue");

        frank.buyProperty(blue1);
        blue1.setHouses(3); // 3 maisons

        assertEquals(210, blue1.calculateRent(), "Le loyer devrait être de 210 pour trois maisons.");

        blue1.setHotel(true); // Un hôtel
        assertEquals(500, blue1.calculateRent(), "Le loyer devrait être de 500 avec un hôtel.");
    }

    @Test
    public void testSettingPropertyForSaleAndBuying() {
        Player george = new Player("George", 500);
        Player harry = new Player("Harry", 700);
        Property blackAvenue = new Property("Black Avenue", 400, 40);

        george.buyProperty(blackAvenue);
        george.setForSale(blackAvenue, true);

        harry.buyProperty(blackAvenue);

        assertEquals(harry, blackAvenue.getOwner(), "Harry devrait être le nouveau propriétaire de Black Avenue.");
        assertEquals(300, george.getBalance(), "George devrait avoir reçu le paiement.");
        assertEquals(300, harry.getBalance(), "Harry devrait avoir payé pour la propriété.");
    }
}
