(ns euler.41
  (:require [euler.utils :as u]))

(defn n-pandigital?
  "Determines if number consists of all the digits 1-d (where d = number of digits in n)exactly once"
  [n]
  (let [digits (u/explode-digits n)]
    (and (apply distinct? digits) ;no duplicate digits
         (= (set (range 1 (inc (count digits)))) (set digits))))) ;pandigital

;; 9 and 8 pandigitals are all non-prime
(def result
  (reduce max (filter n-pandigital? (take-while #(> 1e7 %) u/primes))))
