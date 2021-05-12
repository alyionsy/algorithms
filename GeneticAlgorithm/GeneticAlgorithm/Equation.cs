using System;

namespace GeneticAlgorithm
{
    public class Equation
    {
        public static int EquationA(ChromosomeVector chromosome)
        {
            var (u, w, x, y, z) = (chromosome.U, chromosome.W, chromosome.X, chromosome.Y, chromosome.Z);
            return Math.Abs(((w * w) * x * (y * y) * z) + ((w * w) * (x * x) * (y * y) * z) + ((u * u) * w * x) + ((w * w) * (x * x) * (y * y) * (z * z)) + (y * z) + 5);
        }
        
        public static int EquationB(ChromosomeVector chromosome)
        {
            var (u, w, x, y, z) = (chromosome.U, chromosome.W, chromosome.X, chromosome.Y, chromosome.Z);
            return Math.Abs(z + (u * x * y) + ((w * w) * x * (z * z)) + (w * (x * x) * (z * z)) + ((u * u) * (x * x) * y * (z * z)) + 50);
        }
        
        public static int EquationExtra(ChromosomeVector chromosome)
        {
            var (u, w, x, y, z) = (chromosome.U, chromosome.W, chromosome.X, chromosome.Y, chromosome.Z);
            return Math.Abs(50 + x + y + (u * w * x * y) + ((u * u) * w * x * y) + (u * x * y * (z * z)));
        }
    }
}