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

;; Make requests to database and cache results
;; When the app starts, we makes the queries and then just use the cache
(defstate composers-rating :start (jdbc/query db-conn req/composers-rating))
(defstate all-work-types :start (jdbc/query db-conn req/all-work-types))
(defstate all-works :start (jdbc/query db-conn req/all-works))
(defstate all-composers :start (jdbc/query db-conn req/all-composers))