;; Requests handlers
(ns tc-recommended.handlers
  (:require [tc-recommended.templates.pages :as pages]
            [tc-recommended.db :refer [composers-rating all-works all-work-types all-composers]]
            [clojure.edn :as edn]
            [tc-recommended.helpers :as helpers]))

;; html headers
(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn- good-html
  "Renders html with 200 status"
  [html]
  {:status  200
   :headers http-header
   :body    html})

(defn top-composers
  "List of composers sorted by contribution to genres"
  [_]
  (good-html (pages/best-composers composers-rating)))

(defn works-by-genre
  "All works filter by a genre from url genre slug"
  [{{slug :slug} :path-params}]
  (let [genre (->> all-work-types
                   (filter #(= (:slug %) slug))
                   (first))
        works (filter #(= (:work_type_id %) (:id genre)) all-works)]
    (good-html (pages/works-by-genre works (:name genre)))))

(defn composer
  "All works by a composer from url"
  [{{composer-id-as-str :composer-id} :path-params}]
  (let [composer-id (edn/read-string composer-id-as-str)
        composer (first (filter #(= (:id %) composer-id) all-composers))
        works (filter #(= (:composer_id %) composer-id) all-works)
        works-grouped-by-genre-name (helpers/group-by-genre-name works)]
    (good-html (pages/works-by-composer works-grouped-by-genre-name composer))))

(defn credits
  "Static credits page"
  [_]
  (good-html (pages/credits)))
