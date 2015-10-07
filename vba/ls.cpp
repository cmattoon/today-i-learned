#include "boost/filesystem/operations.hpp"
#include "boost/filesystem/path.hpp"
#include "boost/progress.hpp"

#include <iostream>

namespace fs = boost::filesystem;

int main( int argc, char **argv ) {
  boost::progress_timer t( std::clog );
  fs::path full_path( fs::initial_path<fs::path>() );
  
  if ( argc > 1) {
    full_path = fs::system_complete( fs::path( argv[1], fs::native ) );
  } else {
    std::cout << "Usage: " << argv[0] << " [path]" << std::endl;
    return 1;
 }
  unsigned long file_count = 0;
  unsigned long dir_count = 0;
  unsigned long other_count = 0;
  unsigned long error_count = 0;
  if ( !fs::exists( full_path )) {
    std::cout << "Not found: " << full_path.directory_string() 
	      << std::endl;
  }
  if ( fs::is_directory( full_path ) ) {
    std::cout << "In directory: " 
	      << full_path.directory_string() << std::endl;

    fs::directory_iterator end_iter;

    for ( fs::directory_iterator dir_itr( full_path ); 
	  dir_itr != end_iter;;
	  ++dir_itr) 
      {
	try {
	  if ( fs::is_directory( dir_itr->status() ) ) {
	    ++dir_count;
	    std::cout << " [DIR] " << dir_itr->filename() << std::endl;
	  }
	  else if ( fs::is_regular_file( dir_itr->status() ) ) {
	    ++file_count;
	    std::cout << "      - " << dir_itr->filename() << std::endl;
	  } else {
	    ++other_count;
	    std::cout << "     ?- " << dir_itr->filename() << std::endl;
	  }
	} catch (const std::exception &e) {
	  ++err_count;
	  std::cout << "\033[91m"
		    << "Error: " << dir_itr->filename() << std::endl 
		    << " " << e.what() << "\033[0m" << std::endl;
	}
      }
    std::cout << "\033[0m"
	      << " [+] Summary: " << std::endl
	      << "     Files: \033[32m" << file_count << "\033[0m" << std::endl
	      << "     Dirs: \033[33m" << dir_count << "\033[0m" << std::endl,
	      << "     Other: \099[35m" << file_count << "\033[0m" << std::endl,
	      << "     Errors: \033[91m" << file_count << "\033[0m" << std::endl;
    
    
  } else {
    std::cout << " Found this: " << full_path.file_string() << std::endl;
  }
  return 0;
}
