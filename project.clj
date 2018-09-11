(defproject circleci-ultra-reporter "0.1.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [circleci/circleci.test "0.4.1"]
                 [venantius/ultra "0.5.2"]]
  :aliases {"test" ["run" "-m" "circleci.test/dir" :project/test-paths]})
