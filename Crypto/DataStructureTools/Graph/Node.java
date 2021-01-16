package DataStructureTools.Graph;

public class Node {
    private Node next;
    public int label;
    private int value;

    public Node(int label){
        this.label = label;
        this.value = 0;
    }

    public void set_next(Node next){
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValue(int addition){
        this.value = this.value + addition;
    }
}
