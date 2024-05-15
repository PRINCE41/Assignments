// use
//  Java streams to count the occurrences of each character in a given string:

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CountChars{

    public static void main(String[] args) {

        //String givenStr = "Java streams to count the occurrences of each character in a given string";
        String givenStr = "Java streams";
        List<String> list =  Arrays.asList(givenStr.split(" "));
        Map<Character, Integer> map = new HashMap<>();

        // streams
        System.out.println("With stream:");
        Map<Character, Long> charCounts = givenStr.chars().mapToObj(c -> (char) c)
                                           .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        charCounts.forEach((character, count) -> System.out.println(character + ": " + count));
       


        // lambda
        list.forEach(x -> {
            for(int i=0; i<x.length(); i++){
                if(map.containsKey(x.charAt(i))){
                    int count = map.get(x.charAt(i));
                    count += 1;
                    map.put(x.charAt(i), count);
                }
                else {
                    map.put(x.charAt(i), 1);
                }
            }
        });

        System.out.println("With labda:");
        map.forEach((character, count) -> System.out.println(character + ": " + count));



        
    }



}