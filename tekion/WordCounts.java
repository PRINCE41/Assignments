// Find duplicate words from a sentence and count number of duplicates for each word
// 	Ex:  “Hi, I am from BCT and I am a developer. I like to code and write optimised code ”

import java.util.*;
public class WordCounts {

    public static void main(String[] args) {

        String givenInput = "Hi, I am from BCT and I am a developer. I like to code and write optimised code";

        Map<String, Integer> map = new HashMap<>();


        Arrays.asList(givenInput.split(" ")).forEach(word -> {
            if(map.get(word) == null) map.put(word, 1);
            else map.put(word, map.get(word) + 1);
        });

       map.forEach((k, v) -> {
        if(v >= 2) System.out.println(k + ":" + v );
       });
        
    }
    
}
