package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.thoughtworks.xstream.annotations.XStreamImplicitCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 根据pid查询分类数据
     * @param pid
     * @return
     */
    public List<Category> queryListByPid(Long pid){
        Category r = new Category();
        r.setParentId(pid);
        //调用mapper根据r查询数据
        List<Category> list = categoryMapper.select(r);
        // 判断是否为空
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return list;
    }

}
