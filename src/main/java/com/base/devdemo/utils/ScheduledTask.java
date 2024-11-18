package com.base.devdemo.utils;

//import org.junit.Test;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 1、在springBoot项目中打开注释
 * 2、 测试类/ 项目中注入这个ScheduledTask
 * 3、运行容器，等待定时任务执行即可
 */
//@Component
public class ScheduledTask {

    // 2024版：
    //性能环境数据库连接信息
    //ip地址：25.161.157.***
    //端口：  2883
    //租户： cxyyfw@******************
    //密码： Ce_Sh1.****
    private static final String URL = "jdbc:mysql://25.161.157.***:2883/cxyyfwdb"; // 数据库 URL
    private static final String USER = "cxyyfw@******************"; // 数据库用户名
    private static final String PASSWORD = "Ce_Sh1.***********"; // 数据库密码

    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    // 每30分钟执行一次 55w * 2 *24 = 1300W + 3200 = 4500W
//    @Scheduled(cron = "0 0/30 * * * ?")
    public void executeRptTask() {
        String sql = "INSERT INTO ymd_rpt_task (id,source,source_type,biz_category,biz_class,biz_type,task_code,task_branch_code,task_org_code,task_dep_code,\n" +
                "store_short_code,store_name,customer_name,customer_contact,task_creation_time,task_begin_time,task_end_time,sub_task_code,sub_task_creation_time,\n" +
                "sub_task_begin_time,sub_task_end_time,policy_no,policy_branch_code,policy_org_code,policy_dep_code,policy_sub_dep_code,biz_system,task_phase_code,\n" +
                "task_phase_name,task_phase_holder_code,task_phase_holder_name,task_phase_holder_branch_code,task_phase_holder_org_code,task_phase_holder_dep_code,\n" +
                "task_phase_creation_time,task_phase_begin_time,task_phase_end_time,create_time,create_by,task_phase_holder_branch_name,task_phase_holder_org_name,\n" +
                "task_phase_holder_dep_name,is_verify,is_user_vip,vip_type_name,satisfaction,feedback,openid,business_apply_no,relation_system_name,store_type_name,\n" +
                "endorsement_no,batch_number,label_text,cross_task,operational_task_code,operational_task_name,correct_flow_new,s_policy_no,s_endorsement_no,j_policy_no,\n" +
                "j_endorsement_no) \n" +
                "SELECT REPLACE(UUID(),'-',''),source,source_type,biz_category,biz_class,biz_type,REPLACE(UUID(),'-',''),task_branch_code,task_org_code,task_dep_code,\n" +
                "store_short_code,store_name,customer_name,customer_contact,task_creation_time,task_begin_time,task_end_time,sub_task_code,sub_task_creation_time,\n" +
                "sub_task_begin_time,sub_task_end_time,policy_no,policy_branch_code,policy_org_code,policy_dep_code,policy_sub_dep_code,biz_system,task_phase_code,\n" +
                "task_phase_name,task_phase_holder_code,task_phase_holder_name,task_phase_holder_branch_code,task_phase_holder_org_code,task_phase_holder_dep_code,\n" +
                "task_phase_creation_time,task_phase_begin_time,task_phase_end_time,create_time,create_by,task_phase_holder_branch_name,task_phase_holder_org_name,\n" +
                "task_phase_holder_dep_name,is_verify,is_user_vip,vip_type_name,satisfaction,feedback,openid,business_apply_no,relation_system_name,store_type_name,\n" +
                "endorsement_no,REPLACE(UUID(),'-',''),label_text,cross_task,operational_task_code,operational_task_name,correct_flow_new,s_policy_no,s_endorsement_no,j_policy_no,\n" +
                "j_endorsement_no   FROM ymd_rpt_task LIMIT 1000000;"; // 替换为你的 SQL 语句
        execteOurSqlAndLoggerSuccessMessage(sql, "executeRptTask");
    }

    // 每8分钟执行一次 180 * 100w = 180000000 + 100w * 110
    @Scheduled(cron = "0 0/8 * * * ?")
    public void executeInsertTaskTrack() {
        // 门店 轨迹表 新增  100w * 200 | 24100 + 25900|  2-5 分钟
        String sql = "INSERT  INTO `ymd_task_track`(`id`,`task_code`,`operation`,`handler_code`,`handler_name`,`create_time`,\n" +
                "`del_flag`,`task_code_child`,`task_type_status`,`operate_type`,`remarks`)\n" +
                " SELECT REPLACE(UUID(),'-',''),REPLACE(UUID(),'-',''),`operation`,`handler_code`,`handler_name`,`create_time`,\n" +
                "`del_flag`,`task_code_child`,`task_type_status`,`operate_type`,`remarks`\n" +
                "  FROM ymd_task_track LIMIT 1000000;"; // 替换为你的 SQL 语句
        execteOurSqlAndLoggerSuccessMessage(sql, "executeInsertTaskTrack");
    }

    private void execteOurSqlAndLoggerSuccessMessage(String sql,String logMessage) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 提交剩余的数据
            int ints = pstmt.executeUpdate();
            logger.info("{}()任务执行数据插入完成！ 共{} 条！", logMessage,ints);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("exception : {}", e);
        }
    }

}

//@Configuration
//@EnableScheduling
class ScheduledTaskConfigTest {

}

// 注入容器中
//    @Test
//    public void TaskGenerateTest() throws Exception {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduledTaskConfigTest.class);
//        consistentTime(context,3600000 * 24);
//    }
//
//    // 持续时间
//    private void consistentTime(AnnotationConfigApplicationContext context, long runningTimes) {
//        // 保持应用程序运行以观察定时任务
//        try {
//            Thread.sleep(runningTimes); // 运行1天  后退出
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        context.close(); // 关闭上下文
//    }
