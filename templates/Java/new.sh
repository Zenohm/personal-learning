#!/bin/sh
if [ $# -eq 0 ]; then
    name="hw?"
else
    name="$@"
fi
cp -r template $name
