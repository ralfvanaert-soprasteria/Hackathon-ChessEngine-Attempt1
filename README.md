<h1>Hackathon-ChessEngine</h1>

A chess engine to be developed during a hackathon.
<br/>
For competing, fork this repository. Please refer to the [Competing with the Engine](#competing-with-the-engine) section.

<!-- TOC -->
* [Setup](#setup)
  * [Competing with the Engine](#competing-with-the-engine)
  * [KnightClubbingLogic](#knightclubbinglogic)
  * [Cutechess-cli](#cutechess-cli)
    * [Linux & MacOS](#linux--macos)
    * [Windows](#windows)
  * [Usage](#usage)
    * [SPRT testing](#sprt-testing)
<!-- TOC -->

# Scope of Hackathon
You have absolute freedom to modify and improve the chess engine in any way you see fit, but there are some restrictions and suggestions.

Restrictions:
- Must be based on this codebase (java 17, maven).
- Must be single threaded: no threads created or parallelism.
- No external sources allowed, including API's, pre-trained models, developed engines, etc.

Suggestive restrictions:
- No opening books. Games will be played from 8 moves deep.
- No endgame tablebases. Implementation too risky and potentially too time consuming for pay-off. 

Suggestions:
- Focus on search, ordering and evaluation improvements.
- Try to keep the engine fast and lightweight. 
  - Any millisecond spent is time not spent searching deeper and finding the better move.
  - Avoid (heavy) complex data structures.
  - Assume input is always valid, avoid unnecessary checks.
- SPRT-test often, after every change.
  - Changed a parameter? Test it!
  - Changed a line? Test it!
  - Reoredered functions? Test it!
- Have fun and be creative!

# Setup
Everything to develop this chess engine.
Prerequisites:
- Java 17 or higher
- Maven
- Git

Setup includes:
- Competing setup
- KnightClubbingLogic maven dependency
- Cutechess-cli installation
- 
## Competing with the Engine
To participate in the hackathon, you need to fork this repository and push your changes to your fork.
1. Fork this repository on GitHub.
2. Clone your forked repository.
3. Make your changes, commit and push them.
4. Create a pull request to the original repository before the deadline.

## KnightClubbingLogic
1. Clone the repository:
   ```
   git clone
   ```
2. Navigate to the project directory and maven install:
   ```
   cd Hackathon-ChessEngine
   mvn install
   ```

# Development


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
- H0 accepted: The new version is not better than the base version.
- H1 accepted: The new version is better than the base version.
- LOS: Likelihood of superiority of new over base version.

#### test_sprt.sh parameters
The docker image runs the `test_sprt.sh` as entrypoint with 3 parameters:
- $1: Base engine version (tag of the jar without .jar, f.e. '1.0')
- $2: New engine version (see above)
- $3: sprt_presets.ini preset name (default: 'default')

In the docker-compose.yml file you can change these parameters.