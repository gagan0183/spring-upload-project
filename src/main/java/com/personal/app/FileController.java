package com.personal.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {
	private static String upload_folder = "D:\\GEGDC\\gx479932\\spring\\Springuploadproject\\src\\main\\resources\\";

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("upload");
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView fileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		if (file.isEmpty()) {
			return new ModelAndView("status", "message",
					"Please select a file and try again");
		}

		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get("src\\main\\resources\\"
					+ file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ModelAndView("status", "message", "File upload successfully");
	}
}
