package com.codersee.csvupload.service

import com.codersee.csvupload.exception.BadRequestException
import com.codersee.csvupload.exception.CsvImportException
import com.codersee.csvupload.model.Company
import com.codersee.csvupload.model.CompanyList
import com.codersee.csvupload.model.User
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

@Service
class CsvService {

    fun uploadCsvFile(file: MultipartFile): CompanyList {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null

        try {
            val charsetName = Charset.forName("SHIFT-JIS").name()
            fileReader = BufferedReader(InputStreamReader(file.inputStream, charsetName))
            val csvToBean = createCSVToBean(fileReader)

            return CompanyList(csvToBean.parse())
        } catch (ex: Exception) {
            throw CsvImportException("Error during csv import")
        } finally {
            closeFileReader(fileReader)
        }
    }

    private fun throwIfFileEmpty(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException("Empty file")
    }

    private fun createCSVToBean(fileReader: BufferedReader?): CsvToBean<Company> =
        CsvToBeanBuilder<Company>(fileReader)
            .withType(Company::class.java)
            .withIgnoreLeadingWhiteSpace(true)
            .build()

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during csv import")
        }
    }
}
