# Multi-Tenancy Using Postgres Row Level Security
This application is using discriminator column pattern to support multi-tenancy. 

Instead of adding filters on the application code, this project uses database level policies to enforce security on the tenant's data. 

This is done using Postgres Row Level Security.

## Technologies Used
    Java 8
    Spring Boot
    Postgres

## Project Setup
1. Create a new Postgres database named 'rls'
2. Create a new database user with name 'application_user' and password as 'password'
3. Run the application. The application uses flyway for the database migration. The flyway will create a new table 'users' and 'tenants' in the database. 
4. The project also contains some testdata. The tenants and users table will be filled with some testdata.
5. The project also contains sql script to enable row level security on the users table.
