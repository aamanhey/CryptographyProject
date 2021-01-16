package DataStructureTools.Graph;

import java.util.Set;

public class GraphInfo {
    // Class to hold a graph used for a perfect code
    public String name;
    private Set<Integer> vpc;
    private String raw_graph; // graph without non-core vertices connected stored as a GraphViz compatible string
    private Graph graph; // public graph

    public GraphInfo(String name){
        this.name = name;
    }

    public GraphInfo(String name, Set<Integer> vpc){
        this.name = name;
        this.vpc = vpc;
    }

    public GraphInfo(String name, Set<Integer> vpc, String raw_graph, Graph graph){
        this.name = name;
        this.vpc = vpc;
        this.raw_graph = raw_graph;
        this.graph = graph;
    }

    public void setRaw_graph(String raw_graph) {
        this.raw_graph = raw_graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public String getName() {
        return name;
    }

    public Graph getGraph() {
        return graph;
    }

    public String getRaw_graph() {
        return raw_graph;
    }

    public Set<Integer> getVpc() {
        return vpc;
    }

    public String getVpcAsString(){
        String format = "Vpc = [";
        int ctr = 0;
        for (Integer element: this.vpc) {
            if(ctr != 0){
                format += ", " + element;
            }else{
                format += element;
            }
            ctr++;
        }
        format += "]";
        return format;
    }
}
