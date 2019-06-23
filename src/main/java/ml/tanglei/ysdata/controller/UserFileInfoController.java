package ml.tanglei.ysdata.controller;

import com.github.pagehelper.PageInfo;
import ml.tanglei.ysdata.VO.ResultVO;
import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;
import ml.tanglei.ysdata.service.UserFileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value = "/userFileInfo")
public class UserFileInfoController {

    @Autowired
    private UserFileInfoService userFileInfoService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String getUserById(){
        return "userFileInfo";
    }

    @PostMapping("/find")
    @ResponseBody
    public ResultVO findUserFileInfo(@RequestBody UserFilePageParamDTO userFilePageParamDTO) {
        PageInfo<UserFileInfo> userFileInfo = userFileInfoService.findUserFileInfo(userFilePageParamDTO);
        return ResultVO.success(userFileInfo);
    }


}
