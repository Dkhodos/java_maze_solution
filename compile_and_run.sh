#!/bin/bash

# Directories
SRC_DIR="src"
DIST_DIR="dist"

# Create the dist directory if it doesn't exist
mkdir -p "$DIST_DIR"

# Compile all Java files from src and place the output in dist
javac -d "$DIST_DIR" "$SRC_DIR"/*.java

# Check if the compilation was successful
if [ $? -eq 0 ]; then
    echo "Compilation successful!"

    # Run the Main class and pass all arguments to it
    java -cp "$DIST_DIR" Main "$@"
else
    echo "Compilation failed!"
fi
