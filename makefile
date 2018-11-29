uberjar: target/alphasample.jar

target/alphasample.jar:
	clj -A:depstar -m hf.depstar.uberjar target/alphasample.jar

main: target/alphasample.jar
	ls Smiley.png | java -cp target/alphasample.jar clojure.main -m alphasample.main 20 100

