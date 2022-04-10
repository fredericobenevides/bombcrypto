(ns bombcrypto.accounts-test
  (:require [clojure.test :refer [deftest testing is]]
            [bombcrypto.accounts :as accounts]
            [bombcrypto.time :as t]))

(deftest get-step
  (testing "should return the step for a valid step index"
    (let [account-id 1
          step (accounts/get-step account-id 0)
          expected-step ((:steps (accounts/accounts-config 0)) 0)]
      (is (= expected-step step))))
  (testing "should return nil for invalid index"
      (let [account-id 1
          step (accounts/get-step account-id -1)
          ]
      (is (= nil step))))
  )

