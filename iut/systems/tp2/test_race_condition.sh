#!/bin/sh
cmd=${1:-./race_condition}

if [ ! -x "$cmd" ]; then
    echo "$cmd is not an executable file"
    exit -1
fi

while true; do
    "$cmd" 2>/dev/null
done | uniq
