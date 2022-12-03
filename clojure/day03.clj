(ns day03
  (:require [clojure.string :as str]
            [clojure.set :as set]))


(defn get-sacks []
  (let [input (slurp "inputs/day03.txt")]
    (->> (str/split-lines input)
         (map vec))))

(defn get-priority [char]
  (if (= (str char) (str/upper-case char))
    (-> char (int) (- 38))
    (-> char (int) (- 96))))

(defn part1 []
  (->> (get-sacks)
       (map #(split-at (/ (count %) 2) %))
       (map #(->> % (map set) (apply set/intersection) (first)))
       (map get-priority)
       (apply +)))

(defn part2 []
  (->> (get-sacks)
       (partition 3) 
       (map #(->> % (map set) (apply set/intersection) (first))) 
       (map get-priority) 
       (apply +)))

(do
  (prn (part1))
  (prn (part2)))