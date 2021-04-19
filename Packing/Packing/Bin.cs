namespace Packing
{
    public class Bin
    {
        private double _load;
        private readonly double _capacity;
        public double FreeSpace => _capacity - _load;

        public Bin()
        {
            _load = 0.0;
            _capacity = 1.0;
        }

        public bool ItemFitsBin(double itemWeight)
        {
            return FreeSpace - itemWeight > 0;
        }
        
        public bool PutItem(double itemWeight)
        {
            if (ItemFitsBin(itemWeight))
            {
                _load += itemWeight;
            }
            
            return ItemFitsBin(itemWeight);
        }
    }
}