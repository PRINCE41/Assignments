// Write a function that will append 2 words and return an array of palindrome from the given words
// list[] = ["abc", "xyxcba", "geekst", "or", "keeg", "bc"]
// list = ["abc", "cba", "an"]
// output: "abccba"

import java.util.*;

public class PalinDromeSet {

    public static void main(String[] args) {
        String[] list = {"abc", "xyxcba", "geekst", "or", "keeg", "bc"}; // output:1
        List<String> toRet = new ArrayList<>();
        for(int j=0; j<list.length; j++){
            for(int i=j+1; i<list.length; i++){
                if(checkPalindrome(list[j] + list[i])) {
                    toRet.add(list[j] + list[i]);
                }
            }
        }
        toRet.forEach(x -> System.out.println(x));
    }

    private static boolean checkPalindrome(String tempStr) {
        return new StringBuilder(tempStr).reverse().toString().equals(tempStr) ? true : false;
    }
    
}
