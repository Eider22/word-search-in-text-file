package com.eider.controllers.contracts;

import com.eider.controllers.dtos.DetailsWordTimesQuantityInFolderDto;
import com.eider.controllers.dtos.ResponseDto;

public interface ISystemController {
    public ResponseDto<DetailsWordTimesQuantityInFolderDto> getDetailsWordTimesQuantityInPathByExtension(String filePath, String word, String extension);
}
