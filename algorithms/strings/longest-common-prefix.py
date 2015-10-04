def lcp(strings):
    """Finds the longest common prefix of a sorted list of strings by 
    checking the first and last string.
    """
    first = min(strings)
    last = max(strings)
    for idx, char in enumerate(first):
        if char != last[idx]:
            return first[:idx]
    return first

if __name__ == '__main__':
    tests = [
        (['foo','foot','food'], 'foo'),
        (['foo','foreign','for'], 'fo'),
        (['foo', 'from', 'fast'], 'f'),
        (['foo', 'bar', 'baz'], '')
        ]
    for (inpt, output) in tests:
        answer = lcp(inpt)
        if answer == output:
            print("\033[92m PASS! \033[0m")
        else:
            print("\033[91m FAIL! \033[0m")
            print("- %s" % (output))
            print("+ %s" % (inpt))
    
