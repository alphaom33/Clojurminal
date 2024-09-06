(ns clojure-noob.character
  (:gen-class))

(def character
  {:name "Yep of course"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defn my-comp
  [first & functions]
  (fn [& args] (reduce #(%2 %1) (apply first args) functions)))

(defn my-assoc-in
  [map [k & ks] v]
    (if (not k)
      v
      (my-assoc-in map ks (assoc {} k v))))

(defn my-update-in
  [map ks fn]
  (my-assoc-in map ks (fn (get-in map ks))))

(def attr #((comp % :attributes) character))

(defn -main
    [& args]
    (println ((my-comp + str) 8 8 8)))