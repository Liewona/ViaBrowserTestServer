package com.liuono.browser.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liuono.browser.bean.RecordBean;
import com.liuono.browser.utils.DBHelper;

public class RecordDao {

    public List<RecordBean> getData(String userId) throws SQLException {
        List<RecordBean> list = new ArrayList<>();
        RecordBean bean = new RecordBean();

        ResultSet rs = DBHelper.querySQL("browser_server", "select * from tb_record where userId='123'");
        while (rs.next()) {
            bean.setRecordName(rs.getString("recordName"));
            bean.setRecordUrl(rs.getString("recordUrl"));
            bean.setRecordTime(rs.getString("recordTime"));
            list.add(bean);
            bean = new RecordBean();
        }

        rs.getStatement().close();
        rs.close();


        return list;
    }
}
