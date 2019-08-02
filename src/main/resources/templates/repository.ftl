package ${model.generatorConfig.repositoryPackage};

import ${model.generatorConfig.entityPackage}.entity.${model.tableNameJava};
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${model.tableRemark}repo
 * @since jdk1.8
 */
@Repository
public interface ${model.tableNameJava}Repository extends CrudRepository<${model.tableNameJava}, Long> {

}
