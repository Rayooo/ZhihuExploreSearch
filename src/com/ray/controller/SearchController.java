package com.ray.controller;

import com.ray.search.SolrSearch;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ResponseBody
    @RequestMapping(value = "content" , produces = "text/html;charset=UTF-8")
    public String content(@RequestParam("content")String content, HttpServletResponse response) throws IOException {

        SolrSearch solrSearch = new SolrSearch();
        return solrSearch.query(content);
    }

    @ResponseBody
    @RequestMapping(value = "crawler")
    public String crawler(){
        SolrSearch solrSearch = new SolrSearch();
        solrSearch.solrIndex();
        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(value = "delete")
    public String delete(){
        SolrSearch solrSearch = new SolrSearch();
        solrSearch.delete();
        return "SUCCESS";
    }

}
