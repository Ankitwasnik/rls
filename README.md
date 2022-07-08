# Multi-Tenancy Using Postgres Row Level Security
This application is using discriminator column pattern to support multi-tenancy. 

Instead of adding filters on the application code, this project uses database level policies to enforce security on the tenant's data. 

This is done using [Postgres Row Level Security](https://www.postgresql.org/docs/current/ddl-rowsecurity.html).

## Technologies Used
    Java 8
    Spring Boot
    Postgres

**Main Class:** src/main/java/com/ankit/rls/RowLevelSecurityApplication.java

## Project Setup
1. Create a new Postgres database named 'rls'
2. Create a new database user with name 'application_user' and password as 'password'
3. Run the application. The application uses flyway for the database migration. The flyway will create a new table 'users' and 'tenants' in the database. 
4. The project contains script to insert testdata. The tenants and users table will be filled with some testdata.
5. The project contains sql script to enable row level security on the users table.

## Additional Information
The users APIs require **x-tenant-id** header to be sent with the request. The 'x-tenant-id' header is used to identify the tenant.