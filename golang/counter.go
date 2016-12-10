/**
 * A modified version of code found here:
 * http://codereview.stackexchange.com/questions/149510/vowel-consonant-counter-in-golang
 */
package main

import (
    "os"
    "bufio"
    "fmt"
)

func formatNumbers(vowelCount int, consonantCount int) {
	fmt.Print("Consonants: ")

	for i := 0; i < consonantCount; i++ {
		fmt.Print("=")
	}

	fmt.Printf(" %d\n", consonantCount)

	fmt.Print("Vowels:     ")

	for i := 0; i < vowelCount; i++ {
		fmt.Print("=")
	}

	fmt.Printf(" %d\n", vowelCount)
}


func takeInput() string {
	reader := bufio.NewReader(os.Stdin)
	fmt.Print("Enter text: ")
	text, _ := reader.ReadString('\n')

	return text
}

func InArray(needle string, haystack []string) bool {
	for _, item := range haystack {
		if item == needle {
			return true
		}
	}
	return false
}

// Returns the number of (vowels, consonants) in `input`
func CountChars(input string) (int, int) {
	var vcount int
	var ccount int

	consonants := []string{"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"}
	vowels := []string{"a", "e", "i", "o", "u",}

	for _, ch := range input {
		if InArray(string(ch), consonants) {
			ccount ++;
		} else if InArray(string(ch), vowels) {
			vcount ++;
		}
	}
	return vcount, ccount
}

func main() {
	formatNumbers(CountChars(takeInput()))
}
