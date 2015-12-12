#!/usr/bin/env python
import matplotlib.pyplot as plt
import numpy
import sys, struct
from scipy.fftpack import fft, fftfreq, fftshift


def getlines(filename):
    with open(filename) as fd:
        return [line.strip() for line in fd.readlines()]

def replay_packets(filename):
    for line in getlines(filename):
        yield tuple(int(i) for i in line[1:-1].split(','))


def replay_bytes(filename):
    """Reconstructs binary packets based on reading in
       17-element tuples each representing a packet.
    """
    for packet in replay_packets(filename):
        yield struct.pack('BBBBBBBBBBBBBBBBB', *packet)

ch1 = []
ch2 = []
ch3 = []
ch4 = []
ch5 = []
ch6 = []

def do_fft(ch):
    samples = len(ch)
    spacing = 1.0 / len(ch)
    x = numpy.linspace(0.0, samples * spacing, samples)
    y = [c for c in ch]

    xf = numpy.linspace(0.0, 1.0/(2.0*spacing), samples/2)
    yf = fft(ch)
    print x
    print y
    print("-----------")
    print xf
    print yf
    plt.plot(xf, 2.0/samples * numpy.abs(yf[0:samples/2]))
    plt.grid()
    plt.show()


last = -1
for pkt in replay_packets(sys.argv[1]):
    seq = int(pkt[3])
    if seq > last:
        ch1.append(pkt[5])
        ch2.append(pkt[7])
        ch3.append(pkt[9])
        ch4.append(pkt[11])
        ch5.append(pkt[13])
        ch6.append(pkt[15])
        """
        group[seq] = {
            'CH1': { 'low': pkt[4], 'high': pkt[5] },
            'CH2': { 'low': pkt[6], 'high': pkt[7] },
            'CH3': { 'low': pkt[8], 'high': pkt[9] },
            'CH4': { 'low': pkt[10], 'high': pkt[11] },
            'CH5': { 'low': pkt[12], 'high': pkt[13] },
            'CH6': { 'low': pkt[14], 'high': pkt[15] }
            }
            """
        last = int(seq)
    else:
        print ch1

        last = -1
        do_fft(ch1)
        do_fft(ch2)
        ch1 = []
        ch2 = []
        ch3 = []
        ch4 = []
        ch5 = []
        ch6 = []
