# jmeter-sample

## requirements
- java 11+
- Maven 3.5+

## tech stack
- [JMeter](https://jmeter.apache.org/)
- [JMeter DSL](https://abstracta.github.io/jmeter-java-dsl/)

## setup
### git clone
```
$ cd ${your workspace}
$ git clone https://github.com/takahiroaoki/jmeter-sample.git
```

### download JMeter
```
$ cd jmeter-sample

# download
$ curl -O https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-5.6.3.tgz

# unfreez
$ tar -xf apache-jmeter-5.6.3.tgz
```

### generate and set JMX file
```
$ sh generate.sh
```

## boot
### GUI mode
For trial and error on local PC.
```
# @apache-jmeter-5.6.3/bin
$ sh jmeter.sh
```

### CUI mode
For implementation on server.
```
# @apache-jmeter-5.6.3/bin
## -n: GUI mode
## -f: overwrite report
$ ./jmeter -nf -t [jmx file] -l [report file] -j [log file] -e -o [Path to web report folder]
```

If your performance test runs so long, you can use nohup command and so output log file to /dev/null and so on.

Search for more information at the [document](https://jmeter.apache.org/usermanual/get-started.html#options).

## trouble shooting
### JMeter cannot read User Defined Variables
The following file path should be relative path from execution context.

I recommend you that you execute jmeter fron "bin" directory, and the relative path starts from "bin" directory.
```
${__StringFromFile(file path)}
```