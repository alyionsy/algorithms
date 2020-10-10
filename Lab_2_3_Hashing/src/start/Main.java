package start;

import entity.HashTableChaining;
import entity.HashTableDoubleHashing;
import entity.HashTableLinearProbing;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTableChaining hashTableChaining = new HashTableChaining(150, 0.616545);
        chainingDemonstration(hashTableChaining);

        HashTableLinearProbing probingHashTable = new HashTableLinearProbing(74, 0.616545);
        linearProbingDemonstration(probingHashTable);

        HashTableDoubleHashing doubleHashingHashTable = new HashTableDoubleHashing(74, 0.616545);
        doubleHashingDemonstration(doubleHashingHashTable);

        experiment();
    }

    private static List<Integer>[] generateKeys(int listsAmount, int listLength, int range) {
        List<Integer>[] keyLists = new ArrayList[listsAmount];

        for (int i = 0; i < listsAmount; i++) {
            keyLists[i] = generateListOfKeys(listLength, range);
        }

        return keyLists;
    }

    private static List<Integer> generateListOfKeys(int lengthOfSeveralList, int range) {
        List<Integer> listOfKeys = new ArrayList<>();

        for (int i = 0; i < lengthOfSeveralList; i++) {
            listOfKeys.add((int) (Math.random() * (range) + 1));
        }

        return listOfKeys;
    }

    private static void chainingDemonstration(HashTableChaining hashTableChaining) {
        for (int i = 0; i < 300; i++)
        {
            hashTableChaining.add(i, 300 - i);
        }

        List<HashTableChaining.Node>[] array = hashTableChaining.table;

        for (int i = 0; i < array.length; i++)
        {
            List<HashTableChaining.Node> currentChain = array[i];
            if (currentChain != null)
            {
                System.out.print("i=" + i + ": ");
                for(HashTableChaining.Node node : currentChain)
                {
                    System.out.print(node.key + "-" + node.data + "; ");
                }
                System.out.println();
            }
            else
            {
                System.out.println(i + " => empty");
            }
        }

        for (int i = 0; i < 300; i++)
        {
            System.out.print(" " + hashTableChaining.get(i));
        }
    }

    private static void linearProbingDemonstration(HashTableLinearProbing probingHashTable) {
        for (int i = 0; i < 30; i++)
        {
            probingHashTable.add(i, 30 - i);
        }

        HashTableLinearProbing.Node[] arrayProbing = probingHashTable.table;

        for (int i = 0; i < arrayProbing.length; i++)
        {
            HashTableLinearProbing.Node currentNode = arrayProbing[i];
            if (currentNode != null)
            {
                System.out.print("i=" + i + ": " + currentNode.key + "-" + currentNode.data + "; ");
                System.out.println();
            }
            else
            {
                System.out.println("i=" + i + ": empty;");
            }
        }

        for (int i = 0; i < 30; i++)
        {
            System.out.print(" " + probingHashTable.get(i));
        }
    }

    private static void doubleHashingDemonstration(HashTableDoubleHashing doubleHashingHashTable) {
        for (int i = 0; i < 30; i++)
        {
            doubleHashingHashTable.add(i, 30 - i);
        }

        HashTableDoubleHashing.Node[] arrayDoubleHashing = doubleHashingHashTable.table;

        for (int i = 0; i < arrayDoubleHashing.length; i++)
        {
            HashTableDoubleHashing.Node currentNode = arrayDoubleHashing[i];
            if (currentNode != null)
            {
                System.out.print("i=" + i + ": " + currentNode.key + "-" + currentNode.data + "; ");
                System.out.println();
            }
            else
            {
                System.out.println("i=" + i + ": empty;");
            }
        }

        for (int i = 0; i < 30; i++)
        {
            System.out.print(" " + doubleHashingHashTable.get(i));
        }
    }

    private static void experiment() {
        int listsAmount = 200;
        int listLength = 800;
        int range = 10000;
        int tableSize = 250;
        double test = 0.876876432;

        List<Integer>[] keyLists = generateKeys(listsAmount, listLength, range);

        int winCounterTest = 0;
        int winCounterKnuth = 0;

        for (int i = 0; i < listsAmount; i++) {
            HashTableChaining chainHashTableKnuth = new HashTableChaining(tableSize, 0.6180339887);
            HashTableChaining chainHashTableTest = new HashTableChaining(tableSize, test);

            for (List<Integer> list : keyLists) {
                for (int key : list) {
                    chainHashTableKnuth.add(key, key);
                    chainHashTableTest.add(key, key);
                }

                int maxKnuth = chainHashTableKnuth.findMaxChain();
                int maxTest = chainHashTableTest.findMaxChain();

                System.out.println("\nlargest chain (test) - " + maxTest);
                System.out.println("largest chain (knuth) - " + maxKnuth);

                if (maxTest <= maxKnuth) {
                    winCounterTest++;
                }
                else {
                    winCounterKnuth++;
                }
            }
        }

        System.out.println("\n-----");
        System.out.println("number of wins (test): " + winCounterTest);
        System.out.println("number of wins (knuth): " + winCounterKnuth);
        System.out.print("!!! winner: ");
        if (winCounterTest >= winCounterKnuth) {
            System.out.print("test");
        }
        else {
            System.out.print("knuth !!!");
        }
    }
}
