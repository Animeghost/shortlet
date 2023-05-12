package com.example.shortletBackend.service;

import com.example.shortletBackend.entities.Pictures;
import com.example.shortletBackend.repositories.PicturesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PictureService {
    private final PicturesRepository picturesRepository;

    public void save(Pictures pictures){
        picturesRepository.save(pictures);
    }

    public void deleteById(Long id){
        picturesRepository.deleteById(id);
    }

}
