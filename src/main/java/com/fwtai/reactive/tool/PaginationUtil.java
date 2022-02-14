package com.fwtai.reactive.tool;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * 分页
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2022/2/14 15:56
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.fwtai.com</url>
*/
public final class PaginationUtil{

    public static <T> HttpHeaders generatePaginationHttpHeaders(final Pageable pageable,final List list,Long totalNumber,String baseUrl) {
        HttpHeaders headers = new HttpHeaders();
        int totalPages = Math.toIntExact(totalNumber/pageable.getPageSize()+1);

        headers.add("X-Total-Count", Long.toString(totalNumber));
        String link = "";
        if ((pageable.getPageNumber() + 1) < totalPages) {
            link = "<" + PaginationUtil.generateUri(baseUrl, pageable.getPageNumber() + 1, list.size()) + ">; rel=\"下一页\",";
        }
        // prev link
        if ((pageable.getPageNumber()) > 0) {
            link += "<" + PaginationUtil.generateUri(baseUrl, pageable.getPageNumber() - 1, list.size()) + ">; rel=\"上一页\",";
        }
        // last and first link
        int lastPage = 0;
        if (totalPages > 0) {
            lastPage = totalPages - 1;
        }
        link += "<" + PaginationUtil.generateUri(baseUrl, lastPage, list.size()) + ">; rel=\"末尾页\",";
        link += "<" + PaginationUtil.generateUri(baseUrl, 0, list.size()) + ">; rel=\"第一页\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    static String generateUri(String baseUrl,int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
    }

    public static HttpHeaders generatePaginationHttpHeaders(final Page page,String baseUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", "" + Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getNumber() + 1, page.getSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getNumber() - 1, page.getSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}