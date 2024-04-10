#!/bin/sh

mvn clean test
rm -r apache-jmeter-5.6.3/bin/cases
cp -r cases apache-jmeter-5.6.3/bin/
