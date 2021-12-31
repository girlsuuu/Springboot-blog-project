package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.lang.Result;
import com.blog.entity.Record;
import com.blog.service.RecordService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class RecordController {

  @Autowired
  RecordService recordService;

  @GetMapping("/records/{userId}")
  public Result list(@PathVariable(name = "userId") Long userId) {

    QueryWrapper<Record> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId);
    List<Record> records = recordService.list(wrapper);
    return Result.success(records);
  }

  @GetMapping("/records/add/{userId}")
  public Result add(@PathVariable(name = "userId") Long userId) throws ParseException {

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String simpleDate = sdf.format(date) + " 00:00:00";
    QueryWrapper<Record> wrapper = new QueryWrapper<>();
    wrapper.eq("user_id", userId);
    wrapper.eq("date", simpleDate);
    Record record = recordService.getOne(wrapper);
    if (record != null) {
      record.setCount(record.getCount() + 1);
      recordService.saveOrUpdate(record);
    } else {
      Record temp = new Record();
      temp.setUserId(userId);
      temp.setDate(sdf.parse(simpleDate));
      recordService.save(temp);
    }

    return Result.success(record);

  }

}
