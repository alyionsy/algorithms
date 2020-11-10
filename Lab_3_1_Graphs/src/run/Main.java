package run;

import entity.Graph;

public class Main {
    public static void main(String[] args) {
        int[][] eulerianList = {{1, 2}, {0, 2, 3, 4}, {0, 1, 4, 5}, {1, 4}, {1, 2, 3, 5}, {2, 4}};
        int[][] doubleConnectedList = {{1, 3}, {0, 3, 4}, {5, 6}, {0, 1, 4}, {0, 1, 3}, {2, 6}, {2, 5}};
        int[][] tripleConnectedList = {{1, 2}, {0, 2}, {0, 1}, {4, 5}, {3, 5}, {3, 4}, {7, 8}, {6, 8}, {6, 7}};
        int[][] bipartiteList = {{1}, {0, 2, 3}, {1, 4}, {1}, {2}};

        Graph graphEulerian = new Graph(eulerianList);
        Graph graphDoubleConnected = new Graph(doubleConnectedList);
        Graph graphTripleConnected = new Graph(tripleConnectedList);
        Graph bipartiteGraph = new Graph(bipartiteList);

        graphManager(graphEulerian);
        graphManager(graphDoubleConnected);
        graphManager(graphTripleConnected);
        graphManager(bipartiteGraph);

    }

    public static void graphManager(Graph graph) {
        System.out.println();
        System.out.println();

        graph.showAdjacencyList();
        System.out.println("-----");

        graph.findComponents();
        System.out.println("number of components: " + graph.getNumberOfComponents() + "\ncomponents: ");
        graph.printComponents();
        System.out.println("-----");

        System.out.println("is eulerian: " + graph.isEulerian());
        if (graph.isEulerian()) {
            System.out.println("eulerian cycle: " + graph.findEulerianCycle());
        }
        System.out.println("-----");

        if (graph.findColors() != null) {
            System.out.println("is bipartite : true. parts:");
            graph.printParts();
        }
        else {
            System.out.println("is bipartite : false");
        }
        System.out.println("-----");
    }
}