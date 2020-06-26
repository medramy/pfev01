package com.esprit.pfe.pfev01.Service;

import com.esprit.pfe.pfev01.Model.DataCRE;
import com.esprit.pfe.pfev01.Repository.DataCRERepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class DataCREService {
    private DataCRERepository dataCRERepository;

    public DataCREService(DataCRERepository dataCRERepository) {
        this.dataCRERepository = dataCRERepository;
    }

    public Iterable<DataCRE> list() {
        return dataCRERepository.findAll();
    }

    public DataCRE save(DataCRE dataCRE) {
        return dataCRERepository.save(dataCRE);
    }

    public Iterable<DataCRE> saveAll(Collection<DataCRE> dataCREList) {
        return dataCRERepository.saveAll(dataCREList);
    }
}
