package com.esprit.pfe.pfev01.processor;

import com.esprit.pfe.pfev01.Model.DataCRE;
import com.esprit.pfe.pfev01.Model.Records;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordsRowMapper implements RowMapper<Records> {

    @Override
    public Records mapRow(ResultSet rs, int rowNum) throws SQLException {

        Records records = new Records();
        records.setNumerocompte(rs.getString("numerocompte"));
        records.setNom(rs.getString("nom"));
        records.setPrenom(rs.getString("prenom"));
        records.setCredit(rs.getFloat("credit"));
        records.setDebit(rs.getFloat("debit"));
        records.setIdRec(rs.getInt("id_rec"));
        return records;
    }
}
