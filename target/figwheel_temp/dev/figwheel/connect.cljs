(ns figwheel.connect (:require [horror.slidesshow] [figwheel.client] [figwheel.client.utils]))
(figwheel.client/start {:on-jsload (fn [& x] (if js/horror.slidesshow.on-load (apply js/horror.slidesshow.on-load x) (figwheel.client.utils/log :debug "Figwheel: :on-jsload hook 'horror.slidesshow/on-load' is missing"))), :build-id "dev", :websocket-url "ws://localhost:3460/figwheel-ws"})

