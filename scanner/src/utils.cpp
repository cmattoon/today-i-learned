#include <iostream>
#include <sstream>
#include <stdio.h>
#include <string>
#include <vector>
#include <limits>
#include "utils.h"

std::string exec(const std::string cmd) {
  const char* command = cmd.c_str();
  FILE* pipe = popen(command, "r");

  if (!pipe) {
    return NULL;
  }

  char buffer[128];
  std::string result = "";

  try {
    while (!feof(pipe)) {
      if (fgets(buffer, 128, pipe) != NULL) {
	result += buffer; 
      }
    }  
  } catch (int e) {
    // 'result += buffer' can throw exception, so make sure pipe is closed.
    std::cout << "Caught exception while executing command. (Exception #" << e << ")" << std::endl;
    pclose(pipe);
    return NULL;
  }

  pclose(pipe);
  return result;
};
/*
std::vector<std::string>
split(const std::string &s, char delim, std::vector<std::string> &elems) {
  std::stringstream ss(s);
  std::string item;
  while (std::getline(ss, item, delim)) {
    elems.push_back(item);
  }
  return elems;
}
*/
std::vector<std::string>
split(std::string s, char delim) {
  std::vector<std::string> elems;
  std::string item;
  std::stringstream ss(s);
  while (std::getline(ss, item, delim)) {
    elems.push_back(item);
  }
  return elems;
}

void PressEnterToContinue() {
  std::cout << "Press Enter to continue..." << std::endl;
  std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
}
