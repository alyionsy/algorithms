using System;

namespace GeneticAlgorithm
{
    public class ChromosomeVector
    {
        public int U;
        public int W;
        public int X;
        public int Y;
        public int Z;
        
        public const int Length = 5;
        
        public int this[int i]
        {
            get => i switch
            {
                0 => U,
                1 => W,
                2 => X,
                3 => Y,
                4 => Z,
                _ => throw new IndexOutOfRangeException()
            };
            set
            {
                switch (i)
                {
                    case 0: U = value; break;
                    case 1: W = value; break;
                    case 2: X = value; break;
                    case 3: Y = value; break;
                    case 4: Z = value; break;
                    default: throw new IndexOutOfRangeException();
                }
            }
        }
        
        public ChromosomeVector() 
        {
            U = 0;
            W = 0;
            X = 0;
            Y = 0;
            Z = 0;
        }

        public ChromosomeVector(int u, int w, int x, int y, int z) 
        {
            U = u;
            W = w;
            X = x;
            Y = y;
            Z = z;
        }

        public ChromosomeVector Clone() => new ChromosomeVector(U, W, X, Y, Z);

        public void Copy(ChromosomeVector chromosome)
        {
            U = chromosome.U;
            W = chromosome.W;
            X = chromosome.X;
            Y = chromosome.Y;
            Z = chromosome.Z;
        }

        public override string ToString()
        {
            return $"({U}, {W}, {X}, {Y}, {Z})";
        }
    }
}