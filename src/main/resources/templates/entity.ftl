package ${model.generatorConfig.entityPackage}.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ${model.tableRemark}实体类
 * @since jdk1.8
 */
@Data
@Entity
@Table(name = "${model.tableName}")
public class ${model.tableNameJava} implements Serializable {
    <#list model.columnSet as column>
    /**
     *${column.columnRemark}
     */
        <#if column.pk>
    @Id//主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
        </#if>
     @Column(name = "${column.columnName}")
     private ${column.columnTypeJava} ${column.columnNameJava} ;

    </#list>
}