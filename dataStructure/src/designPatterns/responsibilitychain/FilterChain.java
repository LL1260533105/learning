package designPatterns.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式：请求创建了一个接收者对象的链。这种模式给予请求的类型，
 * 对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式
 *
 * 过滤器链条
 */
public class FilterChain {

    /**
     * 过滤器集合
     */
    private List<Filter> filterList;

    private int size = 0;

    public FilterChain() {
        filterList = new ArrayList<>(10);
    }

    public void addFilter(Filter filter) {
        filterList.add(filter);
    }

    public boolean doFilter(Request request, Response response) {
        if (size >= filterList.size()) {
            return false;
        }
        Filter f = filterList.get(size);
        size++;
        return f.doFilter(request, response, this);
    }

    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new NumberFilter());
        filterChain.addFilter(new CharFilter());

        Request request = new Request();
        request.setStr("4sdssdc");

        Response response = new Response();
        response.setStr("4sdssdc");

        filterChain.doFilter(request, response);
        System.out.println(request.getStr() + "\n");
        System.out.println(response.getStr() + "\n");
    }
}
