using System.Collections.Generic;

namespace TuringMachine.Machines
{
    public class IsPowerOfTwo : TuringMachine
    {
        public IsPowerOfTwo()
        {
            TuringMachineDictionary = 
                new Dictionary<(int state, char currentSymbol), (int nextState, char newSymbol, int shift)>
                {
                    {(0, '#'), (0, '#', 1)},
                    {(0, '0'), (0, '#', 1)},
                    {(0, '1'), (1, '#', 1)},
                    
                    {(1, '0'), (1, '#', 1)},
                    {(1, '1'), (2, '#', 1)},
                    {(1, '#'), (-1, '1', 1)},
                    
                    {(2, '0'), (2, '#', 1)},
                    {(2, '1'), (2, '#', 1)},
                    {(2, '#'), (-2, '0', 0)}
                };
        }
    }
}