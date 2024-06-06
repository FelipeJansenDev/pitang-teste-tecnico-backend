## Links para acessar o projeto hospedado

Backend - http://54.210.212.29:8080/testeTecnicoPitang/api \
Frontend - http://teste-tecnico-pitang-frontend.s3-website.us-east-2.amazonaws.com/login \
Javadoc - http://teste-tecnico-pitang-javadoc.s3-website.us-east-2.amazonaws.com

## Estórias de Usuário

## SPRINT 1

**EST-001** - Implementação retorno de usuários

Como **USUÁRIO**\
Eu quero **RETORNAR UMA LISTA DE USUÁRIOS** \
Para **TER VISIBILIDADE DE QUAIS USUÁRIOS ESTÃO CADASTRADOS NO SISTEMA**

---

**FEAT-002** - Configuração sequenciador de ID  

Foram adicionados incrementos automáticos na geração dos IDs
de Usuário e Carro.

---

## SPRINT 2

**EST-003** - Implementação CRUD de usuários

Como **USUÁRIO**\
Eu quero **TER CONTROLE SOBRE A MANIPULAÇÃO DAS INFORMAÇÕES DO USUÁRIO** \
Para **INSERIR E DELETAR REGISTROS**

---

**EST-004** - Implementação UPDATE de usuários

Como **USUÁRIO**\
Eu quero **TER CONTROLE SOBRE A MANIPULAÇÃO DAS INFORMAÇÕES DO USUÁRIO** \
Para **ALTERAR REGISTROS DE USUÁRIO**

---

## SPRINT 3

**EST-005** - Implementação exceptionHandler

Como **USUÁRIO**\
Eu quero **TER UMA CLARA VISIBILIDADE SOBRE OS ERROS QUE OCORREM DENTRO DO SISTEMA** \
Para **FACILITAR A ANÁLISE E DESCOBRIR SE É UM ERRO NA INSERÇÃO DE DADOS OU ERRO INTERNO DO SISTEMA**

---

**EST-006** - Implementação validação de E-mail já existente

Como **USUÁRIO**\
Eu quero **QUE MEU E-MAIL SEJA ÚNICO DENTRO DO SISTEMA** \
Para **GARANTIR QUE NÃO TERÁ UM USUÁRIO DUPLICADO**

---

## SPRINT 4

**EST-007** - Implementação validação de dados

Como **USUÁRIO**\
Eu quero **UMA VALIDAÇÃO DE DADOS INSERIDOS** \
Para **GARANTIR QUE TEM NENHUM DADO FALTANDO, INCOMPLETO OU VAZIO**

---

**EST-008** - Implementação segurança

Como **USUÁRIO**\
Eu quero **REALIZAR O LOGIN NO SISTEMA** \
Para **ACESSAR AS INFORMAÇÕES QUE NECESSITAM DE AUTENTICAÇÃO**

---

## SPRINT 5

**EST-009** - Implementação retorno usuário logado

Como **USUÁRIO**\
Eu quero **RETORNAR AS MINHAS INFORMAÇÕES DE LOGIN** \
Para **VISUALIZAR MEUS DADOS DE USUÁRIO**

---

**EST-010** - Implementação do CRUD de CARROS

Como **USUÁRIO**\
Eu quero **LISTAR, RETORNAR, ATUALIZAR E DELETAR CARROS** \
Para **TER CONTROLE SOBRE O GERENCIAMENTO DOS CARROS NA MINHA BASE DE DADOS**

---

## SPRINT 6

**BUGFIX-011** - Ajustes CORS e validação dos dados

Estávamos enfrentado um erro de CORS, o frontend não conseguia acessar, assim como os campos
do objeto não estavam sendo validados.

---

**FEAT-011** - Desenvolvimento de testes unitários

Esse commit reune todos os testes unitários do sistema.

---

## Usuários disponíveis

| Nome de Login | Senha    |
|---------------|----------|
| john         | password |
| janesmith         | password |
| robertbrown         | password |
| emilyjones         | password |
| michaeltaylor         | password |

## COMO EXECUTAR

Existem 2 maneiras que você pode executar este projeto.

1º Modo de executar) Através do maven, executando o código abaixo:
```
mvn spring-boot:run
```

Caso você não tenha o maven configurado na sua máquina, pode utilizar o comando abaixo

```
./mvnw spring-boot:run
```

---

2º Modo de executar) Através da imagem docker. Na pasta devops (https://github.com/FelipeJansenDev/pitang-teste-tecnico/tree/develop/devops),
tem um arquivo chamado docker-compose.yml, caso você queira executar através dele, basta executar o comando abaixo:
```
docker-compose -f docker-compose.yml up
```
Ps.: A imagem docker está pública, então vc não deve ter problemas para executá-lo. Link para a imagem Docker -> https://hub.docker.com/repository/docker/felipejansen/teste-tecnico-pitang/general

PS 2.: 

Tem um arquivo na pasta devops chamado "pitang_teste_tecnico-backend.postman_collection.json", são as requisições da API
para o postman.

*Passos para utilizar as requisições*:

1º) Criar usuário com a requisição "POST criar usuário" (dentro da pasta Usuários)

2º) Realizar login com as credencias "felipe" (sendo o login) e "123" (sendo a senha). Através da requisição "POST realizar Login".

3º) Copiar o token informado e criar um novo header na requisição "POST criar carro" com o seguinte formato. O nome será "Authorization"
e o valor será "Bearer [token copiado aqui]".

4º) Esse é o fluxo básico para inserção de um novo carro.

PS.: Para acelerar o processo de testes, você pode utilizar algumas contas que já são criadas ao iniciar o projeto, basta verificar
a seção "Usuários disponíveis".

## EXECUTANDO OS TESTES UNITÁRIOS

Para executar os testes unitários, basta executar o comando abaixo:

```
./mvnw test
```


## SOLUÇÃO

- O projeto foi gerado utilizando spring-boot devido a facilidade que ele apresenta diante das configurações iniciais
para o início de um novo projeto.
- Decidi utilizar Docker para facilitar o deploy, não preciso gerar um arquivo jar sempre que quero fazer o deploy da minha
aplicação na nuvem, quando subo uma atualização, eu apenas preciso alterar a versão do projeto lá no arquivo docker-compose.yml.
- Utilizei o Mapper para realizar a conversão de model para DTO, onde ele gera automaticante os getters e os setters respectivos,
fazendo o mapeamento dos respectivos campos do DTO para o model e vice-versa, assim eu não preciso construir um DTO de forma manual
  (realizando os sets manualmente).
- Estou utilizando o handler para realizar a captura dos erros e assim setar a mensagem para cada erro de forma personalizada,
assim como o código do erro também
- Não utilizei lombok, pois gosto de ter controle total sobre as classes, métodos getters, setters, construtores e toString de 
todas as classes que eu construo.
- 

OVERVIEW SOBRE A STACK DE TECNOLOGIA:

Backend
- Java 17
- Spring Boot 3.1.2
- H2 Database

Frontend
- Angular 18

OVERVIEW SOBRE A STACK DE DEVOPS:

- Como requisito do teste técnico: A api está sendo executada na AWS em uma instância do Lightsail. 
Está sendo executado atravé do arquivo docker-compose e puxando a imagem que eu fiz o deploy
para o docker hub. O frontend foi armazenado e está sendo executado diretamente de um bucket S3.
