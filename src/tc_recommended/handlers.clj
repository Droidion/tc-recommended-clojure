(ns tc-recommended.handlers
  (:require [tc-recommended.templates.pages :as pages]
            [tc-recommended.db :refer [composers-rating all-works all-work-types all-composers]]))

(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn good-html
  [html]
  {:status  200
   :headers http-header
   :body    html})

(defn top-composers
  [_]
  (good-html (pages/best-composers composers-rating)))

(defn group-by-genre-name [works]
  (reduce (fn [acc el] (assoc acc (:work_type_name el) (conj (get acc (:work_type_name el)) el))) {} works))

(defn works-by-genre
  [{path-params :path-params}]
  (let [slug (:slug path-params)
        genre (first (filter #(= (:slug %) slug) all-work-types))
        works (filter #(= (:work_type_id %) (:id genre)) all-works)]
    (good-html (pages/works-by-genre works (:name genre)))))

(defn composer
  [{path-params :path-params}]
  (let [composer-id (Integer/parseInt (get path-params :composer-id))
        composer (first (filter #(= (:id %) composer-id) all-composers))
        works (filter #(= (:composer_id %) composer-id) all-works)
        works-grouped-by-genre-name (group-by-genre-name works)]
    (println works-grouped-by-genre-name)
    (good-html (pages/works-by-composer works-grouped-by-genre-name composer))))

(defn credits
  [_]
  (good-html (pages/credits)))
