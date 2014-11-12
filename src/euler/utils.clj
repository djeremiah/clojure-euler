(ns euler.utils)

(defn lt
  "Returns a predicate that is true when the argument is strictly less than n"
  [n]
  #(> n %))

(defn prime?
  "Uses Java interop to determine primality"
  [n]
  (.isProbablePrime (BigInteger/valueOf n) 10))

(defn update-in!
  "modified version of core/update-in that works on, and return
transients"
  ([m [k & ks] f & args]
   (if ks
     (assoc! m k (apply update-in! (get m k) ks f args))
     (assoc! m k (apply f (get m k) args)))))  

(def primes
  ((fn gen-primes []
     (letfn [(reinsert [t x p] (update-in! t [(+ x p)] conj p))
             (step 
               [t d]
               (if-let [fs (t d)]
                 (recur (reduce #(reinsert %1 d %2) (dissoc! t d) fs) (inc d))
                 (cons d (lazy-seq (step (assoc! t (* d d) (list d)) (inc d))))))]
       (step (transient {}) 2)))))

(defn- prime-factors*
  "Returns a lazy sequence of prime factors of n, caluclated by trial division"
  [n]
  (if-let [f (first (filter #(zero? (rem n %)) 
                            (take-while (lt (+ 1 (Math/sqrt n))) primes)))]
    (cons f (lazy-seq (prime-factors* (/ n f))))
    (list n)))
  

(defn prime-factors
  "Returns a map with prime factors of n as keys, and their multiplicy as values"
  [n]
  (frequencies (prime-factors* n)))
  

(defn factorial
  "Quick factorial for integers 0 - 9"
  [n]
  (case n
    0 1    
    1 1
    2 2
    3 6
    4 24
    5 120
    6 720
    7 5040
    8 40320
    9 362880
    (throw (IllegalArgumentException. "Cannot handle input > 9"))))

(defn explode-digits
 "Takes an integer and returns a vector of its digits"
 [n]
 (vec (map #(Character/digit % 10) (str n))))
 
(defn assemble-digits
  "Takes a collection of digits and returns an integer"
  [digits]
  (when-let [n (not-empty (apply str (seq digits)))]
    (BigInteger. n)))

(defn exactly-one
  "If coll has one element, returns coll, else nil"
  [coll]
  (when (= 1 (count coll)) coll))

(defn remove-one
  "Removes the first instance of x from coll"
  [x coll]
  (let [[n m] (split-with (partial not= x) coll)]
    (concat n (rest m))))

