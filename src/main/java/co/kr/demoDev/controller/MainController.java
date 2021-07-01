package co.kr.demoDev.controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import co.kr.demoDev.model.CsvVo;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main(Model model) {
		
		return "/list";
	}
	
	@RequestMapping("/csvRead")
	public String csvRead(Model model) throws IOException {
		List<CsvVo> csvList = new ArrayList<>();
		
		String filePath = "D:/uploads/csvDemo05.csv";				
		CSVReader csvReader = new CSVReader(new FileReader(filePath));
		String[] nextLine;
		while((nextLine = csvReader.readNext()) != null) {
			List<String> addCsvString = new ArrayList<>();
			CsvVo csvVo = new CsvVo();
			for (int i=0; i<nextLine.length; i++) {
				addCsvString.add(nextLine[i]);
				System.out.println(i+" "+nextLine[i]);
				System.out.println("addCsvString: "+addCsvString);
			}
			csvVo.setCsvVal(addCsvString);
			csvList.add(csvVo);
//			System.out.println("addCsvString~~~~: "+addCsvString);
		}
		
		model.addAttribute("csvList", csvList);
		csvReader.close();
		
		return "/csvRead";
	}

	@RequestMapping("/csvWrite")
	public String csvWrite(Model model) throws IOException {
//		List<CsvVo> csvList = new ArrayList<>();
//		csvList.add(new CsvVo(1, "a"));
//		csvList.add(new CsvVo(2, "b"));
//		csvList.add(new CsvVo(3, "c"));
//		
//		model.addAttribute("csvList", csvList);
		
		String filePath = "D:/uploads/csvDemo04.csv";
		CSVWriter csvWriter = new CSVWriter(new FileWriter(filePath));
		
//		String[] entries = "EW#City#State".split("#");  // 1
//		csvWriter.writeNext(entries);
		
		String[] entries1 = {"W", "Youngstown", "OH"};  // 3
		csvWriter.writeNext(entries1);
		
		String[] entries2 = {"W", "Williamson", "WV"};
		csvWriter.writeNext(entries2);
		
		csvWriter.close();
		
		return "/list";
	}
}
