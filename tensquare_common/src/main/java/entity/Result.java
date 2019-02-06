package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 返回实体类
 * @author: Young
 * @create: 2019-02-04 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
  private boolean flag; // 是否成功
  private Integer code; // 返回状态码
  private String message; // 返回信息
  private Object data; // 携带数据

  public Result(boolean flag, Integer code, String message) {
    this.flag = flag;
    this.code = code;
    this.message = message;
  }
}
