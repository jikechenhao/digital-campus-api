package com.hwedu.dao;

import com.hwedu.po.FlowerSectionPO;

/**
 * |
 * <p>
 * <p>小结(作息时间)DAO</p>
 *
 * @author xingyongchun
 * @date 2016年4月22日
 */
public interface IFlowerSectionDao {
    /**
     * 添加一条小结记录
     *
     * @param flowerSectionPO
     * @return int
     * @Description: TODO
     * @author xingyongchun
     * @date 2016年4月22日
     */
    public int insertOneSection(FlowerSectionPO flowerSectionPO);
}
