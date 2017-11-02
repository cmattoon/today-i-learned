#!/usr/bin/env python3
import asyncio

async def foo():
    print("Ctx = foo")
    await asyncio.sleep(0)
    print("Switch to Foo again")

async def bar():
    print("Ctx = bar")
    await asyncio.sleep(0)
    print("Switch to Bar")

def main():
    loop = asyncio.get_event_loop()
    tasks = [loop.create_task(foo()), loop.create_task(bar())]
    wait_tasks = asyncio.wait(tasks)
    loop.run_until_complete(wait_tasks)
    loop.close()

if __name__ == '__main__':
    main()
