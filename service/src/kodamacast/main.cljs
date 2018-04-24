(ns kodamacast.main
  (:require [alexa-sdk :as alexa]
            [kodamacast.handlers]
            [kodamacast.handlers.core :as handlers]
            [kodamacast.messages.ja-jp :as ja-jp]))

(enable-console-print!)

(def APP_ID js/undefined)

(def lang-strs
  #js{:ja-JP #js{:translation ja-jp/messages}})

(defn main [event context callback]
  (let [alexa (alexa/handler event context callback)]
    (set! (.-appId alexa) APP_ID)
    (set! (.-resources alexa) lang-strs)
    (.registerHandlers alexa (handlers/handlers))
    (.execute alexa)))

(set! (.-handler js/exports) main)
