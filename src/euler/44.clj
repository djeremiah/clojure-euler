(ns euler.44
  (:require [euler.utils :as u]))

(def pentagonal-numbers
  (map #(/ (* % (- (* 3 %) 1)) 2) (iterate inc 1)))

(defn pentagonal?
  [n]
  (zero? (rem (/ (+ 1 (Math/sqrt (+ 1 (* 24 n))) )6) 1)))


(def result
  (first (for [a pentagonal-numbers
               b (take-while #(> a %) pentagonal-numbers)
               :when (pentagonal? (- a b))
               :when (pentagonal? (+ a b))]
           (- a b))))
