(ns bombcrypto.main
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.heroes :as heroes]
            [bombcrypto.mouse :as mouse]))

(def legendary-stay-home (atom false))

(defn current-time []
  (let [time-format (java.time.format.DateTimeFormatter/ofPattern "dd/MM/yyyy hh:mm:ss")]
    (.format time-format (java.time.LocalDateTime/now))))

(defn go-main-menu [id]
  (mouse/move-to-and-click id 120 125)
  (Thread/sleep 1000))

(defn go-to-treasure-hunt [id]
  (mouse/move-to-and-click id 420 325))

(defn all-super-heroes-go-home [browser-index id]
  (when (= browser-index 1)
    (println "All super heroes is going home")
    (heroes/open-popup id)

    (heroes/click-to-rest-all id)
    
    (heroes/go-to-the-hero id 2)
    (heroes/go-home id 2)
    (heroes/go-home id 2)
    (heroes/go-home id 3)

    (heroes/scroll-to-next-page)
    (heroes/scroll-to-next-page)

    (heroes/go-home id 2)))

(defn only-legendary-go-home [browser-index id]
  (when (= browser-index 1)
    (println "Only legendary is going home")
    (heroes/go-to-the-hero id 1)

    (heroes/scroll-to-next-page)
    (heroes/scroll-to-next-page)

    (heroes/go-home id 5)))

(defn run-on-each-browser []
  (while true
    (println "\nStarting to run bombcrypto")

    (doseq [[index id] (browser/load-ids)]
      (println "Running for id" id (current-time))
      (heroes/open-popup id)
      (heroes/click-to-run-all id)

      ;; after activating all heroes, only the legendary
      ;; goes home if need to stay in home
      (when @legendary-stay-home
        (only-legendary-go-home index id))

      (heroes/close-popup id))

    ;; after running the heroes, keep going to menu
    ;; and treasure hunt for each 110 minutes
    (dotimes [n 22]
      (let [times (inc n)]
        (println times "times. Waiting for 5 minutes before going to the menu" (current-time))
        (Thread/sleep (* 1000 60 5))

        (doseq [[index id] (browser/load-ids)]
          ;; after 40 minutes, make "all super heroes" go home
          (when (= times 8)
            (println "All super heroes goes home")
            (all-super-heroes-go-home index id))

          (println times " times. Going to the menu for id" id (current-time))
          (go-main-menu id)
          (go-to-treasure-hunt id))))

    ;; for each iteration it will enable or disable home
    (reset! legendary-stay-home (not @legendary-stay-home))
    (println "Reset value of legendary-stay-home to" @legendary-stay-home)))

(defn -main []
  (run-on-each-browser))
