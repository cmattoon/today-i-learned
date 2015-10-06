#include <string>
#include <fstream>
#include <vector>
#include <iostream>
#include "datasource.h"

void Datasource::loadFile(std::string filename, std::vector<std::string>& data_vector) {

  std::string current_line;
  std::ifstream infile;
  const char* char_fname = filename.c_str();
  std::cout << " [Datasource] Attempting to load " << char_fname << std::endl;
  infile.open(char_fname);

  if (infile.is_open()) {
    while (std::getline(infile, current_line)) {
      data_vector.push_back(current_line);
    }
    infile.close();
  }
}
