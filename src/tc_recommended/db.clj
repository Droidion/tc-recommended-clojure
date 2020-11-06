(ns tc-recommended.db
  (:require [hikari-cp.core :as cp]
            [honeysql.core :as sql]
            [clojure.java.jdbc :as jdbc]
            [mount.core :refer [defstate]]))

;; Connection string
(def datasource-options {:jdbc-url "jdbc:sqlite:resources/tc-recommended.sqlite"})

;; HikariCP connection pool in state
(defonce datasource (delay (cp/make-datasource datasource-options)))
(defstate db-conn :start {:datasource @datasource})

;; Shortcut for DB query
(defn- make-query [query] (jdbc/query db-conn (sql/format query)))

;; SQL Requests
(defn get-all-work-types
  []
  (make-query {:select [:id :name :slug]
               :from   [:work_type]}))

(defn get-all-composers
  []
  (make-query {:select [:id :name]
               :from   [:composers]}))

(defn get-all-works
  []
  (make-query {:select   [[:c.id :composer_id]
                          [:c.name :composer_name]
                          [:w.name :work_name]
                          [:wt.id :work_type_id]
                          [:wt.name :work_type_name]
                          :position]
               :from     [[:works :w]]
               :join     [[:composers :c] [:= :w.composer_id :c.id] [:work_type :wt] [:= :w.type_id :wt.id]]
               :order-by [:w.type_id :w.position]}))

(defn get-composers-rating
  []
  (jdbc/query db-conn "SELECT c.id,
                              c.name,
                              sum((select count(*) from works where w.type_id = type_id) - w.position + 1) rating
                       FROM composers c
                       JOIN works w ON w.composer_id = c.id
                       GROUP BY c.name
                       ORDER BY rating DESC"))

(defn get-work-type-name-by-id
  [work-types id]
  (:name (first (filter #(= (:id %) id) work-types))))

;; Cache
(defstate composers-rating :start (get-composers-rating))
(defstate all-work-types :start (get-all-work-types))
(defstate all-works :start (get-all-works))
(defstate all-composers :start (get-all-composers))