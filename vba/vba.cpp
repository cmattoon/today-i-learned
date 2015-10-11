#include <string>
#include <iostream>
#include <vector>
#include "intarray2bmp.hpp"
#define MIN_COLOR 0
#define MAX_COLOR 255

namespace ia2 = intarray2bmp;

inline std::vector<char> file_get_contents(const std::string &path, bool is_binary) {
  std::ios_base::openmode openmode = std::ios::ate | std::ios::in;
  if (is_binary) {
    openmode |= std::ios::binary;
  }
  

  std::ifstream input (path.c_str(), openmode); 
  input.seekg(0, std::ios::beg);
  std::vector<char> buffer(
			   (std::istreambuf_iterator<char>(input)),
			   (std::istreambuf_iterator<char>()));
  return buffer;
}



int create_image(const std::string &filename, 
		 const std::vector<char> data,
		 int width
		 ) {
  int **image;

  int height = data.size() / width;
  image = new int*[height];


  
  for (int i = 0; i < height; ++i) {
    image[i] = new int[width];
    for (int j = 0; j < width; ++j) {
      image[i][j] = 0;
    }
  }  
  
  bool result = ia2::intarray2bmp(filename, &(image[0][0]), 
				  height, width,
				  MIN_COLOR, MAX_COLOR);  
}


int main(int argc, char **argv) {

  int width = std::atoi(argv[1]);

  std::string path = "a.out";
  bool is_binary = true;
  std::vector<char> data = file_get_contents(path, is_binary);  
  std::string outfile = "out.bmp";
  return create_image(outfile, data, width);

}
