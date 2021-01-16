package DataStructureTools;

import java.util.Random;

public class RandomSet {
    //https://stackoverflow.com/questions/3420937/algorithm-to-find-which-number-in-a-list-sum-up-to-a-certain-number

    public static Integer[] gen_rand_set(int sum, int num_nodes){
        int half = num_nodes/2;
        Random rand = new Random();
        Integer[] list = new Integer[num_nodes];
        for (int i = 0; i < half; i++) {
            int value = rand.nextInt(sum*100);
            list[i] = value;
            list[i+half] = value * -1;
        }
        if(num_nodes % 2 == 0){
            list[num_nodes - 1] += sum;
        }else{
            list[num_nodes - 1] = sum;
        }
        return list;
    }

    public static void print_list(Integer[] list){
        System.out.print("List: [");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println("]");
    }

    public static int sum(Integer[] list){
        int sum = 0;
        for (int i = 0; i < list.length; i++) {
            if(list[i] != null){
                sum += list[i];
            }
        }
        return sum;
    }
}
