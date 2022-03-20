package com.mrlu.springwebflux.reactor;

import java.util.Observable;

/**
 * @author 简单de快乐
 * @version 1.0
 * @email 1802772962@qq.com
 * @createDate 2021-03-20 22:13
 *
 * Java8观察者模式
 */
public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observer = new ObserverDemo();
        //添加观察者。

        /*
        public interface Observer {
         每当观察对象发生变化时，都会调用此方法
        /**
         * This method is called whenever the observed object is changed. An
         * application calls an <tt>Observable</tt> object's
         * <code>notifyObservers</code> method to have all the object's
         * observers notified of the change.
         *
         * @param   o     the observable object. 可观察的对象
         * @param   arg   an argument passed to the <code>notifyObservers</code>
         *                 method. 传递给<code> notifyObservers </ code> 方法的参数
            void update(Observable o, Object arg);
        }

         */

        //addObserver(Observer o)
        observer.addObserver((o, arg) -> System.out.println("发生变化"));

        observer.addObserver((o, arg) -> System.out.println("被观察者通知，准备改变"));

        //数据变化
        observer.setChanged();

        //通知
        observer.notifyObservers();
    }
}
