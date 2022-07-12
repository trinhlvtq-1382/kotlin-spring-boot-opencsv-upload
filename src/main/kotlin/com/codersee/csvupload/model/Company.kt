package com.codersee.csvupload.model

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition

data class Company(
    @CsvBindByName(column = "会社ID")
    var companyId: Long? = null,
    @CsvBindByName(column = "会社名")
    var companyName: String? = null,
    @CsvBindByName(column = "法定代理人の名前")
    var directorName: String? = null
)

data class CompanyList(
    var dataCSv: List<Company>
)

data class CompanyWithCsvBindByPosition(
    @CsvBindByPosition(position = 0)
    var companyId: Long? = null,
    @CsvBindByPosition(position = 1)
    var companyName: String? = null,
    @CsvBindByPosition(position = 2)
    var directorName: String? = null
)

