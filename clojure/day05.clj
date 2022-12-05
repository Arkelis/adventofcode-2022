(ns day05
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn take-stack [stacks-data stack-index]
  (->> stacks-data
       (map vec)
       (map #(nth % (-> stack-index (* 4) (+ 1))))
       (filter #(not= \space %))))

(defn get-stacks [input]
  (let [lines (str/split-lines input)
        stacks-count (-> lines (last) (str/split #" ") (last) (read-string))
        stacks-data (butlast lines)]
    (->> (range stacks-count)
         (map #(take-stack stacks-data %))
         (vec))))

(defn parse-instruction-line [line]
  (->> line
       (re-seq #"\d+")
       (map read-string)))

(defn get-instructions [input]
  (->> input
       (str/split-lines)
       (map parse-instruction-line)))

(defn parse-input []
  (let [[stacks-input instructions-input]
        (-> (slurp "inputs/day05.txt")
            (str/split #"\n\n"))]
    [(get-stacks stacks-input)
     (get-instructions instructions-input)]))

(defn play-step [stacks step rev?]
  (let [[count orig dest] step
        orig-idx (dec orig)
        dest-idx (dec dest) 
        [to-add orig-stack] (as-> stacks it
                              (nth it orig-idx)
                              (split-at count it)) 
        dest-stack (concat (if rev? (reverse to-add) to-add)
                           (nth stacks dest-idx))]
    (-> stacks
        (assoc orig-idx orig-stack)
        (assoc dest-idx dest-stack))))

(defn play-steps [stacks [step & others] & {:keys [rev?]}]
   (if (nil? step) stacks
   (play-steps (play-step stacks step rev?) others :rev? rev?)))

(defn part1 []
  (->> [:rev? true]
       (concat (parse-input))
       (apply play-steps)
       (map first)
       (str/join)))

(defn part2 []
  (->> [:rev? false]
       (concat (parse-input))
       (apply play-steps)
       (map first)
       (str/join)))

(do
  (println (part1))
  (println (part2)))
  