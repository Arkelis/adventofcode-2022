(ns day01
  (:require [clojure.string :as str]))

(defn sum-maxima [top coll]
  (let [sorted (sort coll)]
    (apply + (take-last top sorted))))

(defn get-sums []
  (let [input (slurp "inputs/day01.txt")]
    (->> (str/split input #"\n\n")
         (map str/split-lines)
         (map #(apply + (map read-string %))))))

(defn part1 []
  (sum-maxima 1 (get-sums)))

(defn part2 []
  (sum-maxima 3 (get-sums)))

(do
  (prn (part1))
  (prn (part2)))