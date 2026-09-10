# Help System — Backend

Internal Q&A system for the Specialisterne training project. Employees can post questions and other employees can 
answer them.

## Status: early setup, this document will change a lot throughout the following days, most likely

## Prerequisites so far

- JDK 17
- Docker Desktop, with WSL integration enabled (if developing on Windows/WSL)

## Tech stack

Generated via Spring Initializr with:

**Maven**: our build tool, manages dependencies and compiling/packaging the app;
**Java 17**: stable and well-supported for years as of now;
**Spring Boot**: core framework.

### Dependencies (subject to change):

**Spring Web**: lets us build REST (REpresentational State Transfer) endpoints (relevant in the controller layer);
**Spring Data JPA**: cuts the need to manually write SQL and gives access to annotations (like @Entity in our domain 
classes);
**MySQL Driver**: allows to actually connect and talk to a MYSQL database.


## Links used, by category
- JPA:
  https://spring.io/guides/gs/accessing-data-jpa
  https://www.geeksforgeeks.org/advance-java/jpa-creating-an-entity/
  https://medium.com/@sumit.dev2148/entity-class-best-practices-and-rules-6c2a9261275b

- Infrastructure and MySQL:
  https://stackoverflow.com/questions/31918987/how-to-start-mysql-server-in-docker-container
  https://vijayasimhabr.medium.com/running-mysql-database-server-with-docker-ad10533473c7
  https://hub.docker.com/_/mysql
  https://spring.io/guides/gs/accessing-data-mysql
  https://docs.spring.io/spring-boot/

- Actual SQL Queries:
  https://www.sqltutorial.org/
  https://github.com/enochtangg/quick-SQL-cheatsheet
  https://www.geeksforgeeks.org/sql/sql-describe-statement/ (small tutorial for the DESCRIBE Statement)

- General consultation for the overall structure of the project
  YouTube guide for a fullstack application: 
https://www.youtube.com/watch?v=lUVureR5GqI&list=WL&index=10 (Brazilian Portuguese)

## Project structure

**Domain model**: to be documented and further refined, see `domain/` package.

## Database, configurations & secrets

The project uses MySQL, run locally via Docker rather than installed directly, so you need to set up your local 
environment:

```bash
docker run --name helpsystem-mysql \
  -e MYSQL_ROOT_PASSWORD=your_own_password \
  -e MYSQL_DATABASE=helpsystem \
  -p 3306:3306 \
  -d mysql:8
```
 
**Note**: naturally, the command above only works the first time. If the container already exists (e.g. after 
restarting your machine), just start it again with:
```bash
docker start helpsystem-mysql
```
Or click the "start" button on Docker Desktop.

We are creating now another file called `application-local.properties`. It will be excluded from Git (and we updated our 
.gitignore file to reflect this) because it holds real database credentials, and it's never to be committed.
To run this project locally, create this file under resources, at the same level as the regular `application.properties`,
with your own database credentials (see `application-local.properties.example` below).

Each teammate can choose their own password here. It only needs to match between your own `docker run` command 
and your own `application-local.properties`.

### Template for application-local.properties.example

```
spring.datasource.url=jdbc:mysql://localhost:3306/helpsystem
spring.datasource.username=root
spring.datasource.password=your_own_password
```

Next, we altered the regular `application.properties` file with the following lines:

- `spring.jpa.hibernate.ddl-auto=update` — lets Hibernate automatically create or update database tables to 
match our `@Entity` classes;
- `spring.jpa.show-sql=true` — prints the SQL Hibernate generates to the console;
- `spring.profiles.active=local` — tells Spring Boot to also load`application-local.properties` and merge it
into the configuration.

Once that's done, navigate to the project's `backend` directory and run `./mvnw spring-boot:run` to test your setup.

Next, enter the container we created earlier with the command `docker exec -it helpsystem-mysql mysql -uroot -p helpsystem`, 
and type the password you chose. Once you're in run the command `SHOW TABLES;`, and you should see something like this:

```
mysql> SHOW TABLES;
+----------------------+
| Tables_in_helpsystem |
+----------------------+
| department           |
| reply                |
| request              |
| user                 |
+----------------------+
4 rows in set (0.00 sec)
```

Let's try actually inserting data into the tables first. The group was offered some suggestions regarding how to 
accomplish this, the first being writing manual SQL. For this, we still need to be inside the container created, so if
you exited it, just run the docker command we used in the paragraph above. If you struggle with SQL, there are two
resources linked above which should help you interact the database for the project's purposes.

First, we tried inserting some data in the `department` table: `INSERT INTO department (department) VALUES ('Sales');`.
You don't need to specify `id` because it's generated automatically:

```
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
```

After running this command, verify if `Sales` was inserted into the chosen table. Run `SELECT * FROM department` and
the output should be the following:

```
mysql> INSERT INTO department (department) VALUES ('Sales');
Query OK, 1 row affected (0.03 sec)

mysql> SELECT * FROM department;
+----+------------+
| id | department |
+----+------------+
|  1 | Sales      |
+----+------------+
1 row in set (0.01 sec)

mysql>
```

Keep in mind that `department` is a very simple table because you only have two parameters, and one of them, `id`, is
generated automatically. As such, we only needed to specify the `department` column (which might be confusing, as that
column shares its name with the table's name).

Let's try to insert something to a more complex table, such as `reply`. If you're not sure what parameters a given 
table has, the DESCRIBE Statement (link above) is very useful here, as it does just that:

```
mysql> DESCRIBE reply;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | int          | NO   | PRI | NULL    | auto_increment |
| answer     | varchar(255) | YES  |     | NULL    |                |
| department | varchar(255) | YES  |     | NULL    |                |
| name       | varchar(255) | YES  |     | NULL    |                |
| question   | varchar(255) | YES  |     | NULL    |                |
| status     | varchar(255) | YES  |     | NULL    |                |
| title      | varchar(255) | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
7 rows in set (0.01 sec)

mysql>
```

Given we have all of these parameters, inserting something manually is a little more complex, but the idea is the same.
Now, we just have to match each `Field` (column, ordered alphabetically after id) to a corresponding value:

```
INSERT INTO reply (title, question, answer, status, name, department) VALUES ('Help!', 'How do I do this?', 
'Like this!', 'Open', 'Johnny Doe', 'Sales');
```

Run this command, and, afterward, run the `SELECT` command one more time for `reply`:

```
mysql> SELECT * FROM reply;
+----+------------+------------+------------+-------------------+--------+-------+
| id | answer     | department | name       | question          | status | title |
+----+------------+------------+------------+-------------------+--------+-------+
|  1 | Like this! | Sales      | Johnny Doe | How do I do this? | Open   | Help! |
+----+------------+------------+------------+-------------------+--------+-------+
1 row in set (0.00 sec)

mysql>
```

If our output looks like this you've successfully inserted the data into the tables.