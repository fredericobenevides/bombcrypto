(ns bombcrypto.main
  (:require [bombcrypto.accounts :as accounts]
            [bombcrypto.mouse :as mouse]
            [bombcrypto.time :as time]))

(def current-cycle (atom 0))

(def cycle-account-2 (atom 0))

(defn run []
  (while true
    (println "\n******** Starting to run bombcrypto. Cycle:" @current-cycle (time/now-with-format) "********\n")

    ;; let me know that the mouse is going to be used before accessing the heroes
    (mouse/move-mouse-around)

    (accounts/start-all-accounts @current-cycle)

    (dotimes [n 18]
      (when (even? n)
        (println "starting/changing account 2. Cycle" @cycle-account-2)
        (accounts/start-account-2 @cycle-account-2)
        (swap! cycle-account-2 inc))
      
      (println "Waiting for 5 minutes before going to the menu. n is" n (time/now-with-format))
      (Thread/sleep (* 1000 60 5))
      (println "Finished waiting." (time/now-with-format))

      (mouse/move-mouse-around)

      (accounts/is-time-to-stop? n)
      (accounts/all-accounts-open-close-chest))

    ;; update the iteration for the next cycle
    (swap! current-cycle inc)))

(defn -main []
  (run))
