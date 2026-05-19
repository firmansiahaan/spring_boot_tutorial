# SpringBoot tutorial

**Spring Boot is a modern Java framework that makes building production-ready applications fast and simple by eliminating boilerplate code, offering auto-configuration, and embedding servers like Tomcat or Jetty. It’s widely used for REST APIs, microservices, and enterprise applications because of its speed, flexibility, and seamless integration with databases, security, and cloud platforms.**   [Spring](https://spring.io/projects/spring-boot/)  [GeeksForGeeks](https://www.geeksforgeeks.org/advance-java/spring-boot/)  [DEV Community](https://dev.to/krishna7852/introduction-to-spring-boot-a-complete-guide-203l)

---

## 🚀 What is Spring Boot?
- **Framework**: Built on top of the Spring ecosystem.
- **Purpose**: Simplifies Java application development by reducing XML configuration and boilerplate code.
- **Embedded Servers**: Includes Tomcat, Jetty, or Undertow, so you don’t need external deployment.
- **Production-Ready**: Comes with monitoring tools, health checks, metrics, and externalized configuration.

---

## 🔑 Core Features
- **Auto-Configuration**: Automatically configures beans and dependencies based on your project setup.
- **Starter Dependencies**: Pre-packaged sets of dependencies (e.g., `spring-boot-starter-web`) to speed up development.
- **Standalone Applications**: Run directly with `java -jar` without needing an external server.
- **Spring Boot Actuator**: Provides endpoints for monitoring (health, metrics, environment).
- **DevTools**: Enables hot reloading for faster development cycles.
- **Database Integration**: Works seamlessly with JPA, Hibernate, JDBC, and NoSQL databases.

---

## ⚖️ Comparison: Spring vs Spring Boot

| Aspect                  | Spring Framework                  | Spring Boot                          |
|--------------------------|-----------------------------------|--------------------------------------|
| **Configuration**        | Manual XML/Java config            | Auto-configuration, sensible defaults |
| **Setup Time**           | Longer, more complex              | Quick, minimal setup                 |
| **Deployment**           | Requires external server (WAR)    | Embedded server (JAR)                 |
| **Best Use Case**        | Large enterprise apps with custom setups | REST APIs, microservices, fast prototyping |
| **Learning Curve**       | Steeper                           | Beginner-friendly                     |

---

## 🛠️ Getting Started
1. **Spring Initializr**: Use [start.spring.io](https://start.spring.io) to bootstrap your project.
2. **Choose Dependencies**: e.g., Web, JPA, Security, Actuator.
3. **Run Application**:  
   ```bash
   mvn spring-boot:run
   ```
   or  
   ```bash
   java -jar target/myapp.jar
   ```
4. **Hello World Example**:
   ```java
   @SpringBootApplication
   public class DemoApplication {
       public static void main(String[] args) {
           SpringApplication.run(DemoApplication.class, args);
       }
   }

   @RestController
   class HelloController {
       @GetMapping("/")
       public String hello() {
           return "Hello, Spring Boot!";
       }
   }
   ```

---

## ⚠️ Risks & Limitations
- **Opinionated Defaults**: While convenient, they may not fit complex enterprise needs.
- **Learning Curve for Advanced Features**: Basic setup is easy, but mastering microservices, security, and cloud integrations requires deeper Spring knowledge.
- **Performance Overhead**: Embedded servers are great for simplicity but may not be optimal for all enterprise deployments.

---

## ✅ Why Use Spring Boot?
- **Fast prototyping** for web apps and APIs.
- **Microservices-ready** with Spring Cloud integration.
- **Enterprise adoption** ensures strong community support.
- **Beginner-friendly** for developers transitioning from plain Java or Servlets/JSP.

---
Perfect! Let’s sketch out a **mini CRUD project with Spring Boot + JPA** so you can see how it compares to your Servlets/JSP workflow. This will give you a hands-on feel for how Spring Boot simplifies things.

---

## 🛠️ Project: Simple Student Management API

### 1. Setup
- Use [Spring Initializr](https://start.spring.io)  
- Dependencies:  
  - `spring-boot-starter-web` (for REST API)  
  - `spring-boot-starter-data-jpa` (for database access)  
  - `h2` (in-memory database for quick testing)  

---

### 2. Entity Class
```java
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // getters and setters
}
```

---

### 3. Repository Interface
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
```

---

### 4. REST Controller
```java
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return repository.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
```

---

### 5. Run & Test
- Start app:  
  ```bash
  mvn spring-boot:run
  ```
- Test endpoints (using Postman or curl):  
  - `GET /students` → list all  
  - `POST /students` → add new  
  - `GET /students/{id}` → get by ID  
  - `PUT /students/{id}` → update  
  - `DELETE /students/{id}` → delete  

---

## 🔍 Why This Is Easier Than Servlets/JSP
- No manual JDBC code → JPA handles persistence.
- No XML config → auto-configuration sets up H2 + JPA.
- REST endpoints are just annotated methods, not servlet boilerplate.
- Embedded server → run instantly with `java -jar`.

---
Let’s extend our **Spring Boot CRUD app** into a **CI/CD pipeline with GitHub Actions** so you can see how automated testing and deployment fit in.

---

## ⚙️ Step 1: Project Setup
- Push your Spring Boot project to GitHub.
- Ensure you have a `pom.xml` (Maven) or `build.gradle` (Gradle).

---

## 📂 Step 2: GitHub Actions Workflow File
Create a file: `.github/workflows/ci.yml`

```yaml
name: Spring Boot CI Pipeline

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      # Checkout code
      - name: Checkout repository
        uses: actions/checkout@v3

      # Set up Java
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      # Build & run tests
      - name: Build with Maven
        run: mvn clean install

      - name: Run tests
        run: mvn test

      # Package JAR
      - name: Package application
        run: mvn package
```

---

## 🧪 Step 3: Add Unit Tests
Example test for your `StudentController`:

```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStudents() throws Exception {
        mockMvc.perform(get("/students"))
               .andExpect(status().isOk());
    }
}
```

---

## 📊 Step 4: Extend Pipeline
- **Code Quality**: Add `mvn verify` with tools like Checkstyle, PMD, or SpotBugs.
- **Security Scan**: Use `mvn dependency-check:check` for vulnerabilities.
- **Coverage Reports**: Integrate JaCoCo for test coverage.
- **Docker Build (Optional)**: Add a job to build and push Docker images.

---

## 🚀 Step 5: Deployment (Optional)
You can extend the workflow to:
- Deploy to **Heroku**, **AWS Elastic Beanstalk**, or **Azure App Service**.
- Or push Docker images to **Docker Hub**.

---

## 🔑 Why This Matters
- Every push triggers **automated build + test**.
- Prevents broken code from reaching production.
- Fits perfectly with **QA integration in CI/CD pipelines**—exactly what you’re learning.

---
Excellent, Firman! Let’s wire up **Robot Framework tests** into your GitHub Actions pipeline so your **Spring Boot backend** and **QA automation** work together seamlessly.

---

## 🛠️ Step 1: Add Robot Framework Tests
Create a folder `tests/robot/` and add a test file, e.g. `student_api.robot`:

```robot
*** Settings ***
Library    RequestsLibrary

*** Variables ***
${BASE_URL}    http://localhost:8080

*** Test Cases ***
Get All Students
    Create Session    api    ${BASE_URL}
    ${response}=    Get Request    api    /students
    Should Be Equal As Integers    ${response.status_code}    200

Create Student
    Create Session    api    ${BASE_URL}
    ${body}=    Create Dictionary    name=Firman    email=firman@example.com
    ${response}=    Post Request    api    /students    json=${body}
    Should Be Equal As Integers    ${response.status_code}    200
```

---

## ⚙️ Step 2: Update GitHub Actions Workflow
Extend `.github/workflows/ci.yml`:

```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build with Maven
        run: mvn clean install

      - name: Run unit tests
        run: mvn test

      - name: Start Spring Boot app in background
        run: nohup mvn spring-boot:run &

      - name: Set up Python
        uses: actions/setup-python@v4
        with:
          python-version: '3.11'

      - name: Install Robot Framework
        run: pip install robotframework requests

      - name: Run Robot Framework tests
        run: robot tests/robot/
```

---

## 📊 Step 3: Pipeline Flow
1. **Build & Unit Tests** → Maven compiles and runs JUnit tests.  
2. **Start App** → Spring Boot runs in background.  
3. **Robot Framework Tests** → API endpoints tested automatically.  
4. **Reports** → Robot generates HTML reports (`report.html`, `log.html`) viewable in GitHub Actions artifacts.

---

## 🚀 Benefits
- **Full-stack validation**: Unit tests check logic, Robot tests check API behavior.  
- **QA integration**: Every push runs automated acceptance tests.  
- **Confidence in CI/CD**: Prevents regressions before deployment.  

---
Let’s take our **Spring Boot + Robot Framework pipeline** one step further by **containerizing the app with Docker** and running the tests inside containers. This simulates a production-like environment and makes your CI/CD workflow more robust.

---

## 🛠️ Step 1: Dockerize the Spring Boot App
Create a `Dockerfile` in your project root:

```dockerfile
# Use official JDK image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy built JAR
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run locally:
```bash
mvn clean package
docker build -t springboot-demo .
docker run -p 8080:8080 springboot-demo
```

---

## 🧪 Step 2: Dockerize Robot Framework Tests
Create another `Dockerfile` in `tests/robot/`:

```dockerfile
FROM python:3.11-slim

WORKDIR /tests

# Install Robot Framework + RequestsLibrary
RUN pip install robotframework requests

# Copy test files
COPY . .

# Run tests against backend
ENTRYPOINT ["robot", "--outputdir", "results", "student_api.robot"]
```

Run tests container:
```bash
docker build -t robot-tests .
docker run --network="host" robot-tests
```

*(Using `--network="host"` lets Robot connect to the Spring Boot app running on localhost:8080.)*

---

## ⚙️ Step 3: Update GitHub Actions Workflow
Extend `.github/workflows/ci.yml`:

```yaml
jobs:
  ci:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build JAR
        run: mvn clean package

      - name: Build Spring Boot Docker image
        run: docker build -t springboot-demo .

      - name: Run Spring Boot container
        run: docker run -d -p 8080:8080 --name app springboot-demo

      - name: Build Robot Framework Docker image
        run: docker build -t robot-tests ./tests/robot

      - name: Run Robot Framework tests
        run: docker run --network="host" robot-tests
```

---

## 📊 Step 4: Pipeline Flow
1. **Build JAR** → Maven packages Spring Boot app.  
2. **Dockerize App** → Build and run container.  
3. **Dockerize Tests** → Robot Framework runs inside its own container.  
4. **Reports** → Robot outputs HTML reports inside container (can be uploaded as GitHub Actions artifacts).  

---

## 🚀 Benefits
- **Environment consistency**: Same Docker image runs locally, in CI, and in production.  
- **Isolation**: Tests run in their own container, independent of host setup.  
- **Scalability**: Easy to extend with multiple services (databases, message queues).  

---
Let’s extend our pipeline into a **Docker Compose setup** so your Spring Boot app, database, and Robot Framework tests all run together in a coordinated environment. This is how real-world teams simulate production locally and in CI/CD.

---

## 🛠️ Step 1: Add Database Service
Let’s use **Postgres** as an example. Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://db:5432/studentdb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

---

## 📂 Step 2: Docker Compose File
Create `docker-compose.yml`:

```yaml
version: '3.8'

services:
  app:
    build: .
    container_name: springboot-app
    ports:
      - "8080:8080"
    depends_on:
      - db

  db:
    image: postgres:15
    container_name: postgres-db
    environment:
      POSTGRES_DB: studentdb
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"

  robot-tests:
    build: ./tests/robot
    container_name: robot-tests
    depends_on:
      - app
    environment:
      BASE_URL: http://app:8080
    networks:
      - default
```

---

## 🧪 Step 3: Robot Framework Tests Adaptation
Update `student_api.robot` to use environment variable:

```robot
*** Settings ***
Library    RequestsLibrary
Library    OperatingSystem

*** Variables ***
${BASE_URL}    %{BASE_URL}

*** Test Cases ***
Get All Students
    Create Session    api    ${BASE_URL}
    ${response}=    Get Request    api    /students
    Should Be Equal As Integers    ${response.status_code}    200
```

---

## ⚙️ Step 4: GitHub Actions Workflow
Extend `.github/workflows/ci.yml`:

```yaml
jobs:
  ci:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build JAR
        run: mvn clean package

      - name: Build and run Docker Compose
        run: docker-compose up --build --abort-on-container-exit

      - name: Show Robot results
        run: cat tests/robot/results/report.html || true
```

---

## 📊 Pipeline Flow
1. **Database container** → Postgres starts.  
2. **Spring Boot container** → App connects to Postgres.  
3. **Robot container** → Runs API tests against the app.  
4. **Reports** → Robot generates HTML logs and reports.  

---

## 🚀 Benefits
- **Full-stack integration testing**: Backend + database + QA in one pipeline.  
- **Production-like setup**: Mirrors real deployments with containers.  
- **Automation-ready**: CI/CD runs everything with one command.  

---
