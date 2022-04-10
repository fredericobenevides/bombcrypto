#!/usr/bin/env bb

(require '[clojure.test :as t]
         '[babashka.classpath :as cp])

(cp/add-classpath "src:test")                        

(require 'bombcrypto.accounts-test)                  

(def test-results
  (t/run-tests 'bombcrypto.accounts-test))           

(def failures-and-errors
  (let [{:keys [:fail :error]} test-results]
    (+ fail error)))                                 

(System/exit failures-and-errors)
