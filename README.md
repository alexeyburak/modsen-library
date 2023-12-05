# What is it?

This is a test project for Modsen internship.

## Project launch guide

*JDK 21 required*

### Clone project

```bash
git clone https://github.com/alexeyburak/modsen-library.git
``` 

### Provide environment configurations (optional)

1. Go to **.env** file;
2. Fill fields
   - *CONFIG_SERVER_ENCRYPT_KEY* 
   - *CONFIG_SERVER_PWD*

### Build project

```bash
mvn clean install
```

### Build and run Docker containers

```bash
docker-compose build && docker-compose up -d 
```

### Linux and MacOS launching (optional)

```bash
bash run.sh
```

## Open API

View Open API documentation by provided link after launching microservices.

```url
http://localhost:8080/swagger-ui.html
```

Select acceptable definitions:

 - account
 - book
 - registration

**Account** microservice provide logic of crud operations for application users.  
**Book** microservice provide logic of crud operations for books and book registries.  
**Registration** microservice provide logic of registration, authorization and JWT token validation.