package my.simpletech.codegenerator.core;

import lombok.Data;

import java.sql.Types;

/**
 * 列
 * @author 赵正来
 * @Date 2019/5/15 10:40
 * @since jdk1.8
 */
@Data
public class Column {
    /**
     * 字段名字
     */
    private String columnName;

    /**
     * java字段名字
     */
    private String columnNameJava;

    /**
     * 字段备注
     */
    private String columnRemark;

    /**
     * 字段类型
     */
    private int columnType;

    /**
     * 对应java类型
     */
    private String columnTypeJava;

    /**
     * 是否为主键
     */
    private boolean pk;


    public void setColumnName(String columnName){
        this.columnName = columnName;
        this.columnNameJava = underlineToCamel(columnName);
    }

    public void setColumnType(int columnType){
        this.columnType = columnType;
        this.columnTypeJava = toColumnTypeJava(columnType);
    }

    public static String toColumnTypeJava(int columnType){
        String typeJava = "";
        if(Types.BIGINT == columnType){
            typeJava = "Long";
        }
        if(Types.BIT == columnType || Types.TINYINT == columnType){
            typeJava = "Byte";
        }
        if(Types.CHAR == columnType || Types.VARCHAR == columnType){
            typeJava = "String";
        }
        if(Types.DATE == columnType || Types.TIMESTAMP == columnType){
            typeJava = "Date";
        }
        if(Types.DECIMAL == columnType){
            typeJava = "BigDecimal";
        }
        if(Types.FLOAT == columnType){
            typeJava = "Float";
        }
        if(Types.DOUBLE == columnType){
            typeJava = "Double";
        }
        return typeJava;
    }


    private static final char UNDERLINE = '_';
    /**
     * 下划线格式字符串转换为驼峰格式字符串
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
