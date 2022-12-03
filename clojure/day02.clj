(ns day02
  (:require [clojure.string :as str]))

(defn shape-of-letter [letter]
  (case letter
    ("A" "X") :rock  
    ("B" "Y") :paper
    ("C" "Z") :scissors))

(def shape-points {:rock 1  :paper 2  :scissors 3})
(def win-against {:scissors :rock  :rock :paper  :paper :scissors})
(def lose-against {:rock :scissors  :paper :rock  :scissors :paper})

(defn match-points [opponent answer]
  (condp = answer 
    (win-against opponent) 6
    (lose-against opponent) 0
    opponent 3))

(defn guess-shape [opponent answer]
  (case answer
    "X" (lose-against opponent)
    "Y" opponent
    "Z" (win-against opponent)))

(defn get-shapes-part1 [[opponent answer]]
  [(shape-of-letter opponent) (shape-of-letter answer)])

(defn get-shapes-part2 [[opponent answer]]
  (let [opponent-shape (shape-of-letter opponent)] 
    [opponent-shape (guess-shape opponent-shape answer)]))

(defn compute-score [[opponent answer]]
  (+ (shape-points answer)
     (match-points opponent answer)))

(defn get-scores [shapes-getter]
  (let [input (slurp "inputs/day02.txt")]
    (->> (str/split-lines input)
         (map #(str/split % #" "))
         (map shapes-getter)
         (map compute-score)
         (apply +))))

(defn part1 []
  (get-scores get-shapes-part1))

(defn part2 []
  (get-scores get-shapes-part2))

(do
  (prn (part1))
  (prn (part2)))