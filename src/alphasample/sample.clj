(ns alphasample.sample)

;; Sample pixels within some restrictions

(defn sample
  "Sample from the pixels that are considered to be okay"
  [pixels okay? n]
  (->> (map vector (range) pixels)
       (filter (fn [[_ pix]]
                 (okay? pix)))
       shuffle
       (take n)
       (mapv (fn [[index _]] index))))
