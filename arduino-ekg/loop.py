#!/usr/bin/env python
"""Serial loopback/intercept.

Reads sys.argv[1] and writes to sys.argv[2],
or throws appropriate IO/OSErrors.

Example:
  ./serial-loop.py /dev/ttyACM0 /dev/ttySIM

Data from serial device /dev/ttyACM0 is read
and manipulated, then output to /dev/ttySIM.
"""
import os
import serial
import struct
import sys
import time
from serialmonitor import EKGModule


if __name__ == '__main__':
    ekg = EKGModule(sys.argv[1])
    out = serial.Serial(
        port=sys.argv[2],
        baudrate=ekg.baudrate,
        parity=ekg.parity,
        stopbits=ekg.stopbits,
        bytesize=ekg.bytesize
        )

    for packet in ekg.capturePackets():
        sys.stdout.write("%s%s" % (str(packet), os.linesep))
        out.write(struct.pack('BBBBBBBBBBBBBBBBB', *packet))
