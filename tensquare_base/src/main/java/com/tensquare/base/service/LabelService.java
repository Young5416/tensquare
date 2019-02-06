package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 标签业务逻辑类
 * @author: Young
 * @create: 2019-02-04 20:49
 */
@Service
@Transactional
public class LabelService {

  @Autowired private LabelDao labelDao;

  @Autowired private IdWorker idWorker;

  /**
   * 查询全部标签
   *
   * @return
   */
  public List<Label> findAll() {
    return labelDao.findAll();
  }
  /**
   * 根据ID查询标签
   *
   * @return
   */
  public Label findById(String id) {
    return labelDao.findById(id).get();
  }
  /**
   * 增加标签
   *
   * @param label
   */
  public void save(Label label) {
    label.setId(idWorker.nextId() + "");
    labelDao.save(label);
  }
  /**
   * 修改标签
   *
   * @param label
   */
  public void update(Label label) {
    labelDao.save(label);
  }

  /**
   * 删除标签
   *
   * @param id
   */
  public void deleteById(String id) {
    labelDao.deleteById(id);
  }

  /**
   * @Description: 条件查询 @Param: [searchMap]
   *
   * @return: java.util.List<com.tensquare.base.pojo.Label> @Author: Young @Date: 2019/2/6
   */
  public List<Label> findSearch(Label label) {
    Specification<Label> labelSpecification = createLabelSpecification(label);
    return labelDao.findAll(labelSpecification);
  }

  /**
   * @Description: 分页条件查询 @Param: [searchMap]
   *
   * @return: java.util.List<com.tensquare.base.pojo.Label> @Author: Young @Date: 2019/2/6
   */
  public Page<Label> findSearch(Label label, int page, int pageSize) {
    PageRequest pageRequest = PageRequest.of(page-1,pageSize);
    Specification<Label> labelSpecification = createLabelSpecification(label);
    return labelDao.findAll(labelSpecification,pageRequest);
  }

  /**
   * @Description: 构建查询条件 @Param: [searchMap]
   *
   * @return:
   *     org.springframework.data.jpa.domain.Specification<com.tensquare.base.pojo.Label> @Author:
   *     Young @Date: 2019/2/6
   */
  private Specification<Label> createLabelSpecification(Label label) {

    return new Specification<Label>() {
      @Override
      public Predicate toPredicate(
          Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicateList = new ArrayList<>();
        if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
          predicateList.add(
              criteriaBuilder.like(
                  root.get("labelname").as(String.class),
                  "%" +label.getLabelname() + "%"));
        }

        if (label.getState()!= null && !"".equals(label.getState())) {
          predicateList.add(
              criteriaBuilder.like(
                  root.get("state").as(String.class), "%" +label.getState() + "%"));
        }

        if (label.getRecommend()!= null && !"".equals(label.getRecommend())) {
          predicateList.add(
              criteriaBuilder.like(
                  root.get("recommend").as(String.class),
                  "%" +label.getRecommend() + "%"));
        }

        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
      }
    };
  }
}
