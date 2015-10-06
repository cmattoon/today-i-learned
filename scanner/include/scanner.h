#ifndef _SCANNER_H
#define _SCANNER_H

#include <string>
#include <vector>

class Scanner {
  std::vector<std::string> host_list;
 public:
  static void readFileAndPingHosts(std::string);
  void pingHosts();
};

#endif
