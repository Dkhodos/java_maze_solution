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

## Usage

- placeholder

## Testing

- placeholder

## Notes

- The maze file should start with a number indicating the size of the maze, followed by the maze grid where `0` represents a walkable path and `1` represents an obstacle.
- The reports are generated as HTML files and can be viewed in a web browser.
- The maze generator can be used to generate random mazes of varying difficulty levels.
