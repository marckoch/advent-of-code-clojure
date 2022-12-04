(ns adventofcode.2022.day04.core
  (:require [clojure.java.io :as io]))

(def sample "resources/2022/day04_sample.txt")
(def input "resources/2022/day04_input.txt")

(defn parse
  "parse a line (e.g. '12-34,45-67') and return a vector of all 4 numbers (e.g. [12 34 45 67])"
  [line]
  (->> line
       (re-find #"(\d+)-(\d+),(\d+)-(\d+)")
       rest
       (map #(Integer/parseInt %))
       (into [])))

(defn one-covers-other
  "check if one area covers the other, input is vector [start1 end1 start2 end2]"
  [vec]
  (let [[s1 e1 s2 e2] vec
        first-covers-second (and (<= s1 s2) (<= e2 e1))
        second-covers-first (and (<= s2 s1) (<= e1 e2))]
    (or first-covers-second second-covers-first)))

(defn has-overlap
  "check if both areas overlap, input is vector [start1 end1 start2 end2]"
  [vec]
  (let [[s1 e1 s2 e2] vec
        first-before-second (< e1 s2)
        first-after-second (< e2 s1)]
    (and (not first-before-second) (not first-after-second))))

(defn part1 []
  (->> (io/reader input)
       line-seq
       (map #(parse %))
       (filter #(one-covers-other %))
       count))

(defn part2 []
  (->> (io/reader input)
       line-seq
       (map #(parse %))
       (filter #(has-overlap %))
       count))

(part1)

(part2)