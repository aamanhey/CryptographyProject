package DataStructureTools.Tree;

public class Leaf {
    // node for the Tree class

    Integer key = 0;
    Integer value = 0;
    Leaf left = null;
    Leaf right = null;

    public Leaf (Integer key, Integer value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}