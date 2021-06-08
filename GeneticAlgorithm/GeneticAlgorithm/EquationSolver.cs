using System;
using System.Collections.Generic;
using System.Linq;

namespace GeneticAlgorithm
{
    public class EquationSolver
    {
        private static readonly Random Random = new Random();

        public delegate int Equation(ChromosomeVector chromosome);

        private readonly Equation _equation;

        private readonly int _populationSize;
        private readonly int _minValue;
        private readonly int _maxValue;
        private readonly int _tournamentGroupAmount;
        private readonly int _childrenAmount;
        private readonly int _mutantsAmount;
        private readonly double _mutationPossibility;
        private readonly double _substitutionPossibility;

        private List<ChromosomeVector> _population;

        public EquationSolver(Equation equation, int populationSize, int minValue, int maxValue, int tournamentGroupAmount,
            int childrenAmount, int mutantsAmount, double mutationPossibility, double substitutionPossibility)
        {
            _equation = equation;
            _populationSize = populationSize;
            _minValue = minValue;
            _maxValue = maxValue;
            _tournamentGroupAmount = tournamentGroupAmount;
            _childrenAmount = childrenAmount;
            _mutantsAmount = mutantsAmount;
            _mutationPossibility = mutationPossibility;
            _substitutionPossibility = substitutionPossibility;
            
            CreatePopulation();
        }
        
        private void CreatePopulation()
        {
            _population = new List<ChromosomeVector>();
            for (var i = 0; i < _populationSize; i++)
            {
                _population.Add(new ChromosomeVector(Random.Next(_minValue, _maxValue), Random.Next(_minValue, _maxValue), 
                    Random.Next(_minValue, _maxValue), Random.Next(_minValue, _maxValue), Random.Next(_minValue, _maxValue)));
            }
        }

        public ChromosomeVector Solve()
        {
            int generation = 0;
            while (true)
            {
                List<ChromosomeVector> selectedChromosomes = TournamentSelection();
                List<ChromosomeVector> children = Crossover(selectedChromosomes);
                Mutation(children);

                children = children.OrderBy(_ => _equation(_)).ToList();

                double averageDeviation = CalculateAverageDeviation(children);
                
                Console.WriteLine($"\ngeneration {generation}: ");
                Console.WriteLine($"closest solution vector: {children[0]}");
                Console.WriteLine($"distance: {_equation(children[0])}");
                Console.WriteLine($"average deviation of the generation: {averageDeviation}");
                
                if (_equation(children[0]) == 0)
                {
                    return children[0];
                }
                
                Substitution(children);
                generation++;
            }
        }

        private List<ChromosomeVector> TournamentSelection()
        {
            List<ChromosomeVector> populationCopy = new List<ChromosomeVector>(_population);
            populationCopy = populationCopy.OrderBy(_ => Random.Next()).ToList();
            List<ChromosomeVector> selected = new List<ChromosomeVector>();
            while (populationCopy.Count > _tournamentGroupAmount)
            {
                selected.Add(populationCopy.Take(_tournamentGroupAmount).OrderBy(_ => _equation(_)).ToList()[0]);
                populationCopy.RemoveRange(0, _tournamentGroupAmount);
            }

            selected.Add(populationCopy.OrderBy(_ => _equation(_)).ToList()[0]);
            return selected;
        }

        private List<ChromosomeVector> Crossover(List<ChromosomeVector> parents)
        {
            var children = new List<ChromosomeVector>();
            while (children.Count < _childrenAmount)
            {
                // removing self-crossover ? 
                var (parentAIndex, parentBIndex) = (0, 0);
                while (parentAIndex == parentBIndex)
                {
                    (parentAIndex, parentBIndex) = (Random.Next(parents.Count), Random.Next(parents.Count));
                }

                ChromosomeVector child = ProportionalCrossover(parents[parentAIndex], parents[parentBIndex]);
                children.Add(child);
            }

            return children;
        }
        
        private ChromosomeVector ProportionalCrossover(ChromosomeVector parentA, ChromosomeVector parentB)
        {
            double fitnessA = Math.Abs(1 / _equation(parentA));
            double fitnessB = Math.Abs(1 / _equation(parentB));
            double probabilityA = fitnessA / (fitnessA + fitnessB);
            double probabilityB = 1 - probabilityA;

            ChromosomeVector child = new ChromosomeVector();
            for (int i = 0; i < ChromosomeVector.Length; i++)
            {
                double probability = Random.NextDouble();
                if (probability < probabilityA)
                {
                    if (probability < probabilityB)
                    {
                        child[i] = probabilityA >= probabilityB ? parentA[i] : parentB[i];
                    }
                    else
                    {
                        child[i] = parentA[i];
                    }
                }
                else
                {
                    child[i] = parentB[i];
                }
            }
            return child;
        }
        
        private void Mutation(List<ChromosomeVector> children)
        {
            var mutants = children
                .OrderByDescending(value => _equation(value))
                .Take(_mutantsAmount);
            
            foreach (var mutant in mutants)
            {
                for (int j = 0; j < ChromosomeVector.Length; j++)
                {
                    if (Random.NextDouble() <= _mutationPossibility)
                    {
                        mutant[j] = Random.Next(_minValue, _maxValue);
                    }
                }
            }
        }

        private void Substitution(List<ChromosomeVector> children)
        {
            var possibilities = GeneratePossibilities(children);
            foreach (var value in _population)
            {
                if (Random.NextDouble() <= _substitutionPossibility)
                {
                    var random = Random.NextDouble();
                    var index = 0;
                    for (var j = 0; j < possibilities.Count; j++)
                    {
                        if (random < possibilities[j])
                        {
                            index = j;
                            break;
                        }
                    }

                    value.Copy(children[index]);
                    children.RemoveAt(index);
                    possibilities.RemoveAt(index);
                }
            }
        }
        
        private List<double> GeneratePossibilities(List<ChromosomeVector> solutions)
        {
            var possibilities = new List<double>();
            
            // fitness = приспособленность
            var fitnessOfSolution = new List<double>();
            var fitnessSum = 0.0d;
            
            foreach (var solution in solutions)
            {
                var fitness = _equation(solution) == 0 ? 1 : Math.Abs(1 / _equation(solution));
                fitnessSum += fitness;
                fitnessOfSolution.Add(fitness);
            }
            
            for (var i = 0; i < solutions.Count; i++)
            {
                var possibility = fitnessOfSolution[i] / fitnessSum;
                possibilities.Add(possibility);
            }

            return possibilities.OrderByDescending(_ => _).ToList();
        }

        private double CalculateAverageDeviation(List<ChromosomeVector> solutions)
        {
            double deviation = 0.0d;
            foreach (var solution in solutions)
            {
                deviation += Math.Abs(_equation(solution));
            }

            return deviation / solutions.Count;
        }
    }
}