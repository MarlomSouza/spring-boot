using System;
using System.Diagnostics;

namespace firstDuplicate
{
    public class Program
    {
       
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            var stopwatch = Stopwatch.StartNew();
            firstDuplicate(new[] { 28, 19, 13, 6, 34, 48, 50, 3, 47, 18, 15, 34, 16, 11, 13, 3, 2, 4, 46, 6, 7, 3, 18, 9, 32, 21, 3, 21, 50, 10, 45, 13, 22, 1, 27, 18, 3, 27, 30, 44, 12, 30, 40, 1, 1, 31, 6, 18, 33, 5 });
            // firstDuplicate(Valor.a);
            stopwatch.Stop();
            Console.WriteLine(stopwatch.Elapsed.TotalSeconds);
            Console.WriteLine("Menor que 3 segundos => " + (stopwatch.Elapsed.TotalSeconds < 3));
        }

        public static int firstDuplicate(int[] a)
        {
            bool possuiDuplicacao = false;
            var minimalIndex = a.Length - 1;

            for (int i = 0; i <= minimalIndex; i++)
            {
                ImprimirEspacoEmBranco(i, a);

                for (int j = i + 1; j < minimalIndex; j++)
                {
                    if (j == minimalIndex)
                        Console.Write(a[j]);
                    else
                        Console.Write(a[j] + " - ");
                    
                    if (a[i] == a[j])
                    {
                        minimalIndex = j;
                        possuiDuplicacao = true;
                        break;
                    }
                }

                Console.WriteLine("");
                Console.WriteLine("### " + a[minimalIndex]);
            }

            return possuiDuplicacao ? a[minimalIndex] : -1;
        }

        private static void ImprimirEspacoEmBranco(int i, int[] a)
        {
            int k = 0;
            while (k < i)
            {
                Console.Write("00 - ");
                k++;
            }
        }
    }
}
