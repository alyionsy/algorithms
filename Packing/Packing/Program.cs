using System;
using System.Collections.Generic;

namespace Packing
{
    class Program
    {
        static void Main(string[] args)
        {
            const int amount = 1000;
            var items = new List<double>();
            
            var random = new Random();
            for (var i = 0; i < amount; i++)
            {
                items.Add(random.NextDouble() % 1);
            }

            Console.WriteLine("- Number of total bins used -");
            Console.WriteLine($"Next-Fit: {Packer.NextFit(items).Count}");
            Console.WriteLine($"First-Fit: {Packer.FirstFit(items).Count}");
            Console.WriteLine($"Best-Fit: {Packer.BestFit(items).Count}");
            Console.WriteLine($"First-Fit Decreasing: {Packer.FirstFitDecreasing(items).Count}");
        }
    }
}