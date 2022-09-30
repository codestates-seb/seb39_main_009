package teamparkinglot.parkinggo.security.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.response.ErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            setErrorResponse(e.getExceptionCode().getStatus(), response, e);
        }
    }

    private void setErrorResponse(int status, HttpServletResponse response, BusinessException e) {

        response.setStatus(status);
        response.setContentType("Application/json;charset=UTF-8");
        ErrorResponse errorResponse = ErrorResponse.of(e.getExceptionCode());
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(errorResponse);
        try {
            response.getWriter().write(json);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
