package DataStructureTools.Tree;

public class Tree implements Map {
    // Implements an AVL Tree Map

    private int size = 0;
    private Leaf root = null;

    public Tree() {
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean put(Integer key, Integer value) {
        int presize = this.size;
        this.root = putRecursive(key,value,root);
        if (this.size > presize){
            return true;
        }else{
            return false;
        }
    }

    private Integer calculateHeight(Leaf node) {
        if(node == null){
            return 0;
        }
        int left_h = 1+ calculateHeight(node.left);
        int right_h = 1+ calculateHeight(node.right);
        return max(left_h,right_h);
    }

    private int max(int x, int y){
        if(x > y){
            return x;
        }else if(y > x){
            return y;
        }else{
            return x;
        }
    }
    private Leaf putRecursive(Integer key, Integer value,Leaf curr_node) {
        if (curr_node == null) {//add new node
            curr_node = new Leaf(key, value);
            this.size += 1;
        }else {
            if (curr_node.key > key) {//move to next left-child node
                curr_node.left = putRecursive(key, value, curr_node.left);
                if (calculateHeight(curr_node.left) - calculateHeight(curr_node.right) > 1) {
                    if (key < curr_node.left.key) {
                        curr_node = left(curr_node);
                    } else {
                        curr_node = lr(curr_node);
                    }
                }
            } else if (curr_node.key < key) {//move to next right-child node
                curr_node.right = putRecursive(key, value, curr_node.right);
                if (calculateHeight(curr_node.right) - calculateHeight(curr_node.left) > 1) {
                    if (key > curr_node.right.key) {
                        curr_node = right(curr_node);
                    } else {
                        curr_node = rl(curr_node);
                    }
                }
            } else {
                curr_node.value = value;
            }
        }
        return curr_node;
    }

    private Leaf left(Leaf sub_root){
        //straight line
        Leaf left_child = sub_root.left;
        sub_root.left = left_child.right;
        left_child.right = sub_root;
        return left_child;
    }
    private Leaf lr(Leaf sub_root){
        sub_root.left = right(sub_root.left);
        return left(sub_root);
    }
    private Leaf rl(Leaf sub_root){
        sub_root.right = left(sub_root.right);
        return right(sub_root);
    }
    private Leaf right(Leaf sub_root){
        Leaf right_child = sub_root.right;
        sub_root.right = right_child.left;
        right_child.left = sub_root;

        return right_child;
    }

    public boolean contains(Integer key) {
        return get(key)!=null;
    }

    public Integer get(Integer key) {
        Integer temp = getRecursive(key,root);
        if(temp == null){
            return null;
        }else{
            return temp;
        }
    }

    private Integer getRecursive(Integer key, Leaf curr_node){
        if(curr_node == null){
            return null;
        }else{
            if(curr_node.key > key){
                return getRecursive(key, curr_node.left);
            }else if(curr_node.key < key){
                return getRecursive(key, curr_node.right);
            }else{
                return curr_node.value;
            }
        }
    }

    public void remove(Integer key) {
        root = removeRecursive(key,root);
    }
    private Leaf removeRecursive(Integer key, Leaf curr_node){
        if(curr_node == null){//if the tree is empty
            return null;
        }else{
            if(curr_node.key == key) {//must be initial
                return null;
            }else if(curr_node.key > key && curr_node.left.key != key) {//move to next left-child node
                return removeRecursive(key, curr_node.left);
            }else if(curr_node.key < key && curr_node.right.key != key){//move to next right-child node
                return removeRecursive(key, curr_node.right);
            }else if(curr_node.left.key == key){//the next one is equal
                this.size -= 1;
                return removalRotate(curr_node,false);
            }else if(curr_node.right.key == key){
                this.size -= 1;
                return removalRotate(curr_node,true);
            }else{
                return null;
            }
        }
    }
    private Leaf removalRotate(Leaf parent_node,boolean right){
        if(!right){
            //the left child is the one to be deleted
            if (parent_node.left.right != null && parent_node.left.left != null){//has both children
                parent_node.left.left.right = parent_node.left.right;//then that node's left child's right child will be the removed's right child
                parent_node.left = parent_node.left.left;//the parent of the removed's left child will be the removed's left child
            }else if(parent_node.left.right != null && parent_node.left.left == null) {//has only right child
                parent_node.left = parent_node.left.right;
            }else if(parent_node.left.right == null && parent_node.left.left != null){//has only left child
                parent_node.left = parent_node.left.left;
            }else{//has no children
                parent_node.left = null;
            }
        }else{
            //the right child is the one to be deleted
            if (parent_node.right.right != null && parent_node.right.left != null){//has both children
                parent_node.right.left.right = parent_node.right.right;//then that sub_root's left child's right child will be the removed's right child
                parent_node.right = parent_node.right.left;//the parent of the removed's left child will be the removed's left child
            }else if(parent_node.right.right != null && parent_node.right.left == null) {//has only right child
                parent_node.right = parent_node.right.right;
            }else if(parent_node.right.right == null && parent_node.right.left != null){//has only left child
                parent_node.right = parent_node.right.left;
            }else{//has no children
                parent_node.right = null;
            }
        }
        return parent_node;
    }

    public void prettyPrint() {
        this.prettyPrint(this.root, 0);
    }

    private void prettyPrint(Leaf node, int depth) {
        if (node == null) {
            return;
        }
        this.prettyPrint(node.right, depth + 1);
        String line = "";
        for (int i = 0; i < depth; i++) {
            line += "  ";
        }
        line += node.key + ": " + node.value;
        System.out.println(line);
        this.prettyPrint(node.left, depth + 1);
    }
    
    /* @return An array of the keys of the map, in sorted order.
            */
    public int[] toKeysArray() {
        int[] result = new int[this.size()];
        int index = 0;

        Leaf[] stack = new Leaf[this.size()];
        int depth = -1;

        Leaf curr = this.root;
        while (curr != null) {
            depth++;
            stack[depth] = curr;
            curr = curr.left;
        }

        while (depth >= 0) {
            result[index] = stack[depth].key;
            index++;

            if (stack[depth].right != null) {
                curr = stack[depth].right;
                while (curr != null) {
                    depth++;
                    stack[depth] = curr;
                    curr = curr.left;
                }
            } else {
                int oldKey = stack[depth].key;
                do {
                    depth--;
                } while (depth >= 0 && stack[depth].key < oldKey);
            }
        }

        return result;
    }

}

