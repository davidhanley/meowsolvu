(ns meowsolvu.solver
  (:require [clojure.set :as set]))

(def ^:private digits (set (range 1 10)))

(defn- valid-board-shape? [board]
  (and (= 9 (count board))
       (every? #(= 9 (count %)) board)
       (every? #(<= 0 % 9) (mapcat identity board))))

(defn- row-values [board row]
  (set (remove zero? (nth board row))))

(defn- col-values [board col]
  (set (remove zero? (map #(nth % col) board))))

(defn- box-values [board row col]
  (let [box-row (* 3 (quot row 3))
        box-col (* 3 (quot col 3))]
    (set (remove zero?
                 (for [r (range box-row (+ box-row 3))
                       c (range box-col (+ box-col 3))]
                   (get-in board [r c]))))))

(defn- candidates [board row col]
  (set/difference digits
                  (row-values board row)
                  (col-values board col)
                  (box-values board row col)))

(defn- empty-cells [board]
  (for [row (range 9)
        col (range 9)
        :when (zero? (get-in board [row col]))]
    [row col]))

(defn- best-empty-cell [board]
  (first
   (sort-by (fn [[row col]] (count (candidates board row col)))
            (empty-cells board))))

(declare solve-board)

(defn- solve-at-cell [board [row col]]
  (some solve-board
        (for [value (candidates board row col)]
          (assoc-in board [row col] value))))

(defn solve-board [board]
  (when-not (valid-board-shape? board)
    (throw (ex-info "Board must be a 9x9 grid with values between 0 and 9"
                    {:board board})))
  (if-let [cell (best-empty-cell board)]
    (solve-at-cell board cell)
    board))
