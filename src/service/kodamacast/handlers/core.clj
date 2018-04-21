(ns kodamacast.handlers.core)

(defmacro defhandler [intent & body]
  `(do (swap! %handlers assoc ~intent #(cljs.core/this-as ~'&self ~@body))
       ~intent))

(defmacro emit [& args]
  `(.emit ~'&self ~@args))

(defmacro t [x]
  `(.t ~'&self ~x))
