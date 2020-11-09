(ns tc-recommended.db-test
  (:require [clojure.test :refer :all]
            [tc-recommended.db :refer :all]
            [mount.core :as mount]))


(deftest db
  (mount/start)
  (testing "get-composers-rating returns non empty collection"
    (let [result (get-composers-rating)]
      (is (> (count result) 0))))
  (testing "get-all-work-types returns non empty collection"
    (let [result (get-all-work-types)]
      (is (> (count result) 0))))
  (testing "get-all-works returns non empty collection"
    (let [result (get-all-works)]
      (is (> (count result) 0))))
  (testing "get-all-composers returns non empty collection"
    (let [result (get-all-composers)]
      (is (> (count result) 0)))))
