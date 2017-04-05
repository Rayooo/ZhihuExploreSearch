package com.ray.crawler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ray.domain.Answer;
import com.ray.domain.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Ray on 2017/4/2.
 */
public class Crawler {

    private void getAnswer(HashSet<Question> questionHashSet) throws IOException {
        Set<Answer> answerHashSet = Collections.synchronizedSet(new HashSet<Answer>());
        for (Question question : questionHashSet) {
            Document doc = Jsoup.connect(question.getUrl()).get();
            Elements answers = doc.select(".CopyrightRichText-richText");
            for(Element e : answers){
                System.out.println(e.text());
                answerHashSet.add(new Answer(question,e.text()));
            }
        }

        Path answerFile = Paths.get("answer.json");
        Files.write(answerFile, (new Gson()).toJson(answerHashSet).getBytes());
    }

    private HashSet<Question> getQuestion() throws IOException {
        HashSet<Question> questionHashSet = new HashSet<>();

        for (int i = 0; i < 100; i+=5) {
            String param = "{\"offset\":"+ i +",\"type\":\"day\"}";
            String requestUrl = "https://www.zhihu.com/node/ExploreAnswerListV2";

            Document doc = Jsoup.connect(requestUrl).data("params",param).get();

            Elements links = doc.select(".question_link");

            for(Element e : links){
                String url = "https://www.zhihu.com" + e.attr("href").split("/answer")[0];
                System.out.println(e.text() + url);

                questionHashSet.add(new Question(e.text(),url));
            }
        }

        String jsonString = JSON.toJSONString(questionHashSet);
        byte[] jsonBytes = JSON.toJSONBytes(questionHashSet);
        System.out.println(Arrays.toString(jsonBytes));
        Path file = Paths.get("question.json");
        Files.write(file, jsonBytes);

        return questionHashSet;
    }


    public static void main(String[] args) {

        Crawler crawler = new Crawler();
        try {
            HashSet<Question> q = crawler.getQuestion();
            crawler.getAnswer(q);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
