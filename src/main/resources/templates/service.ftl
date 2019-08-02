package ${model.generatorConfig.servicePackage};

import cn.com.waterelephant.media.domain.common.vo.RestVo;
import ${model.generatorConfig.entityPackage}.bo.${model.tableNameJava}Bo;
import ${model.generatorConfig.entityPackage}.entity.${model.tableNameJava};
import ${model.generatorConfig.entityPackage}.vo.${model.tableNameJava}Vo;
import ${model.generatorConfig.repositoryPackage}.${model.tableNameJava}Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ${model.tableRemark}service
 * @since jdk1.8
 */
@Service
@Slf4j
public class ${model.tableNameJava}Service {

    private @Autowired
    ${model.tableNameJava}Repository ${model.tableNameJava}Repository;

    /**
     * 新增
     * @param bo
     * @return
     */
    @Transactional
    public RestVo<${model.tableNameJava}Vo> insert(${model.tableNameJava}Bo bo){
        log.info("${model.tableNameJava}Service.insert bo => {}", bo);
        if(bo == null){
            log.info("insert bo is null");
            return RestVo.SUCCESS(null);
        }
        ${model.tableNameJava} entity = new ${model.tableNameJava}();
        BeanUtils.copyProperties(bo, entity);
        entity.setGmtCreated(new Date());
        ${model.tableNameJava}Repository.save(entity);
        ${model.tableNameJava}Vo vo = new ${model.tableNameJava}Vo();
        BeanUtils.copyProperties(entity, vo);
        return RestVo.SUCCESS(vo);
    }

    /**
    * 根据id查找
    * @param id
    * @return
    */
    public RestVo<${model.tableNameJava}Vo> findById(Long id){
        log.info("${model.tableNameJava}Service.findById id => {}", id);
        ${model.tableNameJava} entity = ${model.tableNameJava}Repository.findById(id).get();
        if(entity == null){
            log.info("${model.tableNameJava} findById id=>{} is null", id);
            return RestVo.SUCCESS(null);
        }
        ${model.tableNameJava}Vo vo = new ${model.tableNameJava}Vo();
        BeanUtils.copyProperties(entity, vo);
        return RestVo.SUCCESS(vo);
    }
}
