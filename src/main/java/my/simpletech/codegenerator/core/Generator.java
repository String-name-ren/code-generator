package my.simpletech.codegenerator.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import my.simpletech.codegenerator.config.GeneratorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 代码生成类
 * @author 赵正来
 * @Date 2019/5/16 13:52
 * @since jdk1.8
 */
@Service
@Slf4j
public class Generator {

    @Autowired
    private GeneratorConfig generatorConfig;

    @Autowired
    private DataSource dataSource;

    /**
     * 生成代码
     * @throws SQLException
     */
    public void generator() throws SQLException, IOException {
        Connection connection =dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        //获取表字段和类型
        ResultSet resultSetColumns = metaData.getColumns(null,"%",generatorConfig.getTableName(),"%");
        //主键
        ResultSet resultSetPks = metaData.getPrimaryKeys(generatorConfig.getDbName(),generatorConfig.getDbName(),generatorConfig.getTableName());
        Set<String> pks = new HashSet<>();
        while (resultSetPks.next()) {
            String pk = resultSetPks.getString("COLUMN_NAME");
            pks.add(pk);
        }
        Table table = new Table();
        table.setTableName(generatorConfig.getTableName());
        table.setGeneratorConfig(generatorConfig);
        //表信息
        ResultSet resultSetTable = metaData.getTables(null, "%",null,new String[]{"TABLE"});
        Map<String,String> tableNameMap = new HashMap<>();
        while (resultSetTable.next()) {
            String name = resultSetTable.getString("TABLE_NAME");
            String remarks = resultSetTable.getString("REMARKS");
            tableNameMap.put(name,remarks);
        }
        table.setTableRemark(tableNameMap.get(generatorConfig.getTableName()));
        Set<Column> columnSet = new LinkedHashSet<>();
        while (resultSetColumns.next()){
//            int datasize = resultSetColumns.getInt("COLUMN_SIZE");
//            int digits = resultSetColumns.getInt("DECIMAL_DIGITS");
//            int nullable = resultSetColumns.getInt("NULLABLE");
            String columnName = resultSetColumns.getString("COLUMN_NAME");
            String remarks = resultSetColumns.getString("REMARKS");
            int dataType = resultSetColumns.getInt("DATA_TYPE");
            Column column = new Column();
            column.setColumnName(columnName);
            column.setColumnType(dataType);
            column.setColumnRemark(remarks);
            column.setPk(pks.contains(columnName));
            columnSet.add(column);
            log.info("{}", column);
        }
        table.setColumnSet(columnSet);
        generate(table,"entity.ftl", "",generatorConfig.getEntityPath());
        generate(table,"repository.ftl", "Repository",generatorConfig.getRepositoryPath());
        generate(table,"vo.ftl", "Vo",generatorConfig.getEntityPath());
        generate(table,"bo.ftl", "Bo",generatorConfig.getEntityPath());
        generate(table,"service.ftl", "Service",generatorConfig.getEntityPath());
        connection.close();
    }

    /**
     * 生成实体
     * @param table
     */
    public  void generate(Table table,String ftl, String ext, String path) throws IOException {
        File directory = new File("");// 参数为空
        String dir = directory.getCanonicalPath() + "/src/main/resources/templates";
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File(dir));
            cfg.setDefaultEncoding("UTF-8");
            Map<String, Object> root = new HashMap();
            root.put("model", table);
            Template template = cfg.getTemplate(ftl);
            //OutputStream fos = new  FileOutputStream( new File(dir, table.getTableNameJava()+".java")); //java文件的生成目录
            // Writer out = new OutputStreamWriter(fos);
            System.out.println(path +table.getTableNameJava()+ ext +".java");
            template.process(root, new FileWriter(path +table.getTableNameJava()+ ext +".java"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}
