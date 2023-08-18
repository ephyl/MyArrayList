

import org.example.MyArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MyArrayListTest {

    @Test
    void testToString() {
        MyArrayList<Ship> shipList = new MyArrayList<>();
        shipList.add(new Ship("Holly Molly", 25.1));
        String result = "{java.Ship{name='Holly Molly', weight=25.1}}";

        assertEquals(result, shipList.toString());
    }

    @Test
    void testSize() {
        MyArrayList<Ship> shipList = new MyArrayList<>();
        shipList.add(new Ship("Holly Molly", 25.1));
        shipList.add(new Ship("Cally Dolly", 16.3));
        shipList.add(new Ship("Kinky Drinky", 40.7));
        shipList.add(new Ship("Lucky Bucky", 14.0));
        assertEquals(4, shipList.size());
    }

    @Test
    void add() {
        MyArrayList<Ship> shipList = new MyArrayList<>();

        assertTrue(shipList.add(new Ship("Holly Molly", 25.1)));
    }

    @Test
    void addElementWithIndex() {
        MyArrayList<Ship> shipList = new MyArrayList<>();
        shipList.add(new Ship("Cally Dolly", 16.3));
        shipList.add(new Ship("Kinky Drinky", 40.7));
        shipList.add(0, new Ship("Holly Molly", 25.1));

        assertEquals(new Ship("Holly Molly", 25.1), shipList.get(0));
        assertEquals(new Ship("Cally Dolly", 16.3), shipList.get(1));
    }

    @Test
    void testGetElementByIndex() {
        MyArrayList<Ship> shipList = new MyArrayList<>();

        shipList.add(new Ship("Holly Molly", 25.1));
        shipList.add(new Ship("Cally Dolly", 16.3));
        shipList.add(new Ship("Kinky Drinky", 40.7));
        shipList.add(new Ship("Lucky Bucky", 14.0));
        assertEquals(new Ship("Holly Molly", 25.1), shipList.get(0));
        assertEquals(new Ship("Lucky Bucky", 14.0), shipList.get(3));

    }

    @Test
    void getOutOfIndexException() {
        MyArrayList<Integer> emptyList = new MyArrayList<>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> emptyList.get(-1));
    }
    @Test
    void negativeCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->new MyArrayList<>(-1));
    }
    @Test
    void zeroCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->new MyArrayList<>(0));
    }

    @Test
    void testRemoveElementByIndex() {
        MyArrayList<Ship> shipList = new MyArrayList<>();

        shipList.add(new Ship("Holly Molly", 25.1));
        shipList.add(new Ship("Cally Dolly", 16.3));
        assertEquals(new Ship("Holly Molly", 25.1), shipList.remove(0));

        assertEquals(1, shipList.size());
        assertEquals(new Ship("Cally Dolly", 16.3), shipList.get(0));

        shipList.remove(0);
        assertEquals(0, shipList.size());


    }

    @Test
    void clear() {
        MyArrayList<Ship> shipList = new MyArrayList<>();

        shipList.add(new Ship("Holly Molly", 25.1));
        shipList.add(new Ship("Cally Dolly", 16.3));
        shipList.clear();

        assertEquals(0, shipList.size());

    }

    @Test
    void integerSort() {
        MyArrayList<Integer> integerMyArrayList = new MyArrayList<>();
        integerMyArrayList.add(30);
        integerMyArrayList.add(10);
        integerMyArrayList.add(1);
        integerMyArrayList.add(42);

        class IntComparator implements Comparator<Integer> {

            @Override
            public int compare(Integer integer, Integer t1) {
                return integer - t1;
            }
        }

        IntComparator intComparator = new IntComparator();

        integerMyArrayList.sort(intComparator);

        assertEquals("{1, 10, 30, 42}", integerMyArrayList.toString());


    }

    @Test
    void completeObjectSort() {
        MyArrayList<Ship> shipList = new MyArrayList<>();
        shipList.add(new Ship("Holly Molly", 25.1));
        shipList.add(new Ship("Cally Dolly", 16.3));
        shipList.add(new Ship("Kinky Drinky", 40.7));
        shipList.add(new Ship("Lucky Bucky", 14.0));
        assertEquals(4, shipList.size());


        class ShipComparator implements Comparator<Ship> {
            @Override
            public int compare(Ship s1, Ship s2) {
                return Double.compare(s1.getWeight(), s2.getWeight());
            }

        }
        ShipComparator shipComparator = new ShipComparator();
        shipList.sort(shipComparator);
        String sortResult = "{java.Ship{name='Lucky Bucky', weight=14.0}, java.Ship{name='Cally Dolly', weight=16.3}, java.Ship{name='Holly Molly', weight=25.1}, java.Ship{name='Kinky Drinky', weight=40.7}}";
        assertEquals(sortResult, shipList.toString());

    }


}