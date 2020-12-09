package designPatterns.responsibilitychain;

public class CharFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        String str = request.getStr();
        request.setStr(str + "6666");
        filterChain.doFilter(request, response);
        String str1 = response.getStr();
        response.setStr(str1 + "6666");
        return true;
    }
}