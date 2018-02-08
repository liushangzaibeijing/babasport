// Decompiled by DJ v3.12.12.100 Copyright 2015 Atanas Neshkov  Date: 2017/12/26 17:09:53
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Paginable.java

package com.nibnait.common.page;


public interface Paginable
{

    public abstract int getPageNo();

    public abstract int getPageSize();

    public abstract int getTotalCount();

    public abstract int getTotalPage();

    public abstract boolean isFirstPage();

    public abstract boolean isLastPage();

    public abstract int getNextPage();

    public abstract int getPrePage();
}
