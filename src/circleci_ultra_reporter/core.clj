(ns circleci-ultra-reporter.core
  (:require [clojure.stacktrace :as stack]
            [clojure.test :as test]
            [circleci.test.report :as report]
            [io.aviso.repl :as pretty-repl]
            [ultra.test.diff :as diff]
            [puget.color.ansi :as ansi]))

(deftype UltraReporter []
  report/TestReporter
  (default [this m]
    (when report/*debug*
      (test/with-test-out (prn m))))

  (pass [this m]
    (test/with-test-out (test/inc-report-counter :pass)))

  (fail [this {:keys [type expected actual diffs message] :as event}]
    (test/with-test-out
      (test/inc-report-counter :fail)
      (println (str (ansi/sgr "\nFAIL" :red) " in " (report/testing-vars-str event)))
      (when (seq test/*testing-contexts*) (println (report/testing-contexts-str)))
      (when message (println message))
      (if (seq diffs)
        (doseq [[actual [a b]] diffs
                :when (or a b)]
          (diff/prn-diffs a b actual expected))
                (diff/print-expected actual expected))))

  (error [this {:keys [message expected actual] :as event}]
    (test/with-test-out
      (test/inc-report-counter :error)
      (println (str (ansi/sgr "\nERROR" :red) " in " (report/testing-vars-str event)))
      (when (seq test/*testing-contexts*) (println (report/testing-contexts-str)))
      (when message (println message))
      (println "expected:" (pr-str expected))
      (print "  actual: ")
      (if (instance? Throwable actual)
        (pretty-repl/pretty-print-stack-trace actual)
                (prn actual))))

  (summary [this {:keys [test pass fail error]}]
    (test/with-test-out
      (println "\nRan" test "tests containing"
               (+ pass fail error) "assertions.")
      (println fail "failures," error "errors.")))

  (begin-test-ns [this m]
    (test/with-test-out
      (println "\nTesting" (ns-name (:ns m)))))

  (end-test-ns [this m])

  (begin-test-var [this m]
    (test/with-test-out
      (test/inc-report-counter :test)))

  (end-test-var [this m]))

(defn ultra-test-reporter [_config]
    (->UltraReporter))
