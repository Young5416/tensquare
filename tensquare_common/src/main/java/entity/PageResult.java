package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description: 返回分页实体类
 * @author: Young
 * @create: 2019-02-04 19:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
  private long total;
  private List<T> rows;
}
