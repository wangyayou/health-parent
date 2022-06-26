package com.itcast.service;

import com.itheima.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface orderSettingService {
    // importOrderSettings
    void importOrderSettings(ArrayList<OrderSetting> orderSettingList);

    List<Map<String, Object>> GetOneMonthOrderSettings(String startDate, String endDate);

    void AddOrderSettingReservations(Integer num, String dateParam);
}
