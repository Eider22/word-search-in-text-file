package com.eider.services.contracts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.eider.exceptions.PathNotFoundException;

public interface IFileManagerService {
    public List<String> fileWordsToList(String filePath) throws FileNotFoundException, IOException;

    public List<String> listPathFileNames(String folderPath) throws PathNotFoundException;

    public List<String> listPathFileNamesByExtension(String folderPath, String extension)
            throws PathNotFoundException;
}
