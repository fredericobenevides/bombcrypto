(ns bombcrypto.screen
  (:require [bombcrypto.mouse :as mouse]))

(defn go-main-menu [browser-id]
  (mouse/move-to-and-click browser-id 30 115)
  (Thread/sleep 1000))

(defn go-treasure-hunt [browser-id]
  (mouse/move-to-and-click browser-id 250 260))

(defn open-heroes-popup [browser-id]
  (mouse/move-to browser-id 250 390)

  (dotimes [_ 2]
    (mouse/click))

  ;; wait popup stay open before going forward
  (Thread/sleep 3000))

(defn close-heroes-popup [browser-id]
  ;; move to close popup
  (mouse/move-to-and-click browser-id 274 155)

  ;; click in the middle of the screen
  (mouse/move-to-and-click browser-id 250 260))

(defn run-all-heroes [browser-id]
  (mouse/move-to-and-click browser-id 204 180)
  (Thread/sleep 2000))

(defn rest-all-heroes [browser-id]
  (mouse/move-to-and-click browser-id 235 180)
  (Thread/sleep 2000))

(defn move-to-hero [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 205))]
    (mouse/move-to browser-id 150 y)
    (mouse/click)
    ))

(defn hero-go-work [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 205))]
    (mouse/move-to browser-id 204 y)
    (mouse/click)))

(defn hero-go-rest [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 205))]
    (mouse/move-to browser-id 235 y)
    (mouse/click)))

(defn hero-go-home [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 205))]
    (mouse/move-to browser-id 265 y)
    (mouse/click)))

(defn previous-page []
  (dotimes [_ 20]
    (mouse/scroll-up)))

(defn next-page-of-heroes []
  (dotimes [_ 20]
    (mouse/scroll-down)))

(defn open-chest [browser-id]
  (mouse/move-to-and-click browser-id 430 115)
  (Thread/sleep 1000))

(defn close-chest [browser-id]
  (mouse/move-to-and-click browser-id 380 158)
  (Thread/sleep 1000))

