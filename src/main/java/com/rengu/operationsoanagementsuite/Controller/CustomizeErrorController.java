package com.rengu.operationsoanagementsuite.Controller;

import com.rengu.operationsoanagementsuite.Entity.ResultEntity;
import com.rengu.operationsoanagementsuite.Repository.UserRepository;
import com.rengu.operationsoanagementsuite.Utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class CustomizeErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = PATH)
    public ResultEntity error(HttpServletRequest request, HttpServletResponse response) {
        Throwable throwable = getError(request);
        Principal principal = request.getUserPrincipal();
        return ResultUtils.resultBuilder(HttpStatus.valueOf(response.getStatus()), ResultUtils.ERROR, principal == null ? null : userRepository.findByUsername(principal.getName()), throwable == null ? null : throwable.getMessage());
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Throwable getError(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getError(requestAttributes);
    }
}
