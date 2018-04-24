(ns kodamacast.handlers.core
  (:require-macros [kodamacast.handlers.core]))

(def %handlers (atom {}))

(defn handlers []
  (clj->js @%handlers))
