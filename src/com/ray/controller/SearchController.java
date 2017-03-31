package com.ray.controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.jvm.hotspot.oops.Instance;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ray on 2017/3/31.
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping("content")
    public void content(@RequestParam("content")String content, HttpServletResponse response) throws IOException {

        String url = "http://localhost:8983/solr/gettingstarted/select?indent=on&q=" + content + "&wt=json";
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse res = client.execute(httpGet);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(result);
    }


}
