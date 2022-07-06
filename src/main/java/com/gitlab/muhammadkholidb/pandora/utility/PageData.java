package com.gitlab.muhammadkholidb.pandora.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@SuppressWarnings("unchecked")
public final class PageData {

    @Getter
    private PageSet pageSet;

    private Object data;

    public static final PageData INSTANCE = new PageData();

    private PageData() {
    }

    public <T> void set(IPage from, IPage to, T data) {
        set(new PageSet(from, to), data);
    }

    public <T> void set(PageSet pageSet, T data) {
        this.pageSet = pageSet;
        this.data = data;
    }

    public <T> T get(IPage from, IPage to) {
        return get(new PageSet(from, to));
    }

    public <T> T get(PageSet pageSet) {
        return this.pageSet.equals(pageSet) ? (T) data : null;
    }

    public boolean hasData(IPage from, IPage to) {
        return hasData(new PageSet(from, to));
    }

    public boolean hasData(PageSet pageSet) {
        return this.pageSet.equals(pageSet) && data != null;
    }

    @AllArgsConstructor
    @Data
    public static class PageSet {

        private IPage from;
        private IPage to;

        public PageSet swap() {
            IPage temp = from;
            from = to;
            to = temp;
            return this;
        }

        public PageSet swapCopy() {
            return new PageSet(to, from);
        }

    }

}
