(ns adventofcode.2022.day03.core
  (:require [clojure.java.io :as io]))

(def sample "resources/2022/day03_sample.txt")
(def input "resources/2022/day03_input.txt")

(defn split-string-in-two
  "split string s in two parts, returns vector of first half and second half
   'abcd' -> ['ab' 'cd']"
  [s]
  (let [len (/ (count s) 2)]
    (vector (subs s 0 len) (subs s len))))

(defn find-common-char
  "find common char in given strings"
  [strings]
  (->> strings
       (map #(set %))
       (apply clojure.set/intersection)
       first))

(defn priority-of
  "get priority of char c, a = 1 ... z = 26, A = 27, Z = 52"
  [c]
  (cond
    (<= (int \a) (int c) (int \z)) (+ (- (int c) 97) 1)
    (<= (int \A) (int c) (int \Z)) (+ (- (int c) 65) 27)))

(defn part1 []
  (->> (io/reader input)
       line-seq
       (map #(split-string-in-two %))
       (map #(find-common-char %))
       (map priority-of)
       (reduce +)))

(defn part2 []
  (->> (io/reader input)
       line-seq
       (partition 3)
       (map #(find-common-char %))
       (map priority-of)
       (reduce +)))

(part1)

(part2)