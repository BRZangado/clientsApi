# CLIENTS API

Essa API é um teste de conhecimento de APIs Rest usando Java e Spring
É um simples API com um CRUD de clientes, porém incrementada com alguns conceitos interessante como mapeamento de erros, mapeamentos de exceções e documentação, além de um camada docker pra facilitar na hora de rodar :3

## Como rodar
Clone ou baixe esto repositório
execute o arquivo deploy.sh e prontinho, só esperar baixar tudo e rodar (a primeira vez pode demorar um pouco, mas as outras são tranquilas)

a API ficará disponível no endereço http://localhost:8087/ <br>
Para conferir a documentação dos endpoints, acesse
http://localhost:8087/swagger-ui.html#/

Você pode testar a api usando postman, e aqui no repositório já tem um collection prontinha com todos os endpoints, é só importar o arquivo clientsApi.postman_collection.json o seu postman

Detalhes Adicionais:
o banco está em modo create-drop, então toda vez que voce reiniciar a api, os dados anteriores irão sumir (para fins de tests)
```
