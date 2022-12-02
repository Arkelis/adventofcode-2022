(ns day02
  (:require [clojure.string :as str]))

(defn shape-of-letter [letter]
  (case letter
    ("A" "X") :rock  
    ("B" "Y") :paper
    ("C" "Z") :scissors))

(defn shape-points [shape]
  (case shape
    :rock 1
    :paper 2
    :scissors 3))

(defn match-points [opponent answer]
  (case [opponent answer]
    ([:scissors :rock] [:rock :paper] [:paper :scissors]) 6 
    ([:paper :rock] [:scissors :paper] [:rock :scissors]) 0
    ([:rock :rock] [:paper :paper] [:scissors :scissors]) 3))

(defn win-against [shape]
  (case shape
    :rock :paper
    :paper :scissors
    :scissors :rock))

(defn lose-against [shape]
  (case shape
    :rock :scissors
    :paper :rock
    :scissors :paper))

(defn guess-shape [opponent answer]
  (case answer
    "X" (lose-against opponent)
    "Y" opponent
    "Z" (win-against opponent)))

(defn compute-score [opponent answer]
  (let [opponent-shape (shape-of-letter opponent)
        answer-shape (shape-of-letter answer)]
    (+ (shape-points answer-shape)
       (match-points opponent-shape answer-shape))))

(defn guess-and-compute-score [opponent answer]
  (let [opponent-shape (shape-of-letter opponent)
        answer-shape (guess-shape opponent-shape answer)]
    (+ (shape-points answer-shape)
       (match-points opponent-shape answer-shape))))

(defn get-data []
  (let [input (slurp "inputs/day02.txt")]
    (->> (str/split-lines input)
         (map #(str/split % #" ")))))

(defn part1 []
  (reduce + (->> (get-data) (map #(apply compute-score %)))))

(defn part2 []
  (reduce + (->> (get-data) (map #(apply guess-and-compute-score %)))))

(do
  (prn (part1))
  (prn (part2)))