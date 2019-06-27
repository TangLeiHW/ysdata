package ml.tanglei.ysdata.controller;

import com.github.pagehelper.PageInfo;
import ml.tanglei.ysdata.VO.ResultVO;
import ml.tanglei.ysdata.dto.UserFilePageParamDTO;
import ml.tanglei.ysdata.model.UserFileInfo;
import ml.tanglei.ysdata.service.UserFileInfoService;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/userFileInfo")
public class UserFileInfoController {

    @Autowired
    private UserFileInfoService userFileInfoService;

    @Autowired
    private RestTemplate restTemplate;

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

    @GetMapping("/getFileUrl")
    @ResponseBody
    public ResultVO getFileUrl(@RequestParam String menuUrl,@RequestParam String userName) {
        if (!StringUtils.isEmpty(menuUrl) || !StringUtils.isEmpty(userName)) {
            return ResultVO.fail(201,"菜单路径或用户名为空！");
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept-Language", "zh-CN,zh;q=0.9");
        requestHeaders.add("Referer", "http://www.ys168.com/help/");
        requestHeaders.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");

        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(menuUrl, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();

        String html = sttr.replaceAll("(?=_mlxx.scdz)(.*?)(?=\\[\n])", "");
        JXDocument jxDocument = JXDocument.create(html);
        List<JXNode> sel = jxDocument.selN("//li[@class=\"xwj\"]");
        sel.forEach(node -> {
            StringBuffer url = new StringBuffer();
            StringBuffer name = new StringBuffer();
            StringBuffer uploadDate = new StringBuffer();
            StringBuffer size = new StringBuffer();
            node.sel(".//a[1]/@href").forEach(v -> {
                if (!v.toString().equals("javascript:;"))
                    url.append(v.toString());
            });node.sel(".//a[1]/@data-url").forEach(v -> {
                url.append(v.toString());
            });
            node.sel(".//a[1]/text()").forEach(v -> {
                name.append(v.toString());
            });
            node.sel(".//a[1]/@title").forEach(v -> {
                uploadDate.append(v.toString());
            });
            node.sel(".//i/text()").forEach(v -> {
                size.append(v.toString());
            });
            if (!StringUtils.isEmpty(url.toString()) && !StringUtils.isEmpty(name.toString()) && !StringUtils.isEmpty(size.toString()) ) {
                UserFileInfo usrFileInfo = UserFileInfo.builder().userName(userName).fileUrl(url.toString()).fileName(name.toString()).fileSize(size.toString()).build();
                userFileInfoService.insertUserFileInfo(usrFileInfo);
            }
        });

        return ResultVO.success();
    }


}
