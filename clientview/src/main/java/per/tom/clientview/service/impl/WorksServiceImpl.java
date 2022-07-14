package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.tom.clientview.config.ProfiloConfig;
import per.tom.clientview.dao.WorksDao;

import per.tom.clientview.pojo.WorksPo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.service.WorksService;
import per.tom.clientview.utils.AssertBase;
import per.tom.clientview.utils.ImageUtils;
import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.PagenationVo;
import per.tom.clientview.vo.WorksSaveVo;
import per.tom.clientview.vo.WorksVo;

import java.util.List;

@Service
public class WorksServiceImpl implements WorksService {

    @Autowired
    WorksDao worksDao;

    @Autowired
    CateService cateService;

    @Autowired
    ProfiloConfig profiloConfig;

    @Autowired
    ImageUtils imageUtils;

    @Autowired
    AssertBase assertBase;

    @Override
    public JsonResultVo getWorksByCateId(Integer cateId, Integer currentPage, Integer pageSize) {

        // 参数校验
        assertBase.assertBase(cateId==null||cateId<0);
        assertBase.assertBase(currentPage==null||currentPage<1);
        assertBase.asserNumber(pageSize, 100, 0);

        // 获取起始索引
        Integer startIndex = (currentPage-1)*pageSize;

        // 查询作品
        List<WorksVo> worksVoList = null;
        Integer countRow = null;
        // 分类id为一级分类
        if (cateService.getParentCateIdByCateId(cateId)==0){
            // 查询此一级分类id对应的所有二级分类id
            Integer[] cateIdArr= cateService.getCateIdByParentCateId(cateId);
            // 查询总记录
            countRow = worksDao.countWorksByCateIdArr(cateIdArr);
            // 执行查询
            worksVoList = worksDao.selectWorksByCateIdArr(cateIdArr,startIndex,pageSize);
        // 分类id为二级分类
        }else {
            // 查询总记录
            countRow = worksDao.countWorksByCateId(cateId);
            // 执行查询
            worksVoList = worksDao.selectWorksByCateId(cateId,startIndex,pageSize);
        }

        // 封装分页信息
        PagenationVo pagenationVo = new PagenationVo(currentPage, pageSize, countRow);
        return JsonResultVo.success(worksVoList, pagenationVo);
    }

    /* 获取作品名字、内容 */
    @Override
    public WorksVo getWorkByWorkId(Integer worksId) {
        assertBase.assertBase(worksId<1||worksId==null);
        WorksVo worksVo = worksDao.getWorkByWorkId(worksId);
        assertBase.assertBase(worksVo==null);
        return worksVo;
    }

    /* 获取所有作品信息 */
    @Override
    public WorksPo getWorkAllByWorkId(Integer worksId) {
        assertBase.assertBase(worksId<1||worksId==null);
        WorksPo worksPo = worksDao.getWorkAllByWorkId(worksId);
        assertBase.assertBase(worksPo==null);
        return worksPo;
    }

    @Transactional
    @Override
    public void saveProfilo(WorksSaveVo worksVo) {
        // 校验空参数
        assertBase.assertBase(null == worksVo);
        // 校验作品id
        assertBase.assertBase(worksVo.getWorksId() < 0);
        // 校验作品名字
        assertBase.asserString(worksVo.getWorksName(), profiloConfig.getProfiloChNameMaxSize(), profiloConfig.getProfiloChNameMinSize(),"作品名字不合法");
        assertBase.asserString(worksVo.getWorksEnName(), profiloConfig.getProfiloEnNameMaxSize(), profiloConfig.getProfiloEnNameMinSize(),"作品名字不合法");
        // 校验作品内容
        assertBase.assertBase(null == worksVo.getWorksContent(),"作品内容不能为空");
        // 校验分类id
        assertBase.assertBase(cateService.getParentCateIdByCateId(worksVo.getWorksCateId()) < 1);
        // 校验设计形式id
        assertBase.assertBase(worksVo.getWorksDesiId() < 1);
        // 保存作品封面
        String imgShowPath = null;
        if (worksVo.getCoverImg()!=null){imgShowPath = imageUtils.saveImg(worksVo.getCoverImg());}
        // 封装PO
        WorksPo worksPo = new WorksPo(worksVo, imgShowPath);
        // 执行添加
        if(worksPo.getWorksId()==0){
            worksDao.insertWorks(worksPo);
        // 执行修改
        }else if(worksPo.getWorksId()>0){
            // 如果图片有改变，则获取原图片地址并删除
            if (worksVo.getCoverImg()!=null){
                imageUtils.deleteImg(worksDao.getWorkCoverByWorkId(worksVo.getWorksId()));
            }
            // 执行修改操作
            worksDao.updateWorkByWorkId(worksPo);
        }
    }

    @Override
    public JsonResultVo getWorksByCateDesi(Integer cateId, Integer desiId, Integer timeOrder, Integer currentPage, Integer pageSize) {

        // 参数校验
        assertBase.assertBase(cateId<0||null == cateId);
        assertBase.assertBase(desiId<0||null == cateId);
        assertBase.asserNumber(timeOrder, 1, 0);
        assertBase.assertBase(currentPage<1||null == currentPage);
        assertBase.asserNumber(pageSize, 100, 0);

        // 获取起始索引
        Integer startIndex = (currentPage-1)*pageSize;

        // 查询作品
        List<WorksVo> worksVoList = null;
        Integer countRow = null;
        // 分类id为二级分类
        if (cateId==0||cateService.getParentCateIdByCateId(cateId)!=0){
            // 查询总记录
            countRow = worksDao.countWorksByCateIdDesi(cateId,desiId);
            // 执行查询
            worksVoList = worksDao.getWorksByCateIdDesi(cateId,desiId,timeOrder,startIndex,pageSize);
        // 分类id为一级分类
        }else {
            // 查询此一级分类id对应的所有二级分类id
            Integer[] cateIdArr= cateService.getCateIdByParentCateId(cateId);
            // 查询总记录
            countRow = worksDao.countWorksByCateAddrDesi(cateIdArr,desiId);
            // 执行查询
            worksVoList = worksDao.getWorksByCateAddrDesi(cateIdArr,desiId,timeOrder,startIndex,pageSize);
        }

        // 封装分页信息
        PagenationVo pagenationVo = new PagenationVo(currentPage, pageSize, countRow);
        return JsonResultVo.success(worksVoList, pagenationVo);
    }

    @Transactional
    @Override
    public void deleteWorksByWorkIds(Integer[] workIds) {
        // 参数校验
        assertBase.asserArray(workIds);
        // 开始删除
        Integer result = worksDao.deleteWorksByWorkIds(workIds);
        // 结果校验
        assertBase.assertBase(result<1,"共删除0个作品");
    }

    @Override
    public void deleteWorkByWorkId(Integer workId) {
        // 参数校验
        assertBase.assertBase(workId<1||null==workId);
        // 开始删除
        Integer result = worksDao.deleteWorkByWorkId(workId);
        // 结果校验
        assertBase.assertBase(result!=1,"删除失败");
    }

}
