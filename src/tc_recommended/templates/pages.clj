(ns tc-recommended.templates.pages
  (:require [hiccup.core :refer [html]]
            [tc-recommended.templates.partials :refer [application get-menu]]))

(def site-title " | Best Works of Classical Music")

(defn content-wrapper
  [content title]
  (html [:aside [:div.menu (get-menu title)]]
        [:div.content content]))

(def title-best-composer "Best Composers")
(defn best-composers [composers]
  (application (str title-best-composer site-title)
               "List of best composers calculated from votes by members of talkclassical.com forum"
               (content-wrapper (html [:h1 title-best-composer]
                     (for [composer composers]
                       [:div
                        [:span.list-el-composer [:a {:href (str "/composer/" (:id composer))} (:name composer)]]
                        [:span.list-el-rate (:rating composer)]])) site-title)))

(def title-credits "Credits")
(defn credits []
  (application (str title-credits site-title)
               "Best works of classical music as voted by members of talkclassical.com forum"
               (content-wrapper (html [:h1 title-credits]
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
                      ". Love classical music. Peace."]) title-credits)))



(def title-works-by-genre "Works by genre")
(defn works-by-genre [works genre-name]
  (application (str title-works-by-genre site-title)
               "Best works by different composers as voted by members of talkclassical.com forum"
               (content-wrapper (html [:h1 (str "Best " genre-name)]
                     (for [work works]
                       [:div
                        [:span.list-el-position (:position work)]
                        [:span.list-el-composer
                         [:a {:href (str "/composer/" (:composer_id work))} (:composer_name work)]]
                        [:span.list-el-work (:work_name work)]])) genre-name)))