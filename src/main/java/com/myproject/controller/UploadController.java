package com.myproject.controller;

import com.myproject.service.AnswerService;
import com.myproject.service.UserService;
import com.myproject.utils.CreatUUID;
import com.myproject.utils.UploadUtitls;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.jta.UserTransactionAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myproject.entiy.DataDownLoad;
import com.myproject.entiy.DownLoadQuestions;
import com.myproject.entiy.DownLoadRow;
import com.myproject.entiy.DownLoadWord;
import com.myproject.pojo.answer;
import com.myproject.pojo.options;
import com.myproject.pojo.ques;
import com.myproject.pojo.question;
import com.myproject.pojo.questionnaire;
import com.myproject.pojo.user;
import com.myproject.service.AnswerService;
import com.myproject.service.OptionsService;
import com.myproject.service.QuesService;
import com.myproject.service.QuestionService;
import com.myproject.service.QuestionnaireService;
import com.myproject.service.UserService;
import com.myproject.utils.CreatUUID;
import com.myproject.utils.UploadUtitls;


@RestController
@RequestMapping("/Upload")
public class UploadController {
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuesService quesService;
	
	@Autowired
	private OptionsService optionsService;
	
//	private String PATH = "http://waixingren.online";
	private String PATH = "http://localhost:8080";
	
	@RequestMapping("/excel")
	public String upLoadExcel(@RequestParam MultipartFile excel) throws IOException{			
		System.out.println(excel.getContentType());       //文件类型
		System.out.println(excel.getName());				//文件参数名
		System.out.println(excel.getSize()); 				//文件大小
		System.out.println(excel.getBytes());				//文件字节
		System.out.println(excel.getClass());				//文件类
		System.out.println(excel.getInputStream());		//文件io本身
		
	    
	    InputStream fis = null;
        Workbook wookbook = null;
        try {
            fis = excel.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // 2003版本的excel，用.xls结尾
            wookbook = new HSSFWorkbook(fis);// 得到工作簿
        } catch (Exception ex) {
            try {
                // 这里需要重新获取流对象，因为前面的异常导致了流的关闭
                fis = excel.getInputStream();
                // 2007版本的excel，用.xlsx结尾
                wookbook = new XSSFWorkbook(fis);// 得到工作簿
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sheet sheet = wookbook.getSheetAt(0);// 得到一个工作表
        int totalRowNum = sheet.getLastRowNum();// 获得数据的总行数

        for (int i = 1; i <= totalRowNum; i++) {
        	 user user = new user();
            Row row = sheet.getRow(i);        
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(6).setCellType(Cell.CELL_TYPE_BOOLEAN);
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
           
            user.setUsername(row.getCell(0).getStringCellValue());
            user.setPassword(row.getCell(1).getStringCellValue());
            user.setNickname(row.getCell(2).getStringCellValue());
            user.setMajor(row.getCell(3).getStringCellValue());
            user.setStudentnumber(row.getCell(4).getStringCellValue());
            user.setName(row.getCell(5).getStringCellValue());
            user.setSex(row.getCell(6).getBooleanCellValue());
            user.setGraduationyear(row.getCell(7).getStringCellValue());
            user.setCorrespondenceaddress(row.getCell(8).getStringCellValue());
            user.setWechat(row.getCell(9).getStringCellValue());
            user.setPhone(row.getCell(10).getStringCellValue());
            user.setEmail(row.getCell(11).getStringCellValue());
            user.setQqnumber(row.getCell(12).getStringCellValue());
            
            userService.addExcel(user);
        }
        
		return "上传成功";
	}
	
	@RequestMapping("/image")
	public String upLoadImage(@RequestParam MultipartFile picture) throws IOException{			
		System.out.println(picture.getContentType());       //文件类型
		System.out.println(picture.getName());				//文件参数名
		System.out.println(picture.getSize()); 				//文件大小
		System.out.println(picture.getBytes());				//文件字节
		System.out.println(picture.getClass());				//文件类
		System.out.println(picture.getInputStream());		//文件io本身
		
		//获取到tomcat的WEB-INF/classes
		String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
		//截取掉包括/WEB-INF/后面的内容
		String rootPath = classpath.substring(0, classpath.indexOf("/webapps/"));
		//webapps/项目名/resource
		String path = rootPath + "/webapps/resource";
		String projectPath = rootPath + "/webapps/myproject-questionnaire/resource";
		//生成唯一文件名ID
		String uuidFileName = UploadUtitls.getUuidFileName(picture.getOriginalFilename());
		//生成该唯一ID目录名
		String realPath = UploadUtitls.getPath(uuidFileName);
		//webapps/项目名/resource/目录名/
		String url = path + realPath;
		String projectUrl = projectPath + realPath;
		File file = new File(url);
		File projectFile = new File(projectUrl);
		
		if(!file.exists()){
			file.mkdirs();
		}
		if(!projectFile.exists()){
			file.mkdirs();
		}
		
		//具体文件路径
		String URL = url + "/" + uuidFileName;
		String projectURL = projectUrl + "/" + uuidFileName;
		//生成目标文件
		File dictFile = new File(URL);
		File projectDictFile = new File(projectURL);
		
		FileUtils.copyInputStreamToFile(picture.getInputStream(), dictFile); 
		FileUtils.copyInputStreamToFile(picture.getInputStream(), projectDictFile); 
		//复制上传文件给目标文件
		//截取包括/resource/后的路径
		String MKV = URL.substring(URL.indexOf("/resource/"), URL.length());
		
		return PATH + MKV;
	}
	
	@RequestMapping("/download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		JSONObject jsonObject = getJSON(request);
		JSONObject jsonParams = JSONObject.parseObject(jsonObject.getString("params"));
		Map m = jsonParams; 	
		
		  try {  
		    String templateFolder = "E:\\eclipseProject\\myproject-questionnaire\\src\\test\\java\\test";   	
		    	String ftlName = "模板.ftl";
				String root = templateFolder;
				String title = "test1";
				//  注意这里的参数，根据你自己业务出入，参数说明上面已经注明！
		        String downLoadPath = CreatUUID.exportWord(templateFolder,ftlName,m,root,title);

		        File file = new File(downLoadPath);	
		        InputStream inputStream;
		        inputStream = new BufferedInputStream(new FileInputStream(file));
	            //通过response将目标文件写到客户端
	            OutputStream out = response.getOutputStream();	 
	            // 写文件
	            int b;
	            while ((b = inputStream.read()) != -1) {
	                out.write(b);
	            }	 
	            inputStream.close();
	            out.close();
	         } catch (Exception e) {
	            try {
	                response.sendRedirect("/error/error.jsp");
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	        }
		}
	
	
	public JSONObject getJSON(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		StringBuffer jb = new StringBuffer();
		String line = null;
		try
		{
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(new String(jb));
		
		return jsonObject;
	}
}
