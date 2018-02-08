// Decompiled by DJ v3.12.12.100 Copyright 2015 Atanas Neshkov  Date: 2017/12/26 17:09:53
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SimplePage.java

package com.nibnait.common.page;

import java.io.Serializable;

// Referenced classes of package com.nibnait.common.page:
//            Paginable

public class SimplePage
    implements Serializable, Paginable
{

    public static int cpn(Integer pageNo)
    {
        return pageNo != null && pageNo.intValue() >= 1 ? pageNo.intValue() : 1;
    }

    public SimplePage()
    {
        totalCount = 0;
        pageSize = 20;
        pageNo = 1;
    }

    public SimplePage(int pageNo, int pageSize, int totalCount)
    {
        this.totalCount = 0;
        this.pageSize = 20;
        this.pageNo = 1;
        setTotalCount(totalCount);
        setPageSize(pageSize);
        setPageNo(pageNo);
        adjustPageNo();
    }

    public void adjustPageNo()
    {
        if(pageNo == 1)
            return;
        int tp = getTotalPage();
        if(pageNo > tp)
            pageNo = tp;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public int getTotalPage()
    {
        int totalPage = totalCount / pageSize;
        if(totalPage == 0 || totalCount % pageSize != 0)
            totalPage++;
        return totalPage;
    }

    public boolean isFirstPage()
    {
        return pageNo <= 1;
    }

    public boolean isLastPage()
    {
        return pageNo >= getTotalPage();
    }

    public int getNextPage()
    {
        if(isLastPage())
            return pageNo;
        else
            return pageNo + 1;
    }

    public int getPrePage()
    {
        if(isFirstPage())
            return pageNo;
        else
            return pageNo - 1;
    }

    public void setTotalCount(int totalCount)
    {
        if(totalCount < 0)
            this.totalCount = 0;
        else
            this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize)
    {
        if(pageSize < 1)
            this.pageSize = 20;
        else
            this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo)
    {
        if(pageNo < 1)
            this.pageNo = 1;
        else
            this.pageNo = pageNo;
    }

    private static final long serialVersionUID = 1L;
    public static final int DEF_COUNT = 20;
    protected int totalCount;
    protected int pageSize;
    protected int pageNo;
}
