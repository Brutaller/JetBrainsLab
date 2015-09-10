package interview.FirstTask;

import java.util.TreeMap;

/**
 * Created by Azat Zaripov on 09.09.2015.
 */
public class Program1 {
    public int nonRecurring(String input) {
        int result = -1;

        TreeMap<Character, Integer> inputArray = new TreeMap<Character, Integer>(); /*add character to TreeMap,*/
        for (int i = 0; i < input.length(); i++) {                                  /*where Integer is the first entry*/
            char c = input.charAt(i);                                               /*of the character in a string*/
            if (inputArray.containsKey(c)) {
                inputArray.put(c, -1);      //if symbol is repeated
            } else {
                inputArray.put(c, i);
            }
        }
        for (Integer i : inputArray.values()) {                             //We are looking for the smallest number > -1
            if ((i != -1) && ((result == -1) || (i < result))) {            //Nothing changes unless there is no such
                result = i;
            }
        }
        return result;
    }
}