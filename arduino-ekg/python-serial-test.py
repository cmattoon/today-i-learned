#!/usr/bin/env python
import curses
import os
import serial
import struct
import sys
import time

class EKGModule:
    START_BIT = '\xA5'

    def __init__(self, port, **kwargs):
        self.port = port
        self.baudrate = kwargs.get('baudrate', 57600)
        # serial.baudrate_constants[57600]
        self.parity = kwargs.get('parity', serial.PARITY_NONE)
        self.stopbits = kwargs.get('stopbits', serial.STOPBITS_ONE)
        self.bytesize = kwargs.get('bytesize', serial.EIGHTBITS)

        self.serial = serial.Serial(
            port=self.port,
            baudrate=self.baudrate,
            parity=self.parity,
            stopbits=self.stopbits,
            bytesize=self.bytesize
            )

    def _verbose(self, msg, v=1):
        sys.stdout.write("%s%s" % (str(msg), os.linesep))

    def findPacket(self):
        """Finds a start byte, reads the remaining 16 bytes
        and returns the string. This leaves the pointer at an
        offset equal to the packet size. We can hopefully just
        read one packet at a time now.
        """
        start = self.serial.read()
        while start != self.START_BIT:
            start = self.serial.read()
        return str("%s%s" % (str(start), str(self.serial.read(16))))


    def capturePackets(self):
        """Continuously polls the serial interface (e.g., /dev/ttyACM0)
           and yields the packet.

        """
        packet = self.serial.read(17)
        while packet is not None:
            if packet.startswith(str(self.START_BIT)):
                current = struct.unpack('BBBBBBBBBBBBBBBBB', packet)
                packet = self.serial.read(17)
                yield current
            packet = self.findPacket()

    def _pp_packet(self, packet):
        """
        TXBuf[0] = 0xa5;    //Sync 0
        TXBuf[1] = 0x5a;    //Sync 1
        TXBuf[2] = 2;       //Protocol version
        TXBuf[3] = 0;       //Packet counter
        TXBuf[4] = 0x02;    //CH1 High Byte
        TXBuf[5] = 0x00;    //CH1 Low Byte
        TXBuf[6] = 0x02;    //CH2 High Byte
        TXBuf[7] = 0x00;    //CH2 Low Byte
        TXBuf[8] = 0x02;    //CH3 High Byte
        TXBuf[9] = 0x00;    //CH3 Low Byte
        TXBuf[10] = 0x02;   //CH4 High Byte
        TXBuf[11] = 0x00;   //CH4 Low Byte
        TXBuf[12] = 0x02;   //CH5 High Byte
        TXBuf[13] = 0x00;   //CH5 Low Byte
        TXBuf[14] = 0x02;   //CH6 High Byte
        TXBuf[15] = 0x00;   //CH6 Low Byte
        TXBuf[2 * NUMCHANNELS + HEADERLEN] =  0x01;    // Switches state
        """
        ID_COLOR = "\033[36m"
        packet_id = "%s" % (packet[3])
        stdscr.addstr(0, 0, "[%s]" % (packet_id.rjust(3)))
        BLANK = " "*60
        ch = 1
        lines = []
        stdscr.addstr(1,1, str(packet))
        for i in xrange(4, 16, 2):

            high_byte = packet[i]
            low_byte = packet[i+1]

            if high_byte == 1:
                high_byte = "(X)"
            elif high_byte == 0:
                high_byte = "(-)"


            pct = int((float(low_byte) / 256.0) * 40.0)
            low_byte = "|"*pct

            flags = curses.color_pair(ch) | curses.A_BOLD
            high = " [Channel %d]\t%s" % (ch,high_byte)
            high = high.ljust(60)
            stdscr.addstr(i, 0, BLANK)
            stdscr.addstr(i, 0, high, flags)

            low = " [Channel %d]\t%s" % (ch, low_byte)
            low = low.ljust(60)
            stdscr.addstr(i+1, 0, BLANK)
            stdscr.addstr(i+1, 0, low, flags)

            ch += 1
            stdscr.refresh()

if __name__ == '__main__':
    ekg = EKGModule(sys.argv[1])
    stdscr = curses.initscr()
    curses.noecho()
    curses.cbreak()
    curses.start_color()
    curses.use_default_colors()
    curses.init_pair(1, curses.COLOR_RED, 0)
    curses.init_pair(2, curses.COLOR_GREEN, 0)
    curses.init_pair(3, curses.COLOR_YELLOW, 0)
    curses.init_pair(4, curses.COLOR_BLUE, 0)
    curses.init_pair(5, curses.COLOR_MAGENTA, 0)
    curses.init_pair(6, curses.COLOR_CYAN, 0)

    for packet in ekg.capturePackets():
        ekg._pp_packet(packet)

