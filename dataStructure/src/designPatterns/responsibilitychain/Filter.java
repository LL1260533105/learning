package designPatterns.responsibilitychain;

public interface Filter {
    boolean doFilter(Request request, Response response, FilterChain filterChain);
}
