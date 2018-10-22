package com.aluksik.fileupload.services;

import com.aluksik.fileupload.domains.LiniaCSV;
import com.aluksik.fileupload.repositories.LiniaCSVRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LiniaCSVService {

    LiniaCSVRepository liniaCSVRepository;

    public LiniaCSVService(LiniaCSVRepository liniaCSVRepository) {
        this.liniaCSVRepository = liniaCSVRepository;
    }

    public void addLine (String text) {
        LiniaCSV linia = new LiniaCSV();
        linia.setLinia(text);

        liniaCSVRepository.save(linia);
    }


    public Set<LiniaCSV> findAll() {
        Set<LiniaCSV> liniaCSVSet = new HashSet<>();

        liniaCSVRepository.findAll().forEach(liniaCSVSet::add);

        return liniaCSVSet;
    }
}
