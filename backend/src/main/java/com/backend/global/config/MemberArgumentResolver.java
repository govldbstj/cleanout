package com.backend.global.config;

import com.backend.member.domain.MemberSession;
import com.backend.member.exception.MemberUnauthorizedException;
import com.backend.util.annotation.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
public class MemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasMemberSessionType = parameter.getParameterType().equals(MemberSession.class);
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        return hasMemberSessionType && hasLoginAnnotation;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            throw new MemberUnauthorizedException();
        }

        MemberSession memberSession = (MemberSession) session.getAttribute("memberSession");

        return memberSession;
    }
}
