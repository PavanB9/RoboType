#!/bin/bash

# RoboType Build Script - Compiles and creates JAR

set -e
 
echo "🔨 Building RoboType JAR..."

# Get script directory
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"
 
# Compile
echo "📝 Compiling RoboTypeGUI.java..."
cd src
javac RoboTypeGUI.java

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

# Create JAR
echo "📦 Creating JAR file..."
cd ..
rm -f RoboType.jar
jar cfe RoboType.jar RoboTypeGUI -C src .

if [ $? -eq 0 ]; then
    echo "✅ Build complete!"
    echo "🚀 Run with: java -jar RoboType.jar"
else
    echo "❌ JAR creation failed!"
    exit 1
fi
