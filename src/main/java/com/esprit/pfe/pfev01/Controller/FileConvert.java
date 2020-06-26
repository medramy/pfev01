package com.esprit.pfe.pfev01.Controller;

import com.esprit.pfe.pfev01.Exception.ResourceNotFoundException;
import com.esprit.pfe.pfev01.Repository.FileRepository;
import com.esprit.pfe.pfev01.Service.FileService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "fichier")
public class FileConvert {

    @Autowired
    private FileService fileService;

    @Autowired
    public FileConvert(final FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload/{id}")
    public ResponseEntity<List<String>> getNode(@RequestParam("File") MultipartFile file, @PathVariable String id) throws ParserConfigurationException, IOException, SAXException, ParseException, ResourceNotFoundException {
        System.out.println("id" + id);
        List<String> list = fileService.getNode(file, id);
        if (list == null) {
            return new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<List<String>>(list, HttpStatus.OK);
        }
    }

    private static final String FILE_PATH = "D:\\workspace spring\\pfev01\\src\\main\\java\\com\\esprit\\pfe\\pfev01\\Files\\records.txt";
    
    @RequestMapping(value="/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile() throws IOException {
        File file = new File(FILE_PATH);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
