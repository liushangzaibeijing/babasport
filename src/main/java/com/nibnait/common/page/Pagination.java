// Decompiled by DJ v3.12.12.100 Copyright 2015 Atanas Neshkov  Date: 2017/12/26 17:09:53
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Pagination.java

package com.nibnait.common.page;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.nibnait.common.page:
//            SimplePage

public class Pagination extends SimplePage
{

    public Pagination()
    {
    }

    public Pagination(int pageNo, int pageSize, int totalCount)
    {
        super(pageNo, pageSize, totalCount);
    }

    public Pagination(int pageNo, int pageSize, int totalCount, List list)
    {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult()
    {
        return (pageNo - 1) * pageSize;
    }

    public List getList()
    {
        return list;
    }

    public void setList(List list)
    {
        this.list = list;
    }

    public List getPageView()
    {
        return pageView;
    }

    public void setPageView(List pageView)
    {
        this.pageView = pageView;
    }

    public void pageView(String url, String params)
    {
        pageView = new ArrayList();
        if(pageNo != 1)
        {
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=1'\"><font size=2>\u9996\u9875</font></a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo - 1).append("'\"><font size=2>\u4E0A\u4E00\u9875</font></a>").toString());
        } else
        {
            pageView.add("<font size=2>\u9996\u9875</font>");
            pageView.add("<font size=2>\u4E0A\u4E00\u9875</font>");
        }
        if(getTotalPage() <= 10)
        {
            for(int i = 0; i < getTotalPage(); i++)
            {
                if(i + 1 == pageNo)
                {
                    pageView.add((new StringBuilder("<strong>")).append(pageNo).append("</strong>").toString());
                    i++;
                    if(pageNo == getTotalPage())
                        break;
                }
                pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(i + 1).append("'\">").append(i + 1).append("</a>").toString());
            }

        } else
        if(getTotalPage() <= 20)
        {
            int l = 0;
            int r = 0;
            if(pageNo < 5)
            {
                l = pageNo - 1;
                r = 10 - l - 1;
            } else
            if(getTotalPage() - pageNo < 5)
            {
                r = getTotalPage() - pageNo;
                l = 9 - r;
            } else
            {
                l = 4;
                r = 5;
            }
            int tmp = pageNo - l;
            for(int i = tmp; i < tmp + 10; i++)
            {
                if(i == pageNo)
                {
                    pageView.add((new StringBuilder("<strong>")).append(pageNo).append("</strong>").toString());
                    i++;
                    if(pageNo == getTotalPage())
                        break;
                }
                pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(i).append("'\">").append(i).append("</a>").toString());
            }

        } else
        if(pageNo < 7)
        {
            for(int i = 0; i < 8; i++)
            {
                if(i + 1 == pageNo)
                {
                    pageView.add((new StringBuilder("<strong>")).append(pageNo).append("</strong>").toString());
                    i++;
                }
                pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(i + 1).append("'\">").append(i + 1).append("</a>").toString());
            }

            pageView.add("...");
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(getTotalPage() - 1).append("'\">").append(getTotalPage() - 1).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(getTotalPage()).append("'\">").append(getTotalPage()).append("</a>").toString());
        } else
        if(pageNo > getTotalPage() - 6)
        {
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(1).append("'\">").append(1).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(2).append("'\">").append(2).append("</a>").toString());
            pageView.add("...");
            for(int i = getTotalPage() - 8; i < getTotalPage(); i++)
            {
                if(i + 1 == pageNo)
                {
                    pageView.add((new StringBuilder("<strong>")).append(pageNo).append("</strong>").toString());
                    i++;
                    if(pageNo == getTotalPage())
                        break;
                }
                pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(i + 1).append("'\">").append(i + 1).append("</a>").toString());
            }

        } else
        {
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(1).append("'\">").append(1).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(2).append("'\">").append(2).append("</a>").toString());
            pageView.add("...");
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo - 2).append("'\">").append(pageNo - 2).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo - 1).append("'\">").append(pageNo - 1).append("</a>").toString());
            pageView.add((new StringBuilder("<strong>")).append(pageNo).append("</strong>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo + 1).append("'\">").append(pageNo + 1).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo + 2).append("'\">").append(pageNo + 2).append("</a>").toString());
            pageView.add("...");
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(getTotalPage() - 1).append("'\">").append(getTotalPage() - 1).append("</a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(getTotalPage()).append("'\">").append(getTotalPage()).append("</a>").toString());
        }
        if(pageNo != getTotalPage())
        {
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(pageNo + 1).append("'\"><font size=2>\u4E0B\u4E00\u9875</font></a>").toString());
            pageView.add((new StringBuilder("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href='")).append(url).append("?").append(params).append("&pageNo=").append(getTotalPage()).append("'\"><font size=2>\u5C3E\u9875</font></a>").toString());
        } else
        {
            pageView.add("<font size=2>\u4E0B\u4E00\u9875</font>");
            pageView.add("<font size=2>\u5C3E\u9875</font>");
        }
        pageView.add((new StringBuilder("\u5171<var>")).append(getTotalPage()).append("</var>\u9875  \u5230\u7B2C<input type='text' id='PAGENO'  size='3' />\u9875  <input type='button' id='skip' class='hand btn60x20' value='\u786E\u5B9A' onclick=\"javascript:window.location.href = '").append(url).append("?").append(params).append("&pageNo=' + $('#PAGENO').val() \"/>").toString());
    }

    private List list;
    private List pageView;
}
