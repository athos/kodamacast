(ns kodamacast.handlers
  (:require [kodamacast.handlers.core :as h :refer [defhandler]]))

(defhandler :LaunchRequest
  (h/emit ":ask" (h/t "DESCRIPTION") ""))

(defhandler :FinishIntent
  (h/emit "AMAZON.StopIntent"))

(defhandler :Unhandled
  (h/emit ":ask" (h/t "SORRY_MESSAGE") (h/t "TRY_AGAIN_MESSAGE")))

(defhandler :AMAZON.HeplIntent
  (h/emit ":ask" (h/t "HELP_MESSAGE") ""))

(defhandler :AMAZON.CancelIntent
  (h/emit "AMAZON.StopIntent"))

(defhandler :AMAZON.StopIntent
  (h/emit ":tell" (h/t "THANKS_MESSAGE")))
