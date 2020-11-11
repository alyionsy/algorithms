package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Graph {
    private int[][] adjacencyList;
    private List<Boolean> used;
    private List<List<Integer>> components;

    public Graph(int[][] adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.used = new ArrayList<>(getGraphSize());
        for (int i = 0; i < getGraphSize(); i++) {
            used.add(false);
        }
        this.components = new ArrayList<>();
    }

    public void depthFirstSearchFindComponent(int current, List<Integer> currentComponent) {
        used.set(current, true);
        currentComponent.add(current);

        for (int next : adjacencyList[current]) {
            if (!used.get(next)) {
                depthFirstSearchFindComponent(next, currentComponent);
            }
        }
    }

    public void determineComponents() {
        for (int i = 0; i < used.size(); i++) {
            if (!used.get(i)) {
                List<Integer> currentComponent = new ArrayList<>();
                depthFirstSearchFindComponent(i, currentComponent);
                components.add(currentComponent);
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

    public void depthFirstSearchColored(int current, List<Integer> firstPart, List<Integer> secondPart,
                                        boolean isFirstColor) {
        used.set(current, true);
        if (isFirstColor) {
            firstPart.add(current);
        }
        else {
            secondPart.add(current);
        }

        for (int vertex: adjacencyList[current]) {
            if (!used.get(vertex)) {
                depthFirstSearchColored(vertex, firstPart, secondPart, !isFirstColor);
            }
        }
    }

    public List<List<Integer>> findColors() {
        List<Integer> firstPart = new ArrayList<>();
        List<Integer> secondPart = new ArrayList<>();

        for (int i = 0; i < getGraphSize(); i++) {
            used.set(i, false);
        }

        for (List<Integer> component: components) {
            depthFirstSearchColored(component.get(0), firstPart, secondPart, true);
        }

        if (firstPart.size() + secondPart.size() != getGraphSize()) {
            return null;
        }

        for (int vertex: firstPart) {
            if (secondPart.contains(vertex)) {
                return null;
            }
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
