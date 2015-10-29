(ns horror.slidesshow
  (:require
    [om.dom :as dom]
    [goog.dom :as gdom]
    [goog.object]
    [om.next :as om :refer-macros [defui]]
    )
  )

(enable-console-print!)

(defmulti read (fn [_ k] k))
(defmulti mutate (fn [_ k _] k))

(defmethod read :slideshow [{:keys [state parser selector] :as env} key params] {:value (:slideshow @state)})

(defmethod mutate 'slideshow/next-image
           [{:keys [state]} _ _]
           (let [current-item (-> @state :slideshow :item)
                 image-count  (-> @state :slideshow :images count)
                 next-item    (if (= (inc current-item) image-count) 0 (inc current-item))]
                {:action (fn [] (swap! state update-in [:slideshow :item] (fn [_] next-item)))}))

(defn make-slideshow []
      {:item   0
       :images ["the-matrix-animated-gif.gif" "giphy2.gif"]})

(def app-state (atom {:slideshow (make-slideshow)}))

(def parser (om/parser {:read read :mutate mutate}))

(def reconciler (om/reconciler {:state app-state :parser parser}))

(defui App
       static om/IQuery
       (query [this]
              [{:slideshow [:item :images]}])
       Object
       (render [this]
               (let [slideshow (:slideshow (om/props this))
                     updater   (fn [] (om/transact! this '[(slideshow/next-image)]))
                     item      (:item slideshow)
                     imgref    (str "images/" (nth   (:images slideshow) item))
                     time      (if (= item 0) (int (* 15000 (.random js/Math))) 1000)]
                    (js/setTimeout updater time)
                    (dom/div #js {:className "s-app-container"}
                            (dom/img #js {:className "myfull" :src imgref } )))))

(def app (om/factory App))

(defn render-slideshow [] (om/add-root! reconciler App (gdom/getElement "app")))

(defn reload [] (render-slideshow))

(render-slideshow)

