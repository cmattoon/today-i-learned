import os
import serial
import sys

class Monitor:
    def __init__(self, device, **kwargs):
        """Args:
           - device (string): The device name (e.g., /dev/ttyACM0)
        """
        self.deviceName = device

