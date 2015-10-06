#include <string>
#include <iostream>


void msg_out(std::string message) {
  std::cout << message << std::endl;
}

void msg_ok(std::string message) {
  std::cout << " [\033[32m-OK-\033[0m] " << message << std::endl;
}

void msg_err(std::string message) {
  std::cout << " [\033[31mFAIL\033[0m] " << message << std::endl;
}
