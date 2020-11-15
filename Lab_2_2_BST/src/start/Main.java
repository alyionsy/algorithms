package start;

import entity.BinarySearchTree;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        int[] arr = { 70, 60, 50, 30, 20, 40, 80 };
        tree.insertArr(arr);

        System.out.println("ASC ORDER:");
        tree.inorderAsc();
        System.out.println("\nDESC ORDER:");
        tree.inorderDesc();

        System.out.println("\ninitial array: " + Arrays.toString(arr));
        tree.print();

        int k = 3;
        System.out.println(k + "\nminimal " + k + " node: " + tree.findKMinimal(k).getData());
        tree.balance();
        System.out.println("balanced:");
        tree.print();
    }
}
