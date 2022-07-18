package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("RequestHeaderServlet.service");

        // START Line
        System.out.println("============= START Line");
        final String method = request.getMethod();
        final String protocol = request.getProtocol();
        final String scheme = request.getScheme();

        System.out.println("method = " + method);
        System.out.println("protocol = " + protocol);
        System.out.println("scheme = " + scheme);

        // Http Header
        System.out.println("============= HTTP Header");
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(el -> {
                    System.out.println(el + ": " + request.getHeader(el));
                });

        // Http Header 편의 조회

        // Cookie 편의 조회

        // Content 편의 조회

        // 기타정보
    }
}
