package ml.tanglei.ysdata.dto;

import lombok.Data;

@Data
public class UserFilePageParamDTO {

    private int pageNum = 1;

    private int pageSize = 10;

    private String fileName;

    private String fileType;

    private String startDate;

    private String endDate;

}
