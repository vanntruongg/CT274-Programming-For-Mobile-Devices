package com.example.mymap.data

import com.example.mymap.models.Place
import com.example.mymap.models.UserMap

object DataGenerate {
    fun generateSimpleData() : List<UserMap> {
        return listOf(
            UserMap("Đại học Cần Thơ",
                listOf(
                    Place("Trường CNTT&TT", "thuộc ĐH Cần Thơ", 10.0308541, 105.768986),
                    Place("Trường Nông Nghiệp", "thuộc ĐH Cần Thơ", 10.0302655, 105.7679642),
                    Place("Hội trường rùa", "nơi tổ chức các hoạt động...", 10.0293402, 105.7690273)
                )
            ),
            UserMap("Ẩm thực",
                listOf(
                    Place("The 80's icafe", "Đường Mạc Thiên Tích", 10.0286827, 105.7732964),
                    Place("Trà sữa Tigon", "Đường Mạc Thiên Tích", 10.0278105, 105.7718373),
                    Place("Cafe Thủy Mộc", "Đường 3/2", 10.0273775, 105.7704913)
                )
            )
        )
    }
}