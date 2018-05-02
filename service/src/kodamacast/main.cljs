(ns kodamacast.main
  (:require [ask-sdk-core :as alexa]
            [kodamacast.handlers]
            [kodamacast.handlers.core :as handlers]))

(enable-console-print!)

(defn- install-handlers [builder]
  (reduce #(.addRequestHandlers %1 %2)
          builder
          (handlers/handlers)))

(set! (.-handler js/exports)
      (.lambda (install-handlers (alexa/SkillBuilders.custom))))
