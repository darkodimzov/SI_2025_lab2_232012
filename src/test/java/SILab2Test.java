import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void EveryStatement() {

        //null
        RuntimeException TC1 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, "0000000000000000"));
        assertEquals("allItems list can't be null!", TC1.getMessage());

        //item name null
        RuntimeException TC2 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(List.of(new Item(null, 0, 0, 0)), "0000000000000000"));
        assertEquals("Invalid item!", TC2.getMessage());

        //discount > 0, card null
        RuntimeException TC3 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(List.of(new Item("Leb", 1, 1, 0.25)), null));
        assertEquals("Invalid card number!", TC3.getMessage());

        //discount 0, card with letters, price > 300
        RuntimeException TC4 = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(List.of(new Item("Leb", 1, 301, 0)), "aaaaaaaaaaaaaaaa"));
        assertEquals("Invalid character in card number!", TC4.getMessage());

        //discount 0, card with numbers, price > 300
        List<Item> TC5 = new ArrayList<>();
        TC5.add(new Item("Leb", 1, 301, 0));
        double rez1 = SILab2.checkCart(TC5, "0000000000000000");
        assertEquals(271, rez1);

    }

    @Test
    public void MultipleCondition() {

        // if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)

        // T - -
        List<Item> TC1 = new ArrayList<>();
        TC1.add(new Item("Leb", 1, 301, 0.25));
        double rez1 = SILab2.checkCart(TC1, "0000000000000000");
        assertEquals(195.75, rez1);

        // F T -
        List<Item> TC2 = new ArrayList<>();
        TC2.add(new Item("Leb", 1, 299, 0.25));
        double rez2 = SILab2.checkCart(TC2, "0000000000000000");
        assertEquals(194.25, rez2);

        // F F T
        List<Item> TC3 = new ArrayList<>();
        TC3.add(new Item("Leb", 11, 299, 0));
        double sum3 = SILab2.checkCart(TC3, "0000000000000000");
        assertEquals(3259.0, sum3);

        // F F F
        List<Item> TC4 = new ArrayList<>();
        TC4.add(new Item("Leb", 9, 299, 0));
        double sum4 = SILab2.checkCart(TC4, "0000000000000000");
        assertEquals(2691.0, sum4);
    }

}