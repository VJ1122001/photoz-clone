package com.example.viruj.photoz.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

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
    public Collection<Photo> get(){
        return photozService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id){
        Photo p=  photozService.get(id);
        if(p== null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return p;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(@PathVariable String id){

        Photo p = photozService.remove(id);
        if(p== null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo p= photozService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        return p;
    }
}
