(ns meowsolvu.test-runner
  (:require [clojure.test :as test]
            [meowsolvu.solver-test]))

(let [{:keys [fail error]} (test/run-tests 'meowsolvu.solver-test)]
  (when (pos? (+ fail error))
    (throw (ex-info "Tests failed" {:fail fail :error error}))))
