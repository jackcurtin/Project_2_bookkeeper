# Bookkeeper Application with Java Spring Boot

Welcome to Bookkeeper - with this app, you can help us forge a massive database
of books, authors, genres, and publishers! All information is publicly available
through our GET endpoints listed below. To contribute to the database, as well
as maintain your own list of favorite books, you will need to register as a user.

###Workflow

The workflow starts with checking our database to what information is required. Duplicate 
entries are not allowed. If you are adding a book that has an author, genre, and/or
publisher that does not already exist in the database, those items will need to be
added prior to the book, as the book object requires each of those items in order to
successfully add the item to our database.

You can browse through all books or find a specific one through the ID in our database. Additionally,
you can filter your search by a specific author, genre, or publisher. If you would like
to save a book to your favorites list, simply specify which book by its ID through the 
favorite endpoint. You will need to be logged in to do so, as well as have created a profile
for your account for the favorites to save to your list. You can view this list at anytime
through the View Profile endpoint.

###Relational Diagram

Before starting this project, we determined that we wanted our API's main entities to
maintain independence of each other's endpoints. In other words, we did not want to have
to access all books by accessing their specific genre first. To visualize this, we created
an ERD diagram, which we have included below.

![plot](/Users/jack/sei/unit2/projects/project2/images/bookkeeper.png)


## API Endpoints
| http method |Endpoint |Functionality| Access|  Header |   Body    |  
| ------ | ------ | ------ | ------ | ------ | ------ |
| Post  | /auth/users/register  |  Create user | Public  | - |  "userName":"gupta", "emailAddress":"5421", "password":"test" |
| Post  | /auth/users/login |  User login to generate JWT Token | Public  | - |  "userName":"gupta" , "password":"test" |
| Put  | /auth/users/updatePassword  |  User Update Password | Private  | Authorization : Bearer {{JWTToken}} |  "userName":"gupta", "password":"test" |
| Get  | /api/books  |  List all books | public  | - | - |
| Get |  /api/books/{id}  |  Gets a single category with the supplied id | public  | - | - |
|POST | /api/books|Creates a new category|Private | Authorization : Bearer {{JWTToken}} | { "title": "book2", "synopsis": "exclusively about",  "pageCount": 4000,  "isbn": 10000009902,  "genre_name":"sc-fi",  "author_first_name":"ragan", "author_last_name":"kori",  "publisher_name":"Book House" } |
|PUT | /api/books/{id}|Updates a book with the supplied id |Private | Authorization : Bearer {{JWTToken}} | { "title": "book2", "synopsis": "exclusively about",  "pageCount": 4000,  "isbn": 10000009902,  "genre_name":"sc-fi",  "author_first_name":"ragan", "author_last_name":"kori",  "publisher_name":"Book House" } |
|DELETE | /api/books/{id}|Delete a book with the supplied id |Private | Authorization : Bearer {{JWTToken}} | -|
|GET | /api/authors | List all the authors |public |  - | - |
|GET | /api/authors/{id}|Gets a single author with the supplied id |Public |-| - |
|POST | /api/authors|Creates a new author |Private |Authorization : Bearer {{JWTToken}} | { "firstName":"suresh",  "lastName":"segira",  "age":30, "nationality":"us" } |
|DELETE | /api/authors/{id}|Delete a author with the supplied id |Private | Authorization : Bearer {{JWTToken}}  | - |
|GET | /api/genres|List all the genres |Public | -| - |
|GET | /api/genres/{id}|Gets a single genres with the supplied id |public | -| - |
|POST| /api/genres|Creates a new genres |Private | Authorization : Bearer {{JWTToken}} | {"name": "Test2",  "description": "in history coloum" } |
|DELETE | /api/authors/{id}|Delete a genres with the supplied id |Private | Authorization : Bearer {{JWTToken}} | -|
|GET | /api/publishers|List all the publishers |Public | -| -|
|GET | /api/publishers/{id}|Gets a single publishers with the supplied id |Public |- |-|
|POST| /api/publishers|Creates a new publishers |Private|Authorization : Bearer {{JWTToken}} | "name":"rajan House1",  "description":"History Books" |
|DELETE | /api/publishers/{id}|Delete a publishers with the supplied id |Private|Authorization : Bearer {{JWTToken}} |-|

## How to run Api Locally
1. Clone repository from
1. Open Folder in IntelliJ
1. Click on run button.
   This should start the Api project.
   





