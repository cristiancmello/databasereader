## Sobre MySQL Connector/J

- Documentação: https://dev.mysql.com/doc/connector-j/en/

- Connector/J implementa Java Database Connectivity API (JDBC). Versão atual suporta a nova X DevAPI.
	- JDBC: API padrão da indústria que permite a conexão entre databases SQL e até mesmo planilhas ou flat files. Ou seja, permite conexão independente do tipo do servidor de banco de dados (SGBD)
- Connector/J é um **driver JDBC Type 4**
	- Quer dizer que implementa a especificação [JDBC 4.2](https://docs.oracle.com/javase/6/docs/technotes/guides/jdbc/getstart/GettingStartedTOC.fm.html)
	- E que é em **Java Puro**, só dependendo da JDK/JRE v8 ou superior
		- Ótimo isso, porque dispensa instalar qualquer outra biblioteca
	- **Problema no uso extensivo do Connector/J**: vamos ter monte de código JDBC replicando padrões de projeto que foram resolvidos com frameworks como **Hibernate**, **Spring JDBC templates** e **MyBatis SQL Maps**.

## Clonando o DatabaseReader

`git clone https://github.com/cristiancmello/databasereader`

## Baixando o Connector/J

> Este tópico é somente para título de conhecimento para saber como baixar o arquivo `mysql-connector-j-8.2.0.jar`. **O repositório clonado já contém o arquivo**.

- Doc: https://dev.mysql.com/doc/connector-j/en/connector-j-installing.html

Temos as opções de usar o connector pela distribuição binária (arquivo `.jar`), como código-fonte a ser compilado (permite customizações mais complexas) e como package do Maven. Vamos adotar a **distribuição binária**.

- Para baixar a distribuição binária, acesse https://dev.mysql.com/downloads/connector/j/
	- [Clique aqui](https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.2.0.zip) para baixar o MySQL Connector/J 8.2 diretamente

## Iniciando o MySQL Container

Execute:

1. `docker.oraclemysql.pull.bat` para fazer `docker pull` do container (significa baixar a imagem do container a partir do Docker Registry público na internet)
2. `docker.oraclemysql.run.bat` para iniciar o container
	1. Porta TCP exposta pelo container: 3306
	2. Acessível pelo host: `localhost:3306`
	3. Um `database schema` chamado `exampledb` é criado automaticamente
	4. Obs.: Não é necessário fazer `docker pull` pois o `docker run`, caso não ache a imagem baixada, realiza o download de modo automático. Entretanto, acho importante exercitar essa separação de responsabilidades.
3. Deseja parar a execução do container? 
	1. Execute `docker.oraclemysql.stop.bat`

> Vale ressaltar que os dados do MySQL estão num [**volume de dados efêmero associado ao container**](https://subscription.packtpub.com/book/cloud-and-networking/9781838983444/7/ch07lvl1sec51/persistent-and-ephemeral-volumes). Quer dizer, quando reiniciar o container, tudo será apagado. É um comportamento padrão do Docker. A documentação do Docker apresenta como criar [volumes persistentes](https://docs.docker.com/storage/volumes/) (não irei tratar disso no momento).

## Comandos de MySQLCLI e SQL úteis para o treinamento

### Acesso a base como usuário root

```sh
mysql -u root -p
```

- Uma senha será solicitada, só digite `example`.

### Statements de SQL para praticar

```sql
-- Mostrar todos os schemas criados
SHOW DATABASES;

-- Selecionar um schema especifico
USE exampledb;

-- Mostrar todas as tabelas criadas num schema
SHOW TABLES;

--Tabela simples com 2 colunas
CREATE TABLE persons(person_id int, name varchar(255));

-- Inserindo registros numa tabela sem PK auto incrementavel
INSERT INTO persons VALUES (1, "Cristian");
INSERT INTO persons VALUES (2, "Pablo");

-- Deletar uma tabela
DROP TABLE persons;

--Tabela simples com 2 colunas, sendo a primeira uma PK auto incrementavel
CREATE TABLE persons(
  person_id INT AUTO_INCREMENT, 
  name varchar(255), 
  PRIMARY KEY(person_id)
);

-- Inserindo registros numa tabela com PK auto incrementavel
INSERT INTO persons VALUES (NULL, "Cristian");
INSERT INTO persons VALUES (NULL, "Pablo");

-- Obtendo total de registros de uma tabela
SELECT COUNT(*) as SIZE from persons;

-- Obtendo todos os registros de uma tabela
SELECT * FROM persons;
```

> Importante: para facilitar a demonstração do exemplo do DatabaseReader, deixe a tabela `persons` criada com pelo menos 3 registros:
>   - (1, "Cristian")
>   - (2, "Pablo")
>   - (3, "Livia")

## Compilando e Iniciando DatabaseReader

- Compile e rode o exemplo `DatabaseReader` com `build-and-run.bat`.
- Algo assim vai aparecer em meio

```
Connection created.
Fetch size: 1
Total Records: 3
First Record: {1, Cristian}
Second Record: {2, Pablo}
First record: {1, Cristian}
Last record: {3, Livia}
Second record: {2, Pablo}
Are there more records? false
Third record: {3, Livia}
Connection was closed.
```

## Acessando MariaDB com MySQL Connector/J

> MariaDB é outro DB relacional com histórica compatibilidade (não total) com MySQL

- Segundo [About MariaDB Connector/J](https://mariadb.com/kb/en/about-mariadb-connector-j/) existe compatibilidade entre
MySQL e MariaDB promovida pelo MySQL Connector/J.
  - Documentação diz que MariaDB Connector/J 3.3.0 é compatível com MySQL >=5.5 e todas as versões do MariaDB, seguindo a JDBC 4.2
  - [MariaDB versus MySQL: Compatibility](https://mariadb.com/kb/en/mariadb-vs-mysql-compatibility/) aponta que existe compatibilidade entre
  atuais MySQL connectors (PHP, Perl, Python, Java, .NET, MyODBC, Ruby, MySQL C connector etc) e MariaDB

### Exercitando

- Execute o `docker.mariadb.run.bat` para iniciar o MariaDB, certificando-se que a porta `3306` esteja livre ou utilize alguma outra caso
haja limitações

- Reproduza as condições de demonstração, com uma tabela `persons` com 3 registros inseridos

- Execute `DatabaseReader` e note que não foi necessário fazer nenhuma mudança no código da aplicação

## Acessando PostgreSQL com PostgreSQL JDBC Driver

Vá em [Postgres Example](./postgres-example).

## Minhas notas

- Observe os arquivos atentamente, como os `.bat`. A medida que surgirem dúvidas, pode me contatar.
- Explore possibilidades do código a vontade, faça alterações ao máximo.
- Irei pedir como exercício alterações a respeito de interface de classe e como isso deve ser refletido pela implementação da classe.

