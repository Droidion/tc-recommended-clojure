(ns tc-recommended.core
  (:require [mount.core :as mount]
            [ring.adapter.jetty :refer [run-jetty]]
            [reitit.ring :as r]
            [tc-recommended.routes :as routes]
            [tc-recommended.db])
  (:gen-class))

(defonce server-handler (atom nil))

(defn stop-server
  "Helper function to stop the server when the component's stop function is called"
  []
  (when @server-handler
    ((.stop @server-handler)
     (reset! server-handler nil))))

(defn- start-server
  []
  (mount/start)
  (let [handler (run-jetty (r/ring-handler
                             routes/routes
                             (r/create-default-handler)) {:join? false :port 7888})]
    (reset! server-handler handler)))

(defn -main
  [& args]
  (start-server))
