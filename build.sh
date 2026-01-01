#!/bin/bash

# RoboType Build Script

echo "🔨 Building RoboType JAR..."

cd "$(dirname "$0")"

# Compile
echo "📝 Compiling Java files..."
cd src
javac *.java

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

# Create JAR
echo "📦 Creating JAR file..."
cd ..
jar cfm RoboType.jar MANIFEST.MF -C src .

if [ $? -eq 0 ]; then
    echo "✅ Build complete! Run with: java -jar RoboType.jar"
else
    echo "❌ Build failed!"
    exit 1
fi
