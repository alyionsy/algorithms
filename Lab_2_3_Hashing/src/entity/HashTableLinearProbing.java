package entity;

public class HashTableLinearProbing {
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

    public HashTableLinearProbing(int size, double C) {
        table = new Node[size];
        this.C = C;
    }


    public void add(int key, int data) {
        int ind = hashFunction(key);
        Node node = new Node(key, data);

        if (table[ind] == null) {
            table[ind] = node;
            return;
        }

        ind++;

        while (ind < table.length) {
            if (table[ind] != null && hashFunction(table[ind].key) > hashFunction(key)) {
                return;
            }

            if (table[ind] != null && hashFunction(table[ind].key) == hashFunction(key)) {
                table[ind] = node;
                return;
            }

            if (table[ind] == null) {
                table[ind] = node;
                return;
            }
        }
    }

    public int get(int key) {
        int ind = hashFunction(key);

        if (table[ind] != null) {
            return table[ind].data;
        }
        return Integer.MIN_VALUE;
    }

    private int hashFunction(int key) {
        return (int)(table.length * (key * C % 1));
    }
}
