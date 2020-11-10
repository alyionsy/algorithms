package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Graph {
    int[][] adjacencyList;
    List<Boolean> used;
    List<List<Integer>> components;

    public Graph(int[][] adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.used = new ArrayList<>(getGraphSize());
        for (int i = 0; i < getGraphSize(); i++) {
            used.add(false);
        }
        this.components = new ArrayList<>();
    }

    public void depthFirstSearch(int current) {
        used.set(current, true);
        for (int next : adjacencyList[current]) {
            if (!used.get(next)) {
                depthFirstSearch(next);
            }
        }
    }

    public void findComponents() {
        List<Integer> included = new ArrayList<>();
        int numberOfComponents = 0;
        for (int i = 0; i < used.size(); i++) {
            if (!used.get(i)) {
                components.add(new ArrayList<>());
                ++numberOfComponents;
                depthFirstSearch(i);
            }
            for (int j = 0; j < used.size(); j++) {
                if (used.get(j) && !included.contains(j)) {
                    components.get(numberOfComponents - 1).add(j);
                    included.add(j);
                }
            }
        }
    }

    public int getGraphSize() {
        int max = 0;
        for (int [] row:
             adjacencyList) {
            for (int element:
                 row) {
                if (element > max) {
                    max = element;
                }
            }
        }
        return max + 1;
    }

    public void printComponents() {
        for (List<Integer> element:
             components) {
            System.out.println(element);
        }
    }

    public int getNumberOfComponents() {
        return components.size();
    }

    public boolean isConnected() {
        return getNumberOfComponents() == 1;
    }

    public boolean isEulerian() {
        if (isConnected()) {
            for (int i = 0; i < getGraphSize(); i++) {
                if (adjacencyList[i].length % 2 != 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Stack<Integer> findEulerianCycle() {
        if (!isEulerian()) {
            return null;
        }

        Stack<Integer> tempStack = new Stack<>();
        Stack<Integer> eulerianCycle = new Stack<>();

        List<List<Integer>> graphCopy = createListCopy();

        int vertex = 0;

        tempStack.push(vertex);

        while (tempStack.size() > 0) {
            vertex = tempStack.peek();

            if (graphCopy.get(vertex).size() > 0) {
                int nextVertex = graphCopy.get(vertex).get(0);

                tempStack.push(nextVertex);

                graphCopy.get(vertex).remove(graphCopy.get(vertex).lastIndexOf(nextVertex));
                graphCopy.get(nextVertex).remove(graphCopy.get(nextVertex).lastIndexOf(vertex));

            }
            else {
                int vertexToEuler = tempStack.pop();
                eulerianCycle.push(vertexToEuler);
            }
        }

        return eulerianCycle;
    }

    private List<List<Integer>> createListCopy() {
        List<List<Integer>> graphCopy = new ArrayList<>();
        for (int i = 0; i < adjacencyList.length; i++) {
            graphCopy.add(new ArrayList<>());
        }

        for (int i = 0; i < adjacencyList.length; i++) {
            for (int j = 0; j < adjacencyList[i].length; j++) {
                graphCopy.get(i).add(adjacencyList[i][j]);
            }
        }
        return graphCopy;
    }

    public void depthFirstSearchColored(int current, List<Integer> colors) {
        for (int next : adjacencyList[current]) {
            if (colors.get(next) == 0) {
                colors.set(next, 3 - colors.get(current));
                depthFirstSearchColored(next, colors);
            }
            else if (colors.get(next).equals(colors.get(current))) {
                colors.set(next, -1);
            }
        }
    }

    public List<List<Integer>> findColors() {
        List<Integer> colors = new ArrayList<>();
        List<Integer> firstPart = new ArrayList<>();
        List<Integer> secondPart = new ArrayList<>();

        for (int i = 0; i < getGraphSize(); i++) {
            colors.add(0);
        }

        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i) == 0) {
                colors.set(i, 1);
                depthFirstSearchColored(i, colors);
            }
            if (colors.get(i) == 1) {
                firstPart.add(i);
            }
            if (colors.get(i) == 2) {
                secondPart.add(i);
            }
        }

        if (colors.contains(-1)) {
            return null;
        }

        List<List<Integer>> parts = new ArrayList<>();
        parts.add(firstPart);
        parts.add(secondPart);
        return parts;
    }

    public void printParts() {
        List<List<Integer>> parts = findColors();
        System.out.println("first part: " + parts.get(0));
        System.out.println("second part: " + parts.get(1));
    }

    public void showAdjacencyList() {
        for (int i = 0; i < adjacencyList.length; i++) {
            System.out.println("vertex " + i + ": " + Arrays.toString(adjacencyList[i]));
        }
    }
}
