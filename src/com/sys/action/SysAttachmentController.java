package com.sys.action;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import com.sys.model.SysAttachment;
import com.sys.model.SysAttachmentExample;
import com.sys.service.SysAttachmentService;
import com.sys.utils.BspUtils;
import com.sys.utils.ConfigUtils;
import com.sys.utils.ResultData;
import com.sys.utils.SysConstant;
import com.sys.utils.SysUtils;

/**
 * 文件上传
 * 
 * @author Administrator
 *
 */
@Controller
public class SysAttachmentController {
	
	@Autowired
	private SysAttachmentService sysAttachmentService;
	@Autowired
	private MultipartResolver multipartResolver;

	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sys/attachment/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResultData upload(HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) multipartResolver.resolveMultipart(request);
				List<SysAttachment> list = new ArrayList<SysAttachment>();
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String fileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (fileName.trim() != "") {
							// 定义上传路径
							String path = File.separator + multiRequest.getParameter("module")
									+ "-" + multiRequest.getParameter("type") + File.separator 
									+ multiRequest.getParameter("businessid") + File.separator + fileName;
							File localFile = new File(ConfigUtils.getValue("SYS.ATTACHMENT_PATH") + path);
							if (!localFile.getParentFile().exists()) {
								localFile.getParentFile().mkdirs();
							}
							file.transferTo(localFile);
							SysAttachment sysAttachment = new SysAttachment();
							sysAttachment.setRecid(SysUtils.getUUID());
							sysAttachment.setModule(multiRequest.getParameter("module"));
							sysAttachment.setBusinessid(multiRequest.getParameter("businessid"));
							sysAttachment.setType(multiRequest.getParameter("type"));
							sysAttachment.setName(fileName);
							sysAttachment.setPath(path);
							sysAttachment.setNote(multiRequest.getParameter("note"));
							sysAttachment.setCreateTime(new Date());
							sysAttachment.setCreater(BspUtils.getLoginUser().getUserId());
							sysAttachment.setUpdateTime(new Date());
							sysAttachment.setUpdater(BspUtils.getLoginUser().getUserId());
							sysAttachment.setAttachmentSize((new BigDecimal(file.getSize())).setScale(2, BigDecimal.ROUND_HALF_UP));
							sysAttachment.setSuffix(fileName.split("\\.")[fileName.split("\\.").length - 1]);
							list.add(sysAttachment);
						}
					}
					// 记录上传该文件后的时间
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);
				}
				sysAttachmentService.saveListSysAttachment(list);
				rd.setCode(SysConstant.SYS_SUCCESS);
				rd.setData(list);
			}else {
				rd.setCode(SysConstant.SYS_ERROR_PARAMETER);
				rd.setData(SysConstant.SYS_ERROR_PARAMETER_DESCRIPTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 获取附件信息
	 * @param module
	 * @param type
	 * @param businessid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sys/attachment/getAttachmentPath/{module}/{businessid}/{type}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResultData getAttachmentPath(@PathVariable String module,@PathVariable String businessid,
			@PathVariable String type,HttpServletRequest request, HttpServletResponse response) {
		ResultData rd = new ResultData();
		try {
			SysAttachmentExample example = new SysAttachmentExample();
			example.createCriteria().andModuleEqualTo(module).andBusinessidEqualTo(businessid).andTypeEqualTo(type);
			example.setOrderByClause("update_time desc");
			List<SysAttachment> list = sysAttachmentService.listSysAttachmentByExample(example);
			rd.setCode(SysConstant.SYS_SUCCESS);
			rd.setData(list);
		}catch(Exception e) {
			e.printStackTrace();
			rd.setCode(SysConstant.SYS_ERROR);
			rd.setData(e.getMessage());
		}
		return rd;
	}
	
	/**
	 * 获取图片
	 * @param recid
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/sys/attachment/getImg/{recid}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImg(@PathVariable String recid,HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(!SysUtils.isNull(recid)) {//附件ID不为空
			SysAttachment sysAttachment = sysAttachmentService.getSysAttachmentById(recid);
			File file = new File(ConfigUtils.getValue("SYS.ATTACHMENT_PATH")+sysAttachment.getPath());
//			File file = new File(ConfigUtils.getValue("SYS.ATTACHMENT_PATH")+path);
			HttpHeaders httpHeaders = new HttpHeaders();
			//设置下载方式
			httpHeaders.setContentDispositionFormData("attachment", file.getName());
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),httpHeaders,HttpStatus.CREATED);
		}else {
			return null;
		}
	}
}
