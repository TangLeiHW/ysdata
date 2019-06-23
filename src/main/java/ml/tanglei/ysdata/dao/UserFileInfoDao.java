package ml.tanglei.ysdata.dao;

import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;

import java.util.List;

public interface UserFileInfoDao {

    List<UserFileInfo> selectUserFileInfo(UserFilePageParamDTO userFilePageParamDTO);
}
