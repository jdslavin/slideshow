(defproject horror "0.1.0-SNAPSHOT"
  :description "Horror slideshow"
  :url ""
  :license {:name "NAVIS"
            :url  "http://www.thenavisway.com"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [org.omcljs/om "1.0.0-alpha11"]
                 [figwheel-sidecar "0.4.1" :scope "provided"]
                 [lein-cljsbuild "1.1.0"]
                 [cljsjs/react-with-addons "0.14.0-1" :scope "test"]
                 ]

  :source-paths ["src" "spec"]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.1"]
            ]

  :cljsbuild {:builds
              [{:id           "dev"
                :source-paths ["src"]
                :figwheel     {:on-jsload "horror.slidesshow/reload"}
                :compiler     {:main                 horror.slidesshow
                               :output-to            "resources/public/js/horror/horror.js"
                               :output-dir           "resources/public/js/horror/out"
                               :recompile-dependents true
                               :asset-path           "js/horror/out"
                               :optimizations        :none}}]}

  :profiles {
             :dev {
                   :source-paths ["dev"]
                   :dependencies [[leiningen "2.5.3"]
                                  [leiningen-core "2.5.3"]]
                   :repl-options {
                                  :init-ns clj.user
                                  :port    7001
                                  }
                   :env          {:dev true}
                   }
             }

  :figwheel {
             :server-port 3460
             :css-dirs    ["resources/public/css"]})
