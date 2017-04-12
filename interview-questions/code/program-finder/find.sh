#!/bin/bash
# Block user from Ctrl+C (SIGINT)
trap '' INT

# get rid of argv[0]
while test ${#} -gt 0
do
    PROG=$(which $1)

    if [ ! -f "${PROG}" ] || [ ! -x "${PROG}" ]; then
        exit 1
    fi
    shift
done
exit 0