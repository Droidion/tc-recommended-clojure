;; Helpers functions for business logic operations
(ns tc-recommended.helpers)

(defn group-by-genre-name
  "Groups works by genre names. Genre names are map keys, lists of works are maps values."
  [works]
  (let [adder (fn [acc el] (-> acc
                               (get (:work_type_name el))
                               (conj el)))
        reducer (fn [acc el] (assoc acc (:work_type_name el) (adder acc el)))]
    (reduce reducer {} works)))