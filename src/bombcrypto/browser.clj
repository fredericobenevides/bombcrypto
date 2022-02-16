(ns bombcrypto.browser
  (:require [clojure.java.shell :as shell]
            [clojure.string :as string]))

(defn load-ids []
  (sort (string/split-lines (:out (shell/sh "sh" "-c" "xdotool search -name 'Bombcrypto - Google Chrome'")))))

