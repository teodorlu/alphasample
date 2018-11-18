# Mac usage instructions

Setup:

1. Install Homebrew
2. Install git: `brew install git`
3. Clone this repository: `git clone
   https://github.com/teodorlu/alphasample.git`. You need to know where you
   cloned it. When you run git clone URL, your project is cloned to the current directory.
4. Install clojure: `brew install clojure`

Usage:

- In the project directory, run `clj -m alphasample.main`.

# Java Swing image representation

Images are represented as RGBA in CSS: rgba(R, G, B, A). In Java Swing, there
seems to be a hexadecimal encoding. Everything is encoded within an integer.

Integer: 32 bits.

RGBA, (0-255): 8 bits, 4 values: 32 bits.

Integer encoding of RGBA values: #AARRGGBB, expressed in base16/hex.
