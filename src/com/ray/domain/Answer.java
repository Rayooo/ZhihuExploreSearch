package com.ray.domain;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Ray on 2017/4/5.
 */
public class Answer {

    @Field
    private Question question;
    @Field
    private String answer;

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
