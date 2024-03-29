package com.atguigu.cache.service;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存;以后再要相同的数据，直接从缓存获取，不用调用方法;
     *
     * CacheManager管理多个Cache组件的,对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字；
     * 几个属性:
     *      cacheNames/value: 指定缓存组件的名字:
     *      key: 缓存数据使用的key；可以用它来制定，默认是使用方法参数的值,比如传的参数为1 则<1,方法的返回值>
     *              编写SpEl表达式： #id;参数id的值  #a0  #p0  #root.args[0]
     *      keyGenerator: key的生成器：可以自己指定key的生成器的组件id
     *                   和key二选一
     *      CacheManager: 指定缓存管理器; 或者指定缓存解析器(二选一)
     *      condition: 指定符合条件的情况下才缓存;
     *                  condition = "#id>0"
     *      unLess:否定缓存;当unLess指定的条件为true，方法的返回值不会被缓存；可以获取到结果进行判断
     *                  unless = "#result == null "
     *      sync: 是否使用异步模式
     * @param id
     * @return
     */

    /**
     * 原理：
     *      1、自动配置类：CacheAutoConfiguration
     *      2、缓存的配置类：
     *      0 = "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     *      1 = "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     *      2 = "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     *      3 = "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     *      4 = "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     *      5 = "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     *      6 = "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     *      7 = "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     *      8 = "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     *      9 = "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     *      3、哪个配置类默认生效: 默认SimpleCacheConfiguration生效
     *
     *      4、给容器中注册了一个CacheManager： ConcurrentMapCacheManager
     *      5、可以获取和创建ConcurrentMapCache类型的缓存组件;它的作用将数据保存在ConcurrentMap中
     *      6、运行流程：
     *      @Cacheable:
     *      1、方法运行之前，先去查询CAche(缓存组件)，按照cacheNames指定的名字获取；
     *          (CAcheManager先获取相应的缓存),第一次获取缓存如果没有cache组件会自动创建；
     *      2、去cache里查找缓存的内容，使用一个key，默认就是方法的参数；
     *          key是按照某种策略生成的：默认是使用keyGenerator生成的；默认使用SimpleKeyGenerator生成的key;
     *          SimpleKeyGenerator生成key的默认策略：
     *                  如果没有参数：key=new SimpleKey();
     *                  如果有一个参数： key=参数的值
     *                  如果有多个参数： key=new SimpleKeyGenerator(params);
     *      3、没有查到缓存就调用目标方法；
     *      4、将目标方法返回的结果放回缓存
     *
     *  总结:
     *      @Cacheable标注的方法执行之前先来检测缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *      如果没有就运行方法并将结果放入缓存;以后再来调用就可以直接调用缓存中的数据;
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id){
        Employee employee = employeeMapper.getEmpById(id);
        System.out.println("查询"+id+"号员工");
        return employee;
    }
}
