(ns tc-recommended.helpers-test
  (:require [clojure.test :refer :all]
            [tc-recommended.helpers :refer :all]))

(deftest helpers
  (testing "group-by-genre-name"
    (let [input [{:composer_id    1,
                  :composer_name  "Beethoven",
                  :work_name      "Piano Sonata No. 20",
                  :work_type_id   33,
                  :work_type_name "Piano Sonatas",
                  :position       10},
                 {:composer_id    2,
                  :composer_name  "Mozart",
                  :work_name      "Symphony No. 41",
                  :work_type_id   44,
                  :work_type_name "Symphonies",
                  :position       100},
                 {:composer_id    3,
                  :composer_name  "Schubert",
                  :work_name      "Symphony No. 5",
                  :work_type_id   55,
                  :work_type_name "Symphonies",
                  :position       1000}]
          expected-output {"Piano Sonatas" '({:composer_id    1,
                                              :composer_name  "Beethoven",
                                              :work_name      "Piano Sonata No. 20",
                                              :work_type_id   33,
                                              :work_type_name "Piano Sonatas",
                                              :position       10}),
                           "Symphonies"    '({:composer_id    3,
                                              :composer_name  "Schubert",
                                              :work_name      "Symphony No. 5",
                                              :work_type_id   55,
                                              :work_type_name "Symphonies",
                                              :position       1000},
                                             {:composer_id    2,
                                              :composer_name  "Mozart",
                                              :work_name      "Symphony No. 41",
                                              :work_type_id   44,
                                              :work_type_name "Symphonies",
                                              :position       100})}]
      (is (= (group-by-genre-name input) expected-output)))))
