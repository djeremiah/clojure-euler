(ns euler.42
  (:require [euler.utils :as u]
            [clojure.math.combinatorics :as comb]))

(def divisors [17 13 11 7 5 3 2])

(defn sub-divisible?
  [digits]
  (every? true?
    (map-indexed #(zero? (mod (u/assemble-digits %2) (divisors %1))) 
                 (reverse (rest (partition 3 1 digits))))))

(def pandigitals
  (remove #(zero? (first %)) (comb/permutations '(0 1 2 3 4 5 6 7 8 9))))

(def result
  (reduce + (map u/assemble-digits (filter sub-divisible? pandigitals))))