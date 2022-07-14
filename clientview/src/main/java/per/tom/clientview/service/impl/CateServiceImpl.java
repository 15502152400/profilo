package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.tom.clientview.dao.CateDao;
import per.tom.clientview.dao.WorksDao;
import per.tom.clientview.pojo.CatePo;
import per.tom.clientview.pojo.WorksPo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.utils.AssertBase;
import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.PagenationVo;


import java.util.List;

@Service
public class CateServiceImpl implements CateService {

    @Autowired
    CateDao cateDao;

    @Autowired
    WorksDao worksDao;

    @Autowired
    AssertBase assertBase;

    @Override
    public List<CatePo> getCateByParentCateId(Integer parentCateId) {

        assertBase.assertBase(parentCateId<0);
        List<CatePo> catePoList = cateDao.selectCateByParentCateId(parentCateId);
        assertBase.assertBase(catePoList.size()<1||null==catePoList,"当前分类下无分类");
        return catePoList;
    }

    @Override
    public Integer getParentCateIdByCateId(Integer cateId) {
        assertBase.assertBase(cateId<1);
        return cateDao.getParentCateIdByCateId(cateId);
    }

    @Override
    public Integer[] getCateIdByParentCateId(Integer cateId) {
        return cateDao.selectCateIdByParentCateId(cateId);
    }

    @Transactional
    @Override
    public void saveCate(CatePo catePo) {
        assertBase.assertBase(catePo.getParentCateId()<0);
        assertBase.asserString(catePo.getCateName(), 30, 1);
        if (catePo.getCateId()!=null){

        }else {
            cateDao.insertCate(catePo);
        }
    }

    @Override
    public JsonResultVo getAllCate(Integer currentPage, Integer pageSize, Integer showOrder, Integer parenCateId) {
        assertBase.assertBase(currentPage<1);
        assertBase.assertBase(pageSize!=0&&pageSize!=10&&pageSize!=20);
        assertBase.assertBase(showOrder!=0&&showOrder!=1);
        assertBase.assertBase(parenCateId<-1);
        Integer startIndex = (currentPage-1)*pageSize;

        List<CatePo> cateList = cateDao.selectAllCateByParentCateId(startIndex,pageSize,showOrder,parenCateId);
        Integer countRow = cateDao.countCateByParentCateId(parenCateId);

        PagenationVo pagenationVo = new PagenationVo(currentPage,pageSize,countRow);

        return JsonResultVo.success(cateList,pagenationVo);
    }

    @Override
    @Transactional
    public JsonResultVo deleteCatesByCateIdList(Integer[] cateIdList) {

        // 检查此分类下是否有作品
        assertBase.assertBase(checkIsHaveProfiloByCateIdList(cateIdList),"删除失败！请确保没有作品使用您所选中的分类再删除");

        assertBase.asserArray(cateIdList,"没有分类被删除");

        Integer result = cateDao.deleteCatesByCateIdList(cateIdList);

        return JsonResultVo.success("成功删除"+result+"个分类");
    }

    @Transactional
    @Override
    public void deleteCateByCateId(Integer cateId) {

        // 检查此分类下是否有作品
        assertBase.assertBase(checkIsHaveProfiloByCateId(cateId),"删除失败！请确保没有作品使用此分类再删除");

        assertBase.assertBase(cateId<1,"没有分类被删除");

        Integer row = cateDao.deleteCateByCateId(cateId);

        assertBase.assertBase(row!=1);
    }

    @Transactional
    @Override
    public void updateCateByCateId(CatePo catePo) {
        assertBase.assertBase(null == catePo);

        cateDao.updateCateByCateId(catePo);
    }

    /* ==================> 二级方法 <================== */

    // 检查此分类下是否有作品
    private boolean checkIsHaveProfiloByCateId(Integer cateId){
        WorksPo worksPo = worksDao.checkIsHaveProfiloByCateId(cateId);
        if (null == worksPo){
            return false;
        }else {
            return true;
        }
    }
    private boolean checkIsHaveProfiloByCateIdList(Integer[] cateIds){
        boolean result = true;
        for (Integer cateId:cateIds) {
            if (!checkIsHaveProfiloByCateId(cateId)){
                result = false;
                break;
            }
        }
        return result;
    }


}
