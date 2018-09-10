(ns circleci-ultra-reporter.core-test
  (:require [clojure.test :refer :all]
            [circleci-ultra-reporter.core :refer :all]))

(deftest failure
  (testing "I fail."
    (is (= 0 1))))

(deftest error
  (testing "I error."
    (is (= 0 (throw (Exception. "opps"))))))
