#include <iostream>
#include <stdio.h>
#include <string.h>
#include <sstream>
#include <vector>
#include "utils.h"
#include "host.h"

int Host::ping(const std::string human_ip) {
  std::string command = "/bin/ping -c 1 ";
  command += human_ip;
  std::string result = exec(command);
  std::vector<std::string> lines = split(result, '\n');
  return pingSuccess(lines);
   
};

/**
 * This is hideous.
 * Abstract the PingResponse, so I can use sockets later.
 * For now, try and parse the output of `ping -c 1 <IP_ADDR>`
 *
 * -----------------------------------------------------------
 * PING 127.0.0.1 (127.0.0.1) 56(84) bytes of data.
 * 64 bytes from 127.0.0.1: icmp_seq=1 ttl=64 time=0.029 ms
 *
 * --- 127.0.0.1 ping statistics ---
 * 1 packets transmitted, 1 received, 0% packet loss, time 0ms
 * rtt min/avg/max/mdev = 0.029/0.029/0.029/0.000 ms
 * -----------------------------------------------------------
 */
bool Host::pingSuccess(std::vector<std::string> resultlines) {
  bool result = false;
  int sent = 0;
  int recv = 0;  

  if (resultlines.size() >= 4) {
    // We want the '1 packets transmitted, x received...' line
    std::stringstream ss;
    ss << resultlines[4];
    int num;
    std::string temp;
    int i = 0;

    
    while (std::getline(ss, temp, ',')) {

      if (std::stringstream(temp) >> num) {
	if (i == 0) {
	  sent = num;
	} else if (i == 1) {
	  recv = num;
	}
	i++;
      }
    }
  }
  return (sent == recv && recv > 0);

};
