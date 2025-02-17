# DebeziumPostgresToOpensearch


This project shows how to implement [Command Query Responsability Segregation (CQRS)](https://www.kurrent.io/cqrs-pattern) using [Debezium](https://debezium.io/) for PostgreSQL database to extract data from SQL database and OpenSearch for read queries.

## Data

The data used here was originally taken from the
[Graduate Admissions](https://www.kaggle.com/mohansacharya/graduate-admissions)
open dataset available on Kaggle.
The admit CSV files are records of students and test scores with their chances
of college admission.  The research CSV files contain a flag per student
for whether or not they have research experience.
These CSV files are in `data` folder.

## Components

The following technologies are used through Docker containers:
* [PostgreSQL](https://www.postgresql.org/), pulled from [Debezium](https://debezium.io/), tailored for use with Connect, as Database for commands.
* [OpenSearch](https://www.opensearch.org), as database for queries.
* [Java 21+](https://openjdk.java.net), to create the projects for applications used in this demo
* [Spring](https://spring.io/), Java framework used in an agregator application to save Kafka Streams data into OpenSearch and in an API application to query OpenSearch data
* [Apache Maven](https://maven.apache.org), to manage the Java projects for the applications used in this demo

The containers are pulled directly from official Docker Hub images.


### Create volumes for Postgres and OpenSearch

```
docker volume create postgresdata

docker volume create opensearchdata
```

### Bring up the entire environment

```
docker compose up -d --build
```

## Loading data into Postgres

We will copy CSV to postgres container following by execute psql command line, mount our local data
files inside, create a database called `students`, and load the data on
students' chance of admission into the `admission` table.

```
docker cp data/admit.csv postgres:/tmp
docker cp data/research.csv postgres:/tmp

docker exec -it postgres psql -U postgres
```

At the command line:

```
CREATE DATABASE students;
\connect students;
```

Load our admission data table:

```
CREATE TABLE admission
(student_id INTEGER, gre INTEGER, toefl INTEGER, cpga DOUBLE PRECISION, admit_chance DOUBLE PRECISION,
CONSTRAINT student_id_pk PRIMARY KEY (student_id));

\copy admission FROM '/tmp/admit.csv' DELIMITER ',' CSV HEADER
```

Load the research data table with:

```
CREATE TABLE research
(student_id INTEGER, rating INTEGER, research INTEGER,
PRIMARY KEY (student_id));

\copy research FROM '/tmp/research.csv' DELIMITER ',' CSV HEADER
```

We can disconnect from Postgres container with the command `exit`.

## Use Postgres database as a source to OpenSearch

The `connector` project implements a Debezium connector to read data from PostgreSQL, writing indexes in OpenSearch corresponding to the PostgreSQL tables.

To run the application and populate OpenSearch indexes, run the command:
```
mvn spring-boot:run -f connector/pom.xml
```


## Query data from OpenSearch

The api application has endpoints to query OpenSearch data populated with connector application.

### Query admission by its id

```
curl http://localhost:8081/api/admission/:studentId
```

### Query students enrolled in a research

```
curl http://localhost:8081/api/research/:researchId
```

### Query average chance of enrollment in a research

```
curl http://localhost:8081/api/researchboost/:researchId
```

## Clear OpenSearch indexes

The api application has endpoints to clear OpenSearch indexes populated with connector application.

### Clear admission index

```
curl -X DELETE http://localhost:8081/api/admission/deleteAll
```

### Clear research index

```
curl -X DELETE http://localhost:8081/api/research/deleteAll
```
