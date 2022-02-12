(ns bombcrypto.heroes
  (:require [clojure.java.shell :as shell]
            [bombcrypto.mouse :as mouse]))

(defn open-popup [id]
  (mouse/move-to id 410 485)

  (dotimes [_ 2]
    (mouse/click))

  ;; wait popup stay open before going forward
  (Thread/sleep 1000))

(defn close-popup [id]
  ;; move to close popup
  (mouse/move-to-and-click id 447 175)

  ;; click in the middle of the screen
  (mouse/move-to-and-click id 410 285))

(defn click-to-run-all [id]
  (mouse/move-to-and-click id 355 210))

(defn click-to-rest-all [id]
  (mouse/move-to-and-click id 395 210))

(defn go-to-the-hero [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 300 y)
    (mouse/click)))

(defn go-work [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 355 y)
    (mouse/click)))

(defn go-rest [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 395 y)
    (mouse/click)))

(defn go-home [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 435 y)
    (mouse/click)))

(defn scroll-to-previous-page []
  (dotimes [_ 20]
    (mouse/scroll-up)))

(defn scroll-to-next-page []
  (dotimes [_ 20]
    (mouse/scroll-down)))

