# desafio-gerenciador-tarefas
Gerenciador de Tarefas feito em Angular + Java para o desafio técnico da empresa ESIG. Oferece um controle de tarefas onde é possível adicionar, editar, excluir, concluir e listar tarefas. Também é possível logar e criar novas contas. 
O projeto é divido entre o front-End em Angular 13 e o back-end uma API REST em Spring Boot.

## Pré-requisitos
* Java 11 or mais recente (JDK).
* git (https://git-scm.com/)
* NPM 
* Node
* Angular/cli

## Rodando o projeto localmente
### Manual
Você pode executar o back-end utilizando uma IDE como o EclipseIDE ou Intellij desde que estejam devidamente configurados para o Java 11 e [Lombok](https://projectlombok.org/). E o front-end no VScode. O projeto também pode ser executado via linha de comando seguindo os comandos mostrados abaixo. 

```
#back-end
cd desafio-gerenciador-tarefas/backend
java -jar dock/target/dock-0.0.1-SNAPSHOT.jar

#fron-tend
cd desafio-gerenciador-tarefas/frontend
ng s --configuration=production
```
### Docker
Para quem tem o [Docker](https://www.docker.com/) instalado é possível rodar o front e o back com um banco de dados local utilizado os comandos abaixo:
```
#back-end
cd desafio-gerenciador-tarefas/
docker-compose up --build -d
```
### Heroku
Uma versão da aplicação também se encontra funcionando na seguinte URL https://desafio-gerenciador-tarefas.herokuapp.com/ (front-end) e https://gerenciador-tarefa-backend.herokuapp.com/ (back-end)
Também é valido considerar que a cada alguns minutos o Heroku coloca as aplicações para dormir, o que pode atrasar algumas requisições.

### Informações adicionais
A aplicação Angular atende por padrão na porta 80 ou 4200. O back antende na porta 8080. 
Por padrão as aplicações bloqueiam/redireciona usuários não logados. O Swagger está habilitado no back-end no endpoint /swagger-ui.html/ e é possível se autenticar com um token JWT desde que seja incluída a palavra Bearer antes do token. 
