(ns bombcrypto.browser
  (:require [clojure.java.shell :as shell]
            [clojure.string :as string]))

(defn load-all-browser-ids []
  (sort (string/split-lines (:out (shell/sh "sh" "-c" "xdotool search -name 'Bombcrypto - Google Chrome'")))))

(defn get-browser-id [browser-id]
  (nth (load-all-browser-ids) (dec browser-id)))
