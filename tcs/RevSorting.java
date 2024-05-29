import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RevSorting{

    public static void main(String[] args) {
        int[] givenNo = {3, 5, 7, 8, 9, 0, 1, 2, 3};

        List<Integer> list = Arrays
            .stream(givenNo)
            .boxed()
            //.sorted((a, b) -> b - a)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        list.forEach(x -> {
            System.out.println(x);
        });
    }

}