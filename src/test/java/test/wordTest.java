package test;

import java.io.IOException;
import java.io.InputStream;

import com.myproject.controller.QuestionnaireController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.controller.UploadController;
import com.myproject.controller.UserController;

public class wordTest {
	
	
//	@Test
//    public void testReadByDoc() throws Exception {
//        word rd = new word();
//        rd.testReadByDoc("D:\\template.doc");
//    }
//	
//	 @Test
//    public void testWrite() throws Exception {
//		 word wd = new word();
//        wd.testWrite();
//    }
	
//	@Test
//	public void testExcel() throws Exception {
//		UploadController.getDataFromExcel("D:\\test.xlsx");
//	}

    @Test
    public void test() {
        QuestionnaireController questionnaireController = new QuestionnaireController();
        questionnaireController.findComplete("1246494ff265415998a6320117b3b810");
    }

}
