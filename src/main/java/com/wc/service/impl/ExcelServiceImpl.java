package com.wc.service.impl;

import com.wc.dao.CompetitionDao;
import com.wc.entity.Reward;
import com.wc.service.ExcelService;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service("excelService")
public class ExcelServiceImpl implements ExcelService {


    @Override
    public List<Reward> getAllByExcel(String file) {
        List<Reward> list=new ArrayList<Reward>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String group_name=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String cup=rs.getCell(j++, i).getContents();


                    System.out.println("group_name:"+group_name+" cup:"+cup);
                    Reward r= new Reward();
                    r.setGroup_name(group_name);
                    r.setCup(cup);
                    list.add(r);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Reward> getAllByExcelONE(String file) {
        List<Reward> list=new ArrayList<Reward>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行

            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String user_name=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String cup=rs.getCell(j++, i).getContents();


                    System.out.println("user_name:"+user_name+" cup:"+cup);
                    Reward r= new Reward();
                    r.setUser_name(user_name);
                    r.setCup(cup);
                    list.add(r);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
