package silly.h1024h.test

import org.junit.Test
import silly.h1024h.dao.HotDao
import silly.h1024h.dao.ResDataDao
import silly.h1024h.dao.TypeListDao
import silly.h1024h.utils.*
import java.util.*


class CommonTest {

    var fileF = arrayOf("3agirl_url.txt", "aimishe_url.txt", "51modozazhi_url.txt", "aiss_url.txt", "aixiu_url.txt", "beautyleg_url.txt", "boluoshe_url.txt", "chizuzhe_url.txt", "damingmowang_url.txt", "ddy_url.txt", "disi_url.txt", "dongganzhixing_url.txt", "feilin_url.txt", "feituwang_url.txt", "girlt_url.txt", "HeiSiAi_url.txt", "huayan_url.txt", "jiamiannvhuang_url.txt", "kelanvshen_url.txt", "Leghacker_url.txt", "ligui_url.txt", "maomengbang_url.txt", "meituibaobei_url.txt", "meixiu_url.txt", "meiyanshe_url.txt", "meiyuanguan_url.txt", "MFStar_url.txt", "mitaoshe_url.txt", "moki_url.txt", "msjlb_url.txt", "niceleg_url.txt", "paimei_url.txt", "pansidong_url.txt", "qingdouke_url.txt", "rosi_url.txt", "ruyixiezhen_url.txt", "shanghaixuancai_url.txt", "sibao_url.txt", "sijianwu_url.txt", "simeivip_url.txt", "sishangxiezhen_url.txt", "tangguohuabao_url.txt", "tangsi_url.txt", "tangyun_url.txt", "taste_url.txt", "tianshisheying_url.txt", "ttns_url.txt", "tuinvlang_url.txt", "tuinvshen_url.txt", "tukmo_url.txt", "Tyingart_url.txt", "vnvlang_url.txt", "xingleyuan_url.txt", "xiurenwang_url.txt", "xiweisha_url.txt", "xixiwang_url.txt", "yannvshen_url.txt", "yingsihui_url.txt", "youguowang_url.txt", "youmihui_url.txt", "youwuguan_url.txt", "youxingguan_url.txt", "yunvlang_url.txt", "zhongguotuimo_url.txt")

    @Test
    fun creatTable() {
        for (file in fileF) {
            val table = "res_" + file.replace("_url.txt", "")
            ResDataDao().createResTable(table)
        }
    }

    @Test
    fun saveData() {
        for (index in 50 until fileF.size) {
            val name = fileF[index] // 63
//            val name = fileF[48] // 63
            val table = name.replace("_url.txt", "")
            val dom = UrlReqUtil.get("https://gitee.com/sillysnnall/H1024H/raw/master/url/$name")
            val split1 = dom.split("\n")
            for (spl in 0 until split1.size) {
                if (split1[spl].isEmpty()) continue
                val split = split1[spl].split("=")
                val sub = split[1].substring(split[1].lastIndexOf("/") + 1, split[1].lastIndexOf(".html"))

                val dom1 = UrlReqUtil.get("https://gitee.com/sillysnnall/H1024H/raw/master/img/$table/$sub.txt")
                System.out.println("类别------------: $sub")
                val split2 = dom1.split("\n")
                var count = 0
                for (sp in split2.indices) {
                    if (split2[sp].isEmpty()) continue
                    val split3 = split2[sp].split("=")
                    val i = if (sp == 0) 1 else 0
                    val params = arrayOf<Any>("'${split3[0]}'", "'${split3[1]}'", "'$sub'", i)
                    ResDataDao().addResData("res_${table.toLowerCase()}", params)
                    count++
                    System.out.println("表名: $table @@@@@ index: $index/${fileF.size} @@@@@ 一层计数: $count/${split2.size} @@@@@ 二层计数: $spl/${split1.size}")
//                    System.out.println("表名: $table @@@@@ index: $48/${fileF.size} @@@@@ 一层计数: $count/${split2.size} @@@@@ 二层计数: $spl/${split1.size}")
                }
            }
        }
        System.out.println("完成------完成")
    }

    @Test
    fun saveTypeListData() {
        val dom = UrlReqUtil.get("https://gitee.com/sillysnnall/H1024H/raw/master/cover_url.txt")
        val split1 = dom.split("\n")
        for (spl in 0 until split1.size) {
            if (split1[spl].isEmpty()) continue
            val split = split1[spl].split("=")
            val replace = "res_" + split[1].replace("_url.txt", "").toLowerCase()
            val params = arrayOf<Any>("'${split[0]}'", "'$replace'")
            TypeListDao().addTypeListData(params)
        }
    }

    @Test
    fun findCover() {
        val findCover = ResDataDao().findCover("res_3agirl", 0, 20)
        for (fin in findCover) {
            System.out.println(fin.name)
        }
    }

    @Test
    fun saveHot() {
        val hotDao = HotDao()
        for (fil in fileF) {
            val dom = UrlReqUtil.get("https://gitee.com/sillysnnall/H1024H/raw/master/url/$fil")
            System.out.println("文件: $fil")
            val table = "res_" + fil.replace("_url.txt", "")
            val split1 = dom.split("\n")
            for (spl in split1) {
                if (spl.isEmpty()) continue
                val split = spl.split("=")
                val sub = split[1].substring(split[1].lastIndexOf("/") + 1, split[1].lastIndexOf(".html"))
                val params = arrayOf<Any>("'$sub'", "'$table'")
                hotDao.addHotData(params)
                System.out.println("type: $sub")
            }
        }
    }

    @Test
    fun hotCount() {
        val hotDao = HotDao()
        hotDao.countHot("26933")
    }

    @Test
    fun getHot() {
        val resDataDao = ResDataDao()
        val sortHotData = HotDao().getSortHotData(0, 3)
        for (data in sortHotData) {
            System.out.println(resDataDao.findByTypeCover(data.table_name, data.type).type)
        }
    }

    @Test
    fun redis() {
        RedisUtil.getRu().setex("qwe", "123", 1800)
    }
    @Test
    fun sign(){
        val str = "哈哈哈,123!qweasdzc"
        val encode = DesUtil.encrypt(str)
        System.out.println("加密: $encode")
        val decode = DesUtil.decrypt(encode)
        System.out.println("解密: $decode")
    }

    @Test
    fun time(){
        val uuid = System.currentTimeMillis()/1000
        System.out.println("解密: $uuid")
    }
}
