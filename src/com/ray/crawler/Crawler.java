package com.ray.crawler;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.ray.domain.Answer;
import com.ray.domain.Question;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ray on 2017/4/2.
 */
public class Crawler {

    private int id = 0;

    public Set<Answer> getAnswer(HashSet<Question> questionHashSet) throws IOException {
        Set<Answer> answerHashSet = Collections.synchronizedSet(new HashSet<Answer>());

        questionHashSet.forEach((question -> {
            Document doc = null;
            synchronized (this){
                try {
                    doc = Jsoup.connect(question.getUrl()).get();
                    Elements answers = doc.select(".CopyrightRichText-richText");
                    for(Element e : answers){
                        id ++;
                        System.out.println(e.text());
                        answerHashSet.add(new Answer(id, question,e.text()));
                        System.out.println(id);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));


        SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
        String path = "answers/"+ dt.format(new Date()) +"question.json";
        File folder=new File("answers");
        if(!folder.exists()){
            folder.mkdirs();
        }
        Path answerFile = Paths.get(path);
        Files.write(answerFile, (new Gson()).toJson(answerHashSet).getBytes());
        return answerHashSet;
    }

    public HashSet<Question> getQuestion() throws IOException {
        HashSet<Question> questionHashSet = new HashSet<>();

        for (int i = 0; i < 80; i+=5) {
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

        SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddhhmmss");
        String path = "answers/"+ dt.format(new Date()) +"question.json";

        File folder=new File("answers");
        if(!folder.exists()){
            folder.mkdirs();
        }

        Path file = Paths.get(path);
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
