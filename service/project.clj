(defproject kodamacast-service "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/athos/kodamacast"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :compiler {:output-to "target/kodamacast-service.js"
                           :output-dir "target/out"
                           :main kodamacast.main
                           :optimizations :simple
                           :target :nodejs
                           :npm-deps {"alexa-sdk" "^1.0.25"}
                           :install-deps true}}]})
