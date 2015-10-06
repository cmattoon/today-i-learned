#include <iostream>
#include <fstream>


int main() {
  std::ofstream file;
  file.open("output.txt", std::ios::out|std::ios::app);
  file << "Data goes here\n";
  file.close();


  std::string line;
  std::ifstream file2 ("output.txt");
  if (file2.is_open()) {
    while ( getline( file2, line) ) {
      std::cout << "\033[32mLine\033[0m: " << line << std::endl;
    }
    file2.close();
  } else {
    std::cout << "Can't open file!!" << std::endl;
  }
  
  return 0;
}
