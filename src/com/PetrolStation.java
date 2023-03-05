package com;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class PetrolStation {
    private final AtomicReference<Double> amount;
    private final ExecutorService service;

    public PetrolStation(AtomicReference<Double> amount, ExecutorService service) {
        this.amount = amount;
        this.service = service;
    }

    void doRefuel(Double amountNeeded) {
        Runnable task = () -> {
            Random random = new Random();
            int low = 3;
            int high = 11;
            long refuellingTime = (random.nextInt(high - low) + low) * 1000;
            try {
                Thread.sleep(refuellingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            amount.updateAndGet(totalFuel ->
                    totalFuel > amountNeeded ? totalFuel - amountNeeded : 0);
            try {
                if (amount.get() > 0) {
                    System.out.println("Рассчитанный результат: " + amount.get() + " "
                            + Thread.currentThread().getName());
                } else {
                    System.out.println("Автозаправочная станция пуста, повторите попытку позже!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        service.execute(task);
    }
}







