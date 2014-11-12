(ns euler.45
  (:require [euler.utils :as u]
            [euler.44 :as f]))

(def hexs
  (map #(* % (- (* 2 %) 1)) (iterate inc 1)))

(def result
  (first (drop-while (u/lt 40756) (filter f/pentagonal? hexs))))