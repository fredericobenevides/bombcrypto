(ns bombcrypto.accounts
  (:require [bombcrypto.browser :as browser]
            [bombcrypto.screen :as screen]))

(def accounts-config
  [{:id 1
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    {:go-rest 5}
                    :next-page
                    {:go-rest 4}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 2
    :steps [{:desc "Step 1"
             :time 20
             :step [:open-heroes
                    :run-all
                    {:go-home 5} ;; 249
                    :next-page
                    {:go-home 2} ;; 254
                    {:go-home 2} ;; 256
                    {:go-home 2} ;; 115
                    {:go-home 4} ;; 305
                    {:go-home 4} ;; 662
                    {:go-home 4} ;; 710 
                    :close-heroes]}
            {:desc "Step 2"
             :time 20
             :step [:open-heroes
                    :run-all
                    {:go-home 1} ;; 564
                    {:go-home 1} ;; 434
                    {:go-home 1} ;; 539 *
                    {:go-home 1} ;; 411 *
                    {:go-home 2} ;; 694
                    {:go-home 2} ;; 253 *
                    {:go-home 5} ;; 550
                    {:go-home 5} ;; 384 *
                    :close-heroes]}
            {:desc "Step 3"
             :time 20
             :step [:open-heroes
                    :run-all
                    {:go-home 1} ;; 564
                    {:go-home 1} ;; 434
                    {:go-home 3} ;; 249
                    {:go-home 3} ;; 694
                    {:go-home 4} ;; 254
                    {:go-home 5} ;; 115
                    {:go-home 5} ;; 550
                    {:go-home 5} ;; 384 
                    :close-heroes]}
            {:desc "Step 4 - skip"
             :time 20
             :step [:skip]}]}
   {:id 3
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    {:go-rest 5}
                    :next-page
                    {:go-rest 2}
                    :next-page
                    {:go-rest 3}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 4
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    {:go-rest 2}
                    {:go-rest 2}
                    {:go-rest 3}
                    {:go-rest 4}
                    {:go-rest 4}
                    :next-page
                    {:go-rest 1}
                    {:go-rest 4}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 5
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    {:go-rest 4}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 6
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    {:go-rest 1}
                    {:go-rest 3}
                    :next-page
                    {:go-rest 2}
                    :next-page
                    {:go-rest 2}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 7
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    {:go-rest 5}
                    :next-page
                    {:go-rest 4}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 8
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    :next-page
                    {:go-rest 5}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 9
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    {:go-rest 5}
                    :next-page
                    {:go-rest 4}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}
   {:id 10
    :steps [{:desc "Step 1"
             :time 60
             :step [:open-heroes
                    :run-all
                    :close-heroes]}
            {:desc "Rest 1"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}
            {:desc "Step 2"
             :time 60
             :step [:open-heroes
                    :run-all
                    :next-page
                    :next-page
                    {:go-rest 5}
                    :close-heroes]}
            {:desc "Rest 2"
             :time 30
             :step [:open-heroes
                    :rest-all
                    :close-heroes]}]}])

(defn get-step [account-id step-index]
  (let [ac (accounts-config (dec account-id))
        steps (:steps ac)]
    (try
      (steps step-index)
      (catch IndexOutOfBoundsException))))

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

(defn start-account [account-id step-index]
  (let [browser-id (browser/get-browser-id account-id)
        account-index (dec account-id)
        account (accounts-config account-index)
        steps (:steps account)
        step-info (steps step-index)
        step (:step step-info)]
    (run-steps step browser-id account-id)))

