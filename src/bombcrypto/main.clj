(ns bombcrypto.main
  (:require [bombcrypto.accounts :as accounts]
            [bombcrypto.mouse :as mouse]
            [bombcrypto.time :as t]))

(def schedules
  (atom {:accounts
         [{:id 1  :time (t/now) :step 0 :enabled true}
          {:id 2  :time (t/now) :step 0 :enabled true}
          {:id 3  :time (t/now) :step 0 :enabled true}
          {:id 4  :time (t/now) :step 0 :enabled true}
          {:id 5  :time (t/now) :step 0 :enabled true}
          {:id 6  :time (t/now) :step 0 :enabled true}
          {:id 7  :time (t/now) :step 0 :enabled true}
          {:id 8  :time (t/now) :step 0 :enabled true}
          {:id 9  :time (t/now) :step 0 :enabled true}
          {:id 10 :time (t/now) :step 0 :enabled true}]
         :open-chest (t/plus-minutes (t/now) 5)}))

(defn get-schedule-account [account-id]
  (let [account-index (dec account-id)
        accounts (:accounts @schedules)
        account (accounts account-index)]
    {:index account-index :account account}))

(defn update-sched-account-to-next-step! [account-id]
  (let [account-data (get-schedule-account account-id)
        account-index (:index account-data)
        account (:account account-data)
        step-next-index (inc (:step account))
        step-init-index 0]
    (if (accounts/get-step account-id step-next-index)
      (swap! schedules update-in [:accounts] assoc-in [account-index :step] step-next-index)
      (swap! schedules update-in [:accounts] assoc-in [account-index :step] step-init-index))))

(defn update-sched-account-time! [account-id]
  (let [account-data (get-schedule-account account-id)
        account-index (:index account-data)
        account (:account account-data)
        sched-step (:step account)
        sched-time (:time account)
        account-step (accounts/get-step account-id sched-step)
        account-time (:time account-step)]
    (swap! schedules update-in [:accounts] assoc-in [account-index :time] (t/plus-minutes sched-time account-time))))

(defn update-sched-time-open-chest! []
  (let [current-time (:open-chest @schedules)]
    (swap! schedules assoc :open-chest (t/plus-minutes current-time 5))))

(defn log [text]
  (println (t/now-with-format) "=> " text))

(defn menu []
  (println "\n******************************************************")
  (println "Current time:" (t/now-with-format))
  (doseq [accounts (:accounts @schedules)]
    (let [id (:id accounts)
          time (t/format-time (:time accounts))
          step (:step accounts)
          enabled (:enabled accounts)]
      (when enabled
        (println (str "Account-" id " => exec time: " time " Step: " step)))))
  (println "Open chest => exec time:" (t/format-time (:open-chest @schedules)))
  (println "******************************************************\n"))

(defn run-open-and-close-chest []
  (when (t/is-gte? (t/now) (:open-chest @schedules))
    (log "Open and close chest: Executing for all accounts")
    (mouse/move-mouse-around)
    (accounts/all-accounts-open-close-chest)
    (log "Open and close chest: Updating the time for next execution")
    (update-sched-time-open-chest!)))

(defn load-accounts-to-run [schedules]
  (for [accounts (:accounts schedules)
        :let [account-id (:id accounts)
              time (:time accounts)
              step (:step accounts)
              enabled (:enabled accounts)]
        :when (and (t/is-gte? (t/now) time) enabled)]
    (do
      (log (str "Account-" account-id ": is ready to be run"))
      {:account-id account-id :step-index step})))

(defn run-accounts! [accounts-data]
  (when (seq accounts-data)
    (mouse/move-mouse-around)
    (doseq [{:keys [account-id step-index]} accounts-data]
      (log (str "Account-" account-id ": running account with step: " step-index))
      (accounts/start-account account-id step-index)

      (log (str "Account-" account-id ": updating next step and next time"))
      (update-sched-account-to-next-step! account-id)
      (update-sched-account-time! account-id))))

(defn -main []
  (while true
    (menu)

    (run-open-and-close-chest)
    (run-accounts! (load-accounts-to-run @schedules))

    (Thread/sleep (* 1000 60))))

