package ${model.generatorConfig.entityPackage}.bo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ${model.tableRemark}Bo
 * @since jdk1.8
 */
@Data
@ApiModel(description = "${model.tableRemark}Bo")
public class ${model.tableNameJava}Bo implements Serializable {
    <#list model.columnSet as column>
    
     @ApiModelProperty("${column.columnRemark}")
     private ${column.columnTypeJava} ${column.columnNameJava} ;

    </#list>
}