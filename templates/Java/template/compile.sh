#!/bin/sh
sources="$(find -name *.java)"
javac -Xlint -cp "src/" -d "bin/" $sources
