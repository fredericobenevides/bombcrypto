(ns bombcrypto.mouse
  (:require [clojure.java.shell :as shell]))

(defn click
  []
  (shell/sh "sh" "-c" "xdotool click 1")
  (Thread/sleep 800))

(defn move-to [id x y]
  ;; loop 2 times to avoid delay issue with xdotool when its using the same location
  (loop [n -1]
    (when (< n 1)
      (let [x2 (+ x n)]
        (shell/sh "sh" "-c" (str "xdotool mousemove --sync --window " id " " x2 " " y))
        (recur (inc n))))))

(defn move-to-and-click [id x y]
  (move-to id x y)
  (click))

(defn scroll-up
  []
  (shell/sh "sh" "-c" "xdotool click 4"))

(defn scroll-down
  []
  (shell/sh "sh" "-c" "xdotool click 5"))

(defn move-mouse-around []
  (let [x 1920]
    (loop [n 100]
      (when (<= n x)
        (shell/sh "sh" "-c" (str "xdotool mousemove --sync " n " " (+ 300 (rand-int 300))))
        (Thread/sleep 20)
        (recur (+ 15 n))))))
