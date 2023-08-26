package com.example.viruj.photoz.clone.web;

import com.example.viruj.photoz.clone.model.Photo;
import com.example.viruj.photoz.clone.service.PhotozService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotozController {

    //constructor injection // same functionality as @Autowired annotation
    private final PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/photoz")
    public Iterable<Photo> get(){
        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable Integer id){
        Photo p=  photozService.get(id);
        if(p== null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return p;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable Integer id){

        photozService.remove(id);

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo p= photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return p;
    }
}
