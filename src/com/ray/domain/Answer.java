package com.ray.domain;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Ray on 2017/4/5.
 */
public class Answer {

    @Field
    private int id;

    private Question question;

    @Field
    private String questionString;

    @Field
    private String url;

    @Field
    private String answer;

    public Answer(int id, Question question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.questionString = question.getQuestion();
        this.url = question.getUrl();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionString() {
        return questionString;
    }

    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
