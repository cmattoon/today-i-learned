#ifndef _CM_HOST
#define _CM_HOST
#include <string>
#include <vector>

class Host {
 public:
  int ping(const std::string);
  bool pingSuccess(std::vector<std::string>);
 private:
  void ping_(int);
};

#endif
