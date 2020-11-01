using System;

namespace HW1
{
    class Program
    {
        static void Main(string[] args)
        {
            Door door = new Door();
            door.print();
            door.printa();
            door.hardness();

            Console.ReadKey();
        }
    }

    public abstract class Act
    {
        public abstract void printa();
        public abstract int hardness();
    }

    public interface Eyes
    {
        public abstract void print();
    }

    public class Door : Act,Eyes 
    {
        public void print()
        {
            Console.WriteLine("我是一个门");
        }


        public override int hardness()
        {
            Console.WriteLine("我有100耐久");
            return 100;
        }

        public override void printa()
        {
            Console.WriteLine("我是sb");
        }
    }

}
