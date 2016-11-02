#!/bin/sh
if [ $# -eq 0 ]; then
    name="UnnamedProject"
else
    name="$@"
fi
cp -r template $name
