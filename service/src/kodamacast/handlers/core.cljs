(ns kodamacast.handlers.core
  (:require-macros [kodamacast.handlers.core]))

(def %handlers (atom {}))

(defn request-type [input]
  (.. input -requestEnvelope -request -type))

(defn intent-request? [input]
  (= (request-type input) "IntentRequest"))

(defn intent-name [input]
  (.. input -requestEnvelope -request -intent -name))

(defn handlers []
  (vals @%handlers))
