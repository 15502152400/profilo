package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.tom.clientview.config.DesiConfig;
import per.tom.clientview.dao.DesiDao;
import per.tom.clientview.pojo.DesiPo;
import per.tom.clientview.service.DesiService;
import per.tom.clientview.utils.AssertBase;
import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.PagenationVo;

import java.util.List;

@Service
public class DesiServiceImpl implements DesiService {

    @Autowired
    DesiDao desiDao;

    @Autowired
    AssertBase assertBase;

    @Autowired
    DesiConfig desiConfig;

    @Override
    public List<DesiPo> getAllDesi() {
       List<DesiPo> desiList = desiDao.getAllDesi();
       assertBase.assertList(desiList,"无法获取设计形式，请先创建");
       return desiList;
    }

    @Transactional
    @Override
    public void saveDesi(String desiName) {

        assertBase.asserString(desiName, desiConfig.getMaxDesiName(), desiConfig.getMinDesiName(),"设计形式名字填写不合法");

        desiDao.saveDesi(desiName);

    }

    @Override
    public JsonResultVo getAllDesiByPagenation(Integer currentPage, Integer pageSize, Integer showOrder) {
        assertBase.assertBase(currentPage<1);
        assertBase.assertBase(pageSize!=0&&pageSize!=10&&pageSize!=20);
        assertBase.assertBase(showOrder!=0&&showOrder!=1);

        Integer startIndex = (currentPage-1)*pageSize;

        List<DesiPo> desiPoList = desiDao.getAllDesiByPagenation(currentPage,pageSize,showOrder,startIndex);
        Integer countRow = desiDao.countDesi();

        PagenationVo pagenationVo = new PagenationVo(currentPage, pageSize, countRow);

        return JsonResultVo.success(desiPoList,pagenationVo);
    }

    @Override
    public void deleteDesiByDesiIdList(Integer[] desiIdList) {
        assertBase.asserArray(desiIdList);
        desiDao.deleteDesiByDesiIdList(desiIdList);
    }

    @Override
    public void deleteDesiByDesiId(Integer desiId) {
        assertBase.assertBase(desiId<=0);
        desiDao.deleteDesiByDesiId(desiId);
    }

    @Override
    public void updateDesiByDesiId(Integer desiId, String desiName) {
        assertBase.assertBase(desiId<=0);
        assertBase.asserString(desiName, desiConfig.getMaxDesiName(), desiConfig.getMinDesiName(),"设计形式名字不合法");
        desiDao.updateDesiByDesiId(desiId,desiName);
    }
}
