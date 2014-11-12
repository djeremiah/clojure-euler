(ns euler.49
  (:require [euler.utils :as u]
            [clojure.math.combinatorics :as comb]))

(def four-digit-primes
 (take-while (u/lt 1e4) (drop-while (u/lt 1e3) u/primes)))


(defn digit-permutations
  [n]
  (map u/assemble-digits (comb/permutations (u/explode-digits n))))

(defn has-zero-digit?
  [n]
  (some #{0} (u/explode-digits n)))

(def permutation-groups
  (group-by #(first (sort (filter u/prime? (digit-permutations %)))) 
            (remove has-zero-digit? (sort four-digit-primes)))) 

(defn arithmetic?
  [coll]
  (let [[a b c] (sort coll)]
    (= (- b a) (- c b))))

(def result
   (apply str (last (filter euler.49/arithmetic? 
                            (mapcat #(clojure.math.combinatorics/combinations (val %) 3) 
                                    euler.49/permutation-groups)))))
