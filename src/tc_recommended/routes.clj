(ns tc-recommended.routes
  (:require [reitit.ring :as r]
            [reitit.coercion.malli]
            [reitit.ring.coercion :as coercion]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [reitit.ring.middleware.exception :as exception]
            [reitit.ring.middleware.parameters :as parameters]
            [malli.util :as mu]
            [muuntaja.core :as m]
            [tc-recommended.handlers :as handlers]))

(def routes
  (r/router
    [[""
      ["/" {:get {:handler handlers/top-composers}}]
      ["/top-composers" {:get {:handler handlers/top-composers}}]
      ["/genre/:slug" {:get {:handler    handlers/works-by-genre
                                      :parameters {:path [:map [:slug string?]]}}}]
      ["/composer/:composer-id" {:get {:handler    handlers/composer
                                       :parameters {:path [:map [:composer-id int?]]}}}]
      ["/credits" {:get {:handler handlers/credits}}]
      ["/assets/*" (r/create-resource-handler)]]]
    {:data {:coercion   (reitit.coercion.malli/create
                          {;; set of keys to include in error messages
                           :error-keys       #{#_:type :coercion :in :schema :value :errors :humanized #_:transformed}
                           ;; schema identity function (default: close all map schemas)
                           :compile          mu/closed-schema
                           ;; strip-extra-keys (effects only predefined transformers)
                           :strip-extra-keys true
                           ;; add/set default values
                           :default-values   true
                           ;; malli options
                           :options          nil})
            :muuntaja   m/instance
            :middleware [;; query-params & form-params
                         parameters/parameters-middleware
                         ;; content-negotiation
                         muuntaja/format-negotiate-middleware
                         ;; encoding response body
                         muuntaja/format-response-middleware
                         ;; exception handling
                         exception/exception-middleware
                         ;; coercing response bodys
                         coercion/coerce-response-middleware
                         ;; coercing request parameters
                         coercion/coerce-request-middleware]}}))