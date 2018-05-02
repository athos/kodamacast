(ns kodamacast.handlers.core
  (:require [kodamacast.messages.ja-jp :as msg]))

(defmacro defhandler [handler-name args & body]
  (let [target (or (:target (meta args))
                   `(fn [input#]
                      (and (intent-request? input#)
                           (= (intent-name input#) ~(name handler-name)))))]
    `(do (swap! %handlers assoc '~handler-name
                (cljs.core/js-obj "canHandle" ~target
                                  "handle" (fn ~(with-meta args
                                                  (dissoc (meta args) :target))
                                             ~@body)))
         '~handler-name)))

(defmacro t [id]
  (or (get msg/messages (keyword id))
      (throw (ex-info "No such message id found" {:id id}))))
