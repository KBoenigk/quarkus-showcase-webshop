# Quarkus Showcase Webshop

This is an adapted copy of Stephan Müllers showcase for the microservice framework [Quarkus](https://quarkus.io): https://github.com/stephan-mueller/quarkus-showcase. It contains a hello world application, which demonstrates several features of Quarkus and Eclipse Microprofile.

Software requirements to run the samples are `maven`, `openjdk-8` (or any other JDK 8) and `docker`.
When running the Maven lifecycle it will create the war package and use the `quarkus-maven-plugin` to create a runnable 
JAR (fat JAR) which contains the application and the Quarkus application server. The fat JAR will be copied into a
Docker image using Spotify's `dockerfile-maven-plugin` during the package phase.

**Notable Features:**
* Dockerfiles for runnable JAR & Native Executable 
* Integration of MP Health, MP Metrics and MP OpenAPI
* Quarkus tests with Testcontainers, Rest-Assured, DbUnit (Database-Rider) and Postman/newman
* Acceptance tests with Testcontainers, Rest-Assured and Cucumber
* Code-Coverage for Testcontainer tests
* [CircleCI](https://circleci.com) Integration
* [Sonarcloud](https://sonarcloud.io) Integration


## How to run

Before running the application it needs to be compiled and packaged using `Maven`. It creates the runnable JAR and Docker image and can be 
run via `docker`:

```shell script
docker-compose up database
mvn clean package
docker-compose up application
```

If everything worked you can access the OpenAPI UI via [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui). In addition to
that the Quarkus Health UI can be accessed via http://localhost:8080/q/health-ui/.


### How to run Quarkus Dev Mode

If you want to run the application in Quarkus dev mode, you have to start the database before:

```shell script
$ docker-compose up database
$ mvn quarkus:dev
```

If everything worked you can access the Dev UI via [http://localhost:8080/q/dev](http://localhost:8080/q/dev).


<!--
### How to run a native image 

Before building a native executable and a native image, you have to install the GraalVM on your machine.

1. Install GraalVM (_Hint: check the Quarkus guide [building a native executable](https://quarkus.io/guides/building-native-image) for further details_)
2. Set GRAALVM_HOME environment variable to the GraalVM installation directory
3. Install the `native-image` tool using `gu install`

```shell script
$ export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-ce-java11-20.1.0/Contents/Home/
$ ${GRAALVM_HOME}/bin/gu install native-image
```

To create a docker image with a native executable you have to build the application with the Maven profile `native` 
```shell script
$ mvn clean package -Pnative
$ docker-compose up application
```

**Please note:**
 
The native executable is tailored for a specific operating system (Linux, macOS, Windows etc). 
If you build the native executable on macOS or Windows, you will not be able to use it inside a Linux based docker container. 

You can check the machine architecture with the following command:
```
$ file target/quarkus-showcase-webshop-runner
```
For a native executable build on macOs the output is:
> target/quarkus-showcase-webshop-runner: Mach-O 64-bit executable x86_64

For a native executable build in a Linux container the output is: 
> target/quarkus-showcase-webshop-runner: ELF 64-bit LSB executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib64/l, for GNU/Linux 3.2.0


To build a native executable which is usable inside a docker container you have to set the property 
`quarkus.native.container-build=true` (the property is set by default when using the Maven profile `native`). 
Instead of your machine a Linux container runtime is used for the build.    

If you want to try out the native executable on your machine use the following commands:
```shell script
$ mvn clean package -Pnative -Dquarkus.native.container-build=false -Ddockerfile.skip
$ ./target/quarkus-showcase-webshop-runner
```
-->

### Resolving issues

Sometimes it may happen that the containers did not stop as expected when trying to stop the pipeline early. This may
result in running containers although they should have been stopped and removed. To detect them you need to check
Docker:

```shell script
$ docker ps -a | grep quarkus-showcase-webshop
```

If there are containers remaining although the application has been stopped you can remove them:

```shell script
$ docker rm <ids of the containers>
```


## Features

### Application 

The application is a very simple "Hello World" greeting service. It supports GET requests for generating a greeting message, and a PUT 
request for changing the greeting itself. The response is encoded using JSON.

Try the application
***TODO***

### Health, Metrics and OpenAPI

The application server provides built-in support for health, metrics and openapi endpoints.

Health liveness and readiness
```shell script
curl -s -X GET http://localhost:8080/q/health

curl -s -X GET http://localhost:8080/q/health/live

curl -s -X GET http://localhost:8080/q/health/ready
```

Metrics in Prometheus / JSON Format
```shell script
curl -s -X GET http://localhost:8080/q/metrics

curl -H 'Accept: application/json' -X GET http://localhost:8080/q/metrics
```

OpenAPI in YAML / JSON Format
```shell script
curl -s -X GET http://localhost:8080/q/openapi

curl -H 'Accept: application/json' -X GET http://localhost:8080/q/openapi
```


### Integration tests with QuarkusTest, Testcontainers, REST-assured, DbUnit, Cucumber and Postman/Newman

For the application a set of integration tests is provided. The tests bases on `@QuarkusTest` combined with other testing frameworks like 
REST-assured, DbUnit (Database-Rider) and Postman/Newman.

When running a single or a set tests annotated with `@QuarkusTest` the Quarkus test extension is configured for the test, which starts 
Quarkus with profile `test`. Quarkus will then remain running for the duration of the test run. This makes testing very fast, because 
Quarkus is only started once.

#### Integration tests with Quarkus-Test, Testcontainers and DbUnit (Database-Rider)

[DbUnit](http://dbunit.sourceforge.net) is a JUnit extension targeted at database-driven projects that, among other things, puts the 
database into a known state between test runs. [Database-Rider](https://github.com/database-rider/database-rider) integrates JUnit and 
DbUnit through JUnit rules and, in case of CDI based tests, a CDI interceptor. The combination enables developers to prepare the database 
state for testing through yaml, xml, json, xls or csv files. Most inspiration of Database Rider was taken from [Arquillian extension 
persistence](https://docs.jboss.org/author/display/ARQ/Persistence.html).

The best and easiest way to provide a real database for integration tests is to use Testcontainers (e.g. `PostgreSQLContainer`). To 
integrate QuarkusTest with a Testcontainer Quarkus' notion of test resources can be used. Like described in the 
[Blog Post of Gunnar Morling](https://www.morling.dev/blog/quarkus-and-testcontainers/) an implementation of the 
`QuarkusTestResourceLifecycleManager` interface, which controls the resource’s lifecycle is a easy solution.

***TODO***

To enable the test resource the `@QuarkusTestResource` annotation has to be used. In addition to that the `@DBRider` CDI interceptor 
controls DbUnit to prepare and verify the database for each test case.  

***TODO***

#### Integration tests with Quarkus-Test, Testcontainers, REST-assured and DbUnit (Database-Rider)

[REST-assured](http://rest-assured.io) is a popular testframework for testing and validating REST services that brings the the simplicity 
of dynamic languages into the Java domain. 

***TODO***

#### API Tests with Testcontainers and Postman/Newman

Postman is an popular API client that supports automated API testing. Test collections developed in Postman can be exported and integrated 
with your CI/CD pipeline by using [Newman](https://github.com/postmanlabs/newman), Postman's command line Collection Runner. 

Newman allows you to run and test a Postman Collection directly from the command line. It is built with extensibility in mind so that it 
can easily integrate it with continuous integration servers, build systems and even Testcontainers.

To automate Postman test collections with Testcontainers the newman docker image is required. The collection and the environment file has 
to be copied to the docker image, and a file system bind has to be configured, to be able to access the test reports.

**IMPORTANT I**: The newman container is started and stopped for the execution of a single command - running the collection. To prevent that 
the containers is stopped before the test collection is executed, a `OneShotStartupCheckStrategy` with a timeout of 10 seconds has to be 
configured for the newman container.

**IMPORTANT II**: If you want to access a application running on the testcontainer host system from a testcontainer, the host ports have 
to be exposed. To configure Testcontainers to expose ports from the host system you have to call
`Testcontainers.exposeHostPorts(localServerPort);`.  

***TODO***

#### Acceptance tests with Testcontainers, REST-assured and Cucumber

[Cucumber](https://github.com/cucumber/cucumber-jvm) is one of the most popular tools that supports Behaviour-Driven Development(BDD) for
the Java language. Cucumber reads executable specifications written in natural language and validates that the software does what those
specifications say. The specifications consist of several examples or scenarios - which is why this approach is known as
[Specification by Example](https://en.wikipedia.org/wiki/Specification_by_example).

***TODO***