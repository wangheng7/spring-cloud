package com.thread.test7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:    BlockingQueueDemo
 * Package:    com.thread.test7
 * Description:
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(2);

//        System.out.println(blockingQueue.add("a"));
//        System.out.println(blockingQueue.add("b"));
//        System.out.println(blockingQueue.add("c"));

//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.offer("e"));
//        System.out.println(blockingQueue.offer("f"));
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.poll());


//        blockingQueue.put("g");
//        blockingQueue.put("g");
//        blockingQueue.put("g");

        System.out.println(blockingQueue.offer("h",3, TimeUnit.SECONDS));

    }
}
