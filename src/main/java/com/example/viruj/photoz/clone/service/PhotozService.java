package com.example.viruj.photoz.clone.service;

import com.example.viruj.photoz.clone.model.Photo;
import com.example.viruj.photoz.clone.repository.PhotozRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
//@Component
public class PhotozService {

    private final PhotozRepository photozRepository;

    public PhotozService(PhotozRepository photozRepository) {
        this.photozRepository = photozRepository;
    }

    public Iterable<Photo> get() {
        return photozRepository.findAll();
    }

    public Photo get(Integer id) {
        return photozRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photozRepository.deleteById(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {

        Photo p= new Photo();
        p.setContentType(contentType);
        p.setFileName(fileName);
        p.setData(data);
        photozRepository.save(p);
        return p;
    }
}
