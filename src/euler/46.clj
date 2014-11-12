(ns euler.46
  (:require [euler.utils :as u]))

(defn goldbach?
  [n]
  (first (for [p (rest u/primes) :while (> n p)
               :let [x (/ (- n p) 2)]
               :when (zero? (rem (Math/sqrt x) 1))]
           n)))

(def result
  (first (remove goldbach? (remove u/prime? (iterate #(+ 2 %) 3)))))