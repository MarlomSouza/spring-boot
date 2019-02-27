using System;

namespace AutoMapperTeste.Dominio
{
    public class Pessoa
    {
        public string Nome { get; }
        public int Idade { get; }
        public string Cpf { get; }
        public string Email { get; }
        public Pessoa(string nome, int idade, string cpf, string email)
        {
            ValidarTempo(nome, idade, cpf, email);

            this.Nome = nome;
            this.Idade = idade;
            this.Cpf = cpf;
            this.Email = email;

        }

        private void ValidarTempo(string nome, int idade, string cpf, string email)
        {
            if (string.IsNullOrWhiteSpace(nome))
                throw new ArgumentException("Nome é obrigatorio");
            if (idade <= 0)
                throw new ArgumentException("Idade inválida");
            if (string.IsNullOrWhiteSpace(cpf))
                throw new ArgumentException("Cpf é obrigatorio");
            if (string.IsNullOrWhiteSpace(email))
                throw new ArgumentException("email é obrigatorio");

        }



    }
}