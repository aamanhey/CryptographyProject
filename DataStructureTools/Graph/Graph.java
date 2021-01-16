package DataStructureTools.Graph;

import java.util.HashMap;
import java.util.Map;

public class Graph {
    // undirected, unweighted

    public String name;
    private HashMap<Integer, Node> headList;

    public Graph(String name){
        this.name = name;
        this.headList = new HashMap<>();
    }

    public Graph(Graph graph){
        this.name = graph.name;
        this.headList = graph.headList;
    }

    public void setHeadList(HashMap<Integer, Node> headList) {
        this.headList = headList;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Node> getHeadList() {
        return headList;
    }

    public void remove(int source, int dest){
        remove_from_graph(source, dest);
        remove_from_graph(dest, source);
        clean();
    }

    private void remove_from_graph(int source, int dest){
        // removes an edge from the graph
        int node_key = source;
        if(this.headList.containsKey(node_key)){
            // if the node exists in the map
            Node node = headList.get(node_key);
            if(node.label == dest){
                headList.replace(node_key,node.getNext());
            }
            while(node.getNext() != null){
                node = node.getNext();
                if(node.label == dest){
                    headList.replace(node_key,node); // setting the replace as null if it was the last one in list
                }
            }
        }
    }

    public void put(int source, int dest){
        put_in_graph(source,dest);
        // adds an egde to the other node's list
        put_in_graph(dest, source);
    }

    private void put_in_graph(int source, int dest){
        // puts an edge into the graph
        int node_key = source;
        Node node_value = new Node(dest);
        if(this.headList.containsKey(node_key)){
            // if the node exists in the map
            Node existing_node = headList.get(node_key);
            while(existing_node.getNext() != null){
                existing_node = existing_node.getNext();
            }
            existing_node.set_next(node_value);
        }else{
            // new node
            this.headList.put(node_key, node_value);
        }
    }

    public String print_graph(){
        String string_graph = "--Graph--\n";
        for (Map.Entry mapElement : headList.entrySet()) {
            int key = (int)mapElement.getKey();
            Node ptr = (Node)mapElement.getValue();
            string_graph += key + " : " + ptr.label;
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
                string_graph += ", " + ptr.label;
            }
            string_graph += "\n";
        }
        return string_graph;
    }
    public String get_graphviz(){
        String string_graph = "https://dreampuf.github.io/GraphvizOnline/#digraph%20G%20%7B%0A%0A%7D\n";
        for (Map.Entry mapElement : headList.entrySet()) {
            int key = (int)mapElement.getKey();
            Node ptr = (Node)mapElement.getValue();
            // put the value in parenthesis next to labeld
            string_graph += key + " -> " + ptr.label + ";\n";
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
                string_graph += key + " -> " + ptr.label + ";\n";
            }
        }
        return string_graph;
    }

    public String get_graphviz(boolean withValue){
        String string_graph = "https://dreampuf.github.io/GraphvizOnline/#digraph%20G%20%7B%0A%0A%7D\n";
        for (Map.Entry mapElement : headList.entrySet()) {
            int key = (int)mapElement.getKey();
            Node ptr = (Node)mapElement.getValue();
            // put the value in parenthesis next to labeld
            string_graph += String.format("  \"%d(%d)\"->%d\n",key, ptr.getValue(), ptr.label);
            while (ptr.getNext() != null){
                ptr = ptr.getNext();
                //string_graph += String.format("  %d->%d\n",key, ptr.label);
                string_graph += String.format("  \"%d(%d)\"->%d\n",key, ptr.getValue(), ptr.label);
            }
        }
        return string_graph;
    }

    private void clean(){
        for (Map.Entry mapElement : headList.entrySet()) {
            int key = (int)mapElement.getKey();
            Node element = (Node)mapElement.getValue();
            if(element == null){
                headList.remove(key);
            }
        }
    }
}
