(ns tc-recommended.handlers)

(def http-header {"Content-Type" "text/html; charset=UTF-8"})

(defn good-html
  [html]
  {:status 200
   :headers http-header
   :body html})

(defn top-composers
  [_]
  (good-html "foo"))

(defn works-by-genre
  [_]
  (good-html "foo"))

(defn composer
  [_]
  (good-html "foo"))

(defn credits
  [_]
  (good-html "foo"))
