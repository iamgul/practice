package org.gul.weakhashmap;

import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) throws InterruptedException {

        WeakHashMap<Temp,String> hm = new WeakHashMap<>();

        Temp t = new Temp();

        hm.put(t,"sahil");
        System.out.println(hm);
        t = null;
        System.gc();
        Thread.sleep(5000);
        System.out.println(hm);
    }

}

class Temp{
    @Override
    public String toString(){
        return "temp";
    }

    @Override
    public void finalize(){
        System.out.println("Finalize method called");
    }

}
