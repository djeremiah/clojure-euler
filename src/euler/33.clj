(ns euler.33
  (:require [euler.utils :as u]))

(defn shared-digit
  "Takes two integers and if they share one and only one digit, returns that digit"
  [a b]
  (first (u/exactly-one (filter (set (u/explode-digits a)) (u/explode-digits b)))))

(defn remove-digit
  "Removes the first instance of the provided digit from the provided integer, returns the resulting integer"
  [digit n]
  (u/assemble-digits (u/remove-one digit (u/explode-digits n))))

(defn naive-reduce
  "If the numerator and denominator share a digit, return the ratio of the numbers after removing that digit"
  [n d]
  (when-let [s (shared-digit n d)]
    (apply / (map (partial remove-digit s) [n d]))))

(def v
 (for [d (range 10 100) :when (not= 0 (mod d 10)) 
       n (range 10 d)
       :when (= (/ n d) (naive-reduce n d))]
   (/ n d)))

(def result
  (denominator (apply * v)))