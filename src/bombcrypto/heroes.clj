(ns bombcrypto.heroes
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.screen :as screen]))

(def browser-ids (browser/load-ids))

(def n-time-to-stop 6)

(defn- cycle->iteration [cycle iteration]
  (mod cycle iteration))

(def heroes-1-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-1-iteration-1 [:open-heroes
                           :run-all
                           :next-page
                           :next-page
                           {:go-rest 4}
                           :close-heroes])
(def heroes-1-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-2-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-2-iteration-1 [:open-heroes
                           :run-all
                           {:go-home 1}
                           :next-page
                           :next-page
                           {:go-home 4}
                           :close-heroes])
(def heroes-2-is-time-to-rest [:open-heroes
                               :run-all ;; run all to keep heroes in the right place
                               :rest-all
                               {:go-home 1}
                               {:go-home 2}
                               {:go-home 2}
                               :next-page
                               :next-page
                               {:go-home 2}
                               :close-heroes])

(def heroes-3-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-3-iteration-1 [:open-heroes
                           :run-all
                           :next-page
                           {:go-rest 2}
                           {:go-rest 5}
                           :close-heroes])
(def heroes-3-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-4-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-4-iteration-1 [:open-heroes
                           :run-all
                           {:go-rest 3}
                           {:go-rest 4}
                           :next-page
                           {:go-rest 2}
                           {:go-rest 5}
                           {:go-rest 5}
                           :close-heroes])
(def heroes-4-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-5-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-5-iteration-1 [:open-heroes
                           :run-all
                           :next-page
                           {:go-rest 4}
                           :close-heroes])
(def heroes-5-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-6-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-6-iteration-1 [:open-heroes
                           :run-all
                           {:go-rest 1}
                           {:go-rest 5}
                           :next-page
                           :next-page
                           {:go-rest 3}])
(def heroes-6-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-7-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-7-iteration-1 [:open-heroes
                           :run-all
                           :close-heroes])
(def heroes-7-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-8-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-8-iteration-1 [:open-heroes
                           :run-all
                           :close-heroes])
(def heroes-8-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-9-start [:open-heroes
                     :run-all
                     :close-heroes])
(def heroes-9-iteration-1 [:open-heroes
                           :run-all
                           :close-heroes])
(def heroes-9-is-time-to-rest [:open-heroes
                               :rest-all
                               :close-heroes])

(def heroes-10-start [:open-heroes
                      :run-all
                      :close-heroes])
(def heroes-10-iteration-1 [:open-heroes
                            :run-all
                            :close-heroes])
(def heroes-10-is-time-to-rest [:open-heroes
                                :rest-all
                                :close-heroes])

(defn run-heroes [heroes-data browser-id hero-id]
  (println hero-id " - running heroes on the browser with id:" browser-id)
  (doseq [hd heroes-data]
    (println "==> Executing:" hd)
    (if (map? hd)
      (let [key (first (keys hd))]
        (cond
          (= key :go-home) (screen/hero-go-home browser-id (:go-home hd))
          (= key :go-rest) (screen/hero-go-rest browser-id (:go-rest hd))))
      (cond
        (= hd :open-heroes) (screen/open-heroes-popup browser-id)
        (= hd :close-heroes) (screen/close-heroes-popup browser-id)
        (= hd :run-all) (screen/run-all-heroes browser-id)
        (= hd :rest-all) (screen/rest-all-heroes browser-id)
        (= hd :next-page) (do (screen/move-to-hero browser-id 1)
                              (screen/next-page-of-heroes))))))

(defn heroes1-start [cycle]
  (let [browser-id (nth browser-ids 0)
        hero-id 1
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-1-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-1-iteration-1 browser-id hero-id))))

(defn heroes1-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 0)]
    (when (= n-time n-time-to-stop)
      (println "1 - Time to rest/go home" n-time)
      (run-heroes heroes-1-is-time-to-rest browser-id 1))))

(defn heroes2-start [cycle]
  (let [browser-id (nth browser-ids 1)
        hero-id 2
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-2-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-2-iteration-1 browser-id hero-id))))

(defn heroes2-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 1)
        hero-id 2]
    (when (= n-time n-time-to-stop)
      (println "2 - Time to rest/go home" n-time)
      (run-heroes heroes-2-is-time-to-rest browser-id hero-id))))

(defn heroes3-start [cycle]
  (let [browser-id (nth browser-ids 2)
        hero-id 3
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-3-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-3-iteration-1 browser-id hero-id))))

(defn heroes3-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 2)
        hero-id 3]
    (when (= n-time n-time-to-stop)
      (println "3 - Time to rest/go home" n-time)
      (run-heroes heroes-3-is-time-to-rest browser-id hero-id))))

