package com.esprit.pfe.pfev01.XML;

import com.esprit.pfe.pfev01.Model.Records;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordsPreparedStatementSetter implements ItemPreparedStatementSetter<Records> {
    @Override
    public void setValues(Records records, PreparedStatement ps) throws SQLException {
        ps.setString(1, records.getNumerocompte());
        ps.setString(2, records.getNom());
        ps.setString(3, records.getPrenom());
        ps.setFloat(4, records.getDebit());
        ps.setFloat(5, records.getCredit());
    }
}
