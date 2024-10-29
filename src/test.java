import org.junit.jupiter.api.Test;

import java.util.List;

public class test {
    @Test
    public void testTwoPlayers(){
        Player Farouk1= new Player("Farouk1",100);
        Player Farouk2= new Player("Farouk2",200);
        Utility Utility1 = new Utility("Ut 1",200,5,2,3);
        Farouk1.buyProperty(Utility1);
        List<Property> list = Farouk1.getProperties();
        System.out.println("Player "+Farouk1.getName()+"'s properties are:");
        System.out.println(list);
        Farouk1.setForSale(Utility1,false);
        System.out.println("Property "+Farouk1.getProperties().get(0)  +" is for sale?: "+Farouk1.getProperties().get(0).isForSale());
        System.out.println("Player 2 tries bying this property");
        Farouk2.buyProperty(Utility1);
        Farouk1.setForSale(Utility1,true);
        System.out.println("Property "+Farouk1.getProperties().get(0)  +" is for sale?: "+Farouk1.getProperties().get(0).isForSale());
        System.out.println("Player 2 tries bying this property");
        Farouk2.buyProperty(Utility1);
    }
}
