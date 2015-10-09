#include "host.h"
#include "utils.h"
#include "scanner.h"
#include "datasource.h"
#include <pthread.h>
#include <algorithm>
#include <future>


#define MAX_THREADS 5

struct thread_data {
  int thread_id;
  std::string ipAddress;
};

bool future_ping(const std::string &ip_address) {
  std::promise<bool> promise;
  std::future<bool> future = promise.get_future();

  
  std::thread thread([&promise, ip_address]() {
      Host *host = new Host();
      std::cout << "    [Inside thread]["<<ip_address<<"]" << std::endl;
      promise.set_value(host->ping(ip_address));
    });
  bool result = future.get();
  thread.join();
  return result;
}


int main() {
  std::vector<std::string> host_list;
  Datasource::loadFile("ips.list", host_list);
  std::vector<std::string>::iterator iter;
  
  for (iter=host_list.begin(); iter != host_list.end(); ++iter) {
    std::promise<bool> promise;
    std::future<bool> future = promise.get_future();
    std::string ip_address = *iter;

    std::thread the_thread([&promise, ip_address]() {
	Host *host = new Host();
	std::cout << " Thread: Checking " << ip_address << std::endl;
	promise.set_value(host->ping(ip_address));
      });

    if (future.get()) {
      std::cout << " [+] Host " << *iter << " is up" << std::endl;
    } else {
      std::cout << " [+] Host " << *iter << " is down" << std::endl;
    }
    the_thread.join();
  }

  //Scanner::readFileAndPingHosts("ips.list");
  
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
