
import java.util.*;
import java.util.stream.Collectors;
class StringSorting{

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("mno");
        list.add("xyz");
        list.add("abc");
        
        //Collections.sort(list);

        List<String> result = list.stream().sorted().collect(Collectors.toList());

        System.out.print(result.toString());
    }

}