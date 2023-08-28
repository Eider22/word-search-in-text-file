package com.eider.services.implementations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eider.exceptions.PathNotFoundException;
import com.eider.services.contracts.IFileManagerService;
import com.eider.services.utils.FileManageUtils;

public class FileManagerService implements IFileManagerService {

    @Override
    public List<String> fileWordsToList(String filePath) throws FileNotFoundException, IOException {
        String fullTextInFile = FileManageUtils.getTxtString(filePath);

        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b\\p{L}+\\b");
        Matcher matcher = pattern.matcher(fullTextInFile);

        while (matcher.find()) {
            String word = matcher.group();
            words.add(word);
        }

        return words;
    }

    @Override
    public List<String> listPathFileNames(String folderPath) throws PathNotFoundException {
        List<String> fileNameList = new ArrayList<>();
        for (String fileName : FileManageUtils.listPathFileNames(folderPath)) {
            fileNameList.add(fileName);
        }
        return fileNameList;
    }

    @Override
    public List<String> listPathFileNamesByExtension(String folderPath, String extension)
            throws PathNotFoundException {
        String[] allFileNames = FileManageUtils.listPathFileNames(folderPath);
        List<String> fileNamesWithExtension = new ArrayList<>();

        for (String fileName : allFileNames) {
            if (fileName.length() >= extension.length() &&
                    fileName
                            .substring(fileName.length() - extension.length(),
                                    fileName.length())
                            .equals(extension)) {
                fileNamesWithExtension.add(fileName);
            }
        }

        return fileNamesWithExtension;

    }

}
