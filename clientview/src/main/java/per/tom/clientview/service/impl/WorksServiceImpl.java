package per.tom.clientview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.tom.clientview.config.ProfiloConfig;
import per.tom.clientview.dao.CateDao;
import per.tom.clientview.dao.WorksDao;

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
    CateDao cateDao;

    @Autowired
    CateService cateService;

    @Autowired
    ProfiloConfig profiloConfig;

    @Autowired
    ImageUtils imageUtils;

    @Override
    public JsonResultVo getWorksByCateId(Integer cateId, Integer currentPage, Integer pageSize) {

        // 参数校验
        AssertBase.assertBase(cateId==null||cateId<0);
        AssertBase.assertBase(currentPage==null||currentPage<1);
        AssertBase.assertBase(pageSize==null||pageSize<0);

        // 获取起始索引
        Integer startIndex = (currentPage-1)*pageSize;

        // 查询作品
        List<WorksVo> worksVoList = null;
        Integer countRow = null;
        // 分类id为一级分类
        if (cateId<=4){
            // 查询此一级分类id对应的所有二级分类id
            Integer[] cateIdArr= cateDao.selectCateIdByParentCateId(cateId);
            // 查询总记录
            countRow = worksDao.countWorksByCateIdArr(cateIdArr);
            // 执行查询
            worksVoList = worksDao.selectWorksByCateIdArr(cateIdArr,startIndex,pageSize);
        // 分类id为二级分类
        }else if(cateId>5){
            // 查询总记录
            countRow = worksDao.countWorksByCateId(cateId);
            // 执行查询
            worksVoList = worksDao.selectWorksByCateId(cateId,startIndex,pageSize);
        }

        // 封装分页信息
        PagenationVo pagenationVo = new PagenationVo(currentPage, pageSize, countRow);
        return JsonResultVo.success(worksVoList, pagenationVo);
    }

    @Override
    public WorksVo getWorkByWorkId(Integer worksId) {
        AssertBase.assertBase(worksId<1||worksId==null);
        WorksVo worksVo = worksDao.getWorkByWorkId(worksId);
        AssertBase.assertBase(worksVo==null);
        return worksVo;
    }

    @Override
    public void saveProfilo(WorksSaveVo worksVo) {
        // 校验空参数
        AssertBase.assertBase(null == worksVo);
        // 校验作品id
        AssertBase.assertBase(worksVo.getWorksId() < 0);
        // 校验作品名字
        AssertBase.asserString(worksVo.getWorksName(), profiloConfig.getProfiloChNameMaxSize(), profiloConfig.getProfiloChNameMinSize(),"作品名字不合法");
        AssertBase.asserString(worksVo.getWorksEnName(), profiloConfig.getProfiloEnNameMaxSize(), profiloConfig.getProfiloEnNameMinSize(),"作品名字不合法");
        // 校验作品内容
        AssertBase.assertBase(null == worksVo.getWorksContent(),"作品内容不能为空");
        // 校验分类id
        AssertBase.assertBase(cateService.getParentCateIdByCateId(worksVo.getWorksCateId()) < 1);
        // 校验设计形式id
        AssertBase.assertBase(worksVo.getWorksDesiId() < 1);
        // 保存作品封面
        String imgShowPath = imageUtils.saveImg(worksVo.getCoverImg());
        System.out.println(imgShowPath);

    }
}
