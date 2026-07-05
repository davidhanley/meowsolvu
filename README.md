# meowsolvu

A Clojure project to solve meowduku (Sudoku-style) boards.

## Running tests

```bash
mvn test
```

## Using the solver

The solver expects a 9x9 vector of vectors where `0` means empty.
Call `meowsolvu.solver/solve-board` to get a solved board.
The function returns `nil` when the board has no valid solution and throws
`ExceptionInfo` for invalid board shapes or values.
