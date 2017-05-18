#!/usr/bin/env python
import socket
import sys


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
addr = ('localhost', 10000)

def err(msg):
    print >>sys.stderr, msg


err('Starting on %s:%s' % addr)

sock.bind(addr)
sock.listen(1)

while True:
    err('.')
    conn, client = sock.accept()
    try:
        err('connection from %s:%s' % (client))

        while True:
            data = conn.recv(16)
            err('received "%s"' % (str(data)))
            if data:
                err('Replaying...')
                conn.sendall(data)
            else:
                err('No more data from %s' % (str(client)))
                break
    finally:
        conn.close()
