<a name="readme-top"></a>
# U of T Exam Centre

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#project-features">Project Features</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
      </ul>
      <ul>
        <li><a href="#running-the-application">Running the Application</a></li>
      </ul>
    <li>
      <a href="#next-steps">Next Steps</a>
    </li>
  </ol>
</details>

## Project Features
1. Login User
2. Logout User
3. Register new User
4. Update Course Membership
5. Submit Test Document
6. Submit Solution Document
7. Register New Course
8. Submit Discussion Board Message
9. Vote on Solution Document
10. Upload/Download Document
11. State Tracker
12. PostGresAccess
13. FTP Server


### Design Patterns
**1. Factory Design Pattern** 

We currently use a factory design pattern when creating our entities. This allows us to have further expand the types of entities that we can create. An example of this can be seen with the User Factory as we would be able to create an Admin User in the future if needed besides a Common User. All of our factories are available at `src/main/java/entities/factories`

**2. Open for Extension, Closed for Modification**

Implemented User and Message Interfaces that gives us the flexibility of adding different types users and messages 

**3. Dependency Inversion**

Used throughout the project to adhere to Clean Architecture

**4. Observer Pattern**

Views Observe the view  model for updates. The View Model is an observable.


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

### Prerequisites
 [Postgres 14.5](https://www.postgresql.org/download/)
 
 [ICEPdf](https://github.com/pcorless/icepdf)
 
 [Apache Commons Net](https://commons.apache.org/proper/commons-net/download_net.cgi)


### Running the Application
**Setup a local database**

1. Install Postgres 14.5: https://www.postgresql.org/download/
2. Set up Postgres with the `User: postgres` and `Password: postgrespw` and `Port: 5432 (default)` 
3. Download the JDBC precompiled driver: https://jdbc.postgresql.org/download/ (it should download a file called `postgresql-42.5.0.jar` ).
4. Create a directory called `lib` in the root project directory and place the `postgresql-42.5.0.jar` file there.
5. Log into postgres from a terminal using `psql -U postgres` .
6. Run the following SQL query: `CREATE DATABASE exam_centre;` in the PSQL console -> **make sure that you include the semi-colon!**
7. Then run `\c exam_centre` to connect to the exam_centre DB. Alternatively, rerun the PSQL console using psql `-U postgres -d exam_centre` .
8. In the PSQL console, copy and paste the script found at `~/bin/db/initPostgresSchema.sql` .
9. Wooo hooo ye did it !


After the local database is set up, running the program should take you to the login/register screen which would then lead to the main view once logged in.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Next Steps
 **Reset User Password** 
 
 We plan to add a system to reset user passwords. This was originally a feature we planned on demonstrating, however, due to time constraints we decided it was not a core feature of our project and pushed it to be completed in the future.
 
 **Addressing Code Smells**
 
 Although our project strictly follows Clean Architecture, there remains some code smells that can make the code unclear or hard to read. We aim to address these issues in the future to allow for a better reading and understanding experience.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
