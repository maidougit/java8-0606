# java基础 及 集合框架
---
## list 初始容量
<pre>
ArrayList实现了List接口，继承了AbstractList，底层是数组实现的，一般我们把它认为是可以自增扩容的数组。
它是非线程安全的，一般多用于单线程环境下(与Vector最大的区别就是，Vector是线程安全的，所以ArrayList 性能相对Vector 会好些)，
它实现了Serializable接口，因此它支持序列化，能够通过序列化传输(实际上java类库中的大部分类都是实现了这个接口的)，
实现了RandomAccess接口，支持快速随机访问(只是个标注接口，并没有实际的方法),这里主要表现为可以通过下标直接访问(底层是数组实现的，
所以直接用数组下标来索引)，实现了Cloneable接口，能被克隆。

</pre>
```java
public class ListData{
  public static void main(String[] args){
      // 默认初始容量 DEFAULT_CAPACITY 10  java7开始支持菱形操作 <>
    List  list = new java.util.ArrayList<>(3);
    
    cn.hutool.core.lang.Console.log( "初始容量：{}" , list.DEFAULT_CAPACITY);
  }
}
```

## list与 LinkedList 区别
<pre>
---|Collection: 单列集合  
            ---|List: 有存储顺序, 可重复  
                ---|ArrayList:  数组实现, 查找快, 增删慢  
                            由于是数组实现, 在增和删的时候会牵扯到数组  
                                                增容, 以及拷贝元素. 所以慢。数组是可以直接  
                                                按索引查找, 所以查找时较快  
                ---|LinkedList: 链表实现, 增删快, 查找慢  
                            由于链表实现, 增加时只要让前一个元素记住自  
                                               己就可以, 删除时让前一个元素记住后一个元  
                                               素, 后一个元素记住前一个元素. 这样的增删效  
                                             率较高但查询时需要一个一个的遍历, 所以效率  
                                                较低  
                ---|Vector: 和ArrayList原理相同, 但线程安全, 效率略低  
                             和ArrayList实现方式相同, 但考虑了线程安全问  
                                                题, 所以效率略低  
            ---|Set: 无存储顺序, 不可重复  
                ---|HashSet  
                ---|TreeSet  
                ---|LinkedHashSet  
---| Map: 键值对  
        ---|HashMap  
        ---|TreeMap  
        ---|HashTable  
        ---|LinkedHashMap
</pre>
```java
public class ListUtil<T> extends LinkedList<T>{

    public ListUtil<T> append(T t) {
        add(t);
        return this;
    }

    public ListUtil<T> append(List<T> t) {
        addAll(t);
        return this;
    }

    @Test
    public void test() {
        ListUtil<String> list = new ListUtil<>();
        list.append("1").append("2");

        Console.log("结果：{}", list.toString());
    }
}
```

## HashMap 与 HashTable 区别（安全角度考虑）linkedHashMap
<pre>
1 HashMap不是线程安全的
            hastmap是一个接口 是map接口的子接口，是将键映射到值的对象，其中键和值都是对象，
            并且不能包含重复键，但可以包含重复值。HashMap允许null key和null value，而hashtable不允许。
2   HashTable是线程安全的一个Collection。

HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口，主要区别在于HashMap允许空（null）键值（key）,由于非线程安全，效率上可能高于Hashtable。
HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。
HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey。因为contains方法容易让人引起误解。 
Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现。
最大的不同是，Hashtable的方法是Synchronize的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，而HashMap 就必须为之提供外同步。 
Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异。
</pre>
```java
public class MapUtil<K, V> extends LinkedHashMap<K,V> {
    public MapUtil() {
    }

    public MapUtil(K key, V value) {
        this.put(key, value);
    }

    public MapUtil<K, V> push(K key, V value) {
        put(key, value);
        return this;
    }

    public MapUtil<K, V> pushAll(Map<? extends K, ? extends V> map) {
        putAll(map);
        return this;
    }

    @Test
    public void test() {
      MapUtil<String, String> mapUtil = new MapUtil<>();

       mapUtil.push("name", "参数化").push("name1", "参数化");

        Console.log("结果：{}", mapUtil);
    }

}
```

## 并发线程 CurrentHashMap的使用 与 HashMap区别

## Set集合的使用

## jre与jdk

## 封装继承多态的理解

