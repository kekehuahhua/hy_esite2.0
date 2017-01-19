package com.huiyee.esite.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.dto.EditFileDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.TreeNode;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.PageFileUtil;
import com.opensymphony.xwork2.ActionContext;

public class EditFileAction extends AbstractAction
{
	private String json;
	private String path;
	private EditFileDto dto;
	private static String[] imgeArray =
	{ "bmp", "dib", "gif", "jfif", "jpe", "jpeg", "jpg", "png", "tif", "tiff", "ico" };
	private static String[] mediaArray =
	{ "mp3", "swf" };
	private static String[] textArray =
	{ "jsp", "css", "js" };
	private static String imgLocate = "res_image";

	private File file;
	private String fileFileName;
	private String fileContentType;
	private long siteid;

	private String resultMsg;
	private String isImg;
	private static final String FILE_TYPE_IMG = "img";
	private static final String FILE_TYPE_TEXT = "text";
	private static final String FILE_TYPE_MEDIA = "media";
	private Site site;

	@Override
	public String execute() throws Exception
	{
		String path = "";
		if ("Y".equals(isImg))
		{
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			path = HyConfig.getRootPath() + "/" + account.getOwner().getId() + "/" + siteid;
		}
		else if ("N".equals(isImg))
		{
			path = HyConfig.getRoot() + "/res/" + siteid;
		}
		else if ("P".equals(isImg))
		{
			path = HyConfig.getShowPagePath() + "/" + siteid+"/public";
		}
		else
		{
			return SUCCESS;
		}
		File f = new File(path);
		List<TreeNode> t = tree("", f, new TreeNode());
		if (t != null)
		{
			json = new Gson().toJson(t);
		}
		else
		{
			json = new Gson().toJson("");
		}
		site=pageCompose.findSiteById(siteid);
		return SUCCESS;
	}

	public String edit() throws Exception
	{
		dto = new EditFileDto();
		if (StringUtils.isNotEmpty(path))
		{
			File f = new File(path);
			if (f != null)
			{
				if (f.isDirectory())
				{
					dto.setSuccess(false);
					dto.setMsg("��ָ����Ҫ�༭���ļ�.");
				}
				else
				{
					dto.setSuccess(true);
					dto.setFilename(f.getName());
					String type = FileUploadService.getFileSuffix(f.getName());
					if (Arrays.asList(imgeArray).contains(type.toLowerCase()))
					{
						dto.setFiletype(FILE_TYPE_IMG);
						String imgPath = path.replace("\\", "/");
						imgPath=path.replace("/imgserver/site", "");
						// /res_image/siteid/......
						dto.setImgPath(HyConfig.getImgDomain() + imgPath + "?" + Math.random());// ��random��Ϊ�˲���ͼƬ����
					}
					else if (Arrays.asList(mediaArray).contains(type.toLowerCase()))
					{
						dto.setFiletype(FILE_TYPE_MEDIA);
					}
					else if (Arrays.asList(textArray).contains(type.toLowerCase()))
					{
						dto.setFiletype(FILE_TYPE_TEXT);
						String str = PageFileUtil.readFile(path);
						dto.setCode(StringEscapeUtils.escapeHtml4(str));

					}

				}
				return SUCCESS;
			}
		}
		dto.setSuccess(false);
		dto.setMsg("�Ҳ���ָ���ļ�.");
		return SUCCESS;
	}

	public String subFile() throws Exception
	{
		resultMsg = "�޸�ʧ��!";
		if (StringUtils.isNotEmpty(path) && dto != null)
		{
			File f = new File(path);
			if (f != null)
			{
				if (FILE_TYPE_IMG.equals(dto.getFiletype()) && file != null)
				{
					PageFileUtil.copyFile(file, f.getPath());
					resultMsg = "�޸ĳɹ�!";
				}

				if (FILE_TYPE_MEDIA.equals(dto.getFiletype()) && file != null)
				{
					PageFileUtil.copyFile(file, f.getPath());
					resultMsg = "�޸ĳɹ�!";
				}

				if (FILE_TYPE_TEXT.equals(dto.getFiletype()) && StringUtils.isNotEmpty(dto.getCode()))
				{
					PageFileUtil.SaveFile(path, dto.getCode());
					resultMsg = "�޸ĳɹ�!";
				}
			}
		}
		dto.setMsg("�Ҳ���ָ���ļ�.");
		return SUCCESS;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	// ��ʾĿ¼�ķ���
	public static List<TreeNode> tree(String str, File f, TreeNode node) throws Exception
	{
		// �жϴ�������Ƿ�Ϊһ���ļ��ж���
		if (!f.isDirectory())
		{
			System.out.println("������Ĳ���һ���ļ��У�����·���Ƿ����󣡣�");
		}
		else
		{
			File[] t = f.listFiles();
			List<TreeNode> list = new ArrayList<TreeNode>();
			for (int i = 0; i < t.length; i++)
			{
				// �ж��ļ��б��еĶ����Ƿ�Ϊ�ļ��ж����������ִ��tree�ݹ飬ֱ���Ѵ��ļ����������ļ����Ϊֹ
				TreeNode n = new TreeNode();
				n.setName(t[i].getName());
				n.setPath(t[i].getCanonicalPath());
				if (t[i].isDirectory())
				{
					tree(str, t[i], n);
					n.setParent(true);
				}
				list.add(n);
			}
			if (list.size() > 0)
			{
				node.setChildren(list);
			}
			return node.getChildren();
		}
		return null;

	}

	public String getJson()
	{
		return json;
	}

	public void setJson(String json)
	{
		this.json = json;
	}

	public EditFileDto getDto()
	{
		return dto;
	}

	public void setDto(EditFileDto dto)
	{
		this.dto = dto;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public String getResultMsg()
	{
		return resultMsg;
	}

	public void setResultMsg(String resultMsg)
	{
		this.resultMsg = resultMsg;
	}

	public String getIsImg()
	{
		return isImg;
	}

	public void setIsImg(String isImg)
	{
		this.isImg = isImg;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
}
