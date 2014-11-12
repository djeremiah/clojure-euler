(ns euler.36
  (:require [euler.utils :as u]))

(defn palindrome?
  "Determines if the string is a lexical palindrome"
  [s]
  (= s (clojure.string/reverse s)))

(def result
  (apply + (map #(Integer/valueOf (second %)) (filter #(every? palindrome? %) (map #(vector (Integer/toString % 2)(Integer/toString %)) (range 1e6))))))