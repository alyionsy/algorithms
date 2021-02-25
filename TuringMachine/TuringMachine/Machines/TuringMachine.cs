using System.Collections.Generic;

namespace TuringMachine.Machines
{
    public class TuringMachine
    {
        protected Dictionary<(int state, char currentSymbol), (int nextState, char newSymbol, int shift)>
            TuringMachineDictionary { get; set; }

        public char[] Processing(char[] tape)
        {
            int state = 0;
            int indexOfCurrentSymbol = 0;
            
            while (state != -1 && state != -2)
            { 
                (int nextState, char newSymbol, int shift) nextStateInfo =
                    TuringMachineDictionary[(state, tape[indexOfCurrentSymbol])];
                
                state = nextStateInfo.nextState;
                tape[indexOfCurrentSymbol] = nextStateInfo.newSymbol;
                indexOfCurrentSymbol += nextStateInfo.shift;
            }

            return tape;
        }
    }
}