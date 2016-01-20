#!/usr/bin/env python
"""
"""
import sys
COLORMAP = {
    'black': '40',
    'red': '41',
    'green': '42',
    'orange': '43',
    'blue': '44',
    'magenta': '45',
    'cyan': '46',
    'white': '47'
    }

def print_graph(num, denom, color='blue', *args, **kwargs):
    try:
        color = COLORMAP[color]
    except KeyError:
        color = COLORMAP['blue']

    num = float(num)
    denom = float(denom)
    assert denom != 0.0

    pct = num / float(denom)
    width = kwargs.get('width', 80)
    if pct <= 1:
        bar = ' ' * int(width * pct)
    else:
        bar = ' ' * width
    pct = "%s"%(pct)
    print(" %s (%d/%d)\n \033[%sm%s\033[0m" % (pct, num, denom, color, bar))

a = float(sys.argv[1])
b = float(sys.argv[2])


print_graph(a, b)
print_graph(a+(a*0.1), b, 'green')
print_graph(a-(a*0.1), b, 'red')
print_graph(a-(a*0.35), b+b*0.15,'orange')
