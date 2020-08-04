(ns tc-recommended.core
  (:require [mount.core :as mount]
            [tc-recommended.db])
  (:gen-class))

(defn- start-server
  []
  (mount/start))

(defn -main
  [& args]
  (start-server))
