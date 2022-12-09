<a name="readme-top"></a>
# U of T Exam Centre

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#project-features">Project Features</a>
      <ul>
        <li><a href="#use-cases">Use Cases</a>
        <li><a href="#persistent-storage">Persistent Storage</a>
        <li><a href="#clean-architecture">Clean Architecture</a>
        <li><a href="#design-patterns">Design Patterns</a>
        <li><a href="#documentation">Documentation</a>
        <li><a href="#testing">Testing</a>
      </ul>
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

**What is Exam Centre**

UofT is notorious for its lack of past-exams for students to use to prepare for their exams. Along with this, many students struggle with completing questions under a timed test environment. The Exam Centre solves these issues by providing a platform in which students can upload their own practice exams and share them for other students to use. Students are able to take these practice exams under a timed environment. Along with this, students are able to upload their solutions and discuss the tests in a discussion board, giving a rating for the best solutions uploaded.

### Use Cases

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
11. Update State Tracker

### Persistent Storage
1. **Remote File Access:** 

Implemented using an FTP Server. Uploads and downloads are handled by the FtpAccessManager which implements the FileAccessGateway interface in the interface adapters layer. The FileAccessGateway extends all use case file access gateways in the application business rules layer. This feature is used to manage PDF uploads and downloads for test and solution documents.

2. **Database Access:** 

Implemented using a Postgres 14.5 server. Database requests are handled by the PostgresAccessManager which implements the DatabaseAccessGateway in the interface adapters layer. The DatabaseAccessGateway extends all use case DS gateways in the application business rules layer, with default methods to parse the raw data and construct DB response models. Entity data is returned as DB entity models which implement use case specific DB response models in the relevant use case packages via dependency inversion. This feature is used to maintain synchronised data between users.

### Clean Architecture
1. **Enterprise Business Rules:**
> This layer is contained within the `entities` package, which includes all entity classes/interfaces along with associated entity factories. The entities are designed to relationally represent the nested data structures that the platform depends upon. For example, *Course* entities contain maps of *TestDocument* entities, which contain maps of *SolutionDocument* entities. These entities represent the core logic behind Exam Centre, with interactors in the above layer manipulating them to carry out the platform’s use cases.

2. **Application Business Rules:**
>This layer is contained within the `usecases` package, which includes packages corresponding to each use case. Each use case has an interactor class that implements an input boundary interface that carries out the main operations of the use case. Response models representing useful return data are constructed and passed to an output boundary interface method, implemented by a presenter class in the above layer. Some use cases use nested response models to return more complicated response data. Use cases that require access to persistent storage (i.e. file storage or database) will have use case-specific gateway interfaces that are implemented in above layers and injected via the interactor constructor. Many use cases that entail getting entity data from the database will have a subpackage of DB response interfaces that are implemented by DB response models in the interface adapters layer. Most use case interactors have a reference to a StateTracker entity which tracks the structure of entities that represent the current user and the courses that they belong to. Input data is passed to the interactor’s public use case method via a use case specific request model.

3. **Interface Adapters:**
>This layer is contained within the `iadapters` package, and it contains gateways for persistent storage, use case presenters and controllers, view model data structures and custom exceptions. Each of these features are organised within name-appropriate sub-packages. There is a controller for every use case, which is constructed with a reference to the constructed use case interactor as an input boundary. The controllers are responsible for parsing raw input data and formatting the use case request model, which is then passed to the interactor as a parameter.  There is also a presenter corresponding to each use case, which is responsible for implementing the use case output boundary by parsing the response models and updating the view models as appropriate. Each presenter has a reference to the **MainViewModel** as well as the **ViewsGateway**, a dependency inversion of the **ViewsManager** in the layer above, which are used to update the user interface views in the above layer. Each use case has a custom exception defined, which is thrown in a `prepareFailView` method such that exception can provide useful information and be handled with appropriate updates to the view models.

