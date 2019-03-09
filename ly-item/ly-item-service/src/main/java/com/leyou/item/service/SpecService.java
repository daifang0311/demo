package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class SpecService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid){
        SpecGroup s = new SpecGroup();
        s.setCid(cid);
        List<SpecGroup> list = groupMapper.select(s);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.SPEC_GROUP_NOT_FOUND);
        }
        return list;
    }
    public List<SpecParam> queryParamByGid(Long gid) {
        SpecParam p = new SpecParam();
        p.setGroupId(gid);
        List<SpecParam> list = paramMapper.select(p);
        if (CollectionUtils.isEmpty(list)) {
            throw new LyException(ExceptionEnum.SPEC_PARAM_NOT_FOUND);
        }
        return list;
    }
}
