(ns alphasample.explore
  (:require
   [mikera.image.core :as image]
   [mikera.image.colours :as color]
   [alphasample.filters :refer
    [black? white? transparent? opaque? average-rgba]]
   [alphasample.convert :as convert]
   [alphasample.sample :as sample]))

;; Try to get the transparancy for the image.

(def img
  (image/load-image "/home/teodorlu/workspace/clojure/alphasample/Smiley.png"))

(defn mod-pixels-rgba! [img rgba-filter]
  (image/set-pixels img
                    (->> img
                         image/get-pixels
                         (map convert/color->rgba)
                         rgba-filter
                         (map convert/rgba->color)
                         int-array)))

(defn make-first-200-red [pixels]
  (concat (take 200 (repeat [255 0 0 255]))
          (drop 200 pixels)))


(defn printhex [val]
  (println (format "%x" val)))

(printhex color/blue)
;; ff0000ff
(printhex (color/argb 0 0 255 255))
;; ffff0000

(printhex color/red)
;; ffff0000
(printhex (color/argb 255 0 0 255))
;; 00ffff00

(printhex color/green)
;; ff00ff00
(printhex (color/argb 0 255 0 255))
;; 00ff00ff

(def some-pixels (atom nil))
(defn set-some-pixels! [pixels]
  (reset! some-pixels pixels)
  ;; Seq, not vector!
  pixels)

@some-pixels

(get @some-pixels 33)

(do
  (defn make-20-opaque-pixels-red [pixels]
    (let [selection (sample/sample pixels opaque? 20)
          special? (set selection)]
      (mapv (fn [i]
              (if false
                [255 0 0 255] ;; red
                (get pixels i)))
            (range (count pixels)))))
  (make-20-opaque-pixels-red @some-pixels))


(do
  (def new-img (image/load-image "/home/teodorlu/workspace/clojure/alphasample/Smiley.png"))
  (def new-pixels (->> new-img
                       image/get-pixels
                       (map convert/color->rgba)
                       set-some-pixels!
                       (map (fn [[r g b a]]
                              [r g b a]))
                       (map convert/rgba->color)
                       ;; Now, back to a seq of long values. Can we get an image?
                       int-array))
  (image/set-pixels new-img new-pixels)
  ;; (image/set-pixels new-img (int-array [16776960 16776960 16776960 16776960]))
  (image/show new-img)
  )



