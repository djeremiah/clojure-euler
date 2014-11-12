(ns euler.47
  (:require [euler.utils :as u]))

(defn factors=4
  [n]
  (= 4 (count (u/prime-factors n))))

(def result
  (first (first 
           (filter #(every? factors=4 %)
                   (partition 4 1 (iterate inc 1))))))