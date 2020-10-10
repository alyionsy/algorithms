package entity;

import java.util.ArrayList;
import java.util.List;

public class HashTableChaining {
    public class Node {
        public int key;
        public int data;

        public Node(int key, int data) {
            this.key = key;
            this.data = data;
        }
    }

    public List<Node>[] table;
    public double C;

    public HashTableChaining(int size, double C) {
        table = new List[size];
        this.C = C;
    }

    public void add(int key, int data) {
        int ind = hashFunction(key);
        Node node = new Node(key, data);

        if (table[ind] == null) {
            table[ind] = new ArrayList<>();
        }
        table[ind].add(node);
    }

    public int get(int key) {
        int ind = hashFunction(key);

        if (table[ind] != null) {
            for (Node node:
                 table[ind]) {
                if (node.key == key) {
                    return node.data;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    public int findMaxChain() {
        int maxSize = 0;
        for (List<Node> chain : table) {
            if (chain == null) {
                break;
            }
            if (chain.size() > maxSize) {
                maxSize = chain.size();
            }
        }
        return maxSize;
    }

    private int hashFunction(int key) {
        return (int)(table.length * (key * C % 1));
    }
}
