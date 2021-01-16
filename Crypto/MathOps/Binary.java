package MathOps;

import java.util.ArrayList;

public class Binary {

    public static Integer[] get_binary(int decimal) {
        ArrayList<Integer> binary = new ArrayList<>();
        while (decimal > 0) {
            binary.add(decimal % 2);
            decimal = decimal / 2;
        }
        Integer[] binary_arr = new Integer[binary.size()];
        int ctr = 0;
        for (int i : binary) {
            binary_arr[binary_arr.length - ctr - 1] = i;
            ctr++;
        }
        return binary_arr;
    }
}
