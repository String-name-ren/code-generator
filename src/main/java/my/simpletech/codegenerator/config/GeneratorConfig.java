package my.simpletech.codegenerator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 赵正来
 * @Date 2019/5/16 10:55
 * @since jdk1.8
 */
@Data
@ConfigurationProperties(prefix = "generate")
@Component
public class GeneratorConfig {

    /**
     * 生成表所在db名字
     */
    private String dbName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 生成实体存放路径
     */
    private String entityPath;

    /**
     * 生成实体包
     */
    private String entityPackage;

    /**
     * repository存放路径
     */
    private String repositoryPath;

    /**
     * repository包
     */
    private String repositoryPackage;

    /**
     * service存放路径
     */
    private String servicePath;

    /**
     * service包
     */
    private String servicePackage;
}
