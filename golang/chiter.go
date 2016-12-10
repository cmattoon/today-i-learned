package main

import "fmt"
import "time"

func main() {
	var i int
	ticker := time.NewTicker(time.Millisecond * 250)

	go func() {
		for t := range ticker.C {
			i ++
			fmt.Println(i)
			fmt.Println("Tick", t)
		}
	}()

	time.Sleep(time.Second)
	ticker.Stop()
	fmt.Println("Ticker stopped")
}
