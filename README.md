# ZhihuExploreSearch

### 遇到的坑

Spring MVC返回JSON数据，注意使用@ResponseBody和@RequestMapping(value = "content" , produces = "text/html;charset=UTF-8")，设置字符集为UTF-8可以处理乱码问题

fastjson不会解析嵌套的json，如{ a: {b:"C" } }，就不会解析b这一块的数据，使用Gson可以处理，但是Gson比较慢，占用内存较大

注意放在HashSet和HashMap中的元素一定要写equals和hashCode

使用spring的scheduler处理定时服务，如@Scheduled(cron = "0 0 15 * * *")，这里有个cron表达式说的比较好的 http://stackoverflow.com/questions/26147044/spring-cron-expression-for-every-day-101am

solr分为分布式启动和单节点启动，单节点启动注意要自己新建目录，在solr目录下的server/solr/新建，conf文件从configsets中引入

solr中处理增删改查在search/SolrSearch中

定时任务使用在applicationContext.xml中配置，而且需要在Schedule.java中写注解@Component和@EnableScheduling，然后在方法中标记触发时间@Scheduled(cron = "*/10 * * * * *")，10秒触发一次