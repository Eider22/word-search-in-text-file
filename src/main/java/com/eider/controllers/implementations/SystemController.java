package com.eider.controllers.implementations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.eider.controllers.contracts.ISystemController;
import com.eider.controllers.dtos.DetailsWordTimesQuantityInFileDto;
import com.eider.controllers.dtos.DetailsWordTimesQuantityInFolderDto;
import com.eider.controllers.dtos.ResponseDto;
import com.eider.exceptions.PathNotFoundException;
import com.eider.services.contracts.IFileManagerService;
import com.eider.services.implementations.FileManagerService;

public class SystemController implements ISystemController {

    private IFileManagerService fileManagerService;

    public SystemController(IFileManagerService fileManagerService) {
        this.fileManagerService = fileManagerService != null ? fileManagerService : new FileManagerService();
    }

    @Override
    public ResponseDto<DetailsWordTimesQuantityInFolderDto> getDetailsWordTimesQuantityInPathByExtension(String filePath,
            String word, String extension) {
        ResponseDto<DetailsWordTimesQuantityInFolderDto> response = new ResponseDto<>();
        List<DetailsWordTimesQuantityInFileDto> detailsWordTimesQuantityInFileList = new ArrayList<>();

        try {
            List<String> fileNames = this.fileManagerService.listPathFileNamesByExtension(filePath, extension);

            if (fileNames.isEmpty()) {
                response.setSucces(false);
                response.setMessage("No existen archivos en la carpeta con extensión " + extension);
                return response;
            }

            String filePathAux = "";
            for (String fileName : fileNames) {
                filePathAux = filePath + "/" + fileName;
                List<String> wordsInFileAux = this.fileManagerService.fileWordsToList(filePathAux);
                DetailsWordTimesQuantityInFileDto detailsWordTimesQuantityInFileAux = this
                        .createDetailsWordTimesQuantityInFile(wordsInFileAux, word, fileName);

                detailsWordTimesQuantityInFileList.add(detailsWordTimesQuantityInFileAux);
            }

            DetailsWordTimesQuantityInFolderDto detailsWordTimesQuantityInFolder = this
                    .createDetailsWordTimesQuantityInFolder(detailsWordTimesQuantityInFileList);

            response.setSucces(true);
            response.setMessage("Información obtenida con éxito");
            response.setData(detailsWordTimesQuantityInFolder);
            return response;
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            response.setSucces(false);
            response.setMessage("No se encontró la carpeta que indicaste");
            return response;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            response.setSucces(false);
            response.setMessage("Error interno");
            return response;

        } catch (IOException e) {
            e.printStackTrace();
            response.setSucces(false);
            response.setMessage("Error interno");
            return response;
        }
    }

    private DetailsWordTimesQuantityInFileDto createDetailsWordTimesQuantityInFile(List<String> wordsInFile,
            String inputWord, String fileName) {
        DetailsWordTimesQuantityInFileDto detailsWordTimesQuantityInFileAux = new DetailsWordTimesQuantityInFileDto();
        detailsWordTimesQuantityInFileAux.setFileName(fileName);

        for (String wordInFile : wordsInFile) {
            if (wordInFile.equalsIgnoreCase(inputWord)) {
                detailsWordTimesQuantityInFileAux
                        .setQuantity(detailsWordTimesQuantityInFileAux.getQuantity() + 1);

            }
        }

        return detailsWordTimesQuantityInFileAux;
    }

    private DetailsWordTimesQuantityInFolderDto createDetailsWordTimesQuantityInFolder(
            List<DetailsWordTimesQuantityInFileDto> detailsWordTimesQuantityInFileList) {

        DetailsWordTimesQuantityInFolderDto detailsWordTimesQuantityInFolder = new DetailsWordTimesQuantityInFolderDto();
        detailsWordTimesQuantityInFolder.setDetailsWordTimesQuantityInFiles(detailsWordTimesQuantityInFileList);

        int totalTimesWordInFolderAux = 0;
        for (DetailsWordTimesQuantityInFileDto detailsWordTimesQuantityInFile : detailsWordTimesQuantityInFolder
                .getDetailsWordTimesQuantityInFiles()) {
            totalTimesWordInFolderAux = totalTimesWordInFolderAux
                    + detailsWordTimesQuantityInFile.getQuantity();
        }

        detailsWordTimesQuantityInFolder
                .setQuantity(totalTimesWordInFolderAux);

        return detailsWordTimesQuantityInFolder;
    }

}
