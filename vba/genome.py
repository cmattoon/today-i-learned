#!/usr/bin/env python
"""Parses genome files and generates an output image
Genome files at: http://hgdownload.cse.ucsc.edu/goldenPath/hg38/chromosomes/
@todo - clean, document
"""
import os, sys, math
import datetime
import colorsys
from glob import glob
from md5 import md5
from PIL import Image, ImageDraw

COLORIZE = True
ATG = 'ATG'
TGA = 'TGA'
TAA = 'TAA'
TAG = 'TAG'

STOP_CODON_LIST = [ TGA, TAA, TAG ]

COLORS = {
    ATG: (0, 255, 0),
    TAA: (255, 0, 0),
    TAG: (255, 0, 0),
    TGA: (255, 0, 0),
    'T': (0, 255, 50),
    'A': (239, 0, 255),
    'C': (0, 117, 255),
    'G': (169, 255, 0)
    }

def pixelize(group):
    if group in [ATG, TAA, TAG, TGA]:
        for i in range(3):
            yield COLORS[group]
    else:
        for ch in group:
            try:
                yield COLORS[ch]
            except KeyError:
                sys.stderr.write(ch)
                yield (200, 200, 200)
    
def genome_image(filename):
    img = 'output.png'
    pixels = []
    size = (5000, 1)
    for group in get_groups(get_genome(filename)):
        for px in pixelize(group):
            for i in range(3):
                pixels.append(px)
        pixels.append((255, 255, 255))

    lines = int(len(pixels) / size[0])+1
    size = (size[0], lines)

    im = Image.new('RGB', size)
    im.putdata(pixels)
    im.save(img)
    return im

def showImage(image):
    """Provide a filepath"""
    img = Image.open(image)
    img.show()

def colorize(val):
    val = (val / 255.0)
    rgb = colorsys.hsv_to_rgb(val, 0.99, val)
    return (int(rgb[0]*255), int(rgb[1]*255), int(rgb[2]*255))

def genImage(bytes, size, mx=None):
    bytes = [ord(byte) for byte in bytes]
    bytes = bytes if mx is None else bytes[:mx]

    if COLORIZE is True:
        img = [colorize(b) for b in bytes]
    else:
        img = [(b,b,b) for b in bytes]

    lines = int(len(bytes) / size[0])+1
    size = (size[0], lines)
    im = Image.new('RGB', size)
    im.putdata(img)
    return im

def processFile(filename, width, outdir, outfile=None):
    maxsize = 180000
    size = (width, 1)
    pixels = (size[0] * size[1])
    bytes = getBytes(filename, maxsize)
    img = genImage(bytes, size, maxsize)
    fhash = md5(bytes).hexdigest()

    if outfile is None:
        outfile = "%s-%s.png" % (fhash, str(size[0]))
        outfile = os.path.join(outdir, outfile)
    
    img.save("%s" % (outfile))
    return (outfile, fhash)

def get_genome(filename):
    with open(filename, 'rb') as genome:
        return "".join([line.strip().upper() for line in genome])

def get_groups(genome):
    for i in xrange(0, len(genome), 3):
        yield genome[i:i+3]

if __name__ == '__main__':            
    genome_image(sys.argv[1])
