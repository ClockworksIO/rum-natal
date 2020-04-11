(ns rum-natal.components
  "Basic React Native Components."
  (:require [rum-natal.util :refer [create-element]]))

;;; IMPORT REACT
(set! js/window.React (js/require "react"))
(def ReactNative (js/require "react-native"))

;;; React Native Other
(def Dimensions (.-Dimensions ReactNative))
(def Keyboard (.-Keyboard ReactNative))
(def Platform (.-Platform ReactNative))
(def AppState (.-AppState ReactNative))
(def NativeModules (.-NativeModules ReactNative))
(def DevSettings (.-DevSettings NativeModules))

;;; React Native Components
(def View (partial create-element (.-View ReactNative)))
(def KeyboardAvoidingView (partial create-element (.-KeyboardAvoidingView ReactNative)))
(def ScrollView (partial create-element (.-ScrollView ReactNative)))
(def Text (partial create-element (.-Text ReactNative)))
(def Image (partial create-element (.-Image ReactNative)))
(def Button (partial create-element (.-Button ReactNative)))
(def Modal (partial create-element (.-Modal ReactNative)))
(def StatusBar (partial create-element (.-StatusBar ReactNative)))
(def TouchableHighlight (partial create-element (.-TouchableHighlight ReactNative)))
(def TouchableOpacity (partial create-element (.-TouchableOpacity ReactNative)))
(def TextInput (partial create-element (.-TextInput ReactNative)))
(def Switch (partial create-element (.-Switch ReactNative)))
(def TouchableWithoutFeedback (partial create-element (.-TouchableWithoutFeedback ReactNative)))

;;; Platform Native Components
