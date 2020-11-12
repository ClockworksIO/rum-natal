(ns leiningen.new.rum-natal
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files raw-resourcer]]
            [leiningen.core.main :as main]))


(def render (renderer "rum-natal"))
(def binary (raw-resourcer "rum-natal"))

(defn rum-natal
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :raw-name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' rum-natal project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["rum-natal.edn" (render "rum-natal.edn" data)]
             ["figwheel-bridge.js" (slurp "resources/leiningen/new/rum_natal/figwheel-bridge.js")]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (slurp "resources/leiningen/new/rum_natal/LICENSE")]
             ["CHANGELOG.md" (render "CHANGELOG.md" data)]
             ["docs/rum-natal/usage.md" (slurp "resources/leiningen/new/rum_natal/rum-natal-usage.md")]
             ["docs/rum-natal/components.md" (slurp "resources/leiningen/new/rum_natal/rum-natal-components.md")]
             ["env/dev/user.clj" (render "env/dev/user.clj" data)]
             ["env/dev/env/config.cljs" (render "env/dev/env/config.cljs" data)]
             ["env/dev/env/android/main.cljs" (render "env/dev/env/android/main.cljs" data)]
             ["env/dev/env/ios/main.cljs" (render "env/dev/env/ios/main.cljs" data)]
             ["env/prod/env/ios/main.cljs" (render "env/prod/env/ios/main.cljs" data)]
             ["env/prod/env/android/main.cljs" (render "env/prod/env/android/main.cljs" data)]
             ["src/cljsjs/react.cljs" (render "cljsjs/react.cljs" data)]
             ["src/cljsjs/react/dom.cljs" (render "cljsjs/react/dom.cljs" data)]
             ["src/sablono/compiler.clj" (render "sablono/compiler.clj" data)]
             ["src/sablono/core.cljs" (render "sablono/core.cljs" data)]
             ;
             ["src/rum_natal/core.clj" (render "rum_natal/core.clj" data)]
             ;["src/rum_natal/components.cljs" (render "rum_natal/components.cljs" data)]
             ;["src/rum_natal/util.cljs" (render "rum_natal/util.cljs" data)]
             ;
             ["src/{{name}}/ios/core.cljs" (render "ios/core.cljs" data)]
             ["src/{{name}}/android/core.cljs" (render "android/core.cljs" data)]

             "assets"
             ["assets/images/cljs.png" (binary "assets/images/cljs.png")]
             ["assets/images/cljs@2x.png" (binary "assets/images/cljs@2x.png")]
             ["assets/images/cljs@3x.png" (binary "assets/images/cljs@3x.png")])))
