
# Maze Solver

This project provides a set of search algorithms to solve a maze. The maze is represented as a grid, where each cell can either be a walkable path or an obstacle. The goal is to find a path from a starting point to an end point.

## Features

- **Search Algorithms**: The project includes multiple search algorithms:
- A* Search
- Greedy Search
- Uniform Cost Search
- **Maze Parser**: Parses a maze from a file.
- **Report Generation**: Generates an HTML report based on a given solution path through the maze.
- **Search Executor**: Executes a list of search algorithms on a given maze, logs the execution time, displays the results, and generates a report for each algorithm.
- **Maze Generator**: Generates random mazes based on a given difficulty level.
- **Testing**: Comprehensive unit tests for the search algorithms, maze parser, and maze functionalities.

## Classes

- **Maze**: Represents a grid-based maze with nodes.
- **Node**: Represents a point in the maze.
- **SearchAlgorithm**: An abstract class that provides the base for search algorithms.
- **Frontier**: A priority queue-based frontier used in search algorithms.
- **Logger**: Provides color-coded console logging.
- **MazeParser**: Parses a maze from a file.
- **ReportMaker**: Generates an HTML report.
- **SearchExecutor**: Executes search algorithms and generates reports.
- **SearchResult**: Holds the result of a search algorithm.
- **MazeGenerator**: Generates random mazes.
- **SearchBaseTest**: Base class for search algorithm tests.

## Install
- dependencies are located in `java_maze_solution.iml`
- install using you local IDE package manager

## Usage

### Compile (if not using IDE)

### Main Program
- Run main with `java Main.java <path-to-maze-file>`

### Maze Generator
- Run main in `java MazeGenerator.java`, you may adjust the size and difficulty in the main method

## Tests
- **AStarTest**:
  - `testSolve`: Checks if the A* algorithm can solve a basic maze.
  - `testSolveUnsolvableMaze`: Validates that the A* algorithm correctly identifies an unsolvable maze.
  - `testSolveBigMaze`: Tests the A* algorithm's performance and correctness on a larger maze.

- **GreedySearchTest**:
  - `testSolve`: Checks if the Greedy algorithm can solve a basic maze.
  - `testSolveUnsolvableMaze`: Validates that the Greedy algorithm correctly identifies an unsolvable maze.
  - `testSolveBigMaze`: Tests the Greedy algorithm's performance and correctness on a larger maze.

- **UniformCostSearchTest**:
  - `testSolve`: Checks if the Uniform Cost Search algorithm can solve a basic maze.
  - `testSolveUnsolvableMaze`: Validates that the Uniform Cost Search algorithm correctly identifies an unsolvable maze.
  - `testSolveBigMaze`: Tests the Uniform Cost Search algorithm's performance and correctness on a larger maze.

- **MazeParserTest**:
  - `testParseMaze`: Validates that the maze parser correctly reads and interprets a maze file.
  - `testEmptyFile`: Checks the parser's behavior when provided with an empty file.
  - `testSizeMissingFile`: Validates the parser's behavior when the maze size is missing from the file.

- **MazeTest**:
  - `testGetNeighbors`: Checks if the maze correctly identifies the neighbors of a given node, considering obstacles.

### To run the tests:

- Use stock IntelliJ / Eclipse test runner

## Github CI
- Check `.github/workflows/tests.yaml`

## Notes

- The maze file should start with a number indicating the size of the maze, followed by the maze grid where `0` represents a walkable path and `1` represents an obstacle.
- The reports are generated as HTML files and can be viewed in a web browser.
- The maze generator can be used to generate random mazes of varying difficulty levels.