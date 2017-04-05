package com.ray.search;

import com.ray.crawler.Crawler;
import com.ray.domain.Answer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ray on 2017/4/5.
 */
public class SolrSearch {

    String urlString = "http://localhost:8983/solr/zhihu";

    SolrClient solr = new HttpSolrClient.Builder(urlString).build();

    public static void main(String[] args){
        SolrSearch solrSearch = new SolrSearch();
        solrSearch.solrIndex();

    }

    public void solrIndex(){
        SolrInputDocument doc = new SolrInputDocument();

        Crawler crawler = new Crawler();
        Set<Answer> answers = null;
        try {
            answers = crawler.getAnswer(crawler.getQuestion());
            solr.addBeans(answers);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

}
