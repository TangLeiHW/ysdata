package ml.tanglei.ysdata.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ml.tanglei.ysdata.dao.UserFileInfoDao;
import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;
import ml.tanglei.ysdata.service.UserFileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserFileInfoServiceImpl implements UserFileInfoService {

    @Resource
    private UserFileInfoDao userFileInfoDao;

    @Override
    public PageInfo<UserFileInfo> findUserFileInfo(UserFilePageParamDTO userFilePageParamDTO) {
        PageHelper.startPage(userFilePageParamDTO.getPageNum(),userFilePageParamDTO.getPageSize());
        List<UserFileInfo> userFileInfos = userFileInfoDao.selectUserFileInfo(userFilePageParamDTO);
        PageInfo<UserFileInfo> result = new PageInfo<>(userFileInfos);
        return result;
    }

    @Override
    public void insertUserFileInfo(UserFileInfo userFileInfo) {
        userFileInfoDao.insertUserFileInfo(userFileInfo);
    }
}
