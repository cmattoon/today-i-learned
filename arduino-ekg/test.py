import curses
import serial
import struct
import sys

class Packet:
    def __init__(self, raw):
        self.channels = {}
        self.raw = raw
        self.parse()

    def parse(self):
        self.packet = struct.unpack('BBBBBBBBBBBBBBBBB', self.raw)
        ch = 1
        for i in xrange(4, 16, 2):
            self.channels[ch] = {
                'low': self.packet[i],
                'high': self.packet[i+1]
                }
            ch += 1

    def pretty(self):
        stdscr.addstr(1, 0, "              ")
        stdscr.addstr(1, 0, "Packet: %d" % (self.packet[3]))
        for ch in self.channels:
            low = self.channels[ch]['low']
            high = self.channels[ch]['high']

            high = int((high / 256.0) * 60) * "|"
            
            low = "+" if low is 1 else " "
            stdscr.addstr(ch+4, 0, " "*80)
            stdscr.addstr(ch+4, 0, "[Channel %d][%s] %s" % (ch, low, high), curses.color_pair(ch+1))
            stdscr.refresh()

class Reader:
    START_BYTE = '\xA5'
    def __init__(self, dev):
        self.ser = serial.Serial(dev, baudrate=57600, parity=serial.PARITY_NONE)

    def correct(self):
        byte = self.ser.read()
        while byte != self.START_BYTE:
            byte = self.ser.read()
        return Packet(byte + str(self.ser.read(16)))

    def loop(self):
        data = self.ser.read(17)
        while data:
            data = self.ser.read(17)
            if not data.startswith(self.START_BYTE):
                yield self.correct()
            else:
                yield Packet(data)

if __name__ == '__main__':
    stdscr = curses.initscr()
    curses.start_color()
    curses.use_default_colors()

    for i in range(0, 7):
        curses.init_pair(i+1, i, -1)

    r = Reader(sys.argv[1])
    for data in r.loop():
        data.pretty()
