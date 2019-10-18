package org.jeecg.modules.adminLogs.service.impl;

import org.jeecg.modules.adminLogs.entity.AdminLogs;
import org.jeecg.modules.adminLogs.mapper.AdminLogsMapper;
import org.jeecg.modules.adminLogs.service.IAdminLogsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 后台操作人员日志
 * @author： jeecg-boot
 * @date：   2019-10-16
 * @version： V1.0
 */
@Service
public class AdminLogsServiceImpl extends ServiceImpl<AdminLogsMapper, AdminLogs> implements IAdminLogsService {

}
