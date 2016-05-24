package com.it;


import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {



       /* MemcachedClient memcachedClient = new MemcachedClient(AddrUtil.getAddresses("127.0.0.1:11211"));
        memcachedClient.add("name",10,"Java");

        memcachedClient.delete("name");

        String name = (String) memcachedClient.get("name");
        System.out.println(name);*/





        CacheManager cacheManager = new CacheManager();

        Ehcache ehcache = cacheManager.getEhcache("simpleCache");
        Element element = new Element("name","Java");
        ehcache.put(element);

        ehcache.remove("name");

        //------------------------

        Element element1 = ehcache.get("name");
        System.out.println(element1.getObjectValue());


    }

}
