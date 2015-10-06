#ifndef _DATASOURCE_H
#define _DATASOURCE_H

#include <string>
#include <vector>

class Datasource {
 public:
  static void loadFile(std::string, std::vector<std::string>&);
};

#endif
