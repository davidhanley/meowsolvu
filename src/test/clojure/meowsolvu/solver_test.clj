(ns meowsolvu.solver-test
  (:require [clojure.test :refer [deftest is testing]]
            [meowsolvu.solver :as solver]))

(def puzzle
  [[5 3 0 0 7 0 0 0 0]
   [6 0 0 1 9 5 0 0 0]
   [0 9 8 0 0 0 0 6 0]
   [8 0 0 0 6 0 0 0 3]
   [4 0 0 8 0 3 0 0 1]
   [7 0 0 0 2 0 0 0 6]
   [0 6 0 0 0 0 2 8 0]
   [0 0 0 4 1 9 0 0 5]
   [0 0 0 0 8 0 0 7 9]])

(def expected-solution
  [[5 3 4 6 7 8 9 1 2]
   [6 7 2 1 9 5 3 4 8]
   [1 9 8 3 4 2 5 6 7]
   [8 5 9 7 6 1 4 2 3]
   [4 2 6 8 5 3 7 9 1]
   [7 1 3 9 2 4 8 5 6]
   [9 6 1 5 3 7 2 8 4]
   [2 8 7 4 1 9 6 3 5]
   [3 4 5 2 8 6 1 7 9]])

(deftest solve-board-solves-sudoku-puzzle
  (testing "solver finds expected completed board"
    (is (= expected-solution (solver/solve-board puzzle)))))

(deftest solve-board-validates-shape
  (testing "solver rejects malformed board"
    (let [ex (is (thrown? clojure.lang.ExceptionInfo
                          (solver/solve-board [[1 2 3]])))]
      (is (= :invalid-board-shape (:error (ex-data ex)))))))

(deftest solve-board-returns-nil-for-unsolvable-board
  (testing "solver returns nil when board is valid shape but unsolvable"
    (is (nil? (solver/solve-board [[1 2 3 4 5 6 7 8 0]
                                   [4 5 6 7 8 1 2 3 9]
                                   [7 8 9 2 3 4 5 6 1]
                                   [2 3 4 5 6 7 8 9 2]
                                   [5 6 7 8 9 2 3 1 3]
                                   [8 9 1 3 4 5 6 2 4]
                                   [3 4 5 6 7 8 9 1 5]
                                   [6 7 8 9 1 3 4 5 6]
                                   [9 1 2 1 2 9 1 2 7]])))))
