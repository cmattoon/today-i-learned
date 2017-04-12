Fancy Which
===========
Arguments:
 - a list of binary programs to find (e.g., ls ncat ifconfig vim)

Requirements:
 - For each program:
    - Determine if it exists in PATH
    - Determine if it's executable

 - If all programs exist and are executable, exit with status 0
 - If any program does not exist, or is not executable, exit with appropriate status
 - If no arguments are supplied, print usage and exit with an appropriate status