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

# Tabular results

The script produces tabular results.

The results look like this:

```
$ head -n 5 resources/output/table.tsv 
resources/input/W_DJI-10, BE-1.png	1689	255	250	124	255
resources/input/W_DJI-10, BE-1.png	1699	253	243	109	255
resources/input/W_DJI-10, BE-1.png	1894	255	246	119	255
resources/input/W_DJI-10, BE-1.png	2080	252	245	113	255
resources/input/W_DJI-10, BE-1.png	2179	255	248	118	255
```

This means, with headings:

| File path | pixel number | R | G | B | A |
| resources/input/W_DJI-10, BE-1.png | 1689 | 255 | 250 | 124 | 255 |
| resources/input/W_DJI-10, BE-1.png | 1699 | 253 | 243 | 109 | 255 |
| resources/input/W_DJI-10, BE-1.png | 1894 | 255 | 246 | 119 | 255 |
| resources/input/W_DJI-10, BE-1.png | 2080 | 252 | 245 | 113 | 255 |
| resources/input/W_DJI-10, BE-1.png | 2179 | 255 | 248 | 118 | 255 |
