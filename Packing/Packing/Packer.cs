using System;
using System.Collections.Generic;

namespace Packing
{
    public class Packer
    {
        public static List<Bin> NextFit(List<double> items)
        {
            List<Bin> bins = new List<Bin>{new Bin()};
            foreach (var item in items) 
            {
                if (!bins[^1].PutItem(item))
                {
                    bins.Add(new Bin());
                    bins[^1].PutItem(item);
                }
            }

            return bins;
        }
        
        public static List<Bin> FirstFit(List<double> items)
        {
            List<Bin> bins = new List<Bin>{new Bin()};
            foreach (var item in items)
            {
                bool itemIsPlaced = false;
                foreach (var bin in bins)
                {
                    if (bin.PutItem(item))
                    {
                        itemIsPlaced = true;
                        break;
                    }
                }

                if (itemIsPlaced) continue;
                
                bins.Add(new Bin());
                bins[^1].PutItem(item);
            }

            return bins;
        }
        
        public static List<Bin> BestFit(List<double> items)
        {
            List<Bin> bins = new List<Bin>{new Bin()};
            foreach (var item in items)
            {
                double bestFitFreeSpace = double.MaxValue;
                int bestFitIndex = -1;
                for (var index = 0; index < bins.Count; index++)
                {
                    if (bins[index].ItemFitsBin(item) && bins[index].FreeSpace < bestFitFreeSpace)
                    {
                        bestFitFreeSpace = bins[index].FreeSpace;
                        bestFitIndex = index;
                    }
                }

                if (bestFitIndex != -1)
                {
                    bins[bestFitIndex].PutItem(item);
                    continue;
                }
                
                bins.Add(new Bin());
                bins[^1].PutItem(item);
            }

            return bins;
        }
        
        public static List<Bin> FirstFitDecreasing(List<double> items)
        {
            items.Sort();
            items.Reverse();
            List<Bin> bins = new List<Bin>{new Bin()};
            foreach (var item in items)
            {
                bool itemIsPlaced = false;
                foreach (var bin in bins)
                {
                    if (bin.PutItem(item))
                    {
                        itemIsPlaced = true;
                        break;
                    }
                }

                if (itemIsPlaced) continue;
                
                bins.Add(new Bin());
                bins[^1].PutItem(item);
            }

            return bins;
        }
        
        public static List<Bin> PackNextFit(List<double> parts)
        {
            List<Bin> boxes = new List<Bin>{new Bin()};
            foreach (double part in parts) 
            {
                if (!boxes[^1].PutItem(part))
                {
                    boxes.Add(new Bin());
                    boxes[^1].PutItem(part);
                }
            }

            return boxes;
        }

        public static List<Bin> PackFirstFit(List<double> parts)
        {
            List<Bin> boxes = new List<Bin> {new Bin()};

            foreach (double part in parts)
            {
                if (!boxes[^1].PutItem(part))
                {
                    bool isPutInExistingBox = false;
                    foreach (Bin box in boxes)
                    {
                        if (box.PutItem(part))
                        {
                            isPutInExistingBox = true;
                            break;
                        }
                    }

                    if (!isPutInExistingBox)
                    {
                        boxes.Add(new Bin());
                        boxes[^1].PutItem(part);
                    }
                }
            }

            return boxes;
        }

        public static List<Bin> PackBestFit(List<double> parts)
        {
            List<Bin> boxes = new List<Bin> {new Bin()};

            foreach (double part in parts)
            {
                double spaceOfBestFit = double.MaxValue;
                int indexOfBestFit = -1;
                for (int i = 0; i < boxes.Count; i++)
                {
                    double howMuchSpaceWillBeAfterInsert = boxes[i].FreeSpace - part;
                    if (howMuchSpaceWillBeAfterInsert >= 0 && spaceOfBestFit > howMuchSpaceWillBeAfterInsert)
                    {
                        spaceOfBestFit = howMuchSpaceWillBeAfterInsert;
                        indexOfBestFit = i;
                    }
                }

                if (indexOfBestFit != -1)
                {
                    boxes[indexOfBestFit].PutItem(part);
                }
                else
                {
                    boxes.Add(new Bin());
                    boxes[^1].PutItem(part);
                }
            }

            return boxes;
        }

        public static List<Bin> PackFirstFitDecrease(List<double> parts)
        {
            parts.Sort();
            parts.Reverse();
            return PackFirstFit(parts);
        }
    }
}