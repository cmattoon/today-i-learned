#include "host.h"
#include "utils.h"
#include "scanner.h"


int main() {
  Scanner::readFileAndPingHosts("ips.list");
  /*
  Host *localhost = new Host();
  static const char* arr[] = {"172.21.8.102", "127.0.0.1", "172.21.15.55", "172.21.13.54"};

  std::vector<std::string> host_list(arr, arr+sizeof(arr)/sizeof(arr[0]));
  
  std::vector<std::string>::iterator iter;
  for (iter=host_list.begin(); iter != host_list.end(); ++iter) {
    std::cout << " [+] Attempting to ping host \033[33m" << *iter << "\033[0m" << std::endl;

    if (localhost->ping(*iter)) {
      std::cout << "     [\033[32m-OK-\033[0m] Host is up!" << std::endl;
    } else {
      std::cout << "     [\033[31mFAIL\033[0m] Host not responding to ping!" << std::endl;
    }
    }
  */
}
