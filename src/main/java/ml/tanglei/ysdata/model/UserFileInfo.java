package ml.tanglei.ysdata.model;


import lombok.Data;

import java.util.Date;

@Data
public class UserFileInfo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件大小中文
     */
    private String fileSize;
    /**
     * 文件大小单位Byte
     */
    private Long fileNum;
    /**
     * 文件路径
     */
    private String fileUrl;
    /**
     * 上传时间
     */
    private String uploadDate;
    /**
     * 创建时间
     */
    private Date createTime;

}
