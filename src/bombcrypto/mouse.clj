(ns bombcrypto.mouse
  (:require [clojure.java.shell :as shell]))

(defn click
  []
  (shell/sh "sh" "-c" "xdotool click 1")
  (Thread/sleep 800))

(defn move-to [id x y]
  (shell/sh "sh" "-c" (str "xdotool mousemove --sync --window " id " " x " " y)))

(defn move-to-and-click [id x y]
  (move-to id x y)
  (click))

(defn scroll-up
  []
  (shell/sh "sh" "-c" "xdotool click 4"))

(defn scroll-down
  []
  (shell/sh "sh" "-c" "xdotool click 5"))
