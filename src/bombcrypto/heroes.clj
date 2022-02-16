(ns bombcrypto.heroes
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.screen :as screen]))

(def browser-ids (browser/load-ids))

(defn- cycle->iteration [cycle iteration]
  (mod cycle iteration))

(defn all-heroes-go-menu-treasure-hunt []
  (println "All heroes need to go to the menu and go back to the treasure hunt.")
  (doseq [id browser-ids]
    (screen/go-main-menu id)
    (screen/go-treasure-hunt id)))

(defn open-popup-and-run-heroes [hero-id browser-id]
  (println hero-id "- Starting all heroes for the browser of id" browser-id)
  (screen/open-heroes-popup browser-id)
  (screen/run-all-heroes browser-id)
  (screen/close-heroes-popup browser-id))

(defn heroes1-start [_]
  (let [id (nth browser-ids 0)]
    (open-popup-and-run-heroes 1 id)))

(defn heroes2-start-some [id]
  (println "2 - Starting some heroes and making others to rest")

  (screen/open-heroes-popup id)
  (screen/run-all-heroes id)

  (screen/hero-go-home id 1)
  (screen/hero-go-home id 2)
  (screen/hero-go-home id 2)

  (screen/next-page-of-heroes)
  (screen/next-page-of-heroes)

  (screen/hero-go-home id 2))

(defn heroes2-start [cycle]
  (let [id (nth browser-ids 1)
        iteration (cycle->iteration cycle 1)]
    (cond
      (= iteration 0) (open-popup-and-run-heroes 2 id)
      (= iteration 1) (heroes2-start-some id))))

(defn heroes2-rest [n-time]
  (let [id (nth browser-ids 1)]
    (println "2 - Heroes going home")
    (when (= n-time 8)
      (heroes2-start-some id))))

(defn heroes3-start [cycle]
  (let [id (nth browser-ids 2)
        iteration (cycle->iteration cycle 1)]
    (cond
      (= iteration 0) (open-popup-and-run-heroes 3 id)
      ;; (= iteration 1)
      ;; (heroes3-start-some id)
      )))

(defn heroes4-start [cycle]
  (let [id (nth browser-ids 3)
        iteration (cycle->iteration cycle 1)]
    (cond
      (= iteration 0) (open-popup-and-run-heroes 4 id)
      ;; (= iteration 1)
      ;; (heroes3-start-some id)
      )))

(defn heroes5-start [cycle]
  (let [id (nth browser-ids 4)
        iteration (cycle->iteration cycle 1)]
    (cond
      (= iteration 0) (open-popup-and-run-heroes 5 id)
      ;; (= iteration 1)
      ;; (heroes3-start-some id)
      )))

(defn heroes6-start [cycle]
  (let [id (nth browser-ids 5)
        iteration (cycle->iteration cycle 1)]
    (cond
      (= iteration 0) (open-popup-and-run-heroes 6 id)
      ;; (= iteration 1)
      ;; (heroes3-start-some id)
      )))

(defn start-all-heroes [cycle]
  (heroes1-start cycle)
  (heroes2-start cycle)
  (heroes3-start cycle)
  (heroes4-start cycle)
  (heroes5-start cycle)
  (heroes6-start cycle))

(defn stop-all-heroes []
  (doseq [id browser-ids]
    (screen/open-heroes-popup id)
    (screen/rest-all-heroes id)
    (screen/close-heroes-popup id)))

(defn rest-heroes [n-time]
  (heroes2-rest [n-time]))





