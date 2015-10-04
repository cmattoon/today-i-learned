def str_str(haystack, needle):
    """Checks for `needle` in `haystack`, returning the index of the
    first occurance of `needle`, if it exists. Otherwise, returns -1
    """
    if needle == '' or len(needle) > len(haystack):
        return -1
    i = 0
    j = 0
    hlen = len(haystack)
    nlen = len(needle)

    while i < hlen:
        if hlen - i < nlen:
            # No possible strings left
            return -1
        if haystack[i] == needle[j]:
            # If the first char matches, search the substring backwards.
            nlast = (nlen-1) + j
            hlast = (nlen-1) + i
            while nlast > j:
                if haystack[hlast] != needle[nlast]:
                    break
                nlast -= 1
                hlast -= 1
            if haystack[hlast] == needle[nlast]:
                return i
        i += 1
        j = 0
    return -1
            
if __name__ == '__main__':
    tests = [
        ('', 'foo', -1),
        ('', '', -1),
        ('foo', 'foot', 0),
        ('foo', 'bar', -1),
        ('bar', 'foobar', 3),
        ('baba', 'bbbbbbbbab', -1),
        ('bar', 'barbazbar', 0),
        ('babaaa', 'bbaabbbbbaabbaabbbbbbabbbabaabbbabbabbbbababbbabbabaaababbbaabaaaba',48),
        ('bba','aabaaaababaabbbabbabbbaabababaaaaaababaaabbabbabbabbaaaabbbbbbaabbabbbbbabababbaaabbaabbbababbb', 13),
        ('abc', 'aabcabcabc', 1)
        ]

for (needle, haystack, output) in tests:
    answer = str_str(haystack, needle)
    if answer != output:
        print("\033[91mFAIL\033[0m")
        print("-", answer)
        print("+", output)
        continue
    print("\033[92mPASS\033[0m")
