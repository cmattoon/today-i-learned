#!/usr/bin/env python
"""

"""
import socket
import sys

class QS:
    """QuickSocket"""
    def __init__(self, host='localhost', port=0):
        self.host = host
        self.port = int(port)

        if self.host and self.port:
            self.connect(self.host, self.port)

    def connect(self, host, port):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.connect((host, port))

    def close(self):
        return self.sock.close()

    def readAll(self, bs=128):
        data = None
        while True:
            print("R")
            _data = self._read(bs)
            print(")")
            if not _data: break
            print(">> %s" % (str(_data)))
            data += _data
        return data

    def _read(self, bs=128):
        return self.sock.recv(bs)

    def sendAll(self, message):
        return self.sock.sendall(message)


def get_socket(t=socket.AF_INET, s=socket.SOCK_STREAM):
    return socket.socket(t,s)

def host2ip(host):
    try:
        return socket.gethostbyname(host)
    except socket.gaierror:
        return None




h2i=host2ip

if __name__ == '__main__':
    if len(sys.argv) is 3:
        sock = QS(sys.argv[1], sys.argv[2])
        data = sock.readAll(16)
    else:
        sock = QS()
