(ns day06)

(defn get-input []
  (-> 
    (slurp "inputs/day06.txt")
    (seq)))

(defn find-start-marker
  "Find marker by looking for n consecutive different elements from chars"
  ([n chars]
   (find-start-marker n chars 0))
  ([n chars idx]
   (if (-> (take n chars) (set) (count) (= n))
     (+ idx n)
     (find-start-marker n (rest chars) (+ idx 1)))))

(defn find-packet-start-marker [chars]
  (find-start-marker 4 chars))

(defn find-message-start-marker [chars]
  (find-start-marker 14 chars))

(defn part1 []
  (-> (get-input)
      (find-packet-start-marker)))

(defn part2 []
  (-> (get-input)
      (find-message-start-marker)))

(do
  (println (part1))
  (println (part2)))
  