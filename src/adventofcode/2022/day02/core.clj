(ns adventofcode.2022.day02.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def filename "resources/2022/day02_input.txt")

(defn to-indices
  "take line and return vector of indices, e.g. 'A X' -> [0 0]"
  [line]
  (->>
   (str/split line #" ")
   (#(vector (.indexOf "ABC" (first %))
             (.indexOf "XYZ" (second %))))))

;;;;;;;;;;; part 1

; matrix of win situations
; row is opponents move (A,B,C), column is my move (X,Y,Z),
; element is result of this round: 3 means draw, 6 I win, 0 I lose
;
;      X(R) Y(P) Z(S)
; A(R)  3    6    0
; B(P)  0    3    6
; C(S)  6    0    3
(def win-matrix [[3 6 0]
                 [0 3 6]
                 [6 0 3]])

(defn calc-score-part1
  "calculate the score for part1"
  [[opponents-move my-move]]
  (+ (inc my-move)
     ((win-matrix opponents-move) my-move)))

; part 1
(defn part1 []
  (->> (io/reader filename)
       line-seq
       (map #(to-indices %))
       (map #(calc-score-part1 %))
       (reduce +)))

;;;;;;;;;;; part 2

; matrix of my moves
; row is opponents move (A,B,C), column is desired result (X,Y,Z)
; element in matrix is move i should make
;
;      X(lose) Y(draw)  Z(win)
; A(R)  2(S)     0(R)    1(P)
; B(P)  0(R)     1(P)    2(S)
; C(S)  1(P)     2(S)    0(R)
(def matrix-of-my-moves [[2 0 1]
                         [0 1 2]
                         [1 2 0]])

(defn calc-score-part2
  "calculate the score for part2"
  [[opponents-move desired-result]]
  (+ (* 3 desired-result)
     (inc ((matrix-of-my-moves opponents-move) desired-result))))

; part 2
(defn part2 []
  (->> (io/reader filename)
       line-seq
       (map #(to-indices %))
       (map #(calc-score-part2 %))
       (reduce +)))

(part1)

(part2)