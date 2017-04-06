package com.ray.search;

import com.google.gson.Gson;
import com.ray.crawler.Crawler;
import com.ray.domain.Answer;
import com.ray.domain.Question;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.SolrInputField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
//        solrSearch.solrIndex();
//        solrSearch.delete();
        solrSearch.query("公司");
    }


    //solr建立索引，即增加
    public void solrIndex(){
        SolrInputDocument doc = new SolrInputDocument();

        Crawler crawler = new Crawler();
        Set<Answer> answers;
        try {
            HashSet<Question> q = crawler.getQuestion();
            answers = crawler.getAnswer(q);
            solr.addBeans(answers);
        } catch (IOException | SolrServerException e) {
            e.printStackTrace();
        }
    }

    //solr删除方法
    public void delete(){
        try {
            solr.deleteByQuery("*");
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    //solr查询方法
    public String query(String queryString){
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        ArrayList<HashMap<String,String>> result = new ArrayList<>();

        try {
            QueryResponse response = solr.query(query);
            //此list返回了一个ArrayList<SolrDocument> ，遍历此List得到SolrDocument
            SolrDocumentList list = response.getResults();

            for (SolrDocument document : list) {
                //getFieldNames 得到每一个字段的名称
                for (String s : document.getFieldNames()) {
                    HashMap<String,String> m = new HashMap<>();
                    m.put(s,document.get(s).toString());
                    result.add(m);
                }
            }

        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        return (new Gson()).toJson(result);
    }

}
