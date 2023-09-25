package com.enjoyservice.global.config.dialect;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MysqlCustomDialect extends MySQLDialect {

    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        functionContributions
                .getFunctionRegistry()
                .register( "group_concat",
                        new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
