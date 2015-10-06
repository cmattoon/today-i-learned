#include "host.h"
#include "utils.h"
#include "scanner.h"
#include "datasource.h"
#include <pthread.h>
#define MAX_THREADS 5
struct thread_data {
  int thread_id;
  std::string ipAddress;
};

void threaded_ping(void *threadid) {
  Host *host = new Host();
  long tid;
  tid = (long)threadid;
  std::cout << "\033[01m[Thread " << tid << "\033[0m]: Start" << std::endl;
  
  if (host->ping( ip )) {
    std::cout<<"[Thread "<< tid <<"]: Server "<< ip << " is up"<<std::endl;
    
  } else {
    std::cout<<"[Thread "<<tid<<"]: Server "<<ip<<" is up"<<std::endl;
  }
  pthread_exit(NULL);
}

int main() {
  std::vector<std::string> host_list;
  Datasource::loadFile("ips.list", host_list);
  std::vector<std::string>::iterator iter;

  pthread_t threads[MAX_THREADS];
  int rc;
  int i = 0;
  struct thread_data *data;
  data = (struct thread_data*) threadarg;
  
  for (iter=host_list.begin(); iter != host_list.end(); ++iter) {
    data->ipAddr =*iter;
    data->thread_id = i;
    rc = pthread_create(&threads[i], NULL, threaded_ping, (void *)i);
    i++;
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
