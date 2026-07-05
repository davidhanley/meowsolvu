# meowsolvu

A Clojure project to solve meowduku boards.

## Running tests

```bash
mvn test
```

## Using the solver

The solver expects a 9x9 vector of vectors where `0` means empty.
Call `meowsolvu.solver/solve-board` to get a solved board (or `nil` if unsolvable).
