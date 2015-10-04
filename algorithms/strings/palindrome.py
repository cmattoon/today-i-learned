import re
def isPalindrome(string):
    """Ignore case, other letters"""
    regex = re.compile(r'[^A-Z0-9]', re.IGNORECASE)
    string = re.sub(regex, '', string).upper()
    wlen = len(string)
    mid = wlen // 2
    for i in range(mid):
        if string[i] != string[wlen-i-1]:
            return False
    return True

tests = [
    ("A man, a plan, a canal: Panama", 1),
    ("race a car", 0),
    ("race car", 1),
    ("taco cat", 1),
    ("1a2", 0),
    ('"""', 1),
    ]

for (i, o) in tests:
    a = isPalindrome(i)
    if a == o:
        print("\033[92mPASS\033[0m")
        continue
    print("\033[91mFAIL\033[0m")
    print a, o
