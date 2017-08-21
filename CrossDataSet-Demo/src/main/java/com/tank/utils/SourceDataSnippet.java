package com.tank.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * 代码片段
 */
public class SourceDataSnippet {


  public void fetchRows(String tableA, int unit, boolean withHeader) {
    List<String> rows = new LinkedList<String>();
    boolean isPagination = unit == 0;

  }


  public static SourceDataSnippet instance() {
    return sd;
  }

  private SourceDataSnippet() {

  }

  private  static SourceDataSnippet sd = new SourceDataSnippet();
}
