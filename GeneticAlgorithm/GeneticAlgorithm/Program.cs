using System;
using System.Diagnostics.CodeAnalysis;

namespace GeneticAlgorithm
{
    class Program
    {
        private static EquationSolver.Equation _equation = Equation.EquationB;
        
        private const int PopulationSize = 4000;
        private const int MinValue = -200;
        private const int MaxValue = 200;
        private const int TournamentGroupAmount = 3;
        private const int ChildrenAmount = 5000;
        private const int MutantsAmount = 1000;
        private const double MutationPossibility = 0.4;
        private const double SubstitutionPossibility = 0.3;

        [SuppressMessage("ReSharper.DPA", "DPA0001: Memory allocation issues")]
        static void Main(string[] args)
        {
            EquationSolver solver = new EquationSolver(
                _equation,
                PopulationSize, 
                MinValue, 
                MaxValue,
                TournamentGroupAmount,
                ChildrenAmount,
                MutantsAmount, 
                MutationPossibility, 
                SubstitutionPossibility
            );

            solver.Solve();
        }
    }
}