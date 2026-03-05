import edu.princeton.cs.algs4.In;
import org.apache.hc.core5.annotation.Internal;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int out=0;
        for(int i:L){
            out+=i;
        }
        return out;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> out=new ArrayList<>();
        for(int i:L){
            if(i%2==0){
                out.add(i);
            }
        }
        return out;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> out =new ArrayList<>();
        for (int i:L1){
            if(L2.contains(i)){
                out.add(i);
            }
        }
        return out;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count=0;
        for(String i:words){
            for(char j:i.toCharArray()){
                if(j==c) count++;
            }
        }
        return count;
    }
}
