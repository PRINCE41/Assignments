package accolite;
// Problem: Given the starting and ending time of the meetings. Find the minimum no of rooms required to
//    successfully schedule all the meetings without conflict
//    int start[] = {900, 940, 950, 1100, 1500, 1800};
//    int end[] = {910, 1200, 1120, 1130, 1900, 2000};
//    Output : 3

public class Schedule {

    // private static int start[] = {900, 940, 950, 1100, 1500, 1800};
    // private static int end[] = {910, 1200, 1120, 1130, 1900, 2000};

    private static int start[] = {100, 300, 500};
    private static int end[] = {900, 400, 600};
    

    public static void main(String[] args) {
        int toRet = 0;
    
        for(int i=0; i<start.length-1; i++){
            if(end[i] > start[i+1]) toRet++;
        }

        if(toRet==0) ++toRet;

        System.out.println("Min no of room:" + toRet);
        
    }
    
}
