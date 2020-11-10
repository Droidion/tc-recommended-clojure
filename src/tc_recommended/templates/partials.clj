;; Partial HTML blocks to be used in pages templates
(ns tc-recommended.templates.partials
  (:require [hiccup.page :refer [html5 include-css]]
            [hiccup.core :refer [html]]
            [tc-recommended.db :refer [all-work-types]]))

;; Clojure svg icon
(def clojure-icon [:svg {:height "28" :viewBox "0 0 24 23"}
                   [:title "Clojure Logo"]
                   [:path {:d "m12 .0469971c-6.19698 0-11.237976 5.0420029-11.237976 11.2390029s5.041996 11.239 11.237976 11.239c6.197 0 11.239-5.042 11.239-11.239s-5.042-11.2390029-11.239-11.2390029zm0 1.0820029c5.609 0 10.156 4.548 10.156 10.157 0 .439-.031.871-.085 1.295-.276 1.099-.774 1.832-1.368 2.333-.912.767-2.127 1.015-3.252 1.014-.3067.0007-.6132-.0164-.918-.051 1.0729-1.0597 1.7406-2.4617 1.8874-3.9625.1467-1.5008-.2369-3.00562-1.0842-4.25304-.8473-1.24741-2.1048-2.15864-3.554-2.57542-1.4492-.41677-2.9987-.31277-4.37918.29396-.10055-.06995-.20397-.13567-.31-.197-.445-.25-1.373-.616-2.554-.62-.98947-.00387-1.95912.27734-2.793.81.93898-1.31287 2.17775-2.38266 3.61335-3.12046s3.02653-1.1223 4.64063-1.12154zm-1.774 13.835c.165-.72.617-1.845 1.024-2.769.115-.262.227-.508.328-.726.637 2.268 1.04 3.617 1.762 4.537.108.137.228.259.353.374-.5459.1819-1.1175.2751-1.693.276-.6311-.0007-1.2573-.1121-1.85-.329-.0121-.1447-.0184-.2898-.019-.435-.0032-.3116.0287-.6225.095-.927zm-1.37398.667c-.68736-.4974-1.24709-1.1505-1.63331-1.906-.38623-.7554-.58797-1.5916-.58869-2.44.00067-.8612.20854-1.70949.60607-2.4734.39753-.7639.97304-1.42093 1.67793-1.9156.302.174.576.379.801.615.44198.453.93698 1.453 1.28098 2.314.094.232.178.454.251.656-1.239 2.52-2.06498 3.777-2.39498 5.15zm4.02398-3.224c-.277-.714-.438-1.252-.439-1.253l-.001-.002c-.507-1.943-1.032-3.719-2.122-4.962.5438-.18069 1.113-.27319 1.686-.274 1.4237.00185 2.7885.56821 3.7951 1.57488 1.0067 1.00667 1.5731 2.37147 1.5749 3.79512-.0006.8436-.2 1.6752-.582 2.4273-.3821.7522-.9361 1.4037-1.617 1.9017-.1376-.0359-.2723-.082-.403-.138-.21-.091-.519-.401-.799-.831-.427-.643-.819-1.525-1.094-2.238zm-.876 9.035c-5.60898 0-10.15598-4.547-10.15598-10.157 0-.369.021-.733.059-1.092.832-2.83 2.831-3.877 4.724-3.895.384-.001.76.05 1.12.137-1.17622 1.03311-1.93669 2.45864-2.1397 4.0109-.203 1.5523.1653 3.1255 1.03629 4.4263.87098 1.3009 2.18522 2.2407 3.69779 2.6443 1.5126.4036 3.1203.2435 4.5236-.4505.513.181 1.1.289 1.811.378.267.033.555.049.861.049.934-.0133 1.8632-.1372 2.768-.369-.9358 1.3341-2.1794 2.4231-3.6255 3.1745-1.446.7515-3.0518 1.1434-4.6815 1.1425z"}]])

