package entity;

public class BinarySearchTree {
    static final int COUNT = 10;

    public class Node {
        int data, leftSize, rightSize;
        Node left, right, parent;

        public Node(int item) {
            data = item;
            leftSize = 0;
            rightSize = 0;
            left = null;
            right = null;
            parent = null;
        }

        public int getData() {
            return data;
        }

        public void countSizes()
        {
            leftSize = left == null ? 0 : left.leftSize + left.rightSize + 1;
            rightSize = right == null ? 0 : right.leftSize + right.rightSize + 1;
        }
    }

    private Node root;

    public BinarySearchTree() { root = null; }

    public void insert(int data) { root = insertRec(root, data, null); }

    private Node insertRec(Node root, int data, Node parent) {
        if (root == null) {
            root = new Node(data);
            root.parent = parent;
            return root;
        }

        if (data < root.data) {
            root.leftSize++;
            root.left = insertRec(root.left, data, root);
        } else if (data > root.data) {
            root.rightSize++;
            root.right = insertRec(root.right, data, root);
        }

        return root;
    }

    public void insertArr(int[] arr) {
        for (int value:
             arr) {
            insert(value);
        }
    }

    public void inorderAsc()  {
        inorderAscRec(root);
    }

    public void inorderDesc()  {
        inorderDescRec(root);
    }

    void inorderAscRec(Node root) {
        if (root != null) {
            inorderAscRec(root.left);
            System.out.println(root.data);
            inorderAscRec(root.right);
        }
    }

    void inorderDescRec(Node root) {
        if (root != null) {
            inorderDescRec(root.right);
            System.out.println(root.data);
            inorderDescRec(root.left);
        }
    }

    public Node findKMinimal(int k) {
        return findKMinimal(k, null);
    }

    public Node findKMinimal(int k, Node subtreeHead) {
        if (subtreeHead == null) {
            subtreeHead = root;
        }

        while (subtreeHead.leftSize + 1 != k) {
            if (subtreeHead.leftSize + 1 < k) {
                k -= subtreeHead.leftSize + 1;
                subtreeHead = subtreeHead.right;
            }
            else {
                subtreeHead = subtreeHead.left;
            }
        }

        return subtreeHead;
    }

    public void balance()
    {
        getBalancedSubtree(root);
    }

    private void getBalancedSubtree(Node root) {
        if (root.leftSize + root.rightSize <= 1) {
            return;
        }

        int middleElement = ( root.leftSize + root.rightSize + 1 ) / 2;
        Node nodeToHead = findKMinimal(middleElement + 1, root);

        while (nodeToHead.data != root.data && nodeToHead.parent != null) {
            Node parentNode = nodeToHead.parent;
            if (parentNode.left != null && parentNode.left.data == nodeToHead.data) {
                rotateRight(parentNode);
            } else if (parentNode.right != null && parentNode.right.data == nodeToHead.data) {
                rotateLeft(parentNode);
            }
            nodeToHead = parentNode;
        }

        getBalancedSubtree(nodeToHead.left);
        getBalancedSubtree(nodeToHead.right);
    }

    public void rotateRight(Node parent) {
        int swapData = parent.data;
        parent.data = parent.left.data;
        parent.left.data = swapData;

        Node parentLeft = parent.left;
        Node parentRight = parent.right;

        Node nodeLeft = parent.left.left;
        Node nodeRight = parent.left.right;

        parent.right = parentLeft;
        parent.left = nodeLeft;

        parent.right.right = parentRight;
        parent.right.left = nodeRight;

        if (parent.left != null) {
            parent.left.countSizes();
        }
        if (parent.right != null) {
            parent.right.countSizes();
        }

        parent.countSizes();
        setParentNodes(parent);
    }

    private void rotateLeft(Node parent) {
        int swapData = parent.data;
        parent.data = parent.right.data;
        parent.right.data = swapData;

        Node parentLeft = parent.left;
        Node parentRight = parent.right;

        Node nodeLeft = parent.right.left;
        Node nodeRight = parent.right.right;

        parent.left = parentRight;
        parent.right = nodeRight;

        parent.left.left = parentLeft;
        parent.left.right = nodeLeft;

        if (parent.left != null) {
            parent.left.countSizes();
        }
        if (parent.right != null) {
            parent.right.countSizes();
        }

        parent.countSizes();
        setParentNodes(parent);
    }

    private void setParentNodes(Node parent) {
        if (parent.left != null)
        {
            parent.left.parent = parent;
            if (parent.left.left != null)
            {
                parent.left.left.parent = parent.left;
            }

            if (parent.left.right != null)
            {
                parent.left.right.parent = parent.left;
            }
        }

        if (parent.right != null)
        {
            parent.right.parent =  parent;
            if (parent.right.left != null)
            {
                parent.right.left.parent = parent.right;
            }

            if (parent.right.right != null)
            {
                parent.right.right.parent = parent.right;
            }
        }
    }

    static void printUtil(Node root, int space) {
        if (root == null) {
            return;
        }

        space += COUNT;

        printUtil(root.right, space);

        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(root.data + "\n");

        printUtil(root.left, space);
    }

    public void print() {
        printUtil(root, 0);
    }
}