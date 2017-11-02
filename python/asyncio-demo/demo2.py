#!/usr/bin/env python3
1;4803;0cimport asyncio
import time


start = time.time()


def tick():
    return 'at %1.1f s' % (time.time() - start)

async def f1():
    print("F1 started work {}".format(tick()))
    await asyncio.sleep(1)
    print("F1 ended work {}".format(tick()))

async def f2():
    print("F2 started work {}".format(tick()))
    await asyncio.sleep(2)
    print("F2 ended work {}".format(tick()))

async def f3():
    print("F3 started work {}".format(tick()))
    await asyncio.sleep(3)
    print("F3 ended work {}".format(tick()))

def main():
    io = asyncio.get_event_loop()
    tasks = [io.create_task(f1()), io.create_task(f2()), io.create_task(f3())]
    io.run_until_complete(asyncio.wait(tasks))
    io.close()

if __name__ == '__main__':
    main()
