#!/usr/bin/env python
"""
Validates that a string of parentheses and curly/square braces/brackets
is a valid set of matches.
"""
class Validator:
    """Because OOP?"""
    
    def __init__(self):
        self.openChars = ['(', '[', '{']
        self.closeChars = [')', ']', '}']
        
    def validateString(self, string):
        """Validates a string consisting of only the set of characters in
            self.openChars + self.closeChars
           Handling of any other characters (except leading/trailing whitespace)
           is not defined.
           
        Args:
           - string (String): The string of parens (e.g., "(())")

        Returns:
           - True if `string` is a valid set of open/close chars, else False
        """
        stack = []
        string = string.strip()

        if len(string) % 2 is not 0:
            return False

        for ch in string:
            if ch in self.openChars:
                stack.append(ch)
            elif len(stack) is 0:
                return False
            elif ch in self.closeChars:
                idx = self.closeChars.index(ch)
                if self.openChars[idx] != stack.pop():
                    return False
        return True


tests = [
    (True, ""),
    (True, "()"),
    (True, "()()"),
    (True, "{}[]"),
    (True, "({}[])"),
    (True, "([]){}"),
    (True, "((({{{[[[]]]}}})))"),
    (True, "{()}"),
    
    (False, "{"),
    (False, "}"),
    (False, ")("),
    (False, "{{}"),
    (False, "{}}"),
    (False, "}{()"),
    (False, "[])"),
    (False, "({)}"),
    (False, "([{]})")
    ]
if __name__ == '__main__':
    val = Validator()
    for (i, (answer, inpt)) in enumerate(tests):
        print(" \033[33;01m Test #%d (\"%s\")\033[0m" % (i, inpt))
        out = val.validateString(inpt)
        if out == answer:
            print(" \033[32m PASS \033[0m")
        else:
            print(" \033[31m FAIL \033[0m")
            print("\033[92mExpected:")
            print("+%s\033[0m" % (str(answer)))
            print("\033[91mReceived:")
            print("-%s\033[0m" % (str(out)))
    
