package com.esprit.pfe.pfev01.Service;

import com.esprit.pfe.pfev01.Exception.ResourceNotFoundException;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface FileService {

    public List<String> getNode(MultipartFile file, String id) throws IOException, ParserConfigurationException, SAXException, ParseException, ResourceNotFoundException;

    }
