package com.qxd.birth.dal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class BaseEntity extends IdEntity {

  /**
   * isDeleted字段是否删除，标记为Y，已删除
   */
  public static final String IS_DELETE_ENABLE = "Y";

  /**
   * isDeleted字段是否删除，标记为N，没删除
   */
  public static final String IS_DELETE_UNENABLE = "N";

  protected String isDeleted;
  protected Date gmtCreate;
  protected Date gmtModified;
}
