#!/bin/sh

mvn clean test

python3 ./cases/incremental/demo/setDelay.py


rm -r apache-jmeter-5.6.3/bin/cases
cp -r cases apache-jmeter-5.6.3/bin/
