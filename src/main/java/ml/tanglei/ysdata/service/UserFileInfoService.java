package ml.tanglei.ysdata.service;



import com.github.pagehelper.PageInfo;
import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;

import java.util.List;


public interface UserFileInfoService {

    PageInfo<UserFileInfo> findUserFileInfo(UserFilePageParamDTO userFilePageParamDTO);

    void insertUserFileInfo(UserFileInfo userFileInfo);
}
