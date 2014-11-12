(ns euler.38
  (:require [euler.utils :as u]))

(def pandigits
  #{1 2 3 4 5 6 7 8 9})

(defn pandigital?
  "A number is pandigital if each digit from 1-9 occurs once and only once"
  [n]
  (let [digits (u/explode-digits n)]
    (and (apply distinct? digits)
         (= (set digits) pandigits))))

(defn concatenated-product
  [n t]
  (u/assemble-digits (mapcat #(str (* n %)) t)))

(def tuples
  (map #(range 1 %) (drop 2 (range))))

(def candidates
  (reduce into [(range 99 91 -1) (range 999 918 -1) (range 9999 9182 -1)]))

(def result
  (apply max (for [c candidates
                   t tuples
                   :let [p (concatenated-product c t)]
                   :while (> 1e9 p)
                   :when (pandigital? p)]
               p)))
