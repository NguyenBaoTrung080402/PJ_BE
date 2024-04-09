package com.DSTA.PJ_BE.utils;

import com.DSTA.PJ_BE.Security.CustomUserDetails;
import com.DSTA.PJ_BE.config.ModelMapperConfig;
import com.DSTA.PJ_BE.entity.Account;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Common {

	private static ApplicationContext context = new AnnotationConfigApplicationContext(ModelMapperConfig.class);
	private static ModelMapper mapper = context.getBean(ModelMapper.class);
	private static ObjectMapper oMapper = new ObjectMapper();
	private static Gson gson = new Gson();
	private final Logger log = LoggerFactory.getLogger(Common.class);
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> mapper.map(element, targetClass)).collect(Collectors.toList());
	}

	public static <S, T> Page<T> mapPage(Page<S> sourcePage, Class<T> targetClass) {
		List<T> content = sourcePage.getContent().stream()
				.map(element -> mapper.map(element, targetClass))
				.collect(Collectors.toList());

		return new PageImpl<>(content, sourcePage.getPageable(), sourcePage.getTotalElements());
	}
	public static <T> List<T> convertStringToListObject(String str) {
		List<T> list = new ArrayList<>();
		try {
			list = oMapper.readValue(str, new TypeReference<List<T>>() {
			});
		} catch (Exception e) {
			list = null;
		}
		return list;
	}

	public static <T> T convertStringToObject(String str, Class<T> c) {
		try {
			return gson.fromJson(str, c);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> String convertListToStringJson(List<T> list) {
		try {
			return gson.toJson(list);
		} catch (Exception e) {
			return null;
		}
	}

	public static Account getCurrentUserLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		try {
			CustomUserDetails user = (CustomUserDetails) securityContext.getAuthentication().getPrincipal();
			return user.getAccount();
		} catch (Exception e) {
			return null;
		}
	}

	public static String expiredPinTime() {
		SimpleDateFormat f = new SimpleDateFormat(Constants.YYYYMMDDHHMM00);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, 1);
		return f.format(calendar.getTime());
	}

	public static String currentDateTime() {
		SimpleDateFormat f = new SimpleDateFormat(Constants.YYYYMMDDHHMMSS);
		return f.format(new Date());
	}

	public static String currentTime() {
		SimpleDateFormat f = new SimpleDateFormat(Constants.HHMMSS);
		return f.format(new Date());
	}

	public static String currentDate() {
		SimpleDateFormat f = new SimpleDateFormat(Constants.YYYYMMDD);
		return f.format(new Date());
	}

	public static String convertStringDate(String dateString, String formatInput, String formatOutput) {
		try {
			SimpleDateFormat inputDateFormat = new SimpleDateFormat(formatInput);
			SimpleDateFormat outputDateFormat = new SimpleDateFormat(formatOutput);

			return outputDateFormat.format(inputDateFormat.parse(dateString));
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToString(Date date, String formatOutput) {
		try {
			SimpleDateFormat outputDateFormat = new SimpleDateFormat(formatOutput);
			return outputDateFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date convertStringToDate(String dateString, String formatInput) {
		try {
			SimpleDateFormat inputDateFormat = new SimpleDateFormat(formatInput);
			return inputDateFormat.parse(dateString);
		} catch (Exception e) {
			return null;
		}
	}

	public static String saveFile(MultipartFile file, String path, Long userId, String userName) {
		String fileExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String newFileName = userId + "_" + userName + "." + fileExtension;
		String fileUrl = path + newFileName;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fileUrl));
			return fileUrl;
		} catch (Exception e) {
			return null;
		}
	}

	public static void deleteImageFolder(String imgPath) {
		try {
			File folderDeleting = new File(imgPath);
			if(folderDeleting.exists()){
				FileUtils.deleteDirectory(folderDeleting);
			}
		}catch (Exception ex){
		}
	}

	public static String convertToBase64(String path) throws IOException {
		FileInputStream fileInputStream;
		File file;
		try {
			file = new File(path);
			fileInputStream = new FileInputStream(file);
		} catch (Exception e) {
			file = new File("./assets/no_image.jpg");
			fileInputStream = new FileInputStream(file);
		}

		byte[] fileByte = new byte[(int) file.length()];
		fileInputStream.read(fileByte);
		fileInputStream.close();
		return Base64.getEncoder().encodeToString(fileByte);
	}
}
