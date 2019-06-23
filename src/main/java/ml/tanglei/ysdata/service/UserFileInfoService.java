package ml.tanglei.ysdata.service;



import com.github.pagehelper.PageInfo;
import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;


public interface UserFileInfoService {

    PageInfo<UserFileInfo> findUserFileInfo(UserFilePageParamDTO userFilePageParamDTO);
}
