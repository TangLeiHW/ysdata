package ml.tanglei.ysdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ml.tanglei.ysdata.dao")
public class YsdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(YsdataApplication.class, args);
    }

}
