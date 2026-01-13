
<img width="800" height="320" alt="githubbanner" src="https://github.com/user-attachments/assets/ac5e2faa-f48c-4d40-b8be-2a17f661442f" />

--- 


# Silicon map
<a href="https://siliconmap.jossua.dev" target="_blank">Code documentation (external link)</a>
## About
### Context
Silicon Map is a Java desktop application developed as part of a group project (two people) during the second year of university studies.

### Project Objective
The goal of this project is to provide a monitoring application for machines in a data center. Some details are intentionally simplified, as this project is not intended for production use and we are not data center management experts. Our main objective is to demonstrate our ability to develop a professional application within a limited timeframe (approximately one month).

### Warning
This project is for educational purposes only and is not intended for production use.
## Dependencies
- javafx (user interface)
- mysql-connector (connection with mysql db)
- jbcrypt (password encryption)
- dotenv-java (storing database information in a .env file)

*We use Maven to manage dependencies*

## Starting 
1. Get the project
```bash 
    git clone https://github.com/jossweb/silicon-map
    cd silicon-map
```
2. Setup database 

    You need to set up your own database for this project. You have multiple options.
- You need to set up the database using the .sql file I have placed in ```/sql-struct/siliconmap.sql```. You just need to import this database structure. The database already contains some pre-set users.

    | users | password | role |
    |-------|----------|-----|
    | contact@jossua.dev | 123 | admin |
    | contact1@jossua.dev | 123 | technician |
    | 3m)§?o?JgAS!@Ic§L3d9/T1+fx-9;gyN | ;t§3jZXé'QchZzt<Zn7KM?Z7E(x9≠'6D | admin |
    | 5Uj!=.s:uMI::&P3az@U9a1x,)z8T!7X | 0mm1L/H08>+RQCB&1ffC,E,;V7LaF*+I | technician

    After that, you need to follow the structure of .env.example to create your .env file in the same path (```/src/main/resources/.env```).

    *Note: The remote database is not required in the .env file.*

    ### Remote Database (optional)

    For this project, we included the possibility to use an additional database. For the presentation, our second database is hosted on a VPS, which allows multiple clients to connect to the same database in the same time. I also created a small Rust API that replaces the Java simulator of the application.  
    Using this API, we can send instructions to our simulator. For example, we can add a machine or set all machines to a high-temperature mode to see how the interface behaves when there are problems with your machines.

    You can find all the code of our API at https://github.com/jossweb/Silicon-map-api

    *Warning: If you use the Rust API/simulator, you need to stop the Java simulator in the .env file. *
    ```.env
        REMOTESIMULATOR = "OFF"
    ``` 

3. Start the application
    ```bash
        mvn javafx:run
    ```
    ### Requirements
    - Java 21
    - Maven 3.9