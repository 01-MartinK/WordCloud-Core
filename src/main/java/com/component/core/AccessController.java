package com.component.core;

import com.component.core.user.User;
import com.component.core.user.UserRepository;
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
    private final UserRepository repository;
    AccessController(UserRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> test(@RequestPart MultipartFile document) {
        User user = new User();
        repository.save(user);
        return ResponseEntity.ok().build();
    }

    public List<String> ReadFile(MultipartFile document){
        List<String> lines = new ArrayList<>();

        if (document != null)
            try {
                InputStream inputStream = document.getInputStream();
                new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                        .lines()
                        .forEach(s -> {
                            lines.add(s);
                            System.out.println(s);
                        } );

            }catch (IOException err) {
                System.out.println(err);
            }
        return lines;
    }
}
