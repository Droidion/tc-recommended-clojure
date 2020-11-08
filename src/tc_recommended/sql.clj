;; SQL Requests
(ns tc-recommended.sql
  (:require [honeysql.core :as sql]))

;; All genres lists
(def all-work-types (sql/format {:select [:id :name :slug]
                                 :from   [:work_type]}))

;; All composers list
(def all-composers (sql/format {:select [:id :name]
                                :from   [:composers]}))

;; All works list
(def all-works (sql/format {:select   [[:c.id :composer_id]
                                       [:c.name :composer_name]
                                       [:w.name :work_name]
                                       [:wt.id :work_type_id]
                                       [:wt.name :work_type_name]
                                       :position]
                            :from     [[:works :w]]
                            :join     [[:composers :c] [:= :w.composer_id :c.id] [:work_type :wt] [:= :w.type_id :wt.id]]
                            :order-by [:w.type_id :w.position]}))

;; Composers rated by their input in different genres
(def composers-rating "SELECT c.id,
                              c.name,
                              sum((select count(*) from works where w.type_id = type_id) - w.position + 1) rating
                       FROM composers c
                       JOIN works w ON w.composer_id = c.id
                       GROUP BY c.name
                       ORDER BY rating DESC")