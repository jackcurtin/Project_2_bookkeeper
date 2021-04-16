# Bookkeeper Application with Java Spring Boot

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
   





