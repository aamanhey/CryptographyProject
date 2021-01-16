package Cryptography;

import DataStructureTools.Graph.Graph;
import DataStructureTools.Graph.GraphInfo;
import DataStructureTools.Graph.Node;
import DataStructureTools.RandomSet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class PerfectCode {
    /*
    A perfect code of a graph is a set of vertices that:
    1. None of the vertices in Vpc are adjacent to each other
    2. Every vertex not in Vpc is connected to exactly one vertex in Vpc
     */

    /*
    A perfect code of a graph is a set of vertices that:
    1. None of the vertices in Vpc are adjacent to each other
    2. Every vertex not in Vpc is connected to exactly one vertex in Vpc
     */

    // create a method to find the perfect code of a graph

    public static Graph clump(Graph graph, int value){
        Integer[] values = RandomSet.gen_rand_set(value, graph.getHeadList().size());
        System.out.println("List has correct sum: "+(RandomSet.sum(values)==value));
        RandomSet.print_list(values);
        // iterate over the headlist as we iterate through values
        HashMap<Integer, Node> headList = graph.getHeadList();
        int ctr = 0;
        for (Map.Entry mapElement : headList.entrySet()) {
            Node ptr = (Node)mapElement.getValue();
            ptr.increaseValue(values[ctr]);
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
                ptr.increaseValue(values[ctr]);
            }
            ctr++;
        }
        System.out.println(graph.get_graphviz(true));
        return null;
    }

    public static GraphInfo generate_rand_graph(){
        Graph graph = new Graph("Random Perfect Code Graph");
        Map<Integer, Integer> vpc = generate_rand_map();
        // create the seperate graphs for Vpc
        for (Map.Entry mapElement : vpc.entrySet()) {
            int key = (int)mapElement.getKey();
            HashSet<Integer> set = (HashSet<Integer>)mapElement.getValue();
            for (Integer setElement: set) {
                graph.put(key, setElement);
            }
        }
        Set<Integer> keys = vpc.keySet();
        GraphInfo pc = new GraphInfo("Random Perfect Code Graph", keys);
        pc.setRaw_graph(graph.get_graphviz()); // being stored as a pointer?
        // connect the non-core vertices (complicate the graph)
        int holder_set_key = -1;
        HashSet<Integer> holder_set = null;
        for (Map.Entry mapElement : vpc.entrySet()) {
            if(holder_set_key < 0){
                holder_set_key = (int)mapElement.getKey();
                holder_set = (HashSet<Integer>)mapElement.getValue();
            } else{
                HashSet<Integer> curr_set = (HashSet<Integer>)mapElement.getValue();
                for (Integer holder_set_element: holder_set) {
                    for (Integer setElement: curr_set) {
                        graph.put(holder_set_element, setElement);
                    }
                }
            }
        }
        pc.setGraph(graph);
        return pc;
    }

    private static Map generate_rand_map(){
        Random rand = new Random();
        int num_vpc = rand.nextInt(3) + 2; // 2 to 5 vpc
        Integer[] list = generate_rand_set((9 + num_vpc) * 10, 9 + num_vpc); // idea: multiply by 10 for bigger values??
        // if bound = size then the list will cover all the numbers in that bound
        Map<Integer, HashSet<Integer>> vpc = new HashMap();
        // vertices in Vpc are referred to as core vertices (cv)s
        // create the correct number of places for Vpc
        int ctr = 0;
        for (int i = 0; i < num_vpc; i++) {
            vpc.put(i, new HashSet<>());
        }
        // assign nodes to places in Vpc
        for (int i = num_vpc; i < list.length; i++) {
            vpc.get(ctr%num_vpc).add(list[i]);
            ctr += 1;
        }
        // assign spaces of Vpc to one node each
        ctr = 0;
        for (int i = 0; i < num_vpc; i++) {
            vpc.put(list[i], vpc.get(ctr%num_vpc));
            vpc.remove(ctr%num_vpc);
            ctr += 1;
        }
        return vpc;
    }

    private static Integer[] generate_rand_set(int bound, int size){
        HashSet<Integer> set = new HashSet<>();
        Random rand = new Random();
        while(set.size() < size){
            set.add(rand.nextInt(bound));
        }
        return set.toArray(new Integer[size]);
    }

    private static void print_list(Integer[] list){
        System.out.print("List: ");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

}
