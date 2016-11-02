#!/bin/sh
if [ $# -eq 0 ]; then
    name=${PWD##*/}
else
    name="$@"
fi
mcs -out:bin/$name.exe src/*.c
