(ns alphasample.convert
  (:require
   [mikera.image.colours :as color]))

(defn rgba->color [[r g b a]]
  (color/argb-from-components r g b a))

(defn color->rgba [color]
  (color/components-argb color))
