(ns tc-recommended.specs
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(s/def ::non-blank-string (s/and string? (complement str/blank?)))

(s/def ::composer_id pos-int?)
(s/def ::composer_name ::non-blank-string)
(s/def ::work_name ::non-blank-string)
(s/def ::work_type_id pos-int?)
(s/def ::work_type_name ::non-blank-string)
(s/def ::position pos-int?)

(s/def ::work (s/keys :req [::composer-id
                            ::composer_name
                            ::work_name
                            ::work_type_id
                            ::work_type_name
                            ::position]))

(s/def ::works-by-type (s/map-of string? (s/coll-of ::work)))