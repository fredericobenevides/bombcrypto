(ns bombcrypto.screen
  (:require [bombcrypto.mouse :as mouse]))

(defn go-main-menu [id]
  (mouse/move-to-and-click id 120 125)
  (Thread/sleep 1000))

(defn go-treasure-hunt [id]
  (mouse/move-to-and-click id 420 325))

(defn open-heroes-popup [id]
  (mouse/move-to id 410 485)

  (dotimes [_ 2]
    (mouse/click))

  ;; wait popup stay open before going forward
  (Thread/sleep 1000))

(defn close-heroes-popup [id]
  ;; move to close popup
  (mouse/move-to-and-click id 447 175)

  ;; click in the middle of the screen
  (mouse/move-to-and-click id 410 285))

(defn run-all-heroes [id]
  (mouse/move-to-and-click id 355 210)
  (Thread/sleep 3000))

(defn rest-all-heroes [id]
  (mouse/move-to-and-click id 395 210)
  (Thread/sleep 3000))

(defn move-to-hero [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 300 y)
    (mouse/click)))

(defn hero-go-work [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 355 y)
    (mouse/click)))

(defn hero-go-rest [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 395 y)
    (mouse/click)))

(defn hero-go-home [id number]
  (let [index (- number 1)
        y (-> (* 50 index) (+ 238))]
    (mouse/move-to id 435 y)
    (mouse/click)))

(defn previous-page []
  (dotimes [_ 20]
    (mouse/scroll-up)))

(defn next-page-of-heroes []
  (dotimes [_ 20]
    (mouse/scroll-down)))


