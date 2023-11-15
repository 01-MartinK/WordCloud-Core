package com.component.core;

import com.component.core.mq.Runner;
import com.component.core.mq.TestSender;
import com.component.core.user.User;
import com.component.core.user.UserRepository;
import com.component.core.wordcloud.Wordcloud;
import com.component.core.wordcloud.WordcloudRepository;
import com.google.gson.Gson;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccessController {

    private final Runner runner;
    private final UserRepository userRepository;
    private final WordcloudRepository wordRepository;
    AccessController(Runner runner, UserRepository userRepository, WordcloudRepository wordRepository) {
        this.runner = runner;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    @CrossOrigin
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> test(@RequestPart MultipartFile document) {
        User user = new User();
        Wordcloud wordcloud = new Wordcloud();

        user.setAccessCode(wordcloud.getId());
        List<String> list = ReadFile(document);
        list.add(0, "calculate");

        Runner.runner.sendMessage(new Gson().toJson(list));

        wordRepository.save(wordcloud);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    public List<String> ReadFile(MultipartFile document){
        List<String> lines = new ArrayList<>();

        if (document != null)
            try {
                InputStream inputStream = document.getInputStream();
                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .forEach(lines::add);

            }catch (IOException err) {
                System.out.println(err);
            }
        return lines;
    }
}
