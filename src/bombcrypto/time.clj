(ns bombcrypto.time)

(defn current-time []
  (let [time-format (java.time.format.DateTimeFormatter/ofPattern "dd/MM/yyyy hh:mm:ss")]
    (.format time-format (java.time.LocalDateTime/now))))
