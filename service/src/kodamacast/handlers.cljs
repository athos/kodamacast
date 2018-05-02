(ns kodamacast.handlers
  (:require [kodamacast.handlers.core :as h :refer [defhandler]]))

(h/declare-condition LaunchRequest #(= (h/request-type %) "LaunchRequest"))

(defhandler LaunchRequest [input]
  (.. (.-responseBuilder input)
      (speak (h/t "DESCRIPTION"))
      (getResponse)))

(h/declare-condition FinishIntent
  (fn [input]
    (and (h/intent-request? input)
         (contains? #{"AMAZON.CancelIntent" "AMAZON.StopIntent"}
                    (h/intent-name input)))))

(defhandler FinishIntent [input]
  (.. (.-responseBuilder input)
      (speak (h/t "THANKS_MESSAGE"))
      (getResponse)))

(defhandler Unhandled [input]
  (.. (.-responseBuilder input)
      (speak (h/t "SORRY_MESSAGE"))
      (reprompt (h/t "TRY_AGAIN_MESSAGE"))
      (getResponse)))

(defhandler AMAZON.HelpIntent [input]
  (.. (.-responseBuilder input)
      (speak (h/t "HELP_MESSAGE"))
      (getResponse)))

(def PODCAST_URLS
  {"こぐにきゃすと" "https://s3.amazonaws.com/cognicast/shows/cognicast-103-rich-hickey-spec.mp3"
   "やっていきえふえむ" "https://yatteiki.fm/audio/53.mp3"})

(defhandler PlayPodcast [input]
  (let [slots (.. input -requestEnvelope -request -intent -slots)
        podcast-name (-> slots (aget "podcastName") .-value)]
    (if-let [podcast-url (get PODCAST_URLS podcast-name)]
      (.. (.-responseBuilder input)
          (addAudioPlayerPlayDirective "REPLACE_ALL" podcast-url podcast-url nil 0)
          (speak (str podcast-name "を再生します。"))
          (getResponse))
      (.. (.-responseBuilder input)
          (speak (str podcast-name "に該当するポッドキャストは見つかりませんでした。"))
          (getResponse)))))

(defhandler AMAZON.PauseIntent [input]
  (.. (.-responseBuilder input)
      (addAudioPlayerStopDirective)
      (getResponse)))

(defhandler AMAZON.ResumeIntent [input]
  (.. (.-responseBuilder input)
      (getResponse)))
