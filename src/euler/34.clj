(ns euler.34
  (:require [euler.utils :as u]))

(defn factorion?
  "Is n equal to the sum of the factorials of its digits?"
  [n]
  (= n (apply + (map u/factorial (u/explode-digits n)))))


(def result
  (apply + (filter factorion? (range 3 1854722))))