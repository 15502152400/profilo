package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.tom.clientview.dao.CateDao;
import per.tom.clientview.pojo.CatePo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.utils.AssertBase;


import java.util.List;

@Service
public class CateServiceImpl implements CateService {

    @Autowired
    CateDao cateDao;

    @Override
    public List<CatePo> getCateByParentCateId(Integer parentCateId) {

        AssertBase.assertBase(parentCateId<0);
        List<CatePo> catePoList = cateDao.selectCateByParentCateId(parentCateId);
        AssertBase.assertBase(catePoList.size()<1||null==catePoList,"当前一级分类下没有二级分类");
        return catePoList;
    }

    @Override
    public Integer getParentCateIdByCateId(Integer cateId) {
        AssertBase.assertBase(cateId<1);
        return cateDao.getParentCateIdByCateId(cateId);
    }
}
