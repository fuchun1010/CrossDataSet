package com.tank.datasource;

import javax.validation.constraints.NotNull;
import java.util.function.IntFunction;

public class DataExtractor {

  public DataExtractor(@NotNull String tableName) {
    this.tableName = tableName;
  }

  public String genSqlWithPageNo( int pageNo) {
    StringBuffer buffer = new StringBuffer();
    int start = pageNo == 0 ? 0: (pageNo - 1) * 100;
    buffer.append("(select * from person limit " +start+", 100)");
    buffer.append(" as " + tableName);
    return buffer.toString();
  }

  private String tableName;
}
