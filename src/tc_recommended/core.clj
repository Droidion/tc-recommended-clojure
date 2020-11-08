(ns tc-recommended.core
  (:require [mount.core :as mount]
            [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [tc-recommended.styles :as styles]
            [tc-recommended.routes :as routes]
            [tc-recommended.db])
  (:gen-class))

;; Handler of the currently run Ring server
;; Used for stopping web server from REPL
(defonce server-handler (atom nil))

(defn stop-server
  "Stops Ring web server"
  []
  (when @server-handler
    ((.stop @server-handler)
     (reset! server-handler nil))))

(defn- start-server
  "Starts Ring web server"
  []
  (styles/compile-styles)
  (mount/start)
  (let [handler (run-jetty (r/ring-handler
                             routes/routes
                             (r/create-default-handler)) {:join? false :port 7888})]
    (reset! server-handler handler)))

;; Starts web server when appication starts
(defn -main
  [& args]
  (start-server))
