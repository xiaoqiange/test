package cn.controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    public static void main(String[] args) {
        ShareSource source = new ShareSource();
//        //创建并启动线程
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                source.print10();
//            }
//        }, "B").start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                source.print15();
//            }
//        }, "C").start();
//        new Thread(() -> {
//            for (int i = 0; i < 10; i++) {
//                source.print5();
//            }
//        }, "A").start();
        System.out.println(source.pp("aa"));
    }
}
class ShareSource{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    //每个condition都维护了一个等待队列
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    
    
    public void print5(){
        //首先加锁,在打印五次的时候,别的线程不能打印
        lock.lock();
        try{
            System.out.println("print5得到了锁");
            //这里用while来判断 不用if  防止虚假唤醒
            while (flag != 1) {
                System.out.println("print5 线程挂起");
                //线程挂起后，锁资源将不再拥有（即将该线程放到等待队列中，并且释放锁资源）
                conditionA.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //打印完成后先修改flag,然后唤醒下一个线程
            flag = 2;
            
            conditionB.signal();//唤醒线程，从await()后继续执行
        } catch (Exception e){
            
        }finally{
            System.out.println("print5释放了锁");
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{
            System.out.println("print10得到了锁");
            while (flag != 2) {
                System.out.println("print10 线程挂起");
                conditionB.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            
            flag = 3;
            
            conditionC.signal();
        } catch (Exception e){
            
        }finally{
            System.out.println("print10释放了锁");
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{
            System.out.println("print15得到了锁");
            while (flag != 3) {
                System.out.println("print15 线程挂起");
                conditionC.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            
            flag = 1;
            
            conditionA.signal();
        } catch (Exception e){
            
        }finally{
            System.out.println("print15释放了锁");
            lock.unlock();
        }
    }
    public <E> E pp(E e){
        return e;
    }
}