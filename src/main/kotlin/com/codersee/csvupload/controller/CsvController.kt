package com.codersee.csvupload.controller

import com.codersee.csvupload.model.Company
import com.codersee.csvupload.model.CompanyList
import com.codersee.csvupload.model.Healthz
import com.codersee.csvupload.model.User
import com.codersee.csvupload.service.CsvService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/csv")
class CsvController(
    private val csvService: CsvService
) {

    @PostMapping
    fun uploadCsvFile(
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<CompanyList> {
        val importedEntries = csvService.uploadCsvFile(file)

        return ResponseEntity.ok(importedEntries)
    }

    @GetMapping(value = ["/healthz"])
    fun index(): ResponseEntity<*> {
        val result = Healthz()
        result.message = "Service upload csv is fine !!!"
        return ResponseEntity.ok(result)
    }

}
