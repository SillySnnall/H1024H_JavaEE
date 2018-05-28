package silly.h1024h.dao.impl

interface ResDataDaoImpl {
    /**
     * irCover分页查找
     */
    fun getCover(pageNum: Int, itemCount: Int): List<ImgRes>

    /**
     * irType分页查找
     */
    fun getDetailed(irType: Int, pageNum: Int, itemCount: Int): List<ImgRes>

    /**
     * 存储图片
     */
    fun saveImg(imgResList: List<ImgRes>): Boolean

    /**
     * 根据irCover，irDetails查询数据
     */
    fun findByIrCoverIrDetails(irCover: Int, irDetails: String):List<ImgRes>

    /**
     * 根据irDetails查询数据
     */
    fun findByDetails(irDetails: String):List<ImgRes>

    /**
     * 查询irType最大值
     */
    fun getIrTypeMax():List<ImgRes>

    /**
     * 查询全部irDetails数据，相同的只会返回一个
     */
    fun findAllDetailsOnly(): List<String>

    /**
     * 根据irCover，irType查询数据
     */
    fun findByIrCoverIrType(irCover: Int, irType: Int):List<ImgRes>
}