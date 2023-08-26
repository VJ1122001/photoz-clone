package com.example.viruj.photoz.clone;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
//@Component
public class PhotozService {

    public Map<String, Photo> db= new HashMap<>(){{
        put("1",new Photo("1", "hello.jpg"));
    }};

    public Collection<Photo> get() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public Photo save(String fileName, String contentType, byte[] data) {

        Photo p= new Photo();
        p.setContentType(contentType);
        p.setId(UUID.randomUUID().toString());
        p.setFileName(fileName);
        p.setData(data);
        db.put(p.getId(), p);
        return p;
    }
}
