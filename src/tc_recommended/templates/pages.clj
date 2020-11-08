;; HTML pages templates
(ns tc-recommended.templates.pages
  (:require [hiccup.core :refer [html]]
            [tc-recommended.templates.partials :as part]))

(defn- page
  "Adds top-level html wrapper and context-aware menu"
  [page-title page-descr content]
  (part/application
    (str page-title part/site-title)
    page-descr
    (part/content-wrapper content page-title)))

;; Best composers page
(def title-best-composer "Top Composers")
(defn best-composers [composers]
  (page
    title-best-composer
    "List of best composers calculated from votes by members of talkclassical.com forum"
    (html [:h1 title-best-composer]
          (for [composer composers]
            [:div
             [:span.list-el-composer [:a {:href (str "/composer/" (:id composer))} (:name composer)]]
             [:span.list-el-rate (:rating composer)]]))))

;; Credits page
(def title-credits "Credits")
(defn credits []
  (page
    title-credits
    "Best works of classical music as voted by members of talkclassical.com forum"
    (html [:h1 title-credits]
          [:p
           "All content on this site is an intellectual property of various "
           [:a {:href "https://www.talkclassical.com/"} "talkclassical.com"]
           " forum members. This humble site's creator deeply appreciates their work."]
          [:p
           "The following link was used for acquiring the data: "
           [:a {:href "https://www.talkclassical.com/17996-compilation-tc-top-recommended.html"} "https://www.talkclassical.com/17996-compilation-tc-top-recommended.html"]
           "."]
          [:p
           "This site is written in "
           [:a {:href "https://clojure.org/"} "Clojure"]
           ". Use Clojure for web development. Clojure is great."]
          [:p
           [:a {:href "https://www.nordtheme.com"} "Nord"]
           " color scheme was used for design."]
          [:p
           "See the "
           [:a {:href "https://github.com/Droidion/tc-recommended-clojure"} "source code"]
           ". Love classical music. Peace."])))

;; Works of certain genre page
(def title-works-by-genre "Works by genre")
(defn works-by-genre [works genre-name]
  (page
    title-works-by-genre
    "Best works by different composers as voted by members of talkclassical.com forum"
    (html [:h1 (str "Best " genre-name)]
          (for [work works]
            [:div
             [:span.list-el-position (:position work)]
             [:span.list-el-composer
              [:a {:href (str "/composer/" (:composer_id work))} (:composer_name work)]]
             [:span.list-el-work (:work_name work)]]))))

;; Composer page
(def title-works-by-composer "Works by composer")
(defn works-by-composer [works composer]
  (page
    title-works-by-composer
    "Best works by different composers as voted by members of talkclassical.com forum"
    (html [:h1 (str "Best works by " (:name composer))]
          (for [type (sort (keys works))]
            [:div
             [:h2 type]
             (for [work (reverse (get works type))]
               [:div
                [:span.list-el-position (:position work)]
                [:span.list-el-work (:work_name work)]])
             ]
            ))))