(ns alphasample.filters)

;; Pixel filters

(defn white? [[r g b a]]
  (= [255 255 255]
     [r g b]))

(defn black? [[r g b a]]
  (= [0 0 0]
     [r g b]))

(defn transparent? [[r g b a]]
  (= 0 a))

(defn opaque? [[r g b a]]
  (not= 0 a))

(defn average-rgba [rgba-values]
  (let [color-sum (reduce (fn [[r g b a] [dr dg db da]]
                        [(+ r dr)
                         (+ g dg)
                         (+ b db)
                         (+ a da)])
                      [0 0 0 0]
                      rgba-values)]
    (mapv #(float (/ % (count rgba-values))) color-sum)))
