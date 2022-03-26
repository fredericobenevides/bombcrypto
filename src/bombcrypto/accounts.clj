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
             {:go-home 5} ;; 249
             :next-page
             {:go-home 2} ;; 254
             {:go-home 2} ;; 256
             {:go-home 2} ;; 115
             {:go-home 4} ;; 305
             {:go-home 4} ;; 662
             {:go-home 4} ;; 710 
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-home 1} ;; 564
             {:go-home 1} ;; 434
             {:go-home 1} ;; 539 *
             {:go-home 1} ;; 411 *
             {:go-home 2} ;; 694
             {:go-home 2} ;; 253 *
             {:go-home 5} ;; 550
             {:go-home 5} ;; 384 *
             :close-heroes]
            [:open-heroes
             :run-all
             {:go-home 1} ;; 564
             {:go-home 1} ;; 434
             {:go-home 3} ;; 249
             {:go-home 3} ;; 694
             {:go-home 4} ;; 254
             {:go-home 5} ;; 115
             {:go-home 5} ;; 550
             {:go-home 5} ;; 384 
             :close-heroes]
            [:skip]]
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
             {:go-rest 3}
             :next-page
             {:go-rest 2}
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
                             (screen/next-page-of-heroes))
        :else :do-nothing))))

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
  (doseq [ac accounts-config]
    (let [account-id (:id ac)
          browser-id (browser/get-browser-id account-id)
          count-start (count (:start ac))
          start-index (cycle->start-index cycle count-start)
          start-step ((:start ac) start-index)]
      (when-not (= account-id 2)
        (run-steps start-step browser-id account-id)))))

(defn start-account-2 [cycle]
  (let [ac (accounts-config 1)
        account-id (:id ac)
        browser-id (browser/get-browser-id account-id)
        count-start (count (:start ac))
        start-index (cycle->start-index cycle count-start)
        start-step ((:start ac) start-index)]
    (println "Account 2 is running the start with index " start-index)
    (run-steps start-step browser-id account-id)))

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
      (when-not (= account-id 2)
        (when (= n-time n-time-to-stop)
          (println account-id "- Time to rest/go home" n-time)
          (run-steps stop-step browser-id account-id))))))

