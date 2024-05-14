public class LongestSubString {

    public static void main(String[] args) {
        String s = "bbbbb";
        System.out.println(findLenLongSubStr(s));
        
    }

    private static int findLenLongSubStr(String str){
        String toRet = "" + str.charAt(0);
        for(int i=1; i<str.length(); i++){
            if(!String.valueOf(toRet).contains(String.valueOf(str.charAt(i)))){
                toRet += String.valueOf(str.charAt(i));
            }
        }
        return toRet.length();


    }
    
}