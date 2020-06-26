package com.esprit.pfe.pfev01.XML;

import com.esprit.pfe.pfev01.Model.Records;
import org.springframework.batch.item.ItemProcessor;

public class XMLProcessor implements ItemProcessor<Records, Records> {

    @Override
    public Records process(Records records) throws Exception {
        return records;
    }
}
