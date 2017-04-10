# Scrath4J

一个深受python的scrapy框架影响的Java爬虫框架。  

---

# API概览 

```
Scheduler scheduler = new BaseScheduler().configure(new AbstractStrategy() {
    private Parser parser = new TestParser();

    @Override
    public Parser getParser() {
        return parser;
    }
});

Request request = new SimpleHttpRequest().setURL("https://www.zhihu.com/");
scheduler.submitRequest(request);
long start = System.currentTimeMillis();
scheduler.start();
System.out.println(System.currentTimeMillis() - start);
```  

--- 

# 架构概览

![arch](http://timd.cn/content/images/2017/03/Scratch4J.png)

