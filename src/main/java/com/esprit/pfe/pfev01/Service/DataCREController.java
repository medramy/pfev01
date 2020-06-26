package com.esprit.pfe.pfev01.Service;

import com.esprit.pfe.pfev01.Model.DataCRE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataCREList")
public class DataCREController {
    @Autowired
    private DataCREService dataCREService;
    public DataCREController(DataCREService dataCREService){
        this.dataCREService = dataCREService;
    }
    @GetMapping("/list")
    public Iterable<DataCRE> list() {
        return dataCREService.list();
    }
}
