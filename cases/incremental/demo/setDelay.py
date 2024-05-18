# Script in order to delay some threadGroups

# execute from project root
# python3 ./cases/incremental/demo/setDelay.py

import xml.etree.ElementTree as ET

def main():
    FILE_CONTEXT = "cases/incremental/demo/"
    JMX = FILE_CONTEXT + "case.jmx"

    tree = ET.parse(JMX)
    root = tree.getroot()
    for thread_group in root.iter("ThreadGroup"):
        delay = 0
        if thread_group.get("testname").endswith("-1"):
            delay = 10

        long_prop = thread_group.find("longProp[@name='ThreadGroup.delay']")
        if long_prop is None:
            long_prop = ET.Element("longProp", {"name": "ThreadGroup.delay"})
        long_prop.text = str(delay)
        thread_group.append(long_prop)

    tree.write(JMX)

if __name__ == "__main__":
    main()