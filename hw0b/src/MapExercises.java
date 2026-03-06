import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> out=new HashMap<>();
        for(int i=1;i<=26;i++){
            int tmp='a'+i-1;
            out.put((char)tmp,i);
        }
        return out;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> out=new HashMap<>();
        for(int i=0;i<nums.size();i++){
            int tmp=nums.get(i);
            out.put(tmp,(int)Math.pow(tmp,2));
        }
        return out;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> out=new HashMap<>();
        int len=words.size();
        for(int i=0;i<len;i++){
            String tmp=words.get(i);
            if(out.containsKey(tmp)){
                out.put(tmp,out.get(tmp)+1);
            }else {
                out.put(tmp,1);
            }
        }
        return out;
    }
}
