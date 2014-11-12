(ns euler.35
  (:require [euler.utils :as u]))

(defn rotate
  "Rotates an integer by moving the first digit to the end"
  [n]
  (let [digits (u/explode-digits n)]
    (u/assemble-digits (concat (rest digits) [(first digits)]))))

(defn- count-digits
  [n]
  (count (u/explode-digits n)))

(defn- no-zero-digit?
  [n]
  (not-any? zero? (u/explode-digits n)))

(defn circular?
  "Checks if all rotations of a number are prime"
  [n]
  (and (no-zero-digit? n)
       (every? u/prime? (take (count-digits n) (iterate rotate n)))))

(def result
  (count (filter circular? (take-while #(> 1e6 %) u/primes))))