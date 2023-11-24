# Exemplo usando PostgreSQL JDBC Driver

## Comandos do psql (Postgres CLI) e SQL

```sh
psql --username=postgres
# ou
psql -U postgres exampledb
```

```sql
--Conectar a um database
\c exampledb

--Listar tabelas criadas
\dt

--Create table
CREATE TABLE persons(
	person_id SERIAL, 
	name VARCHAR(255), 
	PRIMARY KEY(person_id)
);

--Insert record
INSERT INTO persons (name) VALUES ('Cristian');
INSERT INTO persons (name) VALUES ('Pablo');
INSERT INTO persons (name) VALUES ('Livia');

SELECT * FROM persons;

DROP TABLE persons;
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