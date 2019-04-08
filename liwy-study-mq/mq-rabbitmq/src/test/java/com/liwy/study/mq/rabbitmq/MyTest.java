package com.liwy.study.mq.rabbitmq;

import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/29 14:55 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public class MyTest extends TestAbstractClass implements TestInterface {

    @Test
    public void test() {
        // str是一个强引用
        String str = new String("HelloWorld");
        // 声明一个引用队列rq
        ReferenceQueue<String> rq = new ReferenceQueue<>();
        // 声明一个软引用,并且将sr指向str所指的对象,也就是"HelloWorld"
        SoftReference<String> sr = new SoftReference<String>(str);
        // 声明一个弱引用,并且将wr指向str所指的对象，并将他和引用队列绑定
        WeakReference<String> wr = new WeakReference<String>(str, rq);

        str = null;  // 将HelloWorld对象的强引用str制空
        sr = null;   // 将HelloWorld对象的软引用sr制空

        // 进行两次垃圾回收，wr引用大概率被回收
        System.gc();
        System.gc();

        // 如果wr被回收，此处会打印null
        System.out.println(wr.get());
        // 通过poll方法得到rq队列中的Reference对象
        WeakReference<String> tmp = (WeakReference) rq.poll();
        System.out.println(tmp);
        System.out.println(tmp.get());
    }

    @Test
    public void testSort() {
        String[] ipArr = {"111.111.111", "22.111.111", "111.111.112"};
        Arrays.sort(ipArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] splito1 = o1.split("\\.");
                String[] splito2 = o2.split("\\.");

                for (int i = 0; i < splito1.length; i++) {
                    int i1 = Integer.parseInt(splito1[i]);
                    int i2 = Integer.parseInt(splito2[i]);
                    System.out.println(i1 + ":" + i2);
                    if (i1 > i2) {
                        return 1;
                    } else if (i1 < i2) {
                        return -1;
                    }
                }
                return 0;
            }
        });
        System.out.println(Arrays.asList(ipArr));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
        String
    }
}
