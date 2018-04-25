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

(def PODCAST_URLS
  {"こぐにきゃすと" "https://s3.amazonaws.com/cognicast/shows/cognicast-103-rich-hickey-spec.mp3"
   "やっていきえふえむ" "https://yatteiki.fm/audio/53.mp3"})

(defhandler :PlayPodcast
  (let [slots (.. &self -event -request -intent -slots)
        podcast-name (-> slots (aget "podcastName") .-value)
        podcast-url (get PODCAST_URLS podcast-name)]
    (.. &self
        -response
        (audioPlayerPlay "REPLACE_ALL" podcast-url podcast-url nil 0)))
  (h/emit ":responseReady"))

(defhandler :AMAZON.PauseIntent
  (.. &self
      -response
      (audioPlayerStop))
  (h/emit ":responseReady"))

(defhandler :AMAZON.ResumeIntent
  #_(.. &self
      -response
      (audioPlayerPlay "REPLACE_ALL" PODCAST_URL PODCAST_URL nil 0))
  (h/emit ":responseReady"))
