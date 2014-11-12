(ns euler.42
  (:require [euler.utils :as u]
            [clojure.java.io :as io]
            [clojure.data.csv :as csv]))

(def triangle-numbers
  (map #(* 1/2 % (inc %)) (iterate inc 1)))

(defn char-val
  [c]
  (.indexOf "\nABCDEFGHIJKLMNOPQRSTUVWXYZ" (str c)))

(defn word-val
  [s]
  (reduce + (map char-val s)))

(defn triangle-word?
  [w]
  (let [val (word-val w)]
    (= val (first (drop-while #(> val %) triangle-numbers)))))
  
(def words
  (with-open [in-file (io/reader (io/resource "data/p042_words.txt"))]
    (flatten (csv/read-csv in-file))))

(def result
  (count (filter triangle-word? words)))