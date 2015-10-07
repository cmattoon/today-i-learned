#include <boost/filesystem.hpp>
#include <iostream>
using std::cout;
using std::endl;
using namespace boost::filesystem;

bool find_file( const path & dir_path,         // in this directory,
                const std::string & file_name, // search for this name,
                path & path_found )            // placing path here if found
{
  if ( !exists( dir_path ) ) return false;
  directory_iterator end_itr; // default construction yields past-the-end
  for ( directory_iterator itr( dir_path );
        itr != end_itr;
        ++itr )
    {
      if ( is_directory(itr->status()) )
	{
	  if ( find_file( itr->path(), file_name, path_found ) ) return true;
	}
      else if ( itr->path().filename() == file_name ) // see below
	{
	  path_found = itr->path();
	  return true;
	}
    }
  return false;
}

int main(int argc, char **argv) {
  if (argc < 2) {
    cout << "Usage: " << argv[0] << " [path]" << endl;
    return 1;
  }
  path p (argv[1]);

  path endpath;
  std::string filename = "foo";
  try {
    if (find_file(argv[1], filename, endpath)) {
      cout << "File " << argv[1] << " found" << endl;
      return 0;
    }
  } catch (const filesystem_error &e) {
    cout << e.what() << endl;
    return 1;
  }
}
