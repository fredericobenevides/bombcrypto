(ns bombcrypto.heroes
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.screen :as screen]))

(def n-time-to-stop 6)

(defn- cycle->start-index [cycle total-start]
  (mod cycle total-start))

(def account-1
  {:id 1
   :start [[:open-heroes
            :run-all
            :close-heroes]
           [:open-heroes
            :run-all
            :next-page
            :next-page
            {:go-rest 4}
            :close-heroes]]
   :stop  [:open-heroes
           :rest-all
           :close-heroes]})

(def account-2
  {:id 2
   :start [[:open-heroes
            :run-all
            :close-heroes]
           [:open-heroes
            :run-all
            {:go-home 1}
            {:go-home 2}
            :next-page
            :next-page
            {:go-home 1}
            {:go-home 1}
            :close-heroes]]
   :stop  [:open-heroes
           :run-all
           :rest-all
           {:go-home 1}
           {:go-home 1}
           :next-page
           :next-page
           {:go-home 1}
           {:go-home 2}
           :close-heroes]})

(def account-3
  {:id 3
   :start [[:open-heroes
            :run-all
            :close-heroes]
           [:open-heroes
            :run-all
            :next-page
            {:go-rest 2}
            {:go-rest 5}
            :close-heroes]]
   :stop  [:open-heroes
           :rest-all
           :close-heroes]})

(def account-4
  {:id 4
   :start [[:open-heroes
            :run-all
            :close-heroes]
           [:open-heroes
            :run-all
            {:go-rest 3}
            {:go-rest 4}
            :next-page
            {:go-rest 1}
            {:go-rest 2}
            {:go-rest 2}
            {:go-rest 5}
            :close-heroes]]
   :stop  [:open-heroes
           :rest-all
           :close-heroes]})

(def account-5
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
           :close-heroes]})

(def account-6
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
           :close-heroes]})

(def account-7
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
           :close-heroes]})

(def account-8
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
           :close-heroes]})

(def account-9
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
           :close-heroes]})

(def account-10
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
           :close-heroes]})

(defn run-account [steps browser-id account-id]
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

(defn heroes-is-time-to-stop? [{account-id :id stop :stop} n-time]
  (let [browser-id (browser/get-browser-id account-id)]
    (when (= n-time n-time-to-stop)
      (println account-id "- Time to rest/go home" n-time)
      (run-account stop browser-id account-id))))

(defn heroes1-start [cycle]
  (let [browser-id (browser/get-browser-id 1)
        hero-id 1
        total-start (count (:start account-1))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-1) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-1) 1) browser-id hero-id))))

(defn heroes2-start [cycle]
  (let [browser-id (browser/get-browser-id 2)
        hero-id 2
        total-start (count (:start account-2))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-2) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-2) 1) browser-id hero-id))))

(defn heroes3-start [cycle]
  (let [browser-id (browser/get-browser-id 3)
        hero-id 3
        total-start (count (:start account-3))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-3) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-3) 1) browser-id hero-id))))

(defn heroes4-start [cycle]
  (let [browser-id (browser/get-browser-id 4)
        hero-id 4
        total-start (count (:start account-4))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-4) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-4) 1) browser-id hero-id))))

(defn heroes5-start [cycle]
  (let [browser-id (browser/get-browser-id 5)
        hero-id 5
        total-start (count (:start account-5))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-5) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-5) 1) browser-id hero-id))))

(defn heroes6-start [cycle]
  (let [browser-id (browser/get-browser-id 6)
        hero-id 6
        total-start (count (:start account-6))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-6) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-6) 1) browser-id hero-id))))

(defn heroes7-start [cycle]
  (let [browser-id (browser/get-browser-id 7)
        hero-id 7
        total-start (count (:start account-7))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-7) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-7) 1) browser-id hero-id))))

(defn heroes8-start [cycle]
  (let [browser-id (browser/get-browser-id 8)
        hero-id 8
        total-start (count (:start account-8))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-8) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-8) 1) browser-id hero-id))))

(defn heroes9-start [cycle]
  (let [browser-id (browser/get-browser-id 9)
        hero-id 9
        total-start (count (:start account-9))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-9) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-9) 1) browser-id hero-id))))

(defn heroes10-start [cycle]
  (let [browser-id (browser/get-browser-id 10)
        hero-id 10
        total-start (count (:start account-10))
        start-index (cycle->start-index cycle total-start)]
    (cond
      (= start-index 0) (run-account (get (:start account-10) 0) browser-id hero-id)
      (= start-index 1) (run-account (get (:start account-10) 1) browser-id hero-id))))

(defn all-heroes-go-menu-treasure-hunt []
  (println "All heroes need to go to the menu and go back to the treasure hunt.")
  (doseq [browser-id (browser/load-all-browser-ids)]
    (println "==>Executing for the browser with id:" browser-id)
    (screen/go-main-menu browser-id)
    (screen/go-treasure-hunt browser-id)))

(defn all-heroes-open-close-chest []
  (println "Open and close chest for all heroes")
  (doseq [browser-id (browser/load-all-browser-ids)]
    (println "==>Executing for the browser with id:" browser-id)
    (screen/open-chest browser-id)
    (screen/close-chest browser-id)))

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
  (doseq [browser-id (browser/load-all-browser-ids)]
    (screen/open-heroes-popup browser-id)
    (screen/rest-all-heroes browser-id)
    (screen/close-heroes-popup browser-id)))

(defn is-time-to-stop? [n-time]
  (heroes-is-time-to-stop? account-1 n-time)
  (heroes-is-time-to-stop? account-2 n-time)
  (heroes-is-time-to-stop? account-3 n-time)
  (heroes-is-time-to-stop? account-4 n-time)
  (heroes-is-time-to-stop? account-5 n-time)
  (heroes-is-time-to-stop? account-6 n-time)
  (heroes-is-time-to-stop? account-7 n-time)
  (heroes-is-time-to-stop? account-8 n-time)
  (heroes-is-time-to-stop? account-9 n-time)
  (heroes-is-time-to-stop? account-10 n-time))

