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

        // Http Header
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(el -> {
                    System.out.println(el);
                });

        // Http Header 편의 조회

        // Cookie 편의 조회

        // Content 편의 조회

        // 기타정보
    }
}
