package com.component.core;

import com.component.core.models.WorkRequest;
import com.component.core.mq.Runner;
import com.component.core.models.user.User;
import com.component.core.models.user.UserRepository;
import com.component.core.models.wordcloud.Wordcloud;
import com.component.core.models.wordcloud.WordcloudRepository;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> upload(@RequestPart MultipartFile document, @RequestPart String list) {
        User user = new User();
        Wordcloud wordcloud = new Wordcloud();

        wordRepository.save(wordcloud);

        WorkRequest request = new WorkRequest(wordcloud.getId().intValue());

        user.setAccessCode(wordcloud.getId());
        request.setWords(ReadFile(document));
        List<String> excludedList = new ArrayList<>(Arrays.asList(list.split(",")));
        if (!excludedList.isEmpty())
            request.setExcludedWords(excludedList);

        Runner.runner.sendMessage(new Gson().toJson(request));

        userRepository.save(user);

        return ResponseEntity.ok().body(wordcloud.getId());
    }

    @CrossOrigin
    @GetMapping(path = "/list/{code}")
    public ResponseEntity<Object> getWordList(@PathVariable Long code){
        Wordcloud wordcloud = wordRepository.findById(code).get();
        return ResponseEntity.ok().body(wordcloud);
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
