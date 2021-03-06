package com.ray.domain;

import com.alibaba.fastjson.JSON;
import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Ray on 2017/4/5.
 */
public class Question {
    @Field
    private String question;
    @Field
    private String url;

    public Question(String question, String url) {
        this.question = question;
        this.url = url;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Question && question.equals(((Question) obj).question);
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }

    @Override
    public String toString() {
        return "{question:"+question+",url:" + url +"}";
    }
}
