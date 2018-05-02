(ns kodamacast.handlers.core
  (:require-macros [kodamacast.handlers.core]))

(def %handlers (atom {}))
(def %conditions (atom {}))

(defn request-type [input]
  (.. input -requestEnvelope -request -type))

(defn intent-request? [input]
  (= (request-type input) "IntentRequest"))

(defn intent-name [input]
  (.. input -requestEnvelope -request -intent -name))

(defn handlers []
  (for [[handler-name impl] @%handlers
        :let [condition (or (get @%conditions handler-name)
                            (let [name (name handler-name)]
                              (fn [input]
                                (and (intent-request? input)
                                     (= (intent-name input) name)))))]]
    #js{:canHandle condition
        :handle impl}))
