#include <iostream>
#include <string>
#include "scanner.h"
#include "datasource.h"
#include "host.h"
#include "messages.h"


void Scanner::readFileAndPingHosts(std::string filename) {
  Host *host = new Host();

  std::vector<std::string> host_list;
  
  Datasource::loadFile(filename, host_list);
  
  std::vector<std::string>::iterator iter;
  for (iter=host_list.begin(); iter !=host_list.end(); ++iter) {
    msg_out(" [DEBUG] Scanning " + *iter + " ...");

    if (host->ping(*iter)) {
      msg_ok("Host " + *iter + " responded to ping!");
    } else {
      msg_err("Host " + *iter + " did not respond to ping!");
    }
    msg_out("");
  }
  
}
