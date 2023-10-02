package d3_threads;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class MySingleton {
    private int count = 0;
    private static final MySingleton instance = new MySingleton();

    private MySingleton(){}

    public static MySingleton getInstance() {
        return instance;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args){
        System.out.println(MySingleton.getInstance().getCount());
//        System.out.println(EnumSingleton.INSTANCE);
//        EnumSingleton singleton = EnumSingleton.INSTANCE;
//        singleton.increment();
//        System.out.println(singleton.getCount());
    }
}

class MyLazySingleton {
    private static volatile MyLazySingleton instance;
    private MyLazySingleton(){}

    public static MyLazySingleton getInstance() {
        if (instance == null) {
            synchronized (MyLazySingleton.class) {
                if (instance == null) {
                    instance = new MyLazySingleton();
                }
            }
        }
        return instance;
    }
}


enum EnumSingleton {
    INSTANCE;
    private int count = 0;
    public void increment(){
        count++;
    };

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}



