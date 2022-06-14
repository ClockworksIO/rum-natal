(defproject {{raw-name}} "0.1.0-snapshot"
  :description "TODO: write description!"
  :url "http://example.com/FIXME"
  :license {:name "Apache 2.0 License"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure       "1.11.1"]
                 [org.clojure/clojurescript "1.11.57"]
                 [org.clojure/core.async    "1.5.648"]
                 [leiningen-core            "2.9.8"]
                 [rum                       "0.12.9"
                  :exclusions [cljsjs/react cljsjs/react-dom sablono]]
                 [rum-native                "1.0.0"]]
  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.8" :exclusions [[org.clojure/clojure]]]]
  :repl-options {:init-ns cljsrn.repl}
  :clean-targets ["target/" "index.ios.js" "index.android.js" #_($PLATFORM_CLEAN$)]
  :aliases {"collect-dev" ^{:doc "Collect static ressources and rebuild index files for development with figwheel."}
                          ["run" "-m" "rum-natal.core/rebuild-dev-index"]
            "prod-build" ^{:doc "Recompile code with prod profile."}
                         ["do" "clean"
                          ["with-profile" "prod" "cljsbuild" "once"]]
            "advanced-build" ^{:doc "Recompile code for production using :advanced compilation."}
                         ["do" "clean"
                          ["with-profile" "advanced" "cljsbuild" "once"]]}
  :jvm-opts ["-XX:+IgnoreUnrecognizedVMOptions" "--add-modules java.xml.bind"]
  :profiles {:dev {:dependencies [[figwheel-sidecar   "0.5.18"]
                                  [cider/piggieback   "0.4.0"]
                                  [binaryage/devtools "1.0.2"]
                                  [camel-snake-kebab  "0.4.2"]]
                   :source-paths ["src" "env/dev"]
                   :cljsbuild    {:builds [
                                           {:id           "ios"
                                            :source-paths ["src" "env/dev"]
                                            :figwheel     true
                                            :compiler     {:output-to     "target/ios/index.js"
                                                           :main          "env.ios.main"
                                                           :output-dir    "target/ios"
                                                           :optimizations :none
                                                           :target :nodejs}}
                                           {:id           "android"
                                            :source-paths ["src" "env/dev"]
                                            :figwheel     true
                                            :compiler     {:output-to     "target/android/index.js"
                                                           :main          "env.android.main"
                                                           :output-dir    "target/android"
                                                           :optimizations :none
                                                           :target :nodejs}}
                                           #_($DEV_PROFILES$)]}
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}
             :prod {:cljsbuild {:builds [
                                         {:id           "ios"
                                          :source-paths ["src" "env/prod"]
                                          :compiler     {:output-to     "index.ios.js"
                                                         :main          "env.ios.main"
                                                         :output-dir    "target/ios"
                                                         :static-fns    true
                                                         :optimize-constants true
                                                         :optimizations :simple
                                                         :target :nodejs
                                                         :closure-defines {"goog.DEBUG" false}}}
                                         {:id           "android"
                                          :source-paths ["src" "env/prod"]
                                          :compiler     {:output-to     "index.android.js"
                                                         :main          "env.android.main"
                                                         :output-dir    "target/android"
                                                         :static-fns    true
                                                         :optimize-constants true
                                                         :optimizations :simple
                                                         :target :nodejs
                                                         :closure-defines {"goog.DEBUG" false}}}
                                         #_($PROD_PROFILES$)]}}
             :advanced {:dependencies [[react-native-externs "0.2.0"]]
                        :cljsbuild {:builds [
                                             {:id           "ios"
                                              :source-paths ["src" "env/prod"]
                                              :compiler     {:output-to     "index.ios.js"
                                                             :main          "env.ios.main"
                                                             :output-dir    "target/ios"
                                                             :static-fns    true
                                                             :optimize-constants true
                                                             :optimizations :advanced
                                                             :target :nodejs
                                                             :closure-defines {"goog.DEBUG" false}}}
                                             {:id           "android"
                                              :source-paths ["src" "env/prod"]
                                              :compiler     {:output-to     "index.android.js"
                                                             :main          "env.android.main"
                                                             :output-dir    "target/android"
                                                             :static-fns    true
                                                             :optimize-constants true
                                                             :optimizations :advanced
                                                             :target :nodejs
                                                             :closure-defines {"goog.DEBUG" false}}}
                                             #_($ADVANCED_PROFILES$)]}}})
