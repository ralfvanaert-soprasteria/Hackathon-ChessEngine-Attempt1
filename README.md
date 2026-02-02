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

## Cutechess-cli
Executables can be found in the `test-sprt/builds` directory.

Picking the right one for your OS:
- Windows: `test-sprt/builds/windows_x86_64/cutechess-cli.zip` for 64-bit
- Linux, depends on architecture (run `uname -m`)
  - `test-sprt/builds/linux_x86_64/cutechess-cli` for x86_64
- MacOS, depends on architecture (run `uname -m`)
  - `test-sprt/builds/macos_arm64/cutechess-cli` for arm64 (M1/M2)

### Linux & MacOS
1. 
   Ensure the `cutechess-cli` is executable. Run:
   ```
   chmod +x cutechess-cli
   ```

2. Move the file to a directory in your PATH
   ```
   sudo mv cutechess-cli /usr/local/bin/
   ```

3. **Verify the Installation**
   ```
   cutechess-cli --help
   ```

### Windows
1. Unzip the `cutechess-cli.zip` file in test-sprt/builds/windows_x86_64/. 
    <br/>Note: the extract locations should be there too.
3. **Verify the Installation**, for example in PowerShell:
   ```powershell
   ./test-sprt/builds/windows_x86_64/cutechess-cli --help
   ```
   
## Usage
To run your chess engine separately, run the jar:
```
java -jar Hackathon-ChessEngine-<version>.jar
```

### SPRT testing
To run the sprt tests, run in `test-sprt` directory:
```
./test_sprt.sh <base_version> <new_version>
```
See `test-sprt/test_sprt.sh` for more details. Results of SPRT:
- H0 accepted: The new version is not better than the base version.
- H1 accepted: The new version is better than the base version.
- LOS: Likelihood of superiority of new over base version.