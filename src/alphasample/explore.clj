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
  (image/load-image "/home/teodorlu/git/alphasample/Smiley.png"))

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
  (reset! some-pixels (vec pixels))
  ;; Seq, not vector!
  pixels)

(do
  (defn make-20-opaque-pixels-red [pixels]
    (let [selection (sample/sample pixels opaque? 20)
          selected? (set selection)]
      (prn (sort selection))
      (prn "/" (count pixels))
      (mapv (fn [i]
              (if (selected? i)
                (do
                  (println "special" i)
                  [255 0 0 255])
                (get pixels i)))
            (range (count pixels)))))
  #_(make-20-opaque-pixels-red @some-pixels))

(defn paint-some-red [pixels]
  (vec (concat (take 900 (repeat [255 0 0 255]))
               (drop 900 pixels))))

(comment
  (do
    (def new-img (image/load-image "/home/teodorlu/workspace/clojure/alphasample/Smiley.png"))
    (def old-pixels (->> new-img
                         image/get-pixels
                         (map convert/color->rgba)
                         vec))
    (def selection (sample/sample old-pixels (fn [[r g b a]]
                                               (> a 100))
                                  200))
    (def selected? (set selection))
    (defn pixel-filter [i]
      (if (selected? i)
        [255 0 0 255]
        (get old-pixels i)
        ))
    (def new-pixels (->> (range (count old-pixels))
                         (map pixel-filter)
                         (map convert/rgba->color)
                         ;; Now, back to a seq of long values. Can we get an image?
                         int-array))
    (image/set-pixels new-img new-pixels)
    (image/save new-img "/home/teodorlu/workspace/clojure/alphasample/Smiley-sampled.png")
    (image/show new-img)
    ))



