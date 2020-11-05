(defproject tc-recommended-clojure "0.1.0-SNAPSHOT"
  :description "Talkclassical recommended works and composers"
  :url "http://example.com/FIXME"
  :main tc-recommended.core
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [mount "0.1.16"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 ;; Web server
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [metosin/reitit-malli "0.5.10"]
                 [metosin/muuntaja "0.6.7"]
                 [metosin/reitit-middleware "0.5.10"]
                 ;; Routing
                 [metosin/reitit-core "0.5.10"]
                 [metosin/reitit-ring "0.5.10"]
                 ;; Database
                 [org.clojure/java.jdbc "0.7.11"]           ;; DB Driver
                 [org.xerial/sqlite-jdbc "3.32.3.2"]        ;; DB Adapter
                 [hikari-cp "2.13.0"]                       ;; DB Pooling
                 [honeysql "1.0.444"]                       ;; Queries constructor
                 ;; Templating
                 [hiccup "1.0.5"]
                 [garden "1.3.10"]]
  :repl-options {:init-ns tc-recommended.core})
