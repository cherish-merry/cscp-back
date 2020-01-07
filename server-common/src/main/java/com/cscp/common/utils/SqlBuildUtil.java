package com.cscp.common.utils;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

import java.util.List;

/*
user s_id,g_id,m_id
school name as school_name
grade name as grade_name
major year

SELECT user.*,school.name as school_name,major.name as major_name,grade.year
FROM user,school,grade,major where user.s_id=school.id
and user.g_id=grade.id and user.m_id=major.id
*/
public class SqlBuildUtil {
    @Data
    private class MasterTable {
        private String tableName;
        private List<String> columnsName;
    }

    @Data
    private class SlaveTable {
        @Data
        private class Column {
            private String columnName;
            private String alias;
        }

        private String tableName;
        private List<Column> columns;
    }

    public String build(MasterTable masterTable,List<SlaveTable> slaveTables){
        return null;
    }
}
