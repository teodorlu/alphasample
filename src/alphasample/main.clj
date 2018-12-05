(ns alphasample.main
  (:gen-class)
  (:require
   [mikera.image.core :as image]
   [alphasample.convert :as convert]
   [alphasample.sample :as sample]
   [clojure.string :as string]))

(defn report-image [imagepath img samples threshold]
  (let [pixels (->> (image/get-pixels img)
                    (map convert/color->rgba)
                    vec)
        chosen-pixels (sample/sample pixels
                                     (fn [[r g b a]]
                                       (> a threshold))
                                     samples)]
    (mapv (fn [pixel]
            {:image-path imagepath
             :pixel-nr pixel
             :rgba (get pixels pixel)})
          (sort chosen-pixels))))


(defn report->table-line [{:keys [image-path pixel-nr rgba]}]
  (string/join "\t" (map str (concat [image-path pixel-nr] rgba))))

;; Usage:
;;  ls resources/input/*.png | clj -m alphasample.main 20 100.0

(defn -main [samples threshold]
  (let [samples (Long/parseLong samples)
        threshold (Double/parseDouble threshold)]
    (doseq [imagepath (line-seq (java.io.BufferedReader. *in*))]
      (try
        (let [img (image/load-image imagepath)]
          (doseq [selection (report-image imagepath img samples threshold)]
            (println (report->table-line selection))
            ))
        (catch Exception e
          (println "Error processing exception for " imagepath ":")
          (prn e)
          )
        ))))
