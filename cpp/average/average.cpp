#include <iostream>
#include <fstream>
#include <set>

int main(int argc, char** argv) {
  std::ifstream input(argv[1]);
  std::multiset<int> values; /* Multiset of ints */
  double total;
  int value; 

  if (!input) {
    std::cout << "Specify a filename as argv[1]" << std::endl;
    return 1;
  }

  while (input >> value) { /* >> handles whitespace */
    if (value) {
      values.insert(value);
    }
  }

  std::cout << "Read file"<<std::endl;

  if (values.size() > 0) {
    std::multiset<int>::iterator iter = values.begin(); 
    while(iter != values.end()) {
      ++iter;
      total += *iter;
    }
    std::cout << "Average is: " << (total / values.size()) << std::endl;
  }
}
