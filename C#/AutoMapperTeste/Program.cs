using System;
using AutoMapperTeste.Dominio;

namespace AutoMapperTeste
{
    class Program
    {
        static void Main(string[] args)
        {
            var nome = "Marlom";

            int idade = 0;
            string cpf = null;
            string email = null;
            
            var pessoa = new Pessoa(nome, idade, cpf, email);
            Console.WriteLine("Hello World!");
        }
    }
}
