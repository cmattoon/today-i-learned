package main

import (
	"fmt"
	"math/rand"
	"runtime"
	"sync"
	"sync/atomic"
	"time"
)
func work() {

}
func main() {
	var state = make(map[int]int)
	var mutex = &sync.Mutex{}
	var ops int64 = 0

	for r := 0; r < 100; r++ {
		go func() {
			total := 0
			for {
				key := rand.Intn(5)
				mutex.Lock()
				total += state[key]
				mutex.Unlock()
				atomic.AddInt64(&ops, 1)
				runtime.Gosched()
			}
		}()
	}

	for w := 0; w < 10; w++ {
		go func() {
			key := rand.Intn(5)
			val := rand.Intn(100)
			mutex.Lock()
			state[key] = val
			mutex.Unlock()
			atomic.AddInt64(&ops, 1)
			runtime.Gosched()
		}()
	}

	time.Sleep(time.Second)

	nops := atomic.LoadInt64(&ops)
	fmt.Println("Total:",nops)

	mutex.Lock()
	fmt.Println("State:", state)
	mutex.Unlock()
}
