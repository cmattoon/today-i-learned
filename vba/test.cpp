#include <iostream>
#include <boost/filesystem.hpp>
#include <string>

namespace fs = boost::filesystem;
/*
void scanDrive(const std::string &drive) {
  try {

    for (fs::recursive_directory_iterator dir_end, dir(drive); dir != dir_end; ++dir) {
      fs::path _path(*dir);
      if (!fs::is_directory(_path)) {
	std::cout << _path.file_string() << std::endl;
      }
    }

  }
  catch (fs::filesystem_error e) {
    std::cout << "\033[91m" << e.what() << "\033[0m" << std::endl;
  }
};
*/
void print_spaces(int num) {
  for (int i=0; i < num; ++i) {
    std::cout << " ";
  }
}

bool find_file(const fs::path *start_path, const std::string &file_name, fs::path &found_path, int level) {
  if (!fs::file_status::exists( start_path )) return false;

  fs::directory_iterator end_itr;
  for (fs::directory_iterator itr(start_path); itr != end_itr; ++itr) {
    if (is_directory(itr->status())) {
      print_spaces(level+1);
      std::cout << itr->path() << std::endl;
      if (find_file(itr->path(), file_name, found_path, level)) return true;
    }
    else if (itr->leaf() == file_name) {
      print_spaces(level+4);
      found_path = itr->path();
      return true;
    }
  }
  return false;
};



int main(int argc, char **argv) {
  if (argv[1]) {
    fs::path path = argv[1];
    
  }
  return 0;
}
