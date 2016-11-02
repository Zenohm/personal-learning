#!/bin/sh
if [ $# -eq 0 ]; then
    name=${PWD##*/}
else
    name="$@"
fi
zip -r "arc/$name.zip" .
