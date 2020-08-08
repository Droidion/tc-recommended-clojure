(ns tc-recommended.templates.pages
  (:require [hiccup.core :refer [html]]
            [tc-recommended.templates.partials :refer [application]]))

(def site-title " | Best Works of Classical Music")

(def title-best-composer "Best Composers")
(defn best-composers [composers]
  (application (str title-best-composer site-title)
               "List of best composers calculated from votes by members of talkclassical.com forum"
               (html [:h1 title-best-composer]
                     (for [composer composers]
                       [:div
                        [:span.list-el-composer [:a {:href (str "/composer/" (:id composer))} (:name composer)]]
                        [:span.list-el-rate (:rating composer)]]))))

(def title-credits "Credits")
(defn credits []
  (application (str title-credits site-title)
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
                      [:a {:href "https://github.com/Droidion/tc-recommended-rust"} "source code"]
                      ". Love classical music. Peace."])))


