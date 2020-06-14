package sg.test.listcompare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.test.listcompare.ComparatorLogic.generateList;

public class ComparatorLogicTest {

    @Test
    void testNormalCase() {
        ComparatorLogic logic = new ComparatorLogic();

        List<Integer> result = generateList(10, 100);

        assertNotNull(result);
        Assertions.assertEquals(10, result.size());
    }


    @Test
    void testZeroInput() {
        ComparatorLogic logic = new ComparatorLogic();

        List<Integer> result = generateList(0, 100);

        assertNotNull(result);
        Assertions.assertEquals(0, result.size());
    }


    @Test
    void testNegativeInput() {
        ComparatorLogic logic = new ComparatorLogic();

        Assertions.assertThrows(InvalidParameterException.class,
                () -> generateList(-10, 100)
        );
    }

    @Test
    void testCompare1() {
        ComparatorLogic logic = new ComparatorLogic();

        List<Integer> listA = List.of(1,2,3,4,5,6);
        List<Integer> listB = List.of(3,5);
        int listChoice = 1;

        List<Integer> result = logic.compareList(listA, listB, ComparatorLogic.Choice.LIST_A);

        assertNotNull(result);
        assertEquals(2,result.size());
        assertTrue(result.contains(3));

    }
}
