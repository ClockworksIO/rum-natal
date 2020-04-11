(defproject {{raw-name}} "0.1.0-snapshot"
  :description "TODO: write description!"
  :url "http://example.com/FIXME"
  :license {:name "Apache 2.0 License"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.clojure/core.async  "0.4.500"]
                 [rum "0.11.2" :exclusions [cljsjs/react cljsjs/react-dom sablono]]]
  :plugins [[lein-figwheel "0.5.19"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-bikeshed "0.5.2"]
            [lein-kibit "0.1.8"]
            [lein-count "1.0.9"]
            [lein-nvd "1.3.1"]]
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
  :jvm-opts ["-XX:+IgnoreUnrecognizedVMOptions" "--add-modules=java.xml.bind"]
  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.18"]
                                  [cider/piggieback "0.4.0"]]
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
