# RoboType - Automated Typing Simulator

RoboType is a cross-platform Java application that simulates realistic human typing patterns using the Robot class from java.awt. It features a user-friendly GUI available for both Windows and macOS.

## Features

- ✨ Simulates human-like typing with varying speeds
- 🐛 Includes random typos and corrections
- 🗑️ Random character deletions to mimic typing errors
- ⚡ Adjustable typing speed (Very Slow to Ultra Fast)
- 🎛️ Toggle typos and deletions on/off
- 🎨 Modern, intuitive GUI with floating stop button
- 💻 Works on both Windows and macOS
- 🚀 Easy one-click launch

## Installation

### Prerequisites
- **Java 8+** must be installed on your system
- [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html)

Verify Java installation:
```bash
java -version
```

## Quick Start - Running the Pre-built JAR

1. Download `RoboType.jar` from the releases
2. **macOS/Linux**: Double-click or run:
   ```bash
   java -jar RoboType.jar
   ```
3. **Windows**: Double-click `RoboType.jar` or run:
   ```cmd
   java -jar RoboType.jar
   ```

## Building from Source

### Option 1: Using the Build Script

**macOS/Linux:**
```bash
cd RoboType
chmod +x build.sh
./build.sh
java -jar RoboType.jar
```

**Windows (Command Prompt):**
```cmd
cd RoboType
build.sh
java -jar RoboType.jar
```

Or manually compile:
```bash
cd src
javac *.java
cd ..
jar cfm RoboType.jar MANIFEST.MF -C src .
java -jar RoboType.jar
```

### Option 2: Manual Compilation

```bash
# Navigate to the project folder
cd RoboType/src

# Compile all Java files
javac RoboType.java RoboTypeGUI.java

# Create the JAR (from RoboType root folder)
cd ..
jar cfm RoboType.jar MANIFEST.MF -C src .

# Run it
java -jar RoboType.jar
```

## How to Use

1. **Enter Text**: Type or paste the text you want RoboType to simulate typing
2. **Adjust Settings**:
   - Use the **Speed Slider** to control typing speed (Very Slow → Ultra Fast)
   - Check **Include Typos** to add realistic mistakes
   - Check **Include Random Deletions** for human-like behavior
3. **Click "Start Typing"**
4. **Switch windows** within 5 seconds to the target text field
5. A floating "STOP TYPING" button will appear for easy cancellation
6. Watch as RoboType types automatically!
7. Use **Reset** to clear and start over

## File Structure

```
RoboType/
├── src/
│   ├── RoboType.java          # Command-line version
│   └── RoboTypeGUI.java       # GUI version (recommended)
├── bin/                        # Compiled classes
├── build.sh                    # Build script (macOS/Linux)
├── RoboType.jar               # Pre-compiled executable
├── MANIFEST.MF                # JAR manifest
├── README.md                  # This file
└── LICENSE                    # GPLv3 License
```

## Two Versions Available

### RoboTypeGUI (Recommended) 🎨
- Modern graphical interface
- Real-time controls and feedback
- Speed adjustment slider
- Floating stop button during typing
- Better user experience

**Main Class**: `RoboTypeGUI`

### RoboType (CLI Version) 💻
- Command-line interface
- Simple text input
- Legacy version with basic functionality

**Main Class**: `RoboType`

## System Requirements

- **Java**: 8 or higher
- **RAM**: 128 MB minimum
- **OS**: Windows, macOS, Linux

## Permissions Note

This application uses Java's `Robot` class for keyboard automation. Depending on your OS and security settings, you may need to:
- **macOS**: Grant accessibility permissions to Java/Terminal
- **Linux**: May require special permissions depending on your window manager
- **Windows**: Run as administrator if issues occur

## Technical Details

- Built with Java Swing for cross-platform GUI
- Uses `java.awt.Robot` for keyboard automation
- Threaded implementation for responsive UI
- Supports lowercase, uppercase, numbers, and special characters
- Realistic typing simulation with:
  - Variable typing speeds
  - Random typos with auto-correction
  - Punctuation pauses
  - Random thinking breaks
  - Character deletion simulation

## Troubleshooting

**JAR won't run:**
- Ensure Java is installed: `java -version`
- Try running from command line instead of double-clicking
- Make sure file is named `RoboType.jar`

**Typing not appearing:**
- Ensure the target window has focus
- Check that Java has accessibility permissions (especially macOS)
- Try running as administrator

**Build fails:**
- Ensure both `RoboType.java` and `RoboTypeGUI.java` are in `src/` folder
- Check Java compiler is installed: `javac -version`

## License

Licensed under GNU General Public License v3 (GPLv3) - See [LICENSE](LICENSE)

## Support

For issues or suggestions, please open an issue on GitHub.