(defn heroes4-start [cycle]
  (let [browser-id (nth browser-ids 3)
        hero-id 4
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-4-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-4-iteration-1 browser-id hero-id))))

(defn heroes4-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 3)
        hero-id 4]
    (when (= n-time n-time-to-stop)
      (println "4 - Time to rest/go home" n-time)
      (run-heroes heroes-4-is-time-to-rest browser-id hero-id))))

(defn heroes5-start [cycle]
  (let [browser-id (nth browser-ids 4)
        hero-id 5
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-5-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-5-iteration-1 browser-id hero-id))))

(defn heroes5-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 4)
        hero-id 5]
    (when (= n-time n-time-to-stop)
      (println "5 - Time to rest/go home" n-time)
      (run-heroes heroes-5-is-time-to-rest browser-id hero-id))))

(defn heroes6-start [cycle]
  (let [browser-id (nth browser-ids 5)
        hero-id 6
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-6-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-6-iteration-1 browser-id hero-id))))

(defn heroes6-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 5)
        hero-id 6]
    (when (= n-time n-time-to-stop)
      (println "6 - Time to rest/go home" n-time)
      (run-heroes heroes-6-is-time-to-rest browser-id hero-id))))

(defn heroes7-start [cycle]
  (let [browser-id (nth browser-ids 6)
        hero-id 7
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-7-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-7-iteration-1 browser-id hero-id))))

(defn heroes7-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 6)]
    (when (= n-time n-time-to-stop)
      (println "7 - Time to rest/go home" n-time)
      (run-heroes heroes-7-is-time-to-rest browser-id 1))))

(defn heroes8-start [cycle]
  (let [browser-id (nth browser-ids 7)
        hero-id 8
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-8-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-8-iteration-1 browser-id hero-id))))

(defn heroes8-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 7)]
    (when (= n-time n-time-to-stop)
      (println "8 - Time to rest/go home" n-time)
      (run-heroes heroes-8-is-time-to-rest browser-id 1))))

(defn heroes9-start [cycle]
  (let [browser-id (nth browser-ids 8)
        hero-id 9
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-9-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-9-iteration-1 browser-id hero-id))))

(defn heroes9-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 8)]
    (when (= n-time n-time-to-stop)
      (println "9 - Time to rest/go home" n-time)
      (run-heroes heroes-9-is-time-to-rest browser-id 1))))

(defn heroes10-start [cycle]
  (let [browser-id (nth browser-ids 9)
        hero-id 10
        total-iterations 2
        iteration (cycle->iteration cycle total-iterations)]
    (cond
      (= iteration 0) (run-heroes heroes-10-start browser-id hero-id)
      (= iteration 1) (run-heroes heroes-10-iteration-1 browser-id hero-id))))

(defn heroes10-is-time-to-rest? [n-time]
  (let [browser-id (nth browser-ids 9)]
    (when (= n-time n-time-to-stop)
      (println "10 - Time to rest/go home" n-time)
      (run-heroes heroes-10-is-time-to-rest browser-id 1))))

(defn all-heroes-go-menu-treasure-hunt []
  (println "All heroes need to go to the menu and go back to the treasure hunt.")
  (doseq [browser-id browser-ids]
    (println "==>Executing for the browser with id:" browser-id)
    (screen/go-main-menu browser-id)
    (screen/go-treasure-hunt browser-id)))

(defn start-all-heroes [cycle]
  (heroes1-start cycle)
  (heroes2-start cycle)
  (heroes3-start cycle)
  (heroes4-start cycle)
  (heroes5-start cycle)
  (heroes6-start cycle)
  (heroes7-start cycle)
  (heroes8-start cycle)
  (heroes9-start cycle)
  (heroes10-start cycle))

(defn stop-all-heroes []
  (doseq [browser-id browser-ids]
    (screen/open-heroes-popup browser-id)
    (screen/rest-all-heroes browser-id)
    (screen/close-heroes-popup browser-id)))

(defn is-time-to-rest? [n-time]
  (heroes1-is-time-to-rest? n-time)
  (heroes2-is-time-to-rest? n-time)
  (heroes3-is-time-to-rest? n-time)
  (heroes4-is-time-to-rest? n-time)
  (heroes5-is-time-to-rest? n-time)
  (heroes6-is-time-to-rest? n-time)
  (heroes7-is-time-to-rest? n-time)
  (heroes8-is-time-to-rest? n-time)
  (heroes9-is-time-to-rest? n-time)
  (heroes10-is-time-to-rest? n-time))

