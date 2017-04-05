package com.ray.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * Created by Ray on 2017/4/5.
 */
public class SolrSearch {
    String urlString = "http://localhost:8983/solr/#/gettingstarted/";
    SolrClient solr = new HttpSolrClient(urlString);
    SolrQuery query = new SolrQuery();


}
