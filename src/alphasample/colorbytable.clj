(ns alphasample.colorbytable
  (:require
   [clojure.string :as string]
   [clojure.pprint :refer [pprint]]
   ))

;; Usage:
;;  cat resources/output/table.tsv | clj -m alphasample.colorbytable resources/output/modified-images

;; 1. Collect all places to place dots, and group by image
;; 2. Handle images, one by one

(def sample-formatted-input
  {:imagepath "resources/input/W_DJI-10, BE-1.png"
   :pixelnr 1689
   :rgba [255	250	124	255]})

(defn table-line->report-entry [line]
  (try
    (let [[path nr r g b a] (string/split line #"\t")]
      {:imagepath path
       :pixelnr (read-string nr)
       :rgba (mapv read-string [r g b a])})
    (catch Exception e
      nil)
    ))

(comment
  (prn (table-line->report-entry "resources/input/W_DJI-10, BE-1.png	1689	255	250	124	255"))
  )

(defn collect-image-entry
  [collected entry]
  (let [path (:imagepath entry)]
    (cond
      (contains? collected (:imagepath entry))
      (update collected path conj (select-keys entry [:pixelnr :rgba]))

      :else
      (assoc collected path
             [(select-keys entry [:pixelnr :rgba])]))))

(defn sample-output [output-dir data]
  (clojure.java.io/make-parents (str output-dir "/testfile"))
  (for [[fname fdata] data]
    (let [output-filename (str output-dir
                               "/"
                               (-> fname
                                   (string/split #"/")
                                   last))]
      output-filename)))

(defn color-images [output-dir data]
  ;; Ensure that output directory exists
  (clojure.java.io/make-parents (str output-dir "/testfile"))
  (for [[fname fdata] data]
    ;; A. copy image to where it should be

    ;; Open image

    ;; Color image

    ;; Save image
    (let [output-filename (str output-dir
                               "/"
                               (-> fname
                                   (string/split #"/")
                                   last))]
      output-filename)))

(comment
  (-> {}
      (collect-image-entry
       (table-line->report-entry "resources/input/W_DJI-10, BE-1.png	1689	255	250	124	255"))
      (collect-image-entry
       (table-line->report-entry "resources/input/W_DJI-10, BE-1.png	1699	253	243	109	255"))
      (collect-image-entry
       (table-line->report-entry "resources/input/Sand_DJI-10, BE-2 copy.png	1805	241	222	31	255"))
      pprint
      )
  )

(->> (slurp "resources/output/smalltable.tsv")
     string/split-lines
     (map table-line->report-entry)
     (reduce collect-image-entry {})
     (take 1)
     (sample-output "resources/step-3")
     )

(defn -main [output-folder]
  (->> (line-seq (java.io.BufferedReader *in*))
       (map table-line->report-entry)
       (reduce collect-image-entry {})
       (sample-output output-folder)
       ))

(defn collect-by-imagepath [entries])


