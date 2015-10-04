def lone_number(array):
    """Uses bitwise xor to cancel all numbers but the one that appears once.
    (n^n = 0)
    (n^n^m = m)
    (n^n^m^m^x = x)
    """
    total = 0
    for el in array:
        total ^= el
    return total

                
if __name__ == '__main__':

    tests = [
        ([1,2,2,3,1], 3),
        ([1,2,2], 1),
        ([4,3,2,1,2,3,4],1),
        ]
    
    for (inpt, output) in tests:
        answer = lone_number(inpt)
        if answer == output:
            print("PASS")
            continue
        print("FAIL")
        print answer
        print output
