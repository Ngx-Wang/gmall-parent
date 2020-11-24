package com.atguigu.jdkproxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//委托类
class DogT1 implements  Dog {

    @Override
    public void eat() {
        System.out.println("狗吃骨头-----------");
    }
}

//代理类
class DogT2 implements InvocationHandler {
    Dog dog;

    public DogT2(Dog dog) {
        this.dog = dog;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("11111111111");
        Object invokeMethod = method.invoke(dog, args);
        System.out.println("222222222222");
        return invokeMethod;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        DogT1 t1 = new DogT1();

        Dog dog = (Dog) Proxy.newProxyInstance(Dog.class.getClassLoader(), new Class[]{Dog.class}, new DogT2(t1));
        dog.eat();

    }
}
