<h1>Hackathon-ChessEngine</h1>

A chess engine to be developed during a hackathon.
<br/>
For competing, fork this repository. Please refer to the [Repository use for hackathon](#repository-use-for-hackathon) section.

<!-- TOC -->
* [Scope of Hackathon](#scope-of-hackathon)
* [Setup](#setup)
  * [Repository use for hackathon](#repository-use-for-hackathon)
  * [KnightClubbingLogic](#knightclubbinglogic)
* [Development](#development)
  * [Restrictions and guidelines](#restrictions-and-guidelines)
    * [Guidelines](#guidelines)
    * [Soft restrictions](#soft-restrictions)
  * [SPRT testing](#sprt-testing)
    * [Using SPRT tests](#using-sprt-tests)
      * [test_sprt.sh parameters](#test_sprtsh-parameters)
<!-- TOC -->

# Scope of Hackathon
You have absolute freedom to modify and improve the chess engine in any way you see fit, but there are some restrictions.

Restrictions:
- Must be based on this codebase (java 17, maven).
- Must be single threaded: no threads created or parallelism.
- No external sources allowed, including API's, pre-trained models, developed engines, etc. 
 
Have fun and be creative!

# Setup
Everything to develop this chess engine.
Prerequisites:
- Java 17 or higher
- Maven
- Git

## Repository use for hackathon
To participate in the hackathon, you need to fork this repository and push your changes to your fork.
1. Fork this repository on GitHub.
2. Clone your forked repository.
3. Make your changes, commit and push them.
4. Create a pull request to the original repository before the deadline.

## KnightClubbingLogic
1. Clone the [repository](https://github.com/rpjvanaert/KnightClubbingLogic#)
2. Navigate to the project directory and maven install:
   ```
   cd KnightClubbingLogic
   mvn install
   ```
   
## Fork this repository
1. Fork this [repository](https://github.com/rpjvanaert/Hackathon-ChessEngine#).
2. Clone it to your local machine.
3. Navigate to the project directory and maven package:
   ```
   cd Hackathon-ChessEngine
   mvn package
   ```
   If it runs successfully, you have a starting point.

## Build base version
1. Build the base version jar and place it in the `test-sprt/engines` directory.
   ```
   mvn package
   cp target/Hackathon-ChessEngine-1.0.jar test-sprt/engines/1.0.jar
   ```
   
## Turning in your work
To turn in your work, create a pull request to the original repository before the deadline.
Make sure to include a description of your changes and improvements in the pull request.

# Development
The goal of this hackathon is to learn chess engine development and have fun improving the engine. 
## Restrictions and guidelines
To keep the challenge managable and learnable, there are some guidelines and soft restrictions.
Guidelines to help you choose what to implement and in which order
### Guidelines
To guide you through the development, here are some key points.
- todo
- Search for sources.
  - Google or DuckDuckGo are your best friends ;)
    - Don't copy-paste, understand.
    - Don't let LLMs generate it, at best use them to understand.
  - [Chess Programming Wiki](https://www.chessprogramming.org/Main_Page)
  - [Sebasian Lague's Chess Engine Video's (Episode 1 & 2)](https://www.youtube.com/watch?v=U4ogK0MIzqk&list=PLFt_AvWsXl0cvHyu32ajwh2qU1i6hl77c)

## SPRT testing
Sequential Probability Ratio Testing (SPRT) is a statistical test method to compare two versions of a chess engine to determine if one is significantly better than the other.
In english: test if your changes improved the engine or not.
It runs games between base and new version until conclusion is reached.

### Using SPRT tests
To run the SPRT tests, first build the engine jar you want to test. 
Make sure both base and new version jars are in the `test-sprt/engines` directory.
Then run the docker compose setup to execute the tests. 
By default it will run the test_sprt.sh script with '1.0' as base version and '1.0-SNAPSHOT' as new version.
If you built different versions, change the parameters accordingly in the docker-compose.yml file. Also see `test-sprt/test_sprt.sh` for details.

Rebuild image to pick up the engines:
```shell
docker compose up --build
```
of Podman:
```shell
podman compose up --build
```
- H0 accepted: The new version is not better than the base version.
- H1 accepted: The new version is better than the base version.
- LOS: Likelihood of superiority of new over base version.

#### test_sprt.sh parameters
The docker image runs the `test_sprt.sh` as entrypoint with 3 parameters:
- $1: Base engine version (tag of the jar without .jar, f.e. '1.0')
- $2: New engine version (see above)
- $3: sprt_presets.ini preset name (default: 'default')

In the docker-compose.yml file you can change these parameters.
