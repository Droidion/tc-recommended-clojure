(ns tc-recommended.handlers
  (:require [tc-recommended.templates.pages :as pages]
            [tc-recommended.db :refer [composers-rating]]))

(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn good-html
  [html]
  {:status  200
   :headers http-header
   :body    html})

(defn top-composers
  [_]
  (good-html (pages/best-composers composers-rating)))

(defn works-by-genre
  [{path-params :path-params}]
  (let [genre-id (get path-params :work-genre-id)])
  (good-html (str "works by genre id " (get path-params :work-genre-id))))

(defn composer
  [{path-params :path-params}]
  (good-html (str "works by composer id " (get path-params :composer-id))))

(defn credits
  [_]
  (good-html (pages/credits)))
