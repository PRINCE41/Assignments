
class VowelFreq {

    private static String str = "This is an interview";
    public static void main(String[] args) {
        long st = System.currentTimeMillis();
        int fA = 0;
        int fE = 0;
        int fI = 0;
        int fO = 0;
        int fU = 0;
        str = str.toLowerCase();
        for(int i=0 ; i<str.length(); i++){
            if(str.charAt(i) == 'a') fA++;
            if(str.charAt(i) == 'e') fE++;
            if(str.charAt(i) == 'i') fI++;
            if(str.charAt(i) == 'o') fO++;
            if(str.charAt(i) == 'u') fU++;
        }
        System.out.println("a-" + fA);
        System.out.println("e-" + fE);
        System.out.println("i-" + fI);
        System.out.println("o-" + fO);
        System.out.println("u-" + fU);
        System.out.println("RTT-" + (System.currentTimeMillis() - st));
    }

}