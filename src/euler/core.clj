(ns euler.core)

(defn -main
  "Run the specified problem"
  [problem & args]
  (load (str "euler/" problem))
  (prn (eval (read-string (str "euler." problem "/result")))))