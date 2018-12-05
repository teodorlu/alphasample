uberjar: target/alphasample.jar

target/alphasample.jar: ./src/alphasample/main.clj ./src/alphasample/convert.clj ./src/alphasample/core.clj ./src/alphasample/explore.clj ./src/alphasample/filters.clj ./src/alphasample/sample.clj
	clj -A:depstar -m hf.depstar.uberjar target/alphasample.jar

main: target/alphasample.jar
	ls "resources/input/W_DJI-10, BE-1.png" | java -cp target/alphasample.jar clojure.main -m alphasample.main 20 100

run:
	echo 123

