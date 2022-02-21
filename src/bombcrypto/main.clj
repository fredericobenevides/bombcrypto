(ns bombcrypto.main
  (:require [bombcrypto.heroes :as heroes]
            [bombcrypto.mouse :as mouse]
            [bombcrypto.time :as time]))

(def current-cycle (atom 0))

(defn run []
  (while true
    (println "\n******** Starting to run bombcrypto. Cycle:" @current-cycle (time/now-with-format) "********\n")

    ;; let me know that the mouse is going to be used before accessing the heroes
    (mouse/move-mouse-around)

    (heroes/start-all-heroes @current-cycle)

    (dotimes [n 20]
      (println "Waiting for 5 minutes before going to the menu. n is" n (time/now-with-format))
      (Thread/sleep (* 1000 60 5))
      (println "Finished waiting." (time/now-with-format))

      (mouse/move-mouse-around)

      (heroes/is-time-to-rest? n)
      (heroes/all-heroes-go-menu-treasure-hunt))

    ;; make sure all heroes stopped before starting again
    (heroes/stop-all-heroes)

    ;; update the iteration for the next cycle
    (swap! current-cycle inc)))

(defn -main []
  (run))
