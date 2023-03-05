package com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) {
        //ThreadSafeList
        ThreadSafeList<String> list = new ThreadSafeList<>();

        Thread thread0 = new Thread(() -> list.add("1"));
        Thread thread1 = new Thread(() -> list.add("2"));
        Thread thread2 = new Thread(() -> list.add("3"));
        thread0.start();
        thread1.start();
        thread2.start();

        Thread thread3 = new Thread(() -> list.remove("1"));
        Thread thread4 = new Thread(() -> list.remove("3"));
        thread3.start();
        thread4.start();

        Thread thread5 = new Thread(() -> list.get(0));
        thread5.start();

        //Petrol station
        ExecutorService service = Executors.newFixedThreadPool(3);
        AtomicReference<Double> amount = new AtomicReference<>(500.0);
        PetrolStation station = new PetrolStation(amount, service);

        station.doRefuel(20.5);
        station.doRefuel(30.0);
        station.doRefuel(30.0);
        station.doRefuel(10.5);
        station.doRefuel(20.0);
        station.doRefuel(20.0);
        station.doRefuel(10.0);
        station.doRefuel(20.5);
        station.doRefuel(40.0);
        station.doRefuel(50.0);
        station.doRefuel(20.0);
        station.doRefuel(20.0);
        station.doRefuel(30.0);
        station.doRefuel(25.0);
        station.doRefuel(10.5);
        station.doRefuel(15.0);
        station.doRefuel(30.0);
        station.doRefuel(10.0);

        service.shutdown();
    }
}
