(ns bombcrypto.time)

(defn format-time [time]
  (let [time-format (java.time.format.DateTimeFormatter/ofPattern "dd/MM/yyyy hh:mm:ss")]
    (.format time-format time)))

(defn now []
  (java.time.LocalDateTime/now))

(defn now-with-format []
  (format-time (now)))

(defn plus-minutes [time minutes]
  (.plusMinutes time minutes))

(defn is-gte? [t1 t2]
  (let [c (.compareTo t1 t2)]
    (cond
      (> c 0) true
      (= c -1) false)))

