# eCommerce

Esta é uma Restful API para lojas online com funcionalidades básicas como:

- Cadastro de clientes
- Cadastro de categorias
- Cadastro de produtos
- Cadastro de pedidos

Para utilização de seus recursos, se faz necessário realizar a autenticação, obtendo um token (jwt).

### Para obter o token

1. Acesse o recurso:

> https://bpecommerce.azurewebsites.net/rest/login/

2. Informe as credenciais de administrador

> `{
  "usuario": "admin",
  "senha": "senha123"
}`

3. Será gerado um token do tipo Bearer, na Header da resposta.


## Confira a documentação:
> https://bpecommerce.azurewebsites.net/swagger-ui.html
