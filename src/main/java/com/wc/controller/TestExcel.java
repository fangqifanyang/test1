package com.wc.controller;

import java.io.File;
import java.util.List;

import com.wc.entity.GroupVo;
import com.wc.entity.Reward;
import com.wc.service.*;
import com.wc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestExcel {

    @Autowired
    private ExcelService excelService;
    @Autowired
    private RewardService rewardService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private UserService userService;


    @PostMapping("/ListReward")
    private R ListReward(Integer uid){
        List<Reward> rewardList=rewardService.ListReward(uid);
        return R.ok().data("rewardList",rewardList);
    }


    @PostMapping("/excel")
    private R excel(@RequestParam("file") MultipartFile file,@RequestParam("cid")Integer cid) throws Exception{

        rewardService.drop(cid);

        competitionService.updatesigned_people(cid);

        String rootPath = "D://";
        String filePath = rootPath + "/Reward/";
        File dir = new File(filePath);
        if (!dir.isDirectory()){
            dir.mkdir();
        }

        //获取文件名
        String fileOriginalName = file.getOriginalFilename();
        System.out.println(fileOriginalName);
        //获取竞赛名
        String fileNameNow = fileOriginalName.substring(0,fileOriginalName.lastIndexOf("."));
        System.out.println(fileNameNow);
        File writeFile = new File(filePath + fileOriginalName);
        //文件写入磁盘
        file.transferTo(writeFile);

        if(competitionService.findCType(cid)>1){
            //得到表格中所有的数据
            List<Reward> listExcel= excelService.getAllByExcel("D://Reward/"+fileOriginalName);
            rewardService.create(cid);
            String tablename="c"+cid;
            for(Reward r : listExcel){
                r.setGroup_id(groupService.findID(r.getGroup_name()));
                rewardService.insert(tablename,r.getGroup_id(),r.getGroup_name(),r.getCup());
                r.setCompetition_id(cid);
                if (!rewardService.checkR(r)) {
                    //不存在就添加
                    rewardService.insertR(r);
                }else {
                    //存在就更新
                    rewardService.updateR(r);
                }
            }
        }else {
            //得到表格中所有的数据
            List<Reward> listExcel= excelService.getAllByExcelONE("D://Reward/"+fileOriginalName);
            rewardService.createONE(cid);
            String tablename="c"+cid;
            for(Reward r : listExcel){
                r.setUser_id(userService.findID(r.getUser_name()));
                rewardService.insertONE(tablename,r.getUser_id(),r.getUser_name(),r.getCup());
                r.setCompetition_id(cid);
                if (!rewardService.checkRONE(r)) {
                    //不存在就添加
                    rewardService.insertRONE(r);
                }else {
                    //存在就更新
                    rewardService.updateRONE(r);
                }
            }
        }

      return R.ok();
    }



}
