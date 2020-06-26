package com.esprit.pfe.pfev01.processor;

import com.esprit.pfe.pfev01.Model.Records;
import com.esprit.pfe.pfev01.Service.FileServiceImpl;
import org.springframework.batch.item.ItemProcessor;

public class RecordsItemProcessor implements ItemProcessor<Records, Records> {
    @Override
    public Records process(Records records) throws Exception {
        System.out.println("Processing result :" + records);
//        for(int i: FileServiceImpl.idRecords)
//            System.out.println("test i: " + i);

        if(!FileServiceImpl.idRecords.contains(records.getIdRec()))
            return null;
        return records;
    }
}
