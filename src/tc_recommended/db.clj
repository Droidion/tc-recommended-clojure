(ns tc-recommended.db
  (:require [hikari-cp.core :as cp]
            [honeysql.core :as sql]
            [clojure.java.jdbc :as jdbc]
            [mount.core :refer [defstate]]))

(def datasource-options {:jdbc-url "jdbc:sqlite:resources/tc-recommended.sqlite"})

;; Create HikariCP connection pool
(defonce datasource
         (delay (cp/make-datasource datasource-options)))

(defn- get-conn
  "Returns db connection as connection pool"
  []
  {:datasource @datasource})

;; db connection in state
(defstate db-conn :start (get-conn))

(defn make-query
  [query]
  (jdbc/query db-conn (sql/format query)))

(defn all-composers
  []
  (make-query {:select [:id :name]
               :from   [:composers]}))

(defn all-work-types
  []
  (make-query {:select [:id :name]
               :from   [:work_type]}))

(defn works-by-type
  [type-id]
  (make-query {:select   [[:c.id :composer_id] [:c.name :composer_name] [:w.name :work_name] :w.position]
               :from     [[:works :w]]
               :join     [[:composers :c] [:= :w.composer_id :c.id]]
               :where    [:= :w.type_id type-id]
               :order-by [:w.position]}))

(defn works-by-composer
  [composer-id]
  (make-query {:select   [[:c.id :composer_id] [:c.name :composer_name] [:w.name :work_name] :position]
               :from     [[:works :w]]
               :join     [[:composers :c] [:= :w.composer_id :c.id]]
               :where    [:= :w.composer_id composer-id]
               :order-by [:w.type_id :w.position]}))

(defn composers-rating
  []
  (jdbc/query db-conn "SELECT c.id,
                              c.name,
                              sum((select count(*) from works where w.type_id = type_id) - w.position + 1) rating
                       FROM composers c
                       JOIN works w ON w.composer_id = c.id
                       GROUP BY c.name
                       ORDER BY rating DESC"))
