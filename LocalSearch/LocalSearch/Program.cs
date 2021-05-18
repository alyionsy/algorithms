using System;
using System.Collections.Generic;
using System.Linq;

namespace LocalSearch
{
    class Program
    {
        private const int MATRIX_SIZE = 10;
        private const int MAX_VALUE = 100;

        private static void Main()
        {
            List<List<int>> weightMatrix = CreateWeightMatrix(MATRIX_SIZE, MAX_VALUE);
            TravellingSalesmanProblemSolver solver = new TravellingSalesmanProblemSolver(weightMatrix);
            
            PrintMatrix(weightMatrix);
                
            List<int> shortestRoute = solver.Solve();
            
            Console.WriteLine($"the last route is the shortest one.");
        }
        
        private static List<List<int>> CreateWeightMatrix(int size, int maxValue)
        {
            Random random = new Random();
            List<List<int>> weightMatrix = new List<List<int>>();
            
            for (int i = 0; i < size; i++)
            {
                weightMatrix.Add(new List<int>());
                for (int j = 0; j < size; j++)
                {
                    weightMatrix[i].Add(0);
                }
            }

            for (int i = 0; i < size; i++)
            {
                for (int j = i; j < size; j++)
                {
                    weightMatrix[i][j] = random.Next(1, maxValue);
                    weightMatrix[j][i] = weightMatrix[i][j];
                }
            }
            
            return weightMatrix;
        }

        private static void PrintMatrix(List<List<int>> matrix)
        {
            Console.WriteLine("matrix:");

            var width = matrix
                .Max(line => line.Max())
                .ToString()
                .Length + 3;

            foreach (List<int> line in matrix)
            {
                foreach (int value in line)
                {
                    Console.Write($"{{0, {width}}}", value.ToString());
                }
                Console.WriteLine();
            }
        }
    }
}