;; Database operations
(ns tc-recommended.db
  (:require [hikari-cp.core :as cp]
            [clojure.java.jdbc :as jdbc]
            [mount.core :refer [defstate]]
            [tc-recommended.sql :as req]))

;; Connection string
(def datasource-options {:jdbc-url "jdbc:sqlite:resources/tc-recommended.sqlite"})

;; HikariCP connection pool in state
(defonce datasource (delay (cp/make-datasource datasource-options)))
(defstate db-conn :start {:datasource @datasource})

(defn get-composers-rating "Returns composers rating from DB" [] (jdbc/query db-conn req/composers-rating))
(defn get-all-work-types "Returns genres from DB" [] (jdbc/query db-conn req/all-work-types))
(defn get-all-works "Returns all works from DB" [] (jdbc/query db-conn req/all-works))
(defn get-all-composers "Returns all composers from DB" [] (jdbc/query db-conn req/all-composers))

;; Make requests to database and cache results
;; When the app starts, we makes the queries and then just use the cache
(defstate composers-rating :start (get-composers-rating))
(defstate all-work-types :start (get-all-work-types))
(defstate all-works :start (get-all-works))
(defstate all-composers :start (get-all-composers))