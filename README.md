##Brasilprev - Loja

Esta é uma API para loja, é uma estrutura que serve de base para uma loja online que trabalha com produtos, clientes , categoria e pedidos.

A aplicação está rodando na **AZURE** e sua documentação pode ser acessada através do link: 
https://lojapedido.azurewebsites.net/swagger-ui.html

O projeto possui uma autenticação utilizando o **JWT** para poder realizar qualquer comando é necessário realizar o login.

Para testar a API, é necessário realizar uma requisição para um endpoint de login, passando um Json com usuário e senha. Segue um exemplo:

https://lojapedido.azurewebsites.net/login

Para testes, o usuário **admin** já se encontra no banco de dados com a senha **admin**.

```
{
	"usuario": "admin",
	"password": "admin"
}
```

O Token de autenticação estará na aba Headers.

Para acessar os demais métodos, basta incluir o token na autenticação do tipo Bearer.
