(ns tc-recommended.styles
  (:require [garden.core :refer [css]]
            [garden.stylesheet :refer [at-media]]
            [garden.units :refer [px]]
            [garden.color :refer [hsl rgb]]))

(def target-css-name "resources/public/styles.css")

;; Sizes
(def global-desktop-width "948px")
(def border-radius ".5rem")

;; Colors
(def accented-bg-color (hsl 220 17 32))
(def selected-color (hsl 213 32 52))
(def accented-color-darker (hsl 179 25 65))
(def accented-color-brighter (hsl 193 43 67))
(def main-bg-color (hsl 220 16 22))
(def main-color (hsl 218 27 94))

;; Mixins
(defn breakpoint
  [content]
  (at-media {:min-width "961px"} content))


(def resets
  [:html :body {:font-size "18px"
                :margin "0"
                :padding "0"
                :width "100%"}])

(def content
  [[:.content {:padding ".4rem 1rem 1rem 1rem"}

   [:div {:margin-top ".8rem"}]
   [:.list-el-composer {:font-size     "1.1rem"
                        :font-weight   "300"
                        :padding-right ".4rem"}]
   [:.list-el-position {:font-size     "1.1rem"
                        :padding-right ".4rem"}]
   [:.list-el-rate {:color     "white"
                    :font-size ".9rem"}]
   [:.list-el-work {:color     accented-color-brighter
                    :font-size ".9rem"}]]
   (breakpoint [:.content {:flex    "1"
                           :padding ".4rem 2rem 1rem 2rem"
                           :width   "initial"}])])

(def footer
  [[:footer {:align-items     "center"
            :display         "flex"
            :justify-content "space-between"
            :margin          "0 auto"
            :padding         ".5rem 0"
            :width           "100%"}
   [:.author-ref {:margin-left "1rem"
                  :font-size   ".9rem"}]
   [:.icons {:align-items "center"
             :display     "flex"}
    [:div {:margin-right "1rem"}
     [:a {:display "block"}
      [:svg {:display "block"}]]]]]
   (breakpoint [:footer {:margin "0 auto 2rem auto"
                         :width  global-desktop-width}])])

(def header
  [[:header {:margin     "0 auto 1rem auto"
            :text-align "center"
            :width      "100%"}
   [:.title {:font-family "'Raleway', sans-serif"
             :font-size   "1.8rem"
             :padding     "1rem 0 .4rem 0"}]
   [:.subtitle {:padding-bottom "1rem"}]]
   (breakpoint [:header {:margin "1rem auto"
                         :width  global-desktop-width}])])

(def aside
  [[:aside {:margin-bottom  "1rem"
           :padding-bottom ".8rem"
           :text-align     "center"}
   [:.menu {:position "static"}
    [:div {:margin  ".8rem .5rem 0 .5rem"
           :display "inline-block"}]
    (breakpoint [:div {:display "block"}])]
    (breakpoint [:.menu {:position "sticky"
                         :top      "1rem"}])]
   (breakpoint [:aside {:margin-bottom "0"
                        :margin-right  "1rem"
                        :padding       "0 1rem .8rem 1rem"
                        :text-align    "left"}])])

(def body
  [:body {:background-color main-bg-color
          :color            main-color
          :font-family      "'Roboto', sans-serif"}])

(def link
  [:a {:color           accented-color-darker
       :text-decoration "none"}
   [:&:hover {:color selected-color}]])

(def headers
  [:h1 :h2 {:font-family "'Raleway', sans-serif"}])

(def h1
  [[:h1 {:font-size  "1.3rem"
        :text-align "center"}]
   (breakpoint [:h1 {:text-align "left"}])])

(def h2
  [[:h2 {:font-size     "1.1rem"
        :margin-top    "1.4rem"
        :margin-bottom "0.5rem"
        :text-align    "center"}]
   (breakpoint [:h2 {:text-align "left"}])])

(def svg
  [:svg {:fill "white"}
   [:&:hover {:fill selected-color}]])

(def backgrounds
  [:aside :.content :header :footer {:background-color accented-bg-color
                                     :border-radius    border-radius}])

(def wrapper
  [[:.wrapper {:align-items    "stretch"
              :display        "flex"
              :flex-direction "column"
              :margin         "0 auto 1rem auto"
              :width          "100%"}]
   (breakpoint [:.wrapper {:flex-direction "row"
                           :width          global-desktop-width}])])

(defn compile-styles
  "Compiles all styles using garden into a single css file"
  []
  (css {:output-to target-css-name} (conj []
                                          resets
                                          content
                                          header
                                          footer
                                          aside
                                          body
                                          link
                                          headers
                                          h1
                                          h2
                                          svg
                                          backgrounds
                                          wrapper)))