(ns bombcrypto.screen
  (:require [bombcrypto.mouse :as mouse]))

(defn go-main-menu [browser-id]
  (mouse/move-to-and-click browser-id 12 85)
  (Thread/sleep 1000))

(defn go-treasure-hunt [browser-id]
  (mouse/move-to-and-click browser-id 235 230))

(defn open-heroes-popup [browser-id]
  (mouse/move-to browser-id 235 360)

  (dotimes [_ 2]
    (mouse/click))

  ;; wait popup stay open before going forward
  (Thread/sleep 3000))

(defn close-heroes-popup [browser-id]
  ;; move to close popup
  (mouse/move-to-and-click browser-id 263 128)

  ;; click in the middle of the screen
  (mouse/move-to-and-click browser-id 235 230))

(defn run-all-heroes [browser-id]
  (mouse/move-to-and-click browser-id 190 151)
  (Thread/sleep 2000))

(defn rest-all-heroes [browser-id]
  (mouse/move-to-and-click browser-id 222 151)
  (Thread/sleep 2000))

(defn move-to-hero [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 173))]
    (mouse/move-to browser-id 150 y)
    (mouse/click)))

(defn hero-go-work [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 173))]
    (mouse/move-to browser-id 190 y)
    (mouse/click)))

(defn hero-go-rest [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 173))]
    (mouse/move-to browser-id 222 y)
    (mouse/click)))

(defn hero-go-home [browser-id number]
  (let [index (- number 1)
        y (-> (* 36 index) (+ 173))]
    (mouse/move-to browser-id 254 y)
    (mouse/click)))

(defn previous-page []
  (dotimes [_ 20]
    (mouse/scroll-up)))

(defn next-page-of-heroes []
  (dotimes [_ 20]
    (mouse/scroll-down)))

(defn open-chest [browser-id]
  (mouse/move-to-and-click browser-id 420 85)
  (Thread/sleep 1000))

(defn close-chest [browser-id]
  (mouse/move-to-and-click browser-id 367 128)
  (Thread/sleep 1000))

