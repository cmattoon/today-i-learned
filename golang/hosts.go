package main

import (
	"os"
	"io/ioutil"
	"strings"
	"sort"
)

// Because this doesn't exist?
type StringSet struct {
	set map[string]bool
}

func NewStringSet() StringSet {
	return StringSet{ make(map[string]bool) }
}

// Returns true if added, false if already exists
func (s StringSet) Add(str string) bool {
	_, found := s.set[str]
	s.set[str] = true
	return !found
}
// Returns the hostnames in alphabetical order
func (s StringSet) GetHostnames() []string {
	keys := make([]string, 0, len(s.set))
	for key := range s.set {
		keys = append(keys, key)
	}
	sort.Strings(keys)
	return keys
}

// Because this doesn't exist?
func pop(s []string) (string, []string) {
	var x string
	x, s = s[0], s[1:len(s)]
	return x, s
}

// Returns the lines of a file
func GetLines(filename string) []string {
	raw, err := ioutil.ReadFile("/etc/hosts")
	if err != nil {
		panic(err)
	}
	return strings.Split(string(raw[:]), "\n")
}

func WriteHostsFile(outfile string, hosts map[string]StringSet) bool {
	// Range outputs a random order -.-
	fd, err := os.Create(outfile)

	if err != nil {
		panic(err)
	}

	defer fd.Close()

	fd.WriteString("# Managed by hosts.go\n")

	for k := range hosts {
		names := hosts[k].GetHostnames()
		line := k + " " + strings.Join(names, " ")
		fd.WriteString(line + "\n")
	}

	return true
}

func ProcessHosts(data []string) map[string]StringSet {
	hosts := make(map[string]StringSet)
	for _, line := range data {
		var ip string
		parts := strings.Split(line, " ")

		if len(parts) > 1 {
			ip, parts = pop(parts)
			if ip[0] == '#' {
				continue
			}

			// Check if nil map
			if len(hosts[ip].set) == 0 {
				hosts[ip] = NewStringSet()
			}

			for _, word := range parts {
				hosts[ip].Add(word)
			}
		}
	}
	return hosts
}
func main() {
	data := GetLines("/etc/hosts")
	hosts := ProcessHosts(data)
	WriteHostsFile("hosts", hosts)
}
