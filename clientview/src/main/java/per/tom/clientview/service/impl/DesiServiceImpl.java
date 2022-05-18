package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.tom.clientview.dao.DesiDao;
import per.tom.clientview.pojo.DesiPo;
import per.tom.clientview.service.DesiService;
import per.tom.clientview.utils.AssertBase;

import java.util.List;

@Service
public class DesiServiceImpl implements DesiService {

    @Autowired
    DesiDao desiDao;

    @Override
    public List<DesiPo> getAllDesi() {
       List<DesiPo> desiList = desiDao.getAllDesi();
       AssertBase.assertList(desiList,"无法获取设计形式，请先创建");
       return desiList;
    }
}
