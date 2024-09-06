(ns clojure-noob.core
  (:gen-class))

(defn list-depth
  [to-depth]
  (loop [current to-depth
         count 0]
    (if (not (list? current))
      count
      (recur (nth current 0) (inc count)))))

(defn operand-greater?
  [operand1 operand2]
  (> (int (/ (.indexOf [+ - * /] (eval operand1)) 2)) (int (/ (.indexOf [+ - * /] (eval operand2)) 2))))

(defn order
  [operator out num]
  (let [[replace-operator replace-num out] out]
       (list replace-operator replace-num (list operator out num))))

(defmacro infix-math
  [[first-num1 first-operator first-num2 & math]]
  (loop [[operator num & tail] math
         out (list first-operator first-num1 first-num2)]
    (if (not operator)
      (do (println out) out)
      (recur tail (if (operand-greater? operator (first out))
                    (order operator out num)
                    (list operator out num))))))

(defn -main
  [& args]
  (println (infix-math (1 + 2 * 3 * 5 - 4))))