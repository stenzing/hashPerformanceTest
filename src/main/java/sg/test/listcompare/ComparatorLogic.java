package sg.test.listcompare;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ComparatorLogic {

    public enum Choice {
        LIST_A,
        LIST_B
    }
    private static final Random rnd = new Random();

    public static List<Integer> generateList(int i, int max) {
        if (i<0)
            throw new InvalidParameterException("Size cannot be smaller than 0");
        return IntStream
                .range(0,i)
                .boxed()
                .map(j -> rnd.nextInt(max))
                .collect(Collectors.toList());
    }

    public List<Integer> compareList(List<Integer> listA, List<Integer> listB, Choice listChoice) {

        return compareList(listChoice== Choice.LIST_A?listA:listB, listChoice== Choice.LIST_A?listB:listA);

    }

    public List<Integer> compareList(List<Integer> toHash, List<Integer> toIter) {
        HashSet<Integer> set = new HashSet<>(toHash);

        return toIter.stream()
                .filter(set::contains)
                .collect(Collectors.toList());

    }
}
