package designPatterns.responsibilitychain;

public class NumberFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        String str = request.getStr();
        request.setStr(str + "5555");
        filterChain.doFilter(request, response);
        String str1 = response.getStr();
        response.setStr(str1 + "5555");
        return true;
    }
}
