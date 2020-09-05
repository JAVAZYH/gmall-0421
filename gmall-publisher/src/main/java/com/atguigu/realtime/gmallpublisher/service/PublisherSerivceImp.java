package com.atguigu.realtime.gmallpublisher.service;

import com.atguigu.realtime.gmallpublisher.mapper.DauMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lzc
 * @Date 2020/9/4 15:49
 */
@Service
class PublisherServiceImp implements PublisherService {

    @Autowired
    DauMapper dau;

    @Override
    public Long getDau(String date) {
        return dau.getDau(date);
    }

    @Override
    public Map<String, Long> getHourDau(String date) {
        List<Map<String, Object>> hourDau = dau.getHourDau(date);

        HashMap<String, Long> result = new HashMap<>();

        for (Map<String, Object> map : hourDau) {
            String hour = map.get("LOGHOUR").toString();
            Long count = (Long) map.get("COUNT");
            result.put(hour, count);
        }

        return result;
    }

}
/*

+----------+-----------+
| LOGHOUR  | COUNT(1)  |
+----------+-----------+
| 10       | 3         |
| 14       | 7         |
| 16       | 39        |
+----------+-----------+
List[Map("loghour"->10, "count"-> 3), Map("loghour"->14, "count"-> 7),...]

转成
Map["10"-> 3, "14"->7, ...]

 */