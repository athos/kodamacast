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

(def PODCAST_URL "https://s3.amazonaws.com/cognicast/shows/cognicast-103-rich-hickey-spec.mp3")

(defhandler :Play
  (.. &self
      -response
      (audioPlayerPlay "REPLACE_ALL" PODCAST_URL PODCAST_URL nil 0))
  (h/emit ":responseReady"))

(defhandler :AMAZON.PauseIntent
  (.. &self
      -response
      (audioPlayerStop))
  (h/emit ":responseReady"))

(defhandler :AMAZON.ResumeIntent
  (.. &self
      -response
      (audioPlayerPlay "REPLACE_ALL" PODCAST_URL PODCAST_URL nil 0))
  (h/emit ":responseReady"))
