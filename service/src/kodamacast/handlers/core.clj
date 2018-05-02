(ns kodamacast.handlers.core
  (:require [kodamacast.messages.ja-jp :as msg]))

(defmacro defhandler [handler-name args & body]
  `(do (swap! %handlers assoc '~handler-name (fn ~args ~@body))
       '~handler-name))

(defmacro declare-condition [handler-name condition]
  `(do (swap! %conditions assoc '~handler-name ~condition)
       '~handler-name))

(defmacro t [id]
  (or (get msg/messages (keyword id))
      (throw (ex-info "No such message id found" {:id id}))))
