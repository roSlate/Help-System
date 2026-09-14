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
- JPA and Spring (relevant for different layers):
  https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html
  https://spring.io/guides/gs/accessing-data-jpa
  https://www.geeksforgeeks.org/advance-java/jpa-creating-an-entity/
  https://medium.com/@sumit.dev2148/entity-class-best-practices-and-rules-6c2a9261275b
  https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html
  https://codingtechroom.com/question/-spring-boot-foreign-key-references

- Infrastructure and MySQL:
  https://stackoverflow.com/questions/31918987/how-to-start-mysql-server-in-docker-container
  https://vijayasimhabr.medium.com/running-mysql-database-server-with-docker-ad10533473c7
  https://hub.docker.com/_/mysql
  https://spring.io/guides/gs/accessing-data-mysql
  https://docs.spring.io/spring-boot/
  https://www.devmedia.com.br/criando-uma-conexao-java-mysql-server/16753 (Brazilian Portuguese)
  https://www.baeldung.com/java-connect-mysql

- Actual SQL Queries:
  https://www.sqltutorial.org/
  https://github.com/enochtangg/quick-SQL-cheatsheet
  https://www.geeksforgeeks.org/sql/sql-describe-statement/ (small tutorial for the DESCRIBE Statement)
  https://stackoverflow.com/questions/72994270/jpa-jpql-automatic-method-query-generation (automatic query)

- On password hashing:
  https://docs.spring.io/spring-security/reference/features/integrations/cryptography.html

- General consultation for the overall structure and architecture of the project
  YouTube guide for a fullstack application: 
  https://www.youtube.com/watch?v=lUVureR5GqI&list=WL&index=10 (Brazilian Portuguese)
  https://medium.com/@albinaji.official/service-repository-pattern-in-action-0db4bb9a474b

## Project structure

//TO IMPROVE

**Domain model**: to be documented and further refined, see `domain/` package.

**Regarding backend:**

com.helpsystem
|-domain (where the JPA entities reside)
|-repository (Spring Data JPA repos)
|-HelpsystemApplication

(controllers/service to come later)

**Regarding primary and foreign keys**:

Incompatibility regarding String acting as a foreign key, which it implies a `@ManyToOne` relationship (i.e., 
`department` is marked as both a String and as a FK in User, but it's also its own class); verify ASAP how to procede.

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

Now, let's get back to our code once more and look at the Repository Layer.

## Repository layer

We'll be relying on Spring Data JPA Repositories to verify that domain objects (Replies, Requests, Users, etc.) can be
saved and retrieved through Java, and not just through manual SQL queries/statements (see "Database, configurations & 
secrets" above). Let's start with `Department` first as an example.

First, create a package titled `repository` and create your DepartmentRepository.java class there (choose the interface
option when creating this class). Write the following:

```
package com.helpsystem.repository;

import com.helpsystem.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
```

And for now, that's it. We are telling Spring "this repository manages Department entities". By extending this interface,
we are inheriting working methods such as `save()`, `findById()`, `findAll()`, and others, generated automatically
through Spring. For now, nothing else is required for basic CRUD (Create, Read, Update, Delete) operations, though other
more specific methods might be needed soon (sources explaining how to write tests for Spring Boot apps linked above),
as we'll see.

**Note:** Don't forget to have the database's container running when also running tests. They might fail otherwise.

Now we have to write these repository classes for the remaining domain classes, while applying the same pattern.

Our next step will be writing the methods necessary for a User to be able to register themselves, and, afterward, login.
Given that a User's email is required to register, then we first need a method to find a User by their email. In
UserRepository, you'll find the following method:

```
Optional<User> findByEmail (String email);
```

Given any email, realistically, you'll either have a User associated to it or not. As such, the return type for this
method, Optional, will either return the User or, if not, will handle the "not found" case (and, explicitly, not risk a 
NullPointerException). Also, you might note that we didn't write any manual SQL or logic for actually finding the email.
Once again, we are relying on JPA, as it infers the actual SQL from the method's name automatically (note that it only
works because we wrote "Email" exactly as the field declared in User; had we wrote "findByMail", the method wouldn't 
work properly). You'll find a small tutorial explaining this portion linked above.

## Service layer

For UserService, we'll be using the BCryptPasswordEncoder import (link explaining this import above in its own section).


## Controller layer