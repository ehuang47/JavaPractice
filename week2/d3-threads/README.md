<h1 align="center">
Java Threads
</h1>

> Practice creating and sharing objects between multiple threads: Thread vs Runnable, start vs join vs sleep vs wait, synchronized, daemon threads, race conditions, deadlocks, hashmaps vs synchronizedmap and concurrenthashmap, ReentrantLock and AtomicInteger, singletons.

## Summary

| File                      | Purpose |
|---------------------------| - |
| [CounterThread](./d3_threads/CounterThread.java) | Reproducing counter thread interference/race conditions and solving it with and without synchronized. |
| [MySingleton](./d3_threads/MySingleton.java) | Creating a singleton class (eager, lazy, enum) style. |
| [Driver](./d3_threads/Driver.java) | Solving deadlock situation between 2 threads. |
