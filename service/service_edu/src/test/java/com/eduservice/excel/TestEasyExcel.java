package com.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        String fileName="D:\\output\\test.xlsx";
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }

    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for (int i=0;i<10;i++){
            DemoData data=new DemoData();
            data.setSno(i);
            data.setName("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
