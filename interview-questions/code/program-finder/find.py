#!/usr/bin/env python
import sys, os
import subprocess, shlex
import signal

def print_usage(name):
    print(os.linesep.join([
            "Usage: %s <list of programs to find>" % (name),
            "Example: %s ls ncat lsof" % (name)
            ]))
    return 1

def which(program):
    """A quick passthrough for `which`
    This could search the environment variable PATH too...

    Returns a string, or None
    """
    px = subprocess.Popen(shlex.split("which %s" % (program)),
                          stdout=subprocess.PIPE,
                          stderr=subprocess.PIPE)

    out, err = px.communicate()

    if out:
        return out.strip()
    return None

def is_exe(path):
    """Tests if `path` is executable
    Returns bool
    """
    return os.access(path, os.X_OK)

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

        if path is not None and is_exe(path):
            print("Found binary! (%s)"% (path))
            continue

        print("Cannot find %s" % (args[i]))
        return 1
    return 0

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
