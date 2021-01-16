package MathOps;

import java.util.HashSet;
import java.util.Random;

public class SumFactors {

    //https://stackoverflow.com/questions/3420937/algorithm-to-find-which-number-in-a-list-sum-up-to-a-certain-number

    /**
     * Breaks down a number into a list of numbers, of length num_factors,
     * that can sum to create number.
     * @param number
     * @param num_factors
     * @return
     */
    public static Integer[] factorize(int number, int num_factors){
        return halves(number, num_factors);
    }

    public static Integer[] halves(int number, int num_factors){
        // splits the number into halves in order to get factors
        int value = number;
        Integer[] factors = new Integer[num_factors];
        int ctr = 0;
        while(value != 1){
            if(value % 2 == 0){
                // even
            }
        }
        return null;
    }

    public static Integer[] rand_gen(int number, int num_factors){
        Integer[] list = new Integer[num_factors];
        Random rand = new Random();
        int value = number;
        int base = number / num_factors; // what if all the random numbers don't add up (aren't enough)
        while (value == number){
            value = rand.nextInt() + 1;
        }
        return list;
    }

    private static Integer[] generate_rand_set(int sum, int size){
        // generates a random set that results in the sum
        // create a function with the AOC we want and use numbers along the function to sum up to it
        // need to specify a width of columns and the number of columns
        int bound = 10;
        HashSet<Integer> set = new HashSet<>();
        Random rand = new Random();
        int ctr = 0;
        while(set.size() < size){
            int value = rand.nextInt(bound * 10);
//            if(ctr % 2 == 0){
//                value = value * -1;
//            }
            set.add(value);
            ctr++;
        }
        return set.toArray(new Integer[size]);
    }

    public static void main(String[] args) {
        //print_list(rand_gen(17,9));
        //segment_test();
        Integer[] list = rand_sum_set(12,9);
        print_list(list);
        System.out.println("Sum: " + sum(list));
    }

    private static void print_list(Integer[] list){
        System.out.print("List: ");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    private static int sum(Integer[] list){
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i] != null){
                sum += list[i];
            }
        }
        return sum;
    }

    private static Integer[] rand_sum_set(int desired_sum, int num_nodes){
        Random rand = new Random();
        Random rand_sign = new Random(rand.nextInt());
        int num_segments = calculate_num_segments(num_nodes);
        Integer[] list = new Integer[num_nodes];
        int ctr = 0;
        int bound = 100;
        System.out.println("Num Segs: "+ num_segments);
        for (int i = 0; i < num_segments; i++) {
            System.out.println("Seg: "+ num_nodes/num_segments);
            System.out.println("Bound: "+ bound);
            for (int j = 0; j < num_nodes/num_segments; j++) {
                //int sign = 1 - rand_sign.nextInt(1) * 2;
                int sign = 1 - (ctr % 2) * 2;
                int value = rand.nextInt(bound) * sign;
                list[ctr] = value;
                ctr++;
            }
            System.out.println("Curr sum: "+sum(list));
            bound = sum(list) - desired_sum;
            if(bound < 0){
                bound = bound * -1;
            }
        }
//        for (int i = 0; i < num_nodes - ctr; i++) {
//
//        }
        list[ctr - 1] = sum(list) - list[ctr - 1] - desired_sum;
        return list;
    }

    private static int calculate_num_segments(int num_nodes){
        double value = Math.sqrt(num_nodes);
        value = Math.floor(value);
        value = num_nodes / value;
        return (int) value;
    }

    private static void segment_test(){
        for (int i = 1; i < 20; i++) {
            System.out.print(i + " : ");
            System.out.println(calculate_num_segments(i));
        }
    }
}
