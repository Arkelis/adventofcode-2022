(ns day04 
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn assignment->set [assignment]
  (let [[start end]
        (as-> assignment it
          (str/split it #"-")
          (map read-string it))]
    (-> (range start (inc end)) set)))

(defn fully-overlap? [[set1 set2]]
  (or (set/subset? set1 set2)
      (set/superset? set1 set2)))

(defn partially-overlap? [sets]
  (->> sets
       (apply set/intersection)
       (seq)))

(defn get-assignments []
  (->> (slurp "inputs/day04.txt")
       (str/split-lines)
       (map #(str/split % #","))
       (map #(map assignment->set %))))

(defn part1 []
  (->> (get-assignments)
       (filter fully-overlap?)
       (count)))

(defn part2 []
  (->> (get-assignments)
       (filter partially-overlap?)
       (count)))

(do (prn (part1))
    (prn (part2)))