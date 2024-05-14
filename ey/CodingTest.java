

public class CodingTest {

    public static void main(String[] args) {
        int[] arr = {2,38,29,20,22,30};
        int highest = -1;
        int secondHighest = -1;

        for(int i=0; i<arr.length; i++){
            if(arr[i] > highest){
                secondHighest = highest;
                highest = arr[i];
            } else if (arr[i] > secondHighest && arr[i] < highest){
                secondHighest = arr[i];
            }
        }

        System.out.println("Second largest no:" + secondHighest);




    //     List<Integer> list = new ArrayList<>();
    //     for(int i=0; i<arr.length; i++){
    //         list.add(arr[i]);
    //     }
    //     //System.out.println(list);
    //     Collections.sort(list);

    //    var val = list.stream().map(x -> list.get(list.size()-2)).findAny();
    //    System.out.println("Second largest no:" + val.get());
 

    }

}