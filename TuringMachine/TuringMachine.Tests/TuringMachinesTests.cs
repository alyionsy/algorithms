using NUnit.Framework;
using TuringMachine.Machines;

namespace TuringMachine.Tests
{
    public class Tests
    {
        [TestCase("###10000#", "1")]
        [TestCase("###10100100010#", "0")]
        public void IsPowerOfTwoTest(string initial, string expected)
        {
            IsPowerOfTwo isPowerOfTwo = new IsPowerOfTwo();
            string actual = new string(isPowerOfTwo.Processing(initial.ToCharArray()));

            Assert.AreEqual(expected, actual.Replace("#", ""), "Value on tape is not correct");
        }
        
        [TestCase("#12031########", "20131")]
        [TestCase("#23031213############", "20233113")]
        public void FirstAllEvenThanAllOddTest(string initial, string expected)
        {
            FirstAllEvenThenAllOdd firstAllEvenThenAllOdd = new FirstAllEvenThenAllOdd();
            string actual = new string(firstAllEvenThenAllOdd.Processing(initial.ToCharArray()));

            Assert.AreEqual(expected, actual.Replace("#", ""), "Value on tape is not correct");
        }

    }
}