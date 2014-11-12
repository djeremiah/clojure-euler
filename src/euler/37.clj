(ns euler.37
  (:require [euler.utils :as u]))

(defn truncate-left
  [n]
  (u/assemble-digits (rest (u/explode-digits n))))

(defn truncate-right
  [n]
  (quot n 10))

(defn truncations
  [trunc-fn n]
  ((fn step 
     [x]
     (if (> 10 x) (list x)
       (cons x (lazy-seq (step (trunc-fn x))))))
    n))

(defn lr-truncable?
  [n]
  (every? u/prime? (flatten (map #(truncations % n) [truncate-left truncate-right]))))
                   
(def result
  (apply + (take 11 (filter lr-truncable? (drop-while #(> 10 %) u/primes)))))
   