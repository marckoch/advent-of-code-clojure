(ns adventofcode.2022.day01.core
  (:require [clojure.java.io :as io]))

(def filename "resources/2022/day01_input.txt")

(defn last-index 
  "get last index of vec v"
  [v]
  (dec (count v)))

(defn update-last-element
  "add x to last element of vec"
  [vec x]
  (update vec (last-index vec) (fn [_] (+ (last vec) x))))

(defn grow-vec
  "if x is '' append 0 to vec, else add x to last element of vec"
  [vec x]
  (if (empty? x)
    (conj vec 0)
    (update-last-element vec (Integer/parseInt x))))

; part 1
(->> (io/reader filename)
     line-seq
     (reduce grow-vec [0])
     sort
     reverse
     first)

; part 2
(->> (io/reader filename)
     line-seq
     (reduce grow-vec [0])
     sort
     reverse
     (take 3)
     (reduce +))