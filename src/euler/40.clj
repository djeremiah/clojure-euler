(ns euler.40
  (:require [euler.utils :as u]))

(defn nth-dig [d]
  (loop [n (dec d) i 1 m 9]
    (let [nx (- n (* i m))]
      (if (neg? nx)
        (let [v ((into [] (str (+ (/ m 9) (quot n i)))) (rem n i))]
          (Integer. (str v)))
        (recur nx (inc i) (* m 10))))))

(def result
  (reduce * (map nth-dig (take 7 (iterate #(* 10 %) 1)))))