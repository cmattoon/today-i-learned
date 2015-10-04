#include <stdio.h>
#include <string>

#define BOOL_FMT(expr) "%s = %s\n", #expr, (expr) ? "true" : "false"

bool IsPalindrome(std::string input) {
  int len = input.length();
  int mid = len / 2;
  
  for (int i = 0; i < mid; ++i) {
    if (input[i] != input[len-i-1]) {
      return false;
    }
  }
  return true;
}

int main() {
  printf(BOOL_FMT(IsPalindrome("tacocat")));
  printf(BOOL_FMT(IsPalindrome("racecar")));
  printf(BOOL_FMT(IsPalindrome("palindrome")));
}
