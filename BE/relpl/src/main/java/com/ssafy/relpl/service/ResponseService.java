package com.ssafy.relpl.service;

import com.ssafy.relpl.service.result.CommonResult;
import com.ssafy.relpl.service.result.ListResult;
import com.ssafy.relpl.service.result.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ResponseService {

    // enum으로 api 요청 결과에 대한 code, message를 정의합니다.
    public enum CommonResponse {
        SUCCESS(200, "성공"),
        FAILED(400, "실패");

        int output;
        String msg;

        CommonResponse(int output, String msg) {
            this.output = output;
            this.msg = msg;
        }

        public int getOutput() {
            return output;
        }

        public String getMsg() {
            return msg;
        }
    }
    // 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }
    // 단일건 결과를 처리하는 메소드 + 리스트 조회 시 필요한 limit 함께 세팅
    public <T> SingleResult<T> getSingleResult(T data, int limit) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }


    public <T> SingleResult<T> getSingleResult(T data, String msg, int code) {
        SingleResult<T> result = new SingleResult<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }


    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }
    // 다중건 결과를 처리하는 메소드 + msg 세팅
    public <T> ListResult<T> getListResult(List<T> list, String msg) {
        ListResult<T> result = new ListResult<>();
        result.setData(list);
        result.setMessage(msg);
        setSuccessResult(result);
        return result;
    }



    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }
    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult(String msg) {
        CommonResult result = new CommonResult();
        result.setMessage(msg);
        setSuccessResultEx(result);
        return result;
    }
    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String msg) {
        CommonResult result = new CommonResult();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
    // 실패 결과만 처리하는 메소드 + data=null
    public <T> ListResult<T> getFailResult(int code, String msg, List<T> data) {
    	ListResult<T> result = new ListResult<>();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
    // 실패 결과만 처리하는 메소드 + data=null
    public <T> SingleResult<T> getFailResult(int code, String msg, T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        result.setMessage(msg);
        result.setCode(CommonResponse.FAILED.output);
        return result;
    }
    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result) {
        result.setCode(CommonResponse.SUCCESS.getOutput());
        result.setMessage(CommonResponse.SUCCESS.getMsg());
    }

    private void setSuccessResultEx(CommonResult result) {
        result.setCode(CommonResponse.SUCCESS.getOutput());
        result.setMessage(result.getMessage());
    }
    
    
}
