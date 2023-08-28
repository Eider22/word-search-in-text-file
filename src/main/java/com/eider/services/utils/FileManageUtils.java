package com.eider.services.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.eider.exceptions.PathNotFoundException;

public class FileManageUtils {

    public static String getTxtString(String file) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        String fullText = "";

        while ((line = reader.readLine()) != null) {
            fullText += line + "\n";
        }

        reader.close();

        return fullText;
    }

    public static Boolean folderPathExist(String folderPath) {
        File folder = new File(folderPath);
        return folder.exists() && folder.isDirectory();
    }

    public static String[] listPathFileNames(String folderPath) throws PathNotFoundException {
        String[] files = new String[] {};
        File directory = new File(folderPath);

        if (directory.isDirectory()) {
            files = directory.list();
        } else {
            throw new PathNotFoundException("No exite la carpeta");
        }

        return files;
    }

    public static void readTxt(String filePath) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }

    public static void readXml(String filePath) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        File xmlFile = new File(filePath);

        Document document = builder.parse(xmlFile);

        Element root = document.getDocumentElement();
        String content = root.getTextContent().trim();
        System.out.println("Contents of greeting: " + content);

    }

    public static void readJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new File(filePath);

        JsonNode rootNode = objectMapper.readTree(jsonFile);

        String name = rootNode.get("name").asText();
        int age = rootNode.get("age").asInt();
        String city = rootNode.get("city").asText();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }

    public static void readCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                for (String field : fields) {
                    System.out.print(field + " | ");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
