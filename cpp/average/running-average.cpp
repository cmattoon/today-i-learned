#include <iostream>
#include <fstream>


int main(int argc, char** argv) {
  std::ifstream input(argv[1]);
  double total = 0.0;
  double average = 0.0;
  int counter = 0; /* Must init to 0 */
  int value;

  if (!input) {
    std::cout << "Specify a filename as argv[1]" << std::endl;
    return 1;
  }

  while (input >> value) {
    if (value) {
      total += value;
      counter += 1;
      average = total / counter;
    }
  }
  std::cout << "Average is: " << average << std::endl;

  /* Explicit > implicit */
  return 0;
}
