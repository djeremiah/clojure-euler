(ns euler.48
  (:require [euler.utils :as u]))

(defn truncated-multiply
  "perform multiplication, but only out to 10 digits"
  [x y]
  (reduce * (map #(u/assemble-digits (take-last 10 (u/explode-digits %))) [x y])))

(defn truncated-addition
  "perform addition, but only out to 10 digits"
  [x y]
  (reduce + (map #(u/assemble-digits (take-last 10 (u/explode-digits %))) [x y])))
  
(defn self-power
  "raise n to n, but truncate to 10 digits"
  [n]
  (reduce truncated-multiply (repeat n n)))  

(def result
  (reduce truncated-addition (map self-power (range 1 1001))))