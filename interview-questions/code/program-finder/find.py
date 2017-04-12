#!/usr/bin/env python
"""

"""
import glob
import sys, os
import subprocess, shlex
import signal

E_NOARGS = 1
E_BADFILE = 2
E_OK = 0

def print_usage(name):
    print(os.linesep.join([
            "Usage: %s <list of programs to find>" % (name),
            "Example: %s ls ncat lsof" % (name)
            ]))
    return E_NOARGS

def which(program):
    """An implementation of `which`
    """

    def _exe(path):
        return os.path.isfile(path) and os.access(path, os.X_OK)

    fpath, fname = os.path.split(program)
    if fpath:
        if _exe(program):
            return program
    else:
        for path in os.environ['PATH'].split(os.pathsep):
            path = path.strip('"')
            exe = os.path.join(path, program)
            if _exe(exe):
                return exe

def main(args):
    with DelayedKeyboardInterrupt():
        return do_stuff(args)

def do_stuff(args):
    """Main section of execution. Moved here to clean it up
    after adding 'with DelayedKeyboardInterrupt' to main.

    Args:
      args (list)
    """
    for i in range(1, len(args)):
        path = which(args[i])

        if path:
            continue
        print("Cannot find '%s'" % (path))
        return E_BADFILE
    return E_OK

class DelayedKeyboardInterrupt(object):
    def __enter__(self):
        self.signal_received = False
        self.old_handler = signal.getsignal(signal.SIGINT)
        signal.signal(signal.SIGINT, self.handler)

    def handler(self, sig, frame):
        self.signal_received = (sig, frame)
        print('SIGINT received. Delaying KeyboardInterrupt.')

    def __exit__(self, type, value, traceback):
        signal.signal(signal.SIGINT, self.old_handler)
        if self.signal_received:
            self.old_handler(*self.signal_received)

if __name__ == '__main__':
    if len(sys.argv) > 1:
        exit(main(sys.argv))
    exit(print_usage(sys.argv[0]))
