package my.simpletech.codegenerator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import my.simpletech.codegenerator.core.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
@Data
public class CodeGeneratorApplication {

    @Autowired
    private Generator generator;

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @PostConstruct
    public void init() throws SQLException, IOException {
        generator.generator();
    }


}
