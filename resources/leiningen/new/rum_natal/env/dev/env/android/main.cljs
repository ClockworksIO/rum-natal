(ns ^:figwheel-no-load env.android.main
  (:require [{{name}}.android.core :as core]
            [figwheel.client :as fw]
            [env.config :as conf]))

(assert (exists? core/init) "Fatal Error - Your core.cljs file doesn't define an 'init' function!!! - Perhaps there was a compilation failure?")
(assert (exists? core/root-component-factory) "Fatal Error - Your core.cljs file doesn't define an 'root-component-factory' function!!! - Perhaps there was a compilation failure?")
(assert (exists? core/mount-app) "Fatal Error - Your core.cljs file doesn't define an 'mount-app' function!!! - Perhaps there was a compilation failure?")

(enable-console-print!)

(fw/start {
           :websocket-url    (:android conf/figwheel-urls)
           :heads-up-display false
           ;; TODO make this Rum something
           :jsload-callback  #(#'core/mount-app)})

(core/init)


;; Do not delete, root-el is used by the figwheel-bridge.js
(def root-el (core/root-component-factory))
