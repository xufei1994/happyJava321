package note.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "DemoFilter")
public class DemoFilter implements javax.servlet.Filter {

    public DemoFilter() {
        //出生
    }

    public void destroy() {
//        我还会回来的
    }


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session = request.getSession();





    }

    public void init(FilterConfig config) throws ServletException {
//       庆贺出生
    }

}
