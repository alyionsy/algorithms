package entity;

public class HashTableDoubleHashing {
    public class Node {
        public int key;
        public int data;

        public Node(int key, int data) {
            this.key = key;
            this.data = data;
        }
    }

    public Node[] table;
    public double C;

    public HashTableDoubleHashing(int size, double C) {
        table = new Node[size];
        this.C = C;
    }

    public void add(int key, int data) {
        Node node = new Node(key, data);

        int hash = doubleHashFunction(key, 1);
        int ind = hash;

        for (int i = 1; table[ind] != null; i++ ) {
            if (ind == hash && i != 1) {
                return;
            }
            if (table[ind] != null) {
                break;
            }
            ind = doubleHashFunction(key, i);
        }

        table[ind] = node;
    }

    public int get(int key) {
        int hash = doubleHashFunction(key, 1);
        int ind = hash;

        for (int i = 1; table[ind] != null; i++ ) {
            if (ind == hash && i != 1) {
                break;
            }
            if (table[ind].key == key) {
                return table[ind].data;
            }
            ind = doubleHashFunction(key, i);
        }

        return Integer.MIN_VALUE;
    }

    private int hashFunction(int key) {
        return (int)(table.length * (key * C % 1));
    }

    private int hashFunctionDivision(int key) {
        return (key % table.length);
    }

    private int doubleHashFunction(int key, int i) {
        return ((hashFunction(key) + i * hashFunctionDivision(key)) % table.length);
    }
}
