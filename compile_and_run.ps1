# Directories
$SRC_DIR = "src"
$DIST_DIR = "dist"

# Create the dist directory if it doesn't exist
if (-not (Test-Path $DIST_DIR)) {
    New-Item -ItemType Directory -Path $DIST_DIR
}

# Compile all Java files from src and place the output in dist
javac -d $DIST_DIR ($SRC_DIR + "\*.java")

# Check if the compilation was successful
if ($?) {
    Write-Host "Compilation successful!"

    # Run the Main class and pass all arguments to it
    java -cp $DIST_DIR Main $args
} else {
    Write-Host "Compilation failed!"
}
