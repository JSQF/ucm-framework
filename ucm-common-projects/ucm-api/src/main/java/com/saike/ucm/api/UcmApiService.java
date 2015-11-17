package com.saike.ucm.api;

import com.meidusa.venus.annotations.Endpoint;
import com.meidusa.venus.annotations.Param;
import com.meidusa.venus.annotations.Service;
import com.saike.ucm.domain.api.UcmProperty;
import com.saike.ucm.exception.api.UcmApiException;

import java.util.List;

/**
 * Created by huawei on 11/17/15.
 */
@Service(name = "UcmApiService", description = "统一配置api服务")
public interface UcmApiService {

    /**
     * 获取ucm配置信息
     *
     * @param projectCode
     * @param version
     * @return
     */
    @Endpoint(name = "getProperties")
    List<UcmProperty> getProperties(@Param(name = "projectCode") String projectCode,
                                    @Param(name = "version") String version) throws UcmApiException;

    /**
     * 获取ucm配置信息(兼容原有zookeeper的版本的客户端)
     *
     * @param projectCode
     * @param version
     * @return
     */
    @Endpoint(name = "getPropertiesInternal")
    List<UcmProperty> getProperties(@Param(name = "host") String host,
                                    @Param(name = "projectCode") String projectCode,
                                    @Param(name = "version") String version) throws UcmApiException;
}
