# ShopJJ back-end

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

This is a Spring Boot backend project that consists of a store system. Users can place orders, view available products, and perform various other operations typically found in a regular store.



## Installation


1. Clone the repository:

```bash
https://github.com/joaolucas6/shopjj-backend.git
```

2. Install dependencies with Maven

## Usage

Start the application and then the API will be accessible at http://localhost:8080


## API Endpoints
You can see all API Endpoints by acessing the url below:


```markdown
http://localhost:8080/swagger-ui/index.html
```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
- User
- Manager
```
If you want to set the role of a user, you should send it in register request body.

Example:

```json
{
  "firstName": "first name",
  "lastName": "last name",
  "email": "email",
  "password": "password",
  "role": "role"
}
```
