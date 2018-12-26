package com.liwy.study.mybatis.dao;

import com.liwy.study.mybatis.bo.ContentBo;
import com.liwy.study.mybatis.bo.Pageable;
import com.liwy.study.mybatis.entity.Content;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>名称：</b> 内容数据访问接口<br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2018/11/28 15:52 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
public interface IContentDao {
    /**
     * <b>描述：</b> 插入内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param content
     * @return int
     */
    int insertContent(Content content);

    /**
     * <b>描述：</b> 插入内容与ID<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param content
     * @return int
     */
    int insertContentAndId(Content content);

    /**
     * <b>描述：</b> 删除内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return int
     */
    int deleteContent(Long id);

    /**
     * <b>描述：</b> 物理删除内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return int
     */
    int deleteContentReal(Long id);

    /**
     * <b>描述：</b> 更新内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param content
     * @param param
     * @return int
     */
    int updateContent(@Param("content") Content content, @Param("param") Content param);

    /**
     * <b>描述：</b> 查询内容列表<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return java.util.List<com.liwy.study.mybatis.entity.Content>
     */
    List<Content> selectContent(@Param("contentBo") ContentBo param, @Param("pageable") Pageable pageable);

    /**
     * <b>描述：</b> 查询内容详细信息列表<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return java.util.List<com.liwy.study.mybatis.bo.ContentBo>
     */
    List<ContentBo> selectContentBo(@Param("contentBo") ContentBo param);

    /**
     * <b>描述：</b> 获取内容信息<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return com.liwy.study.mybatis.entity.Content
     */
    Content getContent(Long id);

    /**
     * <b>描述：</b> 获取内容信息MAP<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return com.liwy.study.mybatis.entity.Content
     */
    Map<String, Object> getContentMap(Long id);

    /**
     * <b>描述：</b> 获取内容详细信息<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param id
     * @return com.liwy.study.mybatis.bo.ContentBo
     */
    ContentBo getContentBo(Long id);

    /**
     * <b>描述：</b> 查询栏目下所有实体的数量<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param contentBo
     * @return void
     */
    void getEntityCount(@Param("channelId") Integer channelId, @Param("contentBo") ContentBo contentBo);

    /**
     * <b>描述：</b> 批量插入内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param contentList
     * @return int
     */
    int batchInsertContent(List<Content> contentList);

    /**
     * <b>描述：</b> 批量更新内容<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param contentList
     * @return int
     */
    int batchUpdateContent(List<Content> contentList);

}
