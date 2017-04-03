package com.ray.crawler;

import com.ray.search.ConnectionFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ray on 2017/4/2.
 */
public class Crawler {
    public static void main(String[] args) throws IOException {

        HashMap<String,String> map = new HashMap<>();

        for (int i = 0; i < 10; i+=5) {
            String param = "{\"offset\":"+ i +",\"type\":\"day\"}";
            String requestUrl = "https://www.zhihu.com/node/ExploreAnswerListV2";

            Document doc = Jsoup.connect(requestUrl).data("params",param).get();

            Elements links = doc.select(".question_link");

            for(Element e : links){
                String url = "https://www.zhihu.com" + e.attr("href").split("/answer")[0];
//                System.out.println(e.text() + url);
                map.put(e.text(), url);
            }
        }


        TransportClient client = ConnectionFactory.getClient();

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

            String json = "{" +
                    "\"questionContent\":\""+ entry.getKey() +"\"," +
                    "\"url\":\""+ entry.getValue() +"\"" +
                    "}";

            //加入到elasticSearch
            IndexResponse response = client.prepareIndex("zhihu","question","1")
                    .setSource(json)
                    .execute()
                    .actionGet();
            System.out.print(response);

        }




    }

}
