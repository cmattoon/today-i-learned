#!/usr/bin/env python
import socket
import sys

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

addr = ('localhost', 10000)
print("Connecting to %s:%s" % (addr))
sock.connect(addr)

try:
    message = "This is the message."
    print("Sending '%s'" % (message))
    sock.sendall(message)

    received = 0
    expected = len(message)

    while received < expected:
        data = sock.recv(16)
        received += len(data)
        print(">> %s" % (data))
finally:
    print("Closing...")
    sock.close()
