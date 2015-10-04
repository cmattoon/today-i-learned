#include <iostream>
#include <fstream>
#include <set>
#include <accumulate.h>

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

  if (values.size() > 0) {
    total = accumulate(values.begin(), values.end(), 0.0);
    std::cout << "Average is: " << total / values.size() << std::endl;
  }
}
