(ns {{name}}.android.core
  ""
  (:require-macros [rum.core :refer [defc]])
  (:require [rum.core :as rum]
            [rum-native.util :as util]
            [rum-native.core :as c]))

;;; REACT SETUP

(set! js/window.React (js/require "react"))
(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))

(def logo-img (js/require "./assets/images/cljs.png"))

(defn alert [title] (.alert (.-Alert ReactNative) title))

(defonce app-state (atom {:greeting "Hello Clojure in iOS and Android with rum-natal!"}))

(defc AppRoot
  < rum/reactive
  [state]
  (c/View {:style {:flexDirection "column"
                   :margin 40
                   :alignItems "center"}}
    (c/Text {:style {:fontSize 30
                     :fontWeight "100"
                     :marginBottom 20
                     :textAlign "center"}}
            (:greeting (rum/react state)))
    (c/Image {:source logo-img
              :style  {:width 80
                       :height 80
                       :marginBottom 30}})
    (c/TouchableHighlight {:style {:backgroundColor "#999"}
                                  :padding 10
                                  :borderRadius 5
                           :onPress #(alert "HELLO!")}
          (c/Text {:style {:color "white"
                           :textAlign "center"
                           :fontWeight "bold"}}
                  "press me"))))


(defonce root-component-factory (util/make-root-component-factory))

(defn mount-app [] (util/mount (AppRoot app-state)))

(defn init []
      (mount-app)
      (.registerComponent app-registry "{{name}}" (fn [] root-component-factory)))
