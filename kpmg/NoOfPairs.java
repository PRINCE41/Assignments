public class NoOfPairs {

    public static void main(String[] args) {
        int[] arr = {4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4};
        int k=2;
        System.out.println(noOfPairs(arr, k));
    }

    private static int noOfPairs(int[] arr, int sum){
        int toRet = 0;
        for(int i=0; i<arr.length; i++){
            for(int k=i+1; k<arr.length; k++){
                //System.out.println(arr[i] + " " + arr[k]);
                if(sum == (arr[i] + arr[k])) {
                    toRet++;
                    arr[i] = sum + sum;
                    arr[k] = sum + sum;
                }
            }
        }
        return toRet;


        // Set<String> set = new HashSet<>();
        // for(int i=0; i<arr.length; i++){
        //     for(int k=i+1; k<arr.length; k++){
        //         if(sum == (arr[i] + arr[k])) set.add("" + arr[i] + arr[k]);
        //     }
        // }
        // return set.size();
    }
    
}