;; Github svg icon
(def github-icon [:svg {:height "28" :viewBox "0 0 29 28"}
                  [:title "GitHub Logo"]
                  [:path {:fill-rule "evenodd" :clip-rule "evenodd" :d "M0 14.3547C0 20.6967 4.1543 26.0765 9.9162 27.9746C10.6417 28.1067 10.906 27.6635 10.906 27.2828C10.906 26.9418 10.8936 26.0394 10.8865 24.8419C6.85322 25.709 6.00224 22.9174 6.00224 22.9174C5.34264 21.2589 4.39197 20.8175 4.39197 20.8175C3.07545 19.9274 4.49167 19.9451 4.49167 19.9451C5.94705 20.0464 6.71258 21.4246 6.71258 21.4246C8.00595 23.6179 10.1067 22.9843 10.9327 22.6169C11.0645 21.6898 11.4392 21.0571 11.8532 20.6985C8.63351 20.3363 5.24829 19.1044 5.24829 13.604C5.24829 12.0372 5.81353 10.755 6.74106 9.75224C6.59152 9.38918 6.09393 7.92903 6.88348 5.95338C6.88348 5.95338 8.10031 5.56742 10.8704 7.42499C12.0267 7.10599 13.2676 6.94738 14.5004 6.94121C15.7324 6.94738 16.9724 7.10599 18.1305 7.42499C20.8988 5.56742 22.1138 5.95338 22.1138 5.95338C22.9052 7.92903 22.4076 9.38918 22.2589 9.75224C23.1882 10.755 23.749 12.0372 23.749 13.604C23.749 19.1185 20.3585 20.3319 17.129 20.687C17.6489 21.1303 18.1126 22.0062 18.1126 23.3447C18.1126 25.264 18.0948 26.8123 18.0948 27.2828C18.0948 27.667 18.3566 28.1138 19.0918 27.9737C24.8493 26.0712 29 20.695 29 14.3547C29 6.42659 22.5073 0 14.4987 0C6.49271 0 0 6.42659 0 14.3547Z"}]])
;; Header
(def header
  [:header
   [:div.title "BEST WORKS OF CLASSICAL MUSIC"]
   [:div.subtitle "Top lists as voted by "
    [:a {:href "https://www.talkclassical.com/17996-compilation-tc-top-recommended.html"} "talkclassical.com"]
    " members"]])

;; Footer
(def footer
  [:footer
   [:div.author-ref
    "Content by "
    [:a {:href "https://www.talkclassical.com/17996-compilation-tc-top-recommended.html"} "talkclassical.com"]
    " forum members."]
   [:div.icons
    [:div [:a {:href "https://clojure.org"} clojure-icon]]
    [:div [:a {:href "https://github.com/Droidion/tc-recommended-clojure"} github-icon]]]])

;; Menu
(defn menu
  [title]
  (let [work-types (->> all-work-types
                        (cons {:name "Credits" :url "credits"})
                        (cons {:name "Top Composers" :url "top-composers"}))]
    (for [genre work-types]
      [:div
       (if (= title (:name genre))
         [:span (:name genre)]
         [:a {:href (if (contains? genre :slug)
                      (str "/genre/" (:slug genre))
                      (str "/" (:url genre)))} (:name genre)])])))

;; Wrapper for adding menu with related current page
(defn content-wrapper
  [content title]
  (html [:aside [:div.menu (menu title)]]
        [:div.content content]))

;; Site name postfix to be used in <title> on all pages
(def site-title " | Best Works of Classical Music")

;; Top-level html wrapper
(defn application [title description content]
  (html5 {:lang "en"} [:head
                       [:title title]
                       [:link {:rel "apple-touch-icon" :sizes "180x180" :href "/assets/apple-touch-icon.png"}]
                       [:link {:rel "icon" :type "image/png" :sizes "32x32" :href "/assets/favicon-32x32.png"}]
                       [:link {:rel "icon" :type "image/png" :sizes "16x16" :href "/assets/favicon-16x16.png"}]
                       [:link {:rel "manifest" :href "/assets/site.webmanifest"}]
                       [:link {:rel "mask-icon" :href "/assets/safari-pinned-tab.svg" :color "#3b4252"}]
                       [:link {:rel "preconnect" :href "https://fonts.gstatic.com/" :crossorigin "true"}]
                       [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
                       [:meta {:name "robots" :content "index, follow"}]
                       [:meta {:name "msapplication-TileColor" :content "#2b5797"}]
                       [:meta {:name "theme-color" :content "#ffffff"}]
                       [:meta {:name "description" :content description}]
                       (include-css "https://fonts.googleapis.com/css2?family=Raleway&family=Roboto:wght@300;400&display=swap")
                       (include-css "/assets/styles.css")]
         [:body header [:div.wrapper content] footer]))