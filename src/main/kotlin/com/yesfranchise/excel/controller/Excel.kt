package com.yesfranchise.excel.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.yesfranchise.excel.data.ExcelDTO
import org.apache.commons.io.FilenameUtils
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile
import java.io.FileInputStream
import java.io.IOException
import java.lang.Exception

@Controller
class Excel {

    @GetMapping("excel")
    fun excel(): String {
        return "excel"
    }

    @PostMapping("excel/read")
    @ResponseBody
    fun excelRead(@RequestParam("word_file") file:MultipartFile, model: Model): String{

        var result: String = ""

        val ext: String = FilenameUtils.getExtension(file.originalFilename)
        if(ext != "docx"){
            throw IOException("확장자 에러")
        }else{
            val list = mutableListOf<ExcelDTO>()
            val document = XWPFDocument(file.inputStream)

            for((i, paragraph) in document.paragraphs.withIndex()){
                for((j, run) in paragraph.runs.withIndex()){
                    if(run.text().startsWith("%") && run.text().endsWith("%")){
                        list.add(ExcelDTO(
                                id = "input$i$j",
                                title = run.text().replace("%",""),
                                contents = ""
                        ))
                    }
                }
            }

            val mapper: ObjectMapper = ObjectMapper()
            try {
                result = mapper.writeValueAsString(list)
            }catch (e: Exception){

            }
        }

        return result
    }
}