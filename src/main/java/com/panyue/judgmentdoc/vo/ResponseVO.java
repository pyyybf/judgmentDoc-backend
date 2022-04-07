package com.panyue.judgmentdoc.vo;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class ResponseVO<T> {

    /**
     * 调用是否成功
     */
    private Boolean success;

    /**
     * 返回的提示信息
     */
    private String message;

    /**
     * 内容
     */
    private T content;

    public static <T> ResponseVO<T> buildSuccess(T content) {
        ResponseVO<T> response = new ResponseVO<T>();
        response.setContent(content);
        response.setSuccess(true);
        return response;
    }

    public static <T> ResponseVO<T> buildFailure(String message) {
        ResponseVO<T> response = new ResponseVO<T>();
        response.setSuccess(false);
        response.setMessage(message);
        System.out.println(message);
        return response;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}
