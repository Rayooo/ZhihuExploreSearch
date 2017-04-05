package com.ray.domain;

/**
 * Created by Ray on 2017/4/5.
 */
public class Answer {
    private int id;
    private Question question;
    private String answer;

    public Answer(int id, Question question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public Answer(Question question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
