package com.moxingwang.core.entity.vo;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 功能：分页器
 */
public class PageVo<T> {
	/**
	 * 页码
	 */
	private int pageNum;
	/**
	 * 页码总数
	 */
	private int pageCount;
	/**
	 * 总数
	 */
	private int count;
	/**
	 * 偏移
	 */
	private int offset;
	/**
	 * 数量
	 */
	private int rows;
	/**
	 * 数据
	 */
	private List<T> list;
	/**
	 * 页码HTML
	 */
	private String pageNumHtml;

	/**
	 * 页面数据汇总描述
	 */
	private String pageRecordsHtml;
	
	/**
	 * portal页面页码
	 */
	private String portalNumHtml;

	/**
	 * JS方法
	 */
	private String method;

	private String pageNumMethod;
	/**
	 * 参数
	 */
	private Map<String, String> args = new HashMap<String, String>();

	public PageVo(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		if (this.pageNum <= 0) {
			this.pageNum = 1;
			return 1;
		} else {
			return pageNum;
		}
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageCount() {
		this.pageCount = ((this.getCount() - 1) / this.getRows()) + 1;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getOffset() {
		this.offset = (this.getPageNum() - 1) * this.getRows();
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getUrl(int num) {
		Iterator<Entry<String, String>> iter = this.getArgs().entrySet()
				.iterator();
		List<String> values = new ArrayList<String>();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			values.add(key + "=" + val);
		}
		values.add("p=" + num);
		return "?" + StringUtils.join(values.toArray(), "&");
	}

	public void setPageRecordsHtml(String pageRecordsHtml) {
		this.pageRecordsHtml = pageRecordsHtml;
	}
	
	public String getPageRecordsHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"paginationRecords\">");
		
		int startRows = this.getOffset() + 1;
		if(startRows > this.getCount()) {
			startRows = this.getCount();
		}
		
		int endRows = this.getOffset() + this.getRows();
		if(endRows > this.getCount()) {
			endRows = this.getCount();
		}
		
		sb.append(startRows + "-" + endRows);
		sb.append(" of ");
		sb.append(this.getCount() + " records ");
		sb.append("(p." + this.getPageNum() + "/" + this.getPageCount() + ")");
		
		//1-10 of 200 records (p.1/20)

		sb.append("</ul>");
		return sb.toString();
	}
	
	public void setPageNumHtml(String pageNumHtml) {
		this.pageNumHtml = pageNumHtml;
	}

