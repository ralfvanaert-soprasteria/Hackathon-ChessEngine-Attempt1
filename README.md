# Hackathon-ChessEngine

# Setup
Everything to develop this chess engine.
Prerequisites:
- Java 17 or higher
- Maven
- Git (and cloned this repository)

Setup includes:
- KnightClubbingLogic maven dependency
- Cutechess-cli installation

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
- Windows: `test-sprt/builds/windows/cutechess-cli.exe`
- Linux, depends on architecture (run `uname -m`)
  - `test-sprt/builds/linux/cutechess-cli-x86_64` for x86_64
- MacOS, depends on architecture (run `uname -m`)
  - `test-sprt/builds/macos/cutechess-cli-arm64` for arm64 (M1/M2)

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
1. Place the `cutechess-cli.exe` file in a directory of your choice. For example in `C:/Program Files/cutechess-cli/`.
2. Add the directory to PATH environment variable:
   - Add the path to the directory where you placed `cutechess-cli.exe`.
3. **Verify the Installation**
   ```
   cutechess-cli --help
   ```