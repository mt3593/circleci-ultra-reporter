(require '[circleci-ultra-reporter.core])

{:selectors {:all (constantly true)
             :default (complement :flaky)}
 :reporters [circleci-ultra-reporter.core/clojure-test-reporter]}
