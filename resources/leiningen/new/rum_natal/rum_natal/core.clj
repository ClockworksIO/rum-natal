(ns rum-natal.core
  "Rum-Natal scripts and helper to management a clojurescript+react-native project."
  (:require
   [clojure.edn :as edn]
   [clojure.string :as string]
   [leiningen.core.main :as main]))


(defn collect-assets
  "Get a list of all asset files.
  Only return one file for images that are present with different resolutions
  following the filename@2x.png convention of react native."
  []
  (let [assets-dir  "./assets"
        re-matcher  #"([\\.0-9A-Za-z \s_-])+(.png|.jpg|.pdf)"]
    (->> (file-seq (clojure.java.io/file assets-dir))
      (filter #(.isFile %))
      (filter #(re-matches re-matcher (.getName %))))))


(defn mk-android-index
  "Rebuild the index.android.js file."
  [proj-name native-modules assets]
  (let [native-modules-js  (mapv (fn [m]
                                   (str "modules['"m"']=require('"m"');"))
                                 native-modules)
        asset-modules-js   (mapv (fn [a]
                                    (str "modules['"(.getPath a)"']=require('"(.getPath a)"');"))
                                 assets)]
    (str "var modules={};\n"
         (string/join "\n" native-modules-js)
         "\n"
         (string/join "\n" asset-modules-js)
         "\n"
         "require('./figwheel-bridge').withModules(modules).start('"proj-name"','android','localhost');")))


(defn mk-ios-index
  "Rebuild the index.io.js file."
  [proj-name native-modules assets]
  (let [native-modules-js  (mapv (fn [m]
                                   (str "modules['"m"']=require('"m"');"))
                                 native-modules)
        asset-modules-js   (mapv (fn [a]
                                    (str "modules['"(.getPath a)"']=require('"(.getPath a)"');"))
                                 assets)]
    (str "var modules={};\n"
         (string/join "\n" native-modules-js)
         "\n"
         (string/join "\n" asset-modules-js)
         "\n"
         "require('./figwheel-bridge').withModules(modules).start('"proj-name"','ios','localhost');")))



(defn rebuild-dev-index
  "Rebuild the index files."
  []
  (main/info "Rebuilding index.[ios|android].js files for development...")
  (let [index-file-ios      "index.ios.js"
        index-file-android  "index.android.js"
        native-modules      (-> (slurp "rum-natal.edn")
                                (edn/read-string)
                                (get :native-modules))
        assets              (collect-assets)
        proj-name           (-> "project.clj" slurp read-string (nth 1))]
    (main/info "Found " (count native-modules) " native modules to include.")
    (main/info "Found " (count assets) " assets to include.")
    (main/info "Rebuilding indices...")
    (with-open [w (clojure.java.io/writer  index-file-android)]
      (.write w (mk-android-index proj-name native-modules assets)))
    (with-open [w (clojure.java.io/writer  index-file-ios)]
      (.write w (mk-ios-index proj-name native-modules assets)))
    (main/info "Successfully rebuild index files.")))
