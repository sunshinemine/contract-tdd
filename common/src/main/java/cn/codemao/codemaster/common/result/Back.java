package cn.codemao.codemaster.common.result;import lombok.Getter;import lombok.Setter;/** * @author lishi */@Getter@Setterpublic class Back<T> {    private Integer code;    private String message;    private T data;    public Back() {    }    public Back(Integer code, String message, T data) {        this.code = code;        this.message = message;        this.data = data;    }    private Back(Result result) {        this.code = result.getCode();        this.message = result.getMessage();    }    private Back(Result result, T data) {        this.code = result.getCode();        this.message = result.getMessage();        this.data = data;    }    public static Back noBody(Result result) {        return new Back(result);    }    public static <T> Back<T> body(Result result, T data) {        return new Back<T>(result, data);    }}