	public String getPageNumHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"pagination\">");
		// 首页，上一页
		if (this.getPageNum() != 1) {
			sb.append("<li><a href='" + this.getUrl(1)
					+ "' title='首页'>首页</a></li>");
			sb.append("<li><a href='" + this.getUrl(this.getPageNum() - 1)
					+ "' title='上一页'>上一页</a></li>");
		}
		// 页码
		if (this.getPageCount() != 1) {
			int startNum = this.getPageNum() - 3 <= 1 ? 1
					: this.getPageNum() - 3;
			int endNum = this.getPageNum() + 3 >= this.getPageCount() ? this
					.getPageCount() : this.getPageNum() + 3;
			if (startNum > 1) {
				sb.append("<li><a href='javascript:void(0);'>...</a></li>");
			}
			for (int i = startNum; i <= endNum; i++) {
				if (i == pageNum) {
//					sb.append("<li class='active'><a href='" + this.getUrl(i)
//							+ "' class='number current' title='" + i + "'>" + i
//							+ "</a></li>");
					// 当前页面不在刷新
					sb.append("<li class='active'><a href='" + "#"
							+ "' class='number current' title='" + i + "'>" + i
							+ "</a></li>");
					
				} else {
					sb.append("<li><a href='" + this.getUrl(i)
							+ "' class='number' title='" + i + "'>" + i
							+ "</a></li>");
				}
			}
			if (endNum < this.getPageCount()) {
				sb.append("<li><a href='javascript:void(0);'>...</a></li>");
			}
		}
		// 下一页，尾页
		if (this.getPageNum() < this.getPageCount()) {
			sb.append("<li><a href='" + this.getUrl(this.getPageNum() + 1)
					+ "' title='下一页'>下一页</a></li>");
			sb.append("<li><a href='" + this.getUrl(this.getPageCount())
					+ "' title='末页'>末页</a></li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		
		// 增加当前页数和总页数的比较
		int pc = this.getPageCount();
		int pn = this.getPageNum();
		if(pn > pc) {
			this.setPageNum(pc);
		}
		
	}

	public Map<String, String> getArgs() {
		return args;
	}

	public void setArgs(Map<String, String> args) {
		this.args = args;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public String getPageNumMethod() {
		StringBuffer sb = new StringBuffer();
		sb.append("<ul class=\"pagination\">");
		// 首页，上一页
		if (this.getPageNum() != 1) {
			int prePageNum = this.getPageNum() - 1;
			sb.append("<li><a href=" + getMethod(1) + " title='首页'>首页</a></li>");
			sb.append("<li><a href=" + getMethod(prePageNum)
					+ " title='上一页'>上一页</a></li>");
		}
		// 页码
		if (this.getPageCount() != 1) {
			int startNum = this.getPageNum() - 3 <= 1 ? 1
					: this.getPageNum() - 3;
			int endNum = this.getPageNum() + 3 >= this.getPageCount() ? this
					.getPageCount() : this.getPageNum() + 3;
			if (startNum > 1) {
				sb.append("<li><a href='javascript:void(0);'>...</a></li>");
			}
			for (int i = startNum; i <= endNum; i++) {
				if (i == pageNum) {
					sb.append("<li class='active'><a   href=" + getMethod(i)
							+ " class='number current' title='" + i + "'>" + i
							+ "</a></li>");
				} else {
					sb.append("<li><a href=" + getMethod(i)
							+ " class='number' title='" + i + "'>" + i
							+ "</a></li>");
				}
			}
			if (endNum < this.getPageCount()) {
				sb.append("<li><a href='javascript:void(0);'>...</a></li>");
			}
		}
		// 下一页，尾页
		if (this.getPageNum() < this.getPageCount()) {
			int nextPageNum = this.getPageNum() + 1;
			sb.append("<li><a href=" + getMethod(nextPageNum)
					+ " title='下一页'>下一页</a></li>");
			sb.append("<li><a href=" + getMethod(this.getPageCount())
					+ " title='末页'>末页</a></li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

	private String getMethod(int i) {
		StringBuffer sb = new StringBuffer();
		sb.append("\"javascript:void(0);\" onclick = \"");
		sb.append(method);
		sb.append("(").append(i).append(")\"");
		return sb.toString();
	}

	public String getPortalNumHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"page_turn\">");

		if (this.getPageNum() == 1) {
			sb.append("<span class='first_page_no'></span>");
			sb.append("\r\n");
			// 如果是首页，则向前翻页置灰
			sb.append("<span class='previous_page_no'></span>");
			sb.append("\r\n");
		} else {
			sb.append("<span class='first_page'><a href=" + this.getMethod(1) + "></a></span>");
			sb.append("\r\n");
			// 如果不是首页，则向前可用
			sb.append("<span class='previous_page'><a href="
					+ this.getMethod(this.getPageNum() - 1) + "></a></span>");
			sb.append("\r\n");
		}
		// 页码
		if (this.getPageCount() != 1) {
			int startNum = this.getPageNum() - 3 <= 1 ? 1
					: this.getPageNum() - 3;
			int endNum = this.getPageNum() + 3 >= this.getPageCount() ? this
					.getPageCount() : this.getPageNum() + 3;

			for (int i = startNum; i <= endNum; i++) {
				if (i == pageNum) {
					// 当前页激活
					sb.append("<span class='current_page'>" + i + ""
							+ "</span>");
				} else {
					// 非激活页
					sb.append("<a class='page' href=" + this.getMethod(i) + " >"
							+ i + "" + "</a>");
				}
			}
		} else {
			sb.append("<span class='current_page'>1</span>");
		}
		
		// 下一页，尾页
		if (this.getPageNum() < this.getPageCount()) {
			sb.append("\r\n");
			sb.append("<a class='next_page' href=" + this.getMethod(this.getPageNum() + 1) + "></a>");	
			sb.append("\r\n");
			sb.append("<a class='last_page' href=" + this.getMethod(this.getPageCount()) + "></a>");
		}else {
			sb.append("\r\n");
			sb.append("<span class='next_page_no'></span>");
			sb.append("\r\n");
			sb.append("<a class='last_page_no' href='#'></a>");
		}
		sb.append("共&nbsp;"+ this.getPageCount() +"&nbsp;页，到&nbsp;<input class='page_num' type='text' onblur='ifPageNumMax(this);' onclick='goColorBack();' id='input_page_num'/>&nbsp;页");
		sb.append("\r\n");
		sb.append("<input class='page_inq' type='submit' value='GO' onclick='pageGo()' /></div>");
		
		return sb.toString();
	}

	public void setPortalNumHtml(String portalNumHtml) {
		this.portalNumHtml = portalNumHtml;
	}

}
