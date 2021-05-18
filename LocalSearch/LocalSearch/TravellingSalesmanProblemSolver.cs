using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LocalSearch
{
    public class TravellingSalesmanProblemSolver
    {
        private static readonly Random Random = new Random();

        private readonly List<List<int>> _weights;
        private readonly List<int> _vertices;

        public TravellingSalesmanProblemSolver(List<List<int>> weights)
        {
            _weights = weights;
            _vertices = new List<int>();
            for (int i = 0; i < weights.Count; i++)
            {
                _vertices.Add(i);
            }
        }

        public List<int> Solve()
        {
            List<int> mainRoute = _vertices.OrderBy(Random.Next).ToList();    // randomizing initial route
            int minimalWeight = CalculateRouteWeight(mainRoute);
            
            Console.WriteLine($"\ninitial random route: {RouteToString(mainRoute)}");
            Console.WriteLine($"weight: {CalculateRouteWeight(mainRoute)}");

            List<int> shortestRoute = new List<int>(mainRoute);

            while (true)
            {
                for (int i = 0; i < mainRoute.Count - 2; i++)
                {
                    for (int j = i + 2; j < mainRoute.Count; j++)
                    {
                        List<int> possibleShortestRoute = SwapNodes(mainRoute, i, j);

                        int newWeight = CalculateRouteWeight(possibleShortestRoute);

                        if (newWeight < minimalWeight)
                        {
                            minimalWeight = newWeight;
                            shortestRoute = possibleShortestRoute;

                            Console.WriteLine("\n=======");
                            Console.WriteLine($"shortest route: {RouteToString(possibleShortestRoute)}");
                            Console.WriteLine($"weight: {minimalWeight}");
                        }
                    }
                }

                if (mainRoute == shortestRoute)
                {
                    return shortestRoute;
                }
                
                mainRoute = shortestRoute;
            }
        }

        private List<int> SwapNodes(List<int> route, int index1, int index2)
        {
            List<int> newRoute = new List<int>(route);
            (newRoute[index1], newRoute[index2]) = (newRoute[index2], newRoute[index1]);

            return newRoute;
        }

        private int CalculateRouteWeight(List<int> route)
        {
            int weight = _weights[route[0]][route[^1]];

            for (int i = 1; i < route.Count; i++)
            {
                weight += _weights[route[i - 1]][route[i]];
            }

            return weight;
        }

        private static string RouteToString(List<int> route)
        {
            StringBuilder builder = new StringBuilder(" ");
            foreach (int vertex in route)
            {
                builder.Append(vertex).Append("  ");
            }

            return builder.Append(route[0]).ToString();
        }
    }
}