/**
 * Inline functions - feature to inc. exec time of program. 
 * Compiler replaces function definition at compile-time instead of runtime
 */
template <typename InputIterator, typename Type> inline
  Type accumulate (InputIterator start, InputIterator stop, Type initial) {
  while (start != stop) {
    initial += *start;
    ++start; 
  }
  return initial;
}
