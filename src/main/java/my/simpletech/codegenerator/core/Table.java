package my.simpletech.codegenerator.core;

import lombok.Data;
import my.simpletech.codegenerator.config.GeneratorConfig;

import java.util.Set;

/**
 * @author 赵正来
 * @Date 2019/5/15 10:39
 * @since jdk1.8
 */
@Data
public class Table {
    /**
     * 表名字
     */
    private String tableName;

    /**
     * 表名字
     */
    private String tableNameJava;

    /**
     * 表备注
     */
    private String tableRemark;

    /**
     * 字段
     */
    private Set<Column> columnSet;

    /**
     * 配置信息
     */
    private GeneratorConfig generatorConfig;

    public void setTableName(String tableName){
        this.tableName = tableName;
        String name = Column.underlineToCamel(tableName);
        this.tableNameJava = toUpperCaseFirstOne(name);
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
