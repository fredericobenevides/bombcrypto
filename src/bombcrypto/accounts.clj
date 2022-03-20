(ns bombcrypto.accounts
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.screen :as screen]))

(def n-time-to-stop 6)

(defn- cycle->start-index [cycle total-start]
  (mod cycle total-start))

(def accounts-config
  [{:id 1
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             {:go-rest 5}
             :next-page
             {:go-rest 4}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 2
    :start [[:open-heroes
             :run-all
             {:go-home 3} ;; 539
             :next-page
             {:go-home 2} ;; 254
             {:go-home 5} ;; 384
             :next-page
             {:go-home 2} ;; 662
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-home 1} ;; 564
             {:go-home 1} ;; 434
             {:go-rest 2} ;; 411
             {:go-home 3} ;; 694
             {:go-rest 3} ;; 253
             {:go-rest 5} ;; 115
             {:go-home 5} ;; 550
             :next-page
             {:go-rest 3} ;;  710
             :close-heroes]]
    :stop  [:open-heroes
            :run-all
            :rest-all
            {:go-home 1} ;; 564
            {:go-home 1} ;; 434
            {:go-home 4} ;; 694
            :next-page
            {:go-home 3} ;; 550
            :close-heroes]}
   {:id 3
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-rest 5}
             :next-page
             {:go-rest 2}
             :next-page
             {:go-rest 3}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 4
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-rest 2}
             {:go-rest 2}
             {:go-rest 3}
             {:go-rest 4}
             {:go-rest 4}
             :next-page
             {:go-rest 1}
             {:go-rest 4}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 5
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             {:go-rest 4}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 6
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-rest 1}
             {:go-rest 4}
             :next-page
             :next-page
             {:go-rest 2}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 7
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             {:go-rest 5}
             :next-page
             {:go-rest 4}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}

   {:id 8
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             :next-page
             {:go-rest 5}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 9
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             {:go-rest 5}
             :next-page
             {:go-rest 4}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}
   {:id 10
    :start [[:open-heroes
             :run-all
             :close-heroes]
            [:open-heroes
             :run-all
             :next-page
             :next-page
             {:go-rest 5}
             :close-heroes]]
    :stop  [:open-heroes
            :rest-all
            :close-heroes]}])

(defn- run-steps [steps browser-id account-id]
  (println account-id " - running heroes on the browser with id:" browser-id)
  (doseq [s steps]
    (println "==> Executing:" s)
    (if (map? s)
      (let [key (first (keys s))]
        (cond
          (= key :go-home) (screen/hero-go-home browser-id (:go-home s))
          (= key :go-rest) (screen/hero-go-rest browser-id (:go-rest s))))
      (cond
        (= s :open-heroes) (screen/open-heroes-popup browser-id)
        (= s :close-heroes) (screen/close-heroes-popup browser-id)
        (= s :run-all) (screen/run-all-heroes browser-id)
        (= s :rest-all) (screen/rest-all-heroes browser-id)
        (= s :next-page) (do (screen/move-to-hero browser-id 1)
                             (screen/next-page-of-heroes))))))

(defn- account1-start [cycle]
  (let [browser-id (browser/get-browser-id 1)
        hero-id 1
        total-start (count (:start (accounts-config 0)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 0)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 0)) 1) browser-id hero-id))))

(defn- account2-start [cycle]
  (let [browser-id (browser/get-browser-id 2)
        hero-id 2
        total-start (count (:start (accounts-config 1)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 1)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 1)) 1) browser-id hero-id))))

(defn- account3-start [cycle]
  (let [browser-id (browser/get-browser-id 3)
        hero-id 3
        total-start (count (:start (accounts-config 2)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 2)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 2)) 1) browser-id hero-id))))

(defn- account4-start [cycle]
  (let [browser-id (browser/get-browser-id 4)
        hero-id 4
        total-start (count (:start (accounts-config 3)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 3)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 3)) 1) browser-id hero-id))))

(defn- account5-start [cycle]
  (let [browser-id (browser/get-browser-id 5)
        hero-id 5
        total-start (count (:start (accounts-config 4)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 4)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 4)) 1) browser-id hero-id))))

(defn- account6-start [cycle]
  (let [browser-id (browser/get-browser-id 6)
        hero-id 6
        total-start (count (:start (accounts-config 5)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 5)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 5)) 1) browser-id hero-id))))

(defn- account7-start [cycle]
  (let [browser-id (browser/get-browser-id 7)
        hero-id 7
        total-start (count (:start (accounts-config 6)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 6)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 6)) 1) browser-id hero-id))))

(defn- account8-start [cycle]
  (let [browser-id (browser/get-browser-id 8)
        hero-id 8
        total-start (count (:start (accounts-config 7)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 7)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 7)) 1) browser-id hero-id))))

(defn- account9-start [cycle]
  (let [browser-id (browser/get-browser-id 9)
        hero-id 9
        total-start (count (:start (accounts-config 8)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 8)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 8)) 1) browser-id hero-id))))

(defn- account10-start [cycle]
  (let [browser-id (browser/get-browser-id 10)
        hero-id 10
        total-start (count (:start (accounts-config 9)))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-steps (get (:start (accounts-config 9)) 0) browser-id hero-id)
      (= start-index 1) (run-steps (get (:start (accounts-config 9)) 1) browser-id hero-id))))

(defn all-accounts-go-menu-treasure-hunt []
  (println "All heroes need to go to the menu and go back to the treasure hunt.")
  (doseq [browser-id (browser/load-all-browser-ids)]
    (println "==>Executing for the browser with id:" browser-id)
    (screen/go-main-menu browser-id)
    (screen/go-treasure-hunt browser-id)))

(defn all-accounts-open-close-chest []
  (println "Open and close chest for all heroes")
  (doseq [browser-id (browser/load-all-browser-ids)]
    (println "==>Executing for the browser with id:" browser-id)
    (screen/open-chest browser-id)
    (screen/close-chest browser-id)))

(defn start-all-accounts [cycle]
  (account1-start cycle)
  (account2-start cycle)
  (account3-start cycle)
  (account4-start cycle)
  (account5-start cycle)
  (account6-start cycle)
  (account7-start cycle)
  (account8-start cycle)
  (account9-start cycle)
  (account10-start cycle))

(defn stop-all-accounts []
  (doseq [browser-id (browser/load-all-browser-ids)]
    (screen/open-heroes-popup browser-id)
    (screen/rest-all-heroes browser-id)
    (screen/close-heroes-popup browser-id)))

(defn is-time-to-stop? [n-time]
  (doseq [ac accounts-config]
    (let [account-id (:id ac)
          browser-id (browser/get-browser-id account-id)
          stop-step (:stop ac)]
      (when (= n-time n-time-to-stop)
        (println account-id "- Time to rest/go home" n-time)
        (run-steps stop-step browser-id account-id)))))

