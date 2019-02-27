using System;
namespace dojo_matriz
{
    public class Transpor
    {
        public void Exemplo()
        {
            var tamanhoDaMatriz = Console.ReadLine().Split();
            var linha = Convert.ToInt32(tamanhoDaMatriz[0]);
            var coluna = Convert.ToInt32(tamanhoDaMatriz[1]);
            var matriz = new int[linha, coluna];

            for (int i = 0; i < linha; i++)
            {
                var numeros = Console.ReadLine().Split();
                for (int j = 0; j < numeros.Length; j++)
                {
                    matriz[i, j] = Convert.ToInt32(numeros[j]);
                }
            }


            Imprimir(matriz);
        }

        private static void Imprimir(int[,] matriz)
        {
            for (int i = 0; i < matriz.GetLength(1); i++)
            {
                for (int j = 0; j < matriz.GetLength(0); j++)
                {
                    Console.Write(matriz[j, i] + " ");
                }
                Console.WriteLine("");
            }
        }
    }
}