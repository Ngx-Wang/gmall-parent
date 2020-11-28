package com.atguigu.gmall.controller;


import com.atguigu.entity.*;
import com.atguigu.gmall.service.SpuInfoService;
import com.atguigu.response.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author wangqiao
 * @since 2020-11-01
 */
@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class SpuInfoController {

    @Autowired
    private SpuInfoService spuInfoService;

    private String imgPath;


    @RequestMapping("spuImageList/{spuId}")
    public Result spuImageList(@PathVariable("spuId") String spuId){

        // spu保存的业务
        List<SpuImage> spuImages = spuInfoService.spuImageList(spuId);

        return Result.ok(spuImages);
    }
    //图片上传到fastDFS中，并返回图片路径，用于回显
    @RequestMapping("/fileUpload")
    public Result fileUpload(@RequestParam("file")MultipartFile multipartFile){
        StringBuffer url = new StringBuffer("http://192.168.200.128:8080");
        try {
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            String fileOriginalFilename = multipartFile.getOriginalFilename();
            String filenameExtension = StringUtils.getFilenameExtension(fileOriginalFilename);

            String[] files = storageClient.upload_file(multipartFile.getBytes(), filenameExtension, null);

            for (String file : files) {
                url.append("/"+file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        imgPath = url+"";
        System.out.println(imgPath);
        return Result.ok(url);
    }




    //分页查询
    @GetMapping("/{page}/{limit}")
    public Result getSpuInfoList(Long category3Id,@PathVariable("page")Long page,
                             @PathVariable("limit") Long limit
                             ){
        IPage<SpuInfo> spuInfoIPage = new Page<>(page,limit);

        QueryWrapper<SpuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("Category3_id",category3Id);
        IPage<SpuInfo> iPage = spuInfoService.page(spuInfoIPage, wrapper);
        return Result.ok(iPage);

    }
    //加载品牌
    @GetMapping("/getTrademarkList")
    public Result getTrademarkList(){
        List<BaseTrademark> trademarkList = spuInfoService.getTrademarkList();
        return Result.ok(trademarkList);
    }
    //加载平台属性字典
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        List<BaseSaleAttr> saleAttrList = spuInfoService.baseSaleAttrList();
        return Result.ok(saleAttrList);
    }

    //添加spu
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){
        spuInfoService.saveSpuInfo(spuInfo);
        return Result.ok();
    }




}

