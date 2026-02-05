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

Setup includes:
- Competing setup
- KnightClubbingLogic maven dependency
- Cutechess-cli installation
- 
## Repository use for hackathon
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
   cd KnightClubbingLogic
   mvn install
   ```

# Development
The goal of this hackathon is to learn chess engine development and have fun improving the engine. 
## Restrictions and guidelines
To keep the challenge managable and learnable, there are some guidelines and soft restrictions.
Guidelines to help you choose what to implement and in which order
### Guidelines
To guide you through the development, here are some key points.
- **ALWAYS TEST YOUR CHANGES WITH SPRT TESTS!**
    - Small changes can have big impact.
    - Test often, after every change.
    - It's rewarding!
- From basics to details. First settle basics before touching details.
  - **Basics:** Start with implementing better evaluation and move ordering.
    - PST eval. (Piece Square Tables)
    - Material eval.
    - Other (opt.) eval. improvements
      - King safety
      - Pawn structure
      - etc.
    - MVV-LVA
    - Other (opt.) basic move ordering
      - Captures
      - Checks
  - **Detail:** Upgrade search into iterative deepening
    - Iterative deepening search
    - Killer moves
    - Transposition table
  - **Other details:** Other notable techniques
    - Search extensions
    - Late move reductions (LMR)
    - Quiescence search
- Search for sources.
  - Google or DuckDuckGo are your best friends ;)
    - Don't copy-paste, understand.
    - Don't let LLMs generate it, at best use them to understand.
  - [Chess Programming Wiki](https://www.chessprogramming.org/Main_Page)
  - [Sebasian Lague's Chess Engine Video's (Episode 1 & 2)](https://www.youtube.com/watch?v=U4ogK0MIzqk&list=PLFt_AvWsXl0cvHyu32ajwh2qU1i6hl77c)

### Soft restrictions
As stated before, besides the hard restrictions, you have absolute freedom to modify and improve the chess engine in any way you see fit.
I do have some soft restrictions, with the intention to keep the challenge doable and fun, reasoning is more important than the actual restriction:
- Single threaded (although also hard restriction, reasoning important)
  - Explanation: no threads created or parallelism. 
  - Reasoning: multi-threading adds a lot of complexity (race conditions, locks, etc.). Not needed to understand chess engines.
- Traditional search (No MCTS)
  - Explanation: Limit to minimax-like search: minimax, negamax, iterative deepening.
  - Reasoning: To keep things simple. Monte Carlo Tree Search is too (mathematically) complex for this hackathon.
- No books or tablebases
  - Explanation: No opening books or endgame tablebases.
  - Reasoning: Implementation gain not worth cost (risk & time). Also, not needed to understand chess engines. On top of that, competition will be SPRT-test from move 8.
- No external sources
  - Explantion: No external APIs, pre-trained models, chess engines, etc.
  - Reasoning: To keep the challenge fair and fun. Focus on your own improvements and lessons.
- No modifications to SPRT testing setup
  - Explanation: No changes to the SPRT testing setup.
  - Reasoning: To keep things simple, time of adjustments to testing setup are not worth the gain.

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
