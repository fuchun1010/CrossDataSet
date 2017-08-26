package com.tank.datasource;

public class DataExtractor {

  public DataExtractor(String tableName) {
    this.tableName = tableName;
  }

  public String genSqlWithPageNo(int pageNo) {
    StringBuffer buffer = new StringBuffer();
    int start = pageNo == 0 ? 0: (pageNo - 1) * 100;
    buffer.append("(select * from person limit " +start+", 100)");
    buffer.append(" as _person");
    return buffer.toString();
  }

  private String tableName;
}
