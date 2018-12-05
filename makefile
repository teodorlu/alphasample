uberjar: target/alphasample.jar

target/alphasample.jar: ./src/alphasample/main.clj ./src/alphasample/convert.clj ./src/alphasample/core.clj ./src/alphasample/explore.clj ./src/alphasample/filters.clj ./src/alphasample/sample.clj ./src/alphasample/colorbytable.clj
	clj -A:depstar -m hf.depstar.uberjar target/alphasample.jar

main: target/alphasample.jar
	ls "resources/input/W_DJI-10, BE-1.png" | java -cp target/alphasample.jar clojure.main -m alphasample.main 20 100

step-1:
	mkdir -p resources/step-2
	ls resources/step-1/*.png | clj -m alphasample.main 50 100 > resources/step-2/table.tsv

step-2:
	mkdir -p resources/step-3

run:
	echo 123

