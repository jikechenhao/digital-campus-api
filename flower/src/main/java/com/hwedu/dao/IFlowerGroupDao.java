package com.hwedu.dao;

import com.hwedu.po.FlowerGroupPO;

/**
 * <p>学习小组DAO</p>
 *
 * @author xingyongchun
 * @date 2016年4月22日
 */
public interface IFlowerGroupDao {
    /**
     * @param flowerGroupPO
     * @return int
     * @Description: 插入一条小组数据
     * @author xingyongchun
     * @date 2016年4月22日
     */
    public int insertOneGroup(FlowerGroupPO flowerGroupPO);
}
