# circleci-ultra-reporter

Reporter for [circleci.test](https://github.com/circleci/circleci.test) that utilises the [ultra](https://github.com/venantius/ultra) library.

## Installation

To install this library add the following to you test dependancies `[circleci-ultra-reporter "0.1.0"]`

Then in your config file for [circleci.test](https://github.com/circleci/circleci.test) (default location is `dev-resources/circleci_test/config.clj` but may not exist if just using default config), add the circleci-ultra-reporter to the list of reporters:

```clojure
(require '[circleci-ultra-reporter.core :refer [ultra-test-reporter]])

{:reporters [ultra-test-reporter]}
```

## Special thanks

This is a library that allows you to have the impressive test reporter from `ultra` combined with the power of `circleci.test`, so all the thanks go to these libraries and their maintainers!
