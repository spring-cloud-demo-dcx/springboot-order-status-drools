package cn.skuu.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TO系统logDO
 *
 * @author dcx
 * @date 2022/11/6 17:20
 **/
@Data
public class SysLogDTO {

    @ApiModelProperty(value = "用户uuid")
    private String userUuid;
    @ApiModelProperty(value = "用户类型")
    private Integer userType;
    @ApiModelProperty(value = "应用")
    private String application;
    @ApiModelProperty(value = "类名")
    private String className;
    @ApiModelProperty(value = "模块名")
    private String methodName;
    @ApiModelProperty(value = "ip地址")
    private String remoteAddr;
    @ApiModelProperty(value = "接口地址")
    private String requestUri;
    @ApiModelProperty(value = "起源")
    private String origin;
    @ApiModelProperty(value = "来源")
    private String referer;
    @ApiModelProperty(value = "客户端")
    private String userAgent;
    @ApiModelProperty(value = "入参")
    private String parameterMapParams;
    @ApiModelProperty(value = "入参")
    private String arraysGetArgsParams;
    @ApiModelProperty(value = "入参")
    private String jsonGetArgsParams;
    @ApiModelProperty(value = "耗时")
    private String millis;
    @ApiModelProperty(value = "出参")
    private String response;
    @ApiModelProperty(value = "uuid")
    private String uuid;
}