4. **Drivers and Frameworks:**
>This layer is represented by both the `drivers` and `frameworks` packages. The `drivers` package contains the driver that initialises a local instance of Exam Centre, constructing all classes and establishing necessary connections to external persistence storage. The `frameworks` package contains two sub-packages: `views` and `dacccess`. The `views` sub-package contains the user interface views that are implemented using the Java Swing framework. The views function solely using a reference to the **MainViewModel** and each feature a generic **update** method which is called by the use case presenters via a collated dependency inversion (**ViewsGateway**/**ViewsManager**). This means that the UI framework may be changed (e.g. to JavaFX) without requiring any changes below the drivers and frameworks layer. The `daccess` sub-package contains the concrete classes for managing external data access, with each implementing the corresponding interfaces in the layer below. This allows for implementations of data storage to be changed (e.g. changing from Postgres to CSV or to NoSQL) without making any changes below the drivers and frameworks layer.

### Design Patterns
**1. Factory Design Pattern** 

We currently use a factory design pattern when creating our entities. This allows us to have further expand the types of entities that we can create. An example of this can be seen with the User Factory as we would be able to create an Admin User in the future if needed besides a Common User. All of our factories are available at `src/main/java/entities/factories`

**2. Open for Extension, Closed for Modification**

Implemented User and Message Interfaces that gives us the flexibility of adding different types users and messages 

**3. Dependency Inversion**

Used throughout the project to adhere to Clean Architecture

**4. Observer Pattern**

Views Observe the view  model for updates. The View Model is an observable.

### Documentation

**IMPORTANT:** Within our documentation, we utilize a custom `@layer` tag that helps specify which layer the class or interface is located in regards to Clean Architecture. In order to get this `@layer` tag, you must add it into your own IntelliJ.

### Testing
We provide unit testing for the following use cases:
1. User Register
2. User Log Out
3. User Log In
4. Vote On Solution
5. Update Course Membership
6. Register Course
7. Update State Tracker
8. Submit Discussion Board Message


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

### Prerequisites
 [Postgres 14.5](https://www.postgresql.org/download/)
 
 [ICEPdf](https://github.com/pcorless/icepdf)
 
 [Apache Commons Net](https://commons.apache.org/proper/commons-net/download_net.cgi)
 
 [FireZilla](https://filezilla-project.org/download.php?type=server)


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


**Setup a local FTP Server**

1. Download FileZilla Server
2. Run FileZilla Server and click on “Connect to FileZilla FTP Server”
3. Input the following when prompted to setup a connection:


  >>a. `Host: localhost`
  
  
  >>b. `Port: 14148`
  
  >>c. `Password: password`
  
  
4. Create a user


  >> a. `Server > Configure > Users`
  
  
  >> b. Click on “Add” at the bottom
  
  
   >>> i. `Name: user`
    
    
   >>> ii. `Password: password`
    
    
  >> c. [Under mount points, click on “Add”](https://filezillapro.com/docs/server/advanced-options/filezilla-server-group-panel/#:~:text=To%20share%20files%20and%20directories,native%20path%20by%20FileZilla%20Server.)
  
  
   >>> i. The *virtual path* is the path that the FTP users will see and it is mapped to the *native path* by FileZilla Server. I used “/” for Mac
    
    
   >>> ii. The native path is a local file path. I used “/etc/” for Mac
    
    
  >> d. Access mode: **Read+Write**
  
  
5. Input the remote path where you’d like files to be uploaded to and downloaded from inside `local.properties`

After the local database and FTP server is set up, running the program should take you to the login/register screen which would then lead to the main view once logged in.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Next Steps
 **Reset User Password** 
 
 We plan to add a system to reset user passwords. This was originally a feature we planned on demonstrating, however, due to time constraints we decided it was not a core feature of our project and pushed it to be completed in the future.
 
 **Addressing Code Smells**
 
 Although our project strictly follows Clean Architecture, there remains some code smells that can make the code unclear or hard to read. We aim to address these issues in the future to allow for a better reading and understanding experience.
 
 **Documentation**
 
 Although we have decent documentation throughout the project, time constraints has led to us skipping on some documentation throughout the code. These issues will be addressed in future PRs.
 
 **Testing**
 
 Although we do have great code coverage in terms of testing, some unit tests still need to be written. Along with this, integration tests still need to be written for the database and FTP server access. These issues will be addressed in future PRs.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
