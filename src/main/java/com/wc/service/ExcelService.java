package com.wc.service;

import com.wc.entity.Reward;

import java.util.List;

public interface ExcelService {
     List<Reward> getAllByExcel(String file);
     List<Reward> getAllByExcelONE(String file);
}
