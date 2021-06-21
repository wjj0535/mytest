/**
 * Project Name:bwtools-common
 * File Name:BaseJsonResult.java
 * Package Name:com.baiwang.cloud.common.model
 * Date:2017年10月20日上午11:08:23
 * Copyright (c) 2017, wuyuegang@baiwang.com All Rights Reserved.
 *
*/

package org.wangjj.practice.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回结果类，封装了统一的返回json结果格式，使用方法参考下面的例子: <br/>
 * {"data":[],"errorCode":-1,"errorMsg":"系统异常，请稍后再试","success":false} <br/>
 * {"data":[{"adress":"beijing","age":20,"name":"test"},{"adress":"shanghai","age":30,"name":"abc"}],"errorCode":0,"errorMsg":"","success":true}
 * <br/>
 * {"data":[{"adress":"hangzhou","age":50,"name":"ttt"}],"errorCode":0,"errorMsg":"","success":true} <br/>
 * <p>
 * 使用方法举例：<br/>
 * <p>
 * <b>1、返回异常结果</b><br/>
 * SystemException e = new SystemException(SystemErrorEnum.SYSTEM_ERROR);<br/>
 * BWJsonResult result = new BWJsonResult(e);<br/>
 * System.out.println(result.toString());<br/>
 * <p>
 * <b>2、返回List正常结果</b><br/>
 * public Class ProductVO extends BaseJsonModel { ... } 定义bean对象，必须继承基类 BaseJsonModel <br/>
 * List &lt;ProductVO&gt list = new ArrayList&lt;ProductVO&gt();<br/>
 * BWJsonResult &lt;ProductVO&gt result = new BWJsonResult&lt;ProductVO&gt(list);<br/>
 * System.out.println(result.toString());<br/>
 * <p>
 * <b>3、返回Object正常结果</b><br/>
 * public Class ProductVO extends BaseJsonModel { ... } 定义bean对象，必须继承基类 BaseJsonModel <br/>
 * ProductVO m3 = new ProductVO();<br/>
 * BWJsonResult &lt;ProductVO&gt result = new BWJsonResult&lt;ProductVO&gt(m3);<br/>
 * System.out.println(result.toString());<br/>
 * 
 * @author wuyuegang
 * @version
 * @since JDK 1.8
 */
public class BWJsonResult<T>
{
    private static final long serialVersionUID = -7532190660864165247L;

    /**
     * 分页返回的总条数
     */
    @JSONField
    private String requestID = null;

    /**
     * 返回值的成功标志
     */
    @JSONField
    private boolean success = true;

    /**
     * 返回值为成功时的提示信息
     */
    @JSONField
    private String message = null;
    
    /**
     * 错误码，成功时为0，失败时由SystemErrorEnum枚举定义
     */
    @JSONField
    private String errorCode = "0";
    
    /**
     * 错误信息，成功是为null，失败时由SystemErrorEnum枚举定义
     */
    @JSONField
    private String errorMsg = null;

    /**
     * 分页返回的总条数
     */
    @JSONField
    private Integer total = 0;
    
    /**
     * 返回值list
     */
    @JSONField
    private List<T> data = new ArrayList<T>();
    
    /**
     * 
     * Creates a new instance of BaseJsonResult.
     *
     */
    public BWJsonResult()
    {
        
    }

    
    /**
     * 
     * Creates a new instance of BWJsonResult.
     *
     * @param data
     */

    public BWJsonResult(List<T> data)
    {
        if (data != null && data.size() > 0)
        {
            this.data = data;
        }
    }
    
    /**
     * 
     * Creates a new instance of BWJsonResult.
     *
     * @param data
     */
    public BWJsonResult(T data)
    {
        if (data != null)
        {
            this.data.add(data);
        }
    }
    
    public boolean isSuccess()
    {
        return success;
    }
    
    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getErrorCode()
    {
        return errorCode;
    }
    
    public String getErrorMsg()
    {
        return errorMsg;
    }
    
    public List<T> getData()
    {
        return data;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
