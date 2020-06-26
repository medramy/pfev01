package com.esprit.pfe.pfev01.Service;

import com.esprit.pfe.pfev01.Exception.ResourceNotFoundException;
import com.esprit.pfe.pfev01.Model.DataCRE;
import com.esprit.pfe.pfev01.Model.File;
import com.esprit.pfe.pfev01.Model.Records;
import com.esprit.pfe.pfev01.Model.User;
import com.esprit.pfe.pfev01.Repository.DataCRERepository;
import com.esprit.pfe.pfev01.Repository.FileRepository;
import com.esprit.pfe.pfev01.Repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonFormat.*;
import static com.fasterxml.jackson.annotation.JsonFormat.Feature.*;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataCRERepository dataCRERepository;

    @Autowired
    private DataCREService dataCREService;

    public static List<Integer> idRecords = new ArrayList<>();

    private static String UPLOADED_FOLDER = "D://workspace spring//pfev01//src//main//java//com//esprit//pfe//pfev01//Files//";
    private Map.Entry<String, JsonNode> field;

    //***
    @Autowired
    public FileServiceImpl(final FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<String> getNode(MultipartFile file, String id) throws IOException, ParserConfigurationException, SAXException, ParseException, ResourceNotFoundException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
        System.out.println("+++++++++  " + path);
        try {
            Files.write(path, bytes);
            System.out.println("2" + path);
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        File f = new File(file.getOriginalFilename(), file.getContentType());
        User user = userRepository.findByUsername(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        f.setUser(user);
        fileRepository.save(f);
        if (file.getOriginalFilename().endsWith(".xml")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(path.toString());
            NodeList list = doc.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node n = list.item(i);
                Element e = (Element) n;
                List<String> cre = new ArrayList<>();
                cre.add(n.getNodeName() + ":" + n.getTextContent());
                System.out.println(cre);
                return cre;
            }
        } else {
            if (file.getOriginalFilename().endsWith(".json")) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(String.valueOf(path));
                Object obj = parser.parse(reader);
                JSONObject jsonObj = (JSONObject) obj;
                ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
                mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
                TypeReference<List<DataCRE>> typeReference = new TypeReference<>() {
                };
                try {
                    List<DataCRE> dataCREList = mapper.readValue(jsonObj.toJSONString(), typeReference);
                    for (DataCRE d : dataCREList) {
                        DataCRE data = new DataCRE();
                        data.setBusinessdoc(d.getBusinessdoc());
                        List<Records> rec = new ArrayList<>();
                        for (Records r : d.getRecords()) {
                            Records rr = new Records();
                            rr.setDatacre(data);
                            rr.setNumerocompte(r.getNumerocompte());
                            rr.setNom(r.getNom());
                            rr.setPrenom(r.getPrenom());
                            rr.setDebit(r.getDebit());
                            rr.setCredit(r.getCredit());
                            rec.add(rr);
                        }
                        data.setRecords(rec);
                        dataCREService.save(data);
                        idRecords.clear();
                        for (Records r : data.getRecords())
                            idRecords.add(r.getIdRec());
                    }
                    ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-context.xml");
                    JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
                    Job job = (Job) context.getBean("recordsJob");
                    try {
                        JobExecution execution = jobLauncher.run(job, new JobParameters());
                        System.out.println("Job Exit Status : " + execution.getStatus());
                    } catch (JobExecutionException e) {
                        System.out.println("Job Records failed");
                        e.printStackTrace();
                    }
                    System.out.println("dataCREList Saved!");
                } catch (IOException e) {
                    System.out.println("Unable to save dataCREList: " + e.getMessage());
                }
            }
            return null;
        }
        return null;
    }
}
