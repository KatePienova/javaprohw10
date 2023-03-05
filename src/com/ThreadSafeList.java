package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeList<T> {
    private final List<T> arrayList = new ArrayList<>();
    final ReentrantLock lock = new ReentrantLock();

    public void add(T element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            arrayList.add(element);
        } finally {
            lock.unlock();
        }
    }

    public void remove(T element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            arrayList.remove(element);
        } finally {
            lock.unlock();
        }
    }

    public T get(int index) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return arrayList.get(index);
        } finally {
            lock.unlock();
        }
    }
}